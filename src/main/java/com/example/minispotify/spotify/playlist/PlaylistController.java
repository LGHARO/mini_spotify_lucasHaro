package com.example.minispotify.spotify.playlist;

import com.example.minispotify.spotify.playlist.dto.SavePlaylistDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/playlists")
    public Playlist postPlaylist(@Valid @RequestBody SavePlaylistDTO dto){
        return playlistService.cadastrarPlaylist(dto);
    }

    @PostMapping("/playlists/{playlistId}/musicas/{musicaId}")
    public Playlist addMusica(@PathVariable Integer playlistId,
                              @PathVariable Integer musicaId,
                              @RequestHeader("X-USER-ID") Integer usuarioId){
        return playlistService.addMusica(playlistId, musicaId, usuarioId);
    }

    @GetMapping("/playlists")
    public Collection<Playlist> buscarPlaylists(){
        return playlistService.buscarPlaylists();
    }

    @GetMapping("/playlists/{id}")
    public Playlist getPlaylist(@PathVariable Integer id){
        return playlistService.buscarPlaylist(id);
    }

    @PutMapping("/playlists/{id}")
    public Playlist updatePlaylist(@PathVariable Integer id, @RequestBody Playlist playlist){
        return playlistService.atualizaPlaylist(id, playlist);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/playlists/{id}")
    public void deletePlaylist(@PathVariable Integer id){
        playlistService.deletarPlaylist(id);
    }
}