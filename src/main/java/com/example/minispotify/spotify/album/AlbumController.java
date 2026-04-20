package com.example.minispotify.spotify.album;

import com.example.minispotify.spotify.album.dto.SaveAlbumDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/albuns")
    public Album criarAlbum(@Valid @RequestBody SaveAlbumDTO dto) {
        return albumService.cadastrarAlbum(dto);
    }

    @GetMapping("/albuns")
    public Collection<Album> listarAlbuns() {
        return albumService.buscarAlbuns();
    }

    @GetMapping("/albuns/{id}")
    public Album buscarAlbum(@PathVariable Integer id) {
        return albumService.buscarAlbum(id);
    }

    @PutMapping("/albuns/{id}")
    public Album atualizarAlbum(@PathVariable Integer id, @RequestBody Album album) {
        return albumService.atualizarAlbum(id, album);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/albuns/{id}")
    public void deletarAlbum(@PathVariable Integer id) {
        albumService.deletarAlbum(id);
    }
}