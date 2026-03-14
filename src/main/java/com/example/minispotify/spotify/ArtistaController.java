package com.example.minispotify.spotify;

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
    public Artista postArtistas(@Valid @RequestBody Artista artista){
        return artistaService.cadastrarArtista(artista);
    }
    @GetMapping("/artistas")
    public Collection<Artista> getArtistas(){
        return artistaService.listaArtistas();
    }

    @GetMapping("/artistas/{id}")
    public Artista getArtista(@PathVariable String id){
        return artistaService.buscaArtista(id);
    }

    @PutMapping("/artistas/{id}")
    public Artista updateArtista(@PathVariable String id ,@RequestBody Artista artista){
        return artistaService.atualizaArtista(id, artista);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/artistas/{id}")
    public void deleteArtista(@PathVariable String id) {
        artistaService.deletarArtista(id);
    }


}
