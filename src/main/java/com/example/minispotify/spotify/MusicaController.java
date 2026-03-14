package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class MusicaController {
    @Autowired
    private MusicaService musicaService;

    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/musicas")
    public Musica postMusica(@Valid @RequestBody Musica musica){
        return musicaService.cadastrarMusica(musica);
    }

    @PostMapping("/musicas/{idMusica}/reproduzir")
    public void reproduzirMusica(@PathVariable String idMusica, @RequestHeader("X-USER-ID") String idUsuario){
        if (usuarioService.isActive(idUsuario)){
            throw new RuntimeException("Usuarios inativos não podem reproduzir musicas");
        }
        else{
            musicaService.reproduzirMusica(idMusica);
        }
    }

    @GetMapping("/musicas")
    public Collection<Musica> buscaMusicas(){
        return musicaService.buscarMusicas();
    }

    @GetMapping("/musicas/{id}")
    public Musica getMusica(@PathVariable String id){
        return musicaService.buscaMusica(id);
    }

    @PutMapping("/musicas/{id}")
    public Musica updateMusica(@PathVariable String id ,@RequestBody Musica musica){
        return musicaService.atualizaMusica(id, musica);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/musicas/{id}")
    public void deleteMusica(@PathVariable String id) {
        musicaService.deletarMusica(id);
    }

}
