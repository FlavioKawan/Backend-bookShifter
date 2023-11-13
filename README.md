# Bookshifter-backend
## Instruções
  Para rodar este projeto localmente, você terá que ter instalado e configurado o seu MySQL Database.

  Na sua instância de MySQL crie uma database chamada testdb através do comando create database testdb;
  ![alt text](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/imagem_query.png)
  Para ajustar as configurações da JPA abra o arquivo application.yml, o caminho para ele é src/main/resources/application.yml.
  No arquivo, configure a porta onde o MySQL estará rodando, para isso altere a porta se for necessário, o numero dela é composto por quatro dígitos logo após o http://localhost: na variável url Como na imagem abaixo
  altere também a variável username para o seu usuário do MySQL e a senha também. Não commite com o seu username e password no arquivo

  ![alt text](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/imagem_yml.png)
  
  Caso não saiba a porta de seu MySQL, abra seu MySQL Workbench, na página inicial estará seu usuario e porta, segue abaixo um exemplo

  ![alt text](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/image_instancia_workbench.png)

### Endpoints rest da aplicação
Para executar as operações abaixo rode o programa na IDE de sua escolha, no Intellij Idea abra o projeto e clique Shift + F10
  Registro de usuário:
    Para registrar o seu usuário e enviar o email de ativação da conta para ele, faça o seguinte:
      Crie uma nova requisição POST com a seguinte URL: http://localhost:8080/rest-register
        na aba body da requisação selecione a opção única raw, e na lista JSON, como abaixo:
        Na interaface de edição de texto entre com os atributos e valores na forma chave : valor, como mostrado abaixo
        ![alt text](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/postman_registro_request.png)
        após isso será enviado um link para o email informado, para ativar a conta cique no link
        ![alt image](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/imagem_email.png)

  Mudança de senha:
    Para mudar a senha de seu usuário crie uma requisição POST com a seguinte url: http://localhost:8080/rest-forgot-password/ e passe o atributo email com seu valor no mesmo formato chave : valor de antes. Será eviado outro link para o email informado exemplo abaixo:
    ![alt image](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/postman_mudar_senha_request.png)
    Para mudar efetivamente a senha, clique no link do email com o botão direito no link e selecione a opção "copiar endereço do link", imagem ilustrando abaixo
    ![alt image](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/imagem_clique_direito.png)
    Copie o link e crie a última requisição POST com o link copiado, inserindo na interface de edição de texto os seguintes atributos: newPassword e newPasswordConfirmation no formato chave valor, como ilustrado abaixo
    ![alt image](https://github.com/JoaoKavalciukiFatecDiadema/Bookshifter-backend/blob/casa/src/main/java/com/example/dslist/images/password_reset_functionality.png)
    
