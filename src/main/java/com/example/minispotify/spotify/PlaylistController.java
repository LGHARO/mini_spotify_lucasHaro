package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/playlists")
    public Playlist postPlaylist(@Valid @RequestBody Playlist playlist){
        return playlistService.cadastrarPlaylist(playlist);
    }

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public Playlist addMusica(@PathVariable String playlistId, @PathVariable String musicaId, @RequestHeader String usuarioId){

        // compara se o usuario que esta adiconando a musica e o dono da playlist
        if (playlistService.getPlaylistUsuarioId(playlistId).equals(usuarioId)) {
            if (!playlistService.buscarMusicaPlaylist(playlistId, musicaId)){
                Musica musica = musicaService.buscaMusica(musicaId);
                return playlistService.addMusica(playlistId, musica);
            }
            else {
                throw new RuntimeException("A musica já esta na Playlist");
            }

        }
        else{
            throw new RuntimeException("Apenas donos da playlist podem adicionar musicas a ela");
        }

    }

    @GetMapping("/playlists")
    public Collection<Playlist> buscarPlaylists(){
        return playlistService.buscarPlaylists();
    }

    @GetMapping("/playlists/{id}")
    public Playlist getPlaylist(@PathVariable String id){
        return playlistService.buscarPlaylist(id);
    }

    @PutMapping("/playlists/{id}")
    public Playlist updatePlaylist(@PathVariable String id, @RequestBody Playlist playlist){
        return playlistService.atualizaPlaylist(id, playlist);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable String id){
        playlistService.deletarPlaylist(id);
    }
}