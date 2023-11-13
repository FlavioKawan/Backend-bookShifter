package com.example.bookshifter.services;

import com.example.bookshifter.api.book.google.FullRequestWrapper;
import com.example.bookshifter.api.book.openlibrary.FullRequestOpenLibrary;
import com.example.bookshifter.dto.BookDTO;
import com.example.bookshifter.dto.BookRequestDTO;
import com.example.bookshifter.entities.Book;
import com.example.bookshifter.entities.Fatec;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.exceptions.ApiException;
import com.example.bookshifter.exceptions.BookException;
import com.example.bookshifter.exceptions.FatecException;
import com.example.bookshifter.repositories.BookRepository;
import com.example.bookshifter.repositories.FatecRepository;
import com.example.bookshifter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements com.example.bookshifter.services.interfaces.BookService {
    @Autowired
    private BookRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FatecRepository fatecRepository;

    @Autowired
    private UserService userService;

    private Authentication auth;

    @Override
    public BookDTO saveBookByIsbn(Long isbn, Long fatecId, BookRequestDTO dto) {
        String bookUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        String largeCoverUrl = "https://covers.openlibrary.org/b/isbn/" + isbn + "-L.jpg";
        String mediumCoverURL = "https://covers.openlibrary.org/b/isbn/" + isbn + "-M.jpg";

        ResponseEntity<FullRequestWrapper> response = restTemplate.getForEntity(bookUrl, FullRequestWrapper.class);

        String url = "https://openlibrary.org/search.json?q=" + isbn;

        ResponseEntity<FullRequestOpenLibrary> extraInfoResponse = restTemplate.getForEntity(url, FullRequestOpenLibrary.class);
        Optional<Fatec> fatecOptional = fatecRepository.findById(fatecId);
        if(fatecOptional.isEmpty()){
            throw new FatecException("Fatec ainda não cadastrada", HttpStatusCode.valueOf(404));
        }

        if(response.hasBody() && extraInfoResponse.hasBody()){
            User owner = userService.getAuthenticatedUserInfo(auth);
            Book newBook = new Book(
                    Objects.requireNonNull(response.getBody()).getItems()[0].getVolumeInfo().getTitle(),
                    List.of(response.getBody().getItems()[0].getVolumeInfo().getAuthors()),
                    Objects.requireNonNull(response.getBody().getItems()[0].getVolumeInfo().getDescription()),
                    Objects.requireNonNull(extraInfoResponse.getBody()).getDocs()[0].getPublisher().get(0),
                    Objects.requireNonNull(extraInfoResponse.getBody().getDocs()[0].getPublishYear().get(0)),
                    Objects.requireNonNull(response.getBody().getItems()[0].getVolumeInfo().getPageCount()),
                    largeCoverUrl,
                    mediumCoverURL,
                    dto.bookState(),
                    fatecOptional.get(),
                    owner
            );

            repository.save(newBook);
            return new BookDTO(newBook);
        } else {
            throw new ApiException("Erro na requisição das apis externas");
        }

    }

    @Override
    public List<BookDTO> findAllBooks(){
        var result = repository.findAll();

        List<BookDTO> books = result.stream().map(BookDTO::new).toList();

        return  books;
    }

    @Override
    public BookDTO findById(Long id){
        var result = repository.findById(id);
        if(result.isEmpty()){
            throw new BookException("Livro não existe", HttpStatusCode.valueOf(404));
        }
        Book bookFound = result.get();

        BookDTO dto = new BookDTO(bookFound);

        return dto;
    }

    @Override
    public void deleteBook(Long id){
        var result = repository.findById(id);

        if(result.isEmpty()){
            throw new BookException("Livro não encontrado", HttpStatusCode.valueOf(404));
        }

        Book book = result.get();
        repository.delete(book);
    }

    @Override
    public List<BookDTO> searchProducts(String query) {
        List<Book> foundBooks = repository.searchAllByQuery(query);
        return foundBooks.stream().map(BookDTO::new).toList();
    }
}
