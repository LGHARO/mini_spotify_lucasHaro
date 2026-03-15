# Mini Spotify API

## Project Description
The Mini Spotify API is a lightweight implementation of the Spotify API that allows users to manage playlists, albums, and tracks, as well as access data about users and artists. This project aims to provide a simplified interface for interacting with music-related content.

## Technologies
- Node.js
- Express
- MongoDB
- Mongoose
- Jest for testing

## Execution Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/LGHARO/mini_spotify_lucasHaro.git
   cd mini_spotify_lucasHaro
   ```
2. Install the dependencies:
   ```bash
   npm install
   ```
3. Start the application:
   ```bash
   npm start
   ```
4. The API will run on `http://localhost:3000`.

## Project Structure
```
mini_spotify_lucasHaro/
├── models/
├── routes/
├── controllers/
├── tests/
├── app.js
└── package.json
```

## API Routes Documentation

### Usuarios
- `GET /usuarios`: Retrieve all users.
- `POST /usuarios`: Create a new user.
- `GET /usuarios/:id`: Retrieve a user by ID.
- `PUT /usuarios/:id`: Update a user by ID.
- `DELETE /usuarios/:id`: Delete a user by ID.

### Artistas
- `GET /artistas`: Retrieve all artists.
- `POST /artistas`: Create a new artist.
- `GET /artistas/:id`: Retrieve an artist by ID.
- `PUT /artistas/:id`: Update an artist by ID.
- `DELETE /artistas/:id`: Delete an artist by ID.

### Albums
- `GET /albums`: Retrieve all albums.
- `POST /albums`: Create a new album.
- `GET /albums/:id`: Retrieve an album by ID.
- `PUT /albums/:id`: Update an album by ID.
- `DELETE /albums/:id`: Delete an album by ID.

### Musicas
- `GET /musicas`: Retrieve all tracks.
- `POST /musicas`: Create a new track.
- `GET /musicas/:id`: Retrieve a track by ID.
- `PUT /musicas/:id`: Update a track by ID.
- `DELETE /musicas/:id`: Delete a track by ID.

### Playlists
- `GET /playlists`: Retrieve all playlists.
- `POST /playlists`: Create a new playlist.
- `GET /playlists/:id`: Retrieve a playlist by ID.
- `PUT /playlists/:id`: Update a playlist by ID.
- `DELETE /playlists/:id`: Delete a playlist by ID.

### Relatorios
- `GET /relatorios`: Retrieve all reports.
- `POST /relatorios`: Create a new report.
- `GET /relatorios/:id`: Retrieve a report by ID.
- `PUT /relatorios/:id`: Update a report by ID.
- `DELETE /relatorios/:id`: Delete a report by ID.

---
This documentation provides a comprehensive overview and guides users through the functionalities available in the Mini Spotify API.