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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/playlists")
    public Playlist postPlaylist(@Valid @RequestBody Playlist playlist){
        return playlistService.cadastrarPlaylist(playlist);
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