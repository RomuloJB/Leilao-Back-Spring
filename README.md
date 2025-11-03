# Projeto Spring: Full-Stack com React e Spring
Este projeto é uma aplicação full-stack utilizando React no front-end e Spring Boot no back-end.

## Estrutura de Pastas
A seguir está uma descrição detalhada da estrutura de pastas do projeto, com base no padrão MVC (Model-View-Controller):

## Backend (Spring Boot)
executar o projeto, abrir o prompt e executar mvnw spring-boot:run

### 1. application.properties
- Arquivo que armazena as configurações do projeto, como a porta de execução e outras variáveis de ambiente.

- Pode haver mais de um arquivo para armazenar diferentes configurações, dependendo do ambiente (ex: desenvolvimento, produção).

### 2. pom.xml
- Contém as informações do projeto, incluindo as dependências necessárias para o funcionamento da aplicação (como o Lombok, DevTools, etc.).

- Em comparação com o React, essas dependências seriam especificadas no arquivo package.json.

- O Maven cria um repositório local na máquina (padrão na pasta .m2 do diretório do usuário).

### 3. Estrutura de Pacotes (Backend)
- A estrutura de pacotes está organizada conforme o padrão MVC:

#### Controlador (Controller):

- Contém as classes responsáveis por gerenciar as requisições HTTP do cliente. Se o cliente está clicando em algum lugar e não funciona, devemos olhar o controller em 1ºlugar para ver se a requisição está chegando para o sistema.

- Não deve lidar diretamente com requisições ao banco de dados ou API externas. Essas responsabilidades devem ser delegadas a outras camadas.

#### Serviço (Service):

- Implementa a lógica de negócios da aplicação.

- Deve envolver as pastas Modelo, Repositório e DTO.

- Cada classe de serviço deve ser anotada com @Service para que o Spring a reconheça como um serviço.

#### Modelo (Model):

- Contém as entidades de domínio, como a entidade Pessoa.

- Representa a estrutura dos dados que serão manipulados pela aplicação.

#### Repositório (Repository):

- Contém as interfaces e classes responsáveis por realizar operações no banco de dados.

- Utiliza o Spring Data JPA ou outras tecnologias para a persistência de dados.

### 4. Outras Pastas de Configuração
#### Configuração (Config):

- Contém configurações gerais da aplicação, como beans e configurações de inicialização.

#### Segurança (Security):

- Contém a configuração de segurança da aplicação, como autenticação, autorização e proteção de rotas.

#### Exceções (Exception):

- Define as exceções personalizadas para a aplicação e as classes de manipulação de erros (ex: tratamento de exceções de banco de dados ou de validação).

### Testes de API
- Para testar as requisições da API, recomenda-se o uso de ferramentas como Insomnia ou Postman. Essas ferramentas permitem realizar e visualizar requisições HTTP para validar o funcionamento dos endpoints da aplicação.

### Resumo de Fluxo de Dados
- Controlador recebe as requisições HTTP.

- As requisições são passadas para o Serviço correspondente.

- O Serviço realiza a lógica de negócios e interage com os dados utilizando o Repositório.

- O Repositório se comunica com o banco de dados para persistir ou recuperar dados.

- O resultado é retornado para o Controlador, que envia a resposta ao cliente.

## Tratamento de exceções

#### Utilitários importantes para o projeto
url importante: http://localhost:8080/pessoa?page=1&size=3&sort=id.asc 

http://localhost:8080/pessoa

mvn install

thymeleaf: cria emails personalizados

#### DNS – Domain Name System

Os seres humanos acessam informações on-line por meio de nomes de domínio, como nytimes.com ou espn.com.
Os navegadores, porém, utilizam endereços de Protocolo de Internet (IP) para localizar e se comunicar com os servidores.
O DNS (Domain Name System) é o sistema responsável por traduzir nomes de domínio em endereços IP correspondentes, permitindo que os navegadores possam localizar e carregar corretamente os recursos na internet.

#### Como uma requisição na internet funciona

Quando um cliente (por exemplo, um navegador) busca um site, ele faz uma requisição ao servidor.
Essa requisição normalmente utiliza o protocolo TCP (Transmission Control Protocol), que estabelece uma conexão confiável entre cliente e servidor antes da troca de dados.
O processo de estabelecimento da conexão TCP é conhecido como Three-Way Handshake:
O cliente envia um pacote com a flag SYN (synchronize).
O servidor responde com um SYN-ACK (synchronize + acknowledgment).
O cliente envia um ACK final confirmando o recebimento — e a conexão é estabelecida.
Após isso, ocorre a transferência de dados (por exemplo, o carregamento da página).

Observação:
O TCP garante que todos os pacotes sejam entregues e na ordem correta, o que é importante para aplicações como navegação web, e-mails e transações.
Já para aplicações em tempo real, como streaming de vídeo, jogos online ou chamadas de voz, utiliza-se o UDP (User Datagram Protocol).
Esse protocolo não garante a entrega nem a ordem dos pacotes, mas é mais rápido e eficiente, ideal para situações onde perder um pequeno pacote é menos grave do que causar atrasos.

#### WebSocket

Se tentássemos criar um chat em tempo real (como o WhatsApp Web) utilizando apenas requisições HTTP, precisaríamos usar requisições periódicas (polling) ou long-polling para verificar se há novas mensagens — o que é ineficiente.
O WebSocket, por outro lado, foi criado justamente para esse tipo de comunicação bidirecional e em tempo real.
O funcionamento é assim:
O cliente inicialmente faz uma requisição HTTP normal para o servidor, pedindo a atualização do protocolo para WebSocket (handshake HTTP → WebSocket).
Se o servidor aceitar, a conexão é atualizada para o protocolo WebSocket.
A partir daí, a comunicação é full-duplex — ou seja, cliente e servidor podem enviar e receber mensagens a qualquer momento.
Isso permite, por exemplo, que o servidor envie mensagens novas a todos os clientes conectados a um mesmo tópico ou sala (como um chat do WhatsApp), sem precisar que o cliente fique pedindo novas informações a todo momento.
