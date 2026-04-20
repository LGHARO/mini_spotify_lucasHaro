package com.example.minispotify.spotify.artista;

import com.example.minispotify.spotify.artista.dto.SaveArtistaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/artistas")
    public Artista criarArtista(@Valid @RequestBody SaveArtistaDTO dto) {
        return artistaService.cadastrarArtista(dto);
    }

    @GetMapping("/artistas")
    public Collection<Artista> listarArtistas() {
        return artistaService.buscarArtistas();
    }

    @GetMapping("/artistas/{id}")
    public Artista buscarArtista(@PathVariable Integer id) {
        return artistaService.buscarArtista(id);
    }

    @PutMapping("/artistas/{id}")
    public Artista atualizarArtista(@PathVariable Integer id, @RequestBody Artista artista) {
        return artistaService.atualizarArtista(id, artista);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/artistas/{id}")
    public void deletarArtista(@PathVariable Integer id) {
        artistaService.deletarArtista(id);
    }
}