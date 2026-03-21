# Mini Spotify API

API REST desenvolvida com **Spring Boot** que simula funcionalidades básicas de um serviço de streaming de música, incluindo gerenciamento de usuários, artistas, álbuns, músicas e playlists.

A aplicação mantém os dados **em memória utilizando HashMap**, sem persistência em banco de dados.

---

# Tecnologias

* Java
* Spring Boot
* Spring Web
* Jakarta Validation
* REST API

A aplicação inicia através da classe principal Spring Boot:

* `SpotifyApplication` 

---

# Estrutura da aplicação

A arquitetura segue o padrão:

```
Controller → Service → Model
```

### Controllers

Responsáveis por expor os endpoints HTTP da API.

* `UsuarioController` 
* `ArtistaController` 
* `AlbumController` 
* `MusicaController` 
* `PlaylistController` 
* `RelatorioController` 

### Services

Contêm a lógica de negócio da aplicação.

* `UsuarioService` 
* `ArtistaService` 
* `AlbumService` 
* `MusicaService` 
* `PlaylistService`
* `HistoricoReproducoesService`

### Models

Representam as entidades do sistema.

* `Usuario` 
* `Artista` 
* `Album` 
* `Musica` 
* `Playlist`
* `HistoricoReproducoes`

### DTO

Objeto utilizado para retorno de relatórios.

* `TopMusicasDTO` 

### Enum

Tipo de usuário do sistema.

* `TipoUsuarios`

  * `FREE`
  * `PREMIUM` 

# Tratamento de erros

A aplicação utiliza `ResponseStatusException` para sinalizar erros nas regras de negócio. Essas exceções são interceptadas por um handler global que padroniza a resposta JSON da API. 

Formato da resposta:

```json
{
  "status": 404,
  "message": "Mensagem de erro"
}
```

Estrutura retornada:

* **status** – código HTTP
* **message** – descrição do erro

---

# Casos comuns de erro

## Recurso não encontrado

Ocorre quando um objeto solicitado não existe no sistema.

Exemplo:

```http
GET /musicas/123
```

Resposta:

```json
{
  "status": 404,
  "message": "Musica não existe"
}
```

Esse comportamento ocorre em diversas operações como:

* busca de música
* busca de artista
* busca de playlist
* busca de álbum

---

## Recurso já existente

Ocorre quando se tenta cadastrar um objeto com um `id` já existente.

Exemplo:

```http
POST /usuarios
```

Resposta:

```json
{
  "status": 409,
  "message": "Usuario ja existe"
}
```

Casos onde isso pode ocorrer:

* criação de usuário
* criação de álbum
* criação de música
* criação de playlist

---

## Dependência inexistente

Ocorre quando um recurso depende de outro que ainda não foi criado.

### Exemplo: criar álbum sem artista

```http
POST /albums
```

Resposta:

```json
{
  "status": 404,
  "message": "Artista não existe"
}
```

Esse tipo de validação ocorre em:

* criação de álbum (precisa de artista)
* criação de música (precisa de artista)

---

## Usuário inválido para operação

Algumas ações exigem validação do usuário.

### Exemplo: reproduzir música com usuário inexistente

```http
POST /musicas/{id}/reproduzir
```

Resposta:

```json
{
  "status": 404,
  "message": "Usuario não existe"
}
```

Outro caso:

Usuários **inativos** não podem reproduzir músicas. 

---

## Operação não permitida

Algumas operações possuem restrições de acesso.

### Exemplo: adicionar música em playlist de outro usuário

```http
POST /playlists/{playlistId}/musicas/{musicaId}
```

Resposta:

```json
{
  "status": 404,
  "message": "Apenas donos da playlist podem adicionar musicas a ela"
}
```

Essa validação garante que apenas o **dono da playlist** possa modificá-la. 

---

## Música duplicada na playlist

Ocorre quando se tenta adicionar uma música que já está na playlist.

Resposta:

```json
{
  "status": 404,
  "message": "A musica ja esta na playlist"
}
```

Esse comportamento é validado antes da inclusão da música na playlist. 

---

## Validação de dados

Alguns campos são obrigatórios e são validados com **Jakarta Validation**.

Exemplos:

* `@NotBlank`
* `@NotNull`
* `@Email`

Caso esses campos não sejam enviados corretamente, a API retorna erro de validação.

---

# Observação sobre validação de dependências

Antes de criar determinados recursos, o sistema valida a existência de objetos relacionados.

Exemplos:

* Um **álbum só pode ser criado se o artista existir**. 
* Uma **música só pode ser criada se o artista existir**. 
* Uma **playlist só pode ser criada se o usuário existir**. 
* Todas as **músicas adicionadas à playlist precisam existir previamente**. 

---

# Funcionalidades

## Usuários

Permite cadastrar e gerenciar usuários da aplicação.

### Endpoints

POST
`/usuarios`

GET
`/usuarios`

GET
`/usuarios/{id}`

GET
`/usuarios/{id}/historico`

PUT
`/usuarios/{id}`

DELETE
`/usuarios/{id}`

---

## Artistas

Gerenciamento de artistas musicais.

### Endpoints

POST
`/artistas`

GET
`/artistas`

GET
`/artistas/{id}`

PUT
`/artistas/{id}`

DELETE
`/artistas/{id}`

---

## Álbuns

Representam coleções de músicas associadas a um artista.

### Endpoints

POST
`/albums`

GET
`/albums`

GET
`/albums/{id}`

PUT
`/albums/{id}`

DELETE
`/albums/{id}`

Um álbum precisa estar associado a um **artista existente**. 

---

## Músicas

Representam faixas individuais pertencentes a um álbum e artista.

### Endpoints

POST
`/musicas`

GET
`/musicas`

GET
`/musicas/{id}`

GET
`/musicas/{id}/historico`

PUT
`/musicas/{id}`

DELETE
`/musicas/{id}`

### Reproduzir música

POST
`/musicas/{idMusica}/reproduzir`

Header obrigatório:

```
X-USER-ID: {idUsuario}
```

Regras:

* usuário deve existir
* usuário deve estar ativo
* música deve existir

Cada reprodução incrementa o contador de reproduções.

---

## Playlists

Coleções de músicas criadas por usuários.

### Endpoints

POST
`/playlists`

GET
`/playlists`

GET
`/playlists/{id}`

PUT
`/playlists/{id}`

DELETE
`/playlists/{id}`

### Adicionar música à playlist

POST

```
/playlists/{playlistId}/musicas/{musicaId}
```

Header obrigatório:

```
X-USER-ID: {usuarioId}
```

Regras:

* playlist deve existir
* usuário deve ser o dono da playlist
* música não pode já estar na playlist 

---

# Relatórios

## Top músicas mais reproduzidas

Endpoint:

```
GET /relatorios/top-musicas
```

Retorna as **10 músicas mais reproduzidas**, contendo:

* título
* artista
* total de reproduções

Exemplo de resposta:

```json
[
  {
    "titulo": "Song A",
    "artista": "Artist A",
    "totalReproducoes": 120
  }
]
```

---

# Histórico de Reprodução

A API permite consultar o histórico de reproduções tanto por **música** quanto por **usuário**.

Cada registro de histórico representa uma reprodução realizada, incluindo informações como:

* usuário
* música
* data/hora da reprodução

---

## Endpoints

### Histórico de uma música

Retorna todas as reproduções de uma música específica.

```
GET /musicas/{musicaId}/historico
```

Exemplo:

```http
GET /musicas/1/historico
```

Resposta:

```json
[
  {
    "usuario": "user1",
    "musica": "Song A",
    "dataHora": "2026-03-21T14:30:00"
  }
]
```

---

### Histórico de um usuário

Retorna todas as músicas reproduzidas por um usuário.

```
GET /usuarios/{usuarioId}/historico
```

Exemplo:

```http
GET /usuarios/1/historico
```

Resposta:

```json
[
  {
    "usuario": "user1",
    "musica": "Song A",
    "dataHora": "2026-03-21T14:30:00"
  }
]
```

---

# Execução do projeto

### 1. Clonar o repositório

```bash
git clone <repo>
```

### 2. Executar aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

### 3. Acessar API

```
http://localhost:8080
```

### 4. Testar a API no Postman

```
É possivel abir os testes ja pré programados no arquivo json anexado na entrega do projeto no blackboard

```

---

# Observações

* Não há persistência em banco de dados.
* Os dados são armazenados em memória.
* Reiniciar a aplicação limpa todos os registros.
