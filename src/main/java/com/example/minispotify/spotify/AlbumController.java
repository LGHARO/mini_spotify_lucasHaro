package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.Collection;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/albums")
    public Album postAlbum(@Valid @RequestBody Album album){
        return albumService.cadastrarAlbum(album);
    }

    @GetMapping("/albums")
    public Collection<Album> buscaAlbums(){
        return albumService.buscarAlbums();
    }

    @GetMapping("/albums/{id}")
    public Album getAlbum(@PathVariable String id){
        return albumService.buscaAlbum(id);
    }

    @PutMapping("/albums/{id}")
    public Album updateAlbum(@PathVariable String id ,@RequestBody Album album){
        return albumService.atualizaAlbum(id, album);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/albums/{id}")
    public void deleteAlbum(@PathVariable String id) {
        albumService.deletarAlbum(id);
    }

}
