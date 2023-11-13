package com.example.bookshifter.services;

import com.example.bookshifter.dto.BookDTO;
import com.example.bookshifter.dto.RegisterUserDTO;

import com.example.bookshifter.dto.UserAndBookDTO;
import com.example.bookshifter.dto.UserDTO;
import com.example.bookshifter.entities.Book;
import com.example.bookshifter.entities.Role;
import com.example.bookshifter.entities.User;
import com.example.bookshifter.exceptions.JWTExcepion;
import com.example.bookshifter.exceptions.UserNotFoundException;
import com.example.bookshifter.repositories.BookRepository;
import com.example.bookshifter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements com.example.bookshifter.services.interfaces.UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<UserDTO> findAll(){
        var result = repository.findAll();

        List<UserDTO> users = result.stream().map(UserDTO::new).toList();
        return users;
    }

    @Override
    public User registerUser(RegisterUserDTO dto){
      User newUser = new User(dto.getFirstName(), dto.getLastName(), dto.getEmail(),
              passwordEncoder.encode(dto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
      return repository.save(newUser);
    }
    @Override
    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")));
    }

    @Override
    public User getAuthenticatedUserInfo(Authentication authentication){
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
           String getEmail = SecurityContextHolder.getContext().getAuthentication().getName();

            Optional<User> userOptional = repository.findByEmail(getEmail);
            if(userOptional.isPresent()){
                return userOptional.get();
            }
        }
        throw new JWTExcepion("Token JWT expirado ou não informado, por favor tente novamente");
    }

    @Override
    public UserAndBookDTO getAuthenticatedUserBooks(String email) {
        Optional<User> userOptional = repository.findByEmail(email);

        if(userOptional.isPresent()){
            List<Book> books = bookRepository.findByOwner(userOptional.get().getId());
            List<BookDTO> booksDTO = books.stream().map(BookDTO::new).toList();

            return new UserAndBookDTO(userOptional.get().getFirstName(), userOptional.get().getLastName(),
                    userOptional.get().getEmail(), booksDTO);
        }

        throw new UserNotFoundException("O email requisatado não está atrelado a nenhum usuário", HttpStatusCode.valueOf(404));
    }
}
