package com.example.minispotify.spotify.musica;

import com.example.minispotify.spotify.historico.HistoricoReproducoes;
import com.example.minispotify.spotify.historico.HistoricoService;
import com.example.minispotify.spotify.musica.dto.SaveMusicaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class MusicaController {

    @Autowired
    private MusicaService musicaService;
    @Autowired
    private HistoricoService historicoService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/musicas")
    public Musica criarMusica(@Valid @RequestBody SaveMusicaDTO dto) {
        return musicaService.cadastrarMusica(dto);
    }

    @GetMapping("/musicas")
    public Collection<Musica> listarMusicas() {
        return musicaService.buscarMusicas();
    }

    @GetMapping("/musicas/{id}")
    public Musica buscarMusica(@PathVariable Integer id) {
        return musicaService.buscaMusica(id);
    }

    @PutMapping("/musicas/{id}")
    public Musica atualizarMusica(@PathVariable Integer id, @RequestBody Musica musica) {
        return musicaService.atualizarMusica(id, musica);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/musicas/{id}")
    public void deletarMusica(@PathVariable Integer id) {
        musicaService.deletarMusica(id);
    }

    @PostMapping("/musicas/{id}/reproduzir")
    public Musica reproduzirMusica(
            @PathVariable Integer id,
            @RequestHeader("X-USER-ID") Integer usuarioId
    ) {
        return musicaService.reproduzirMusica(id, usuarioId);
    }

    @GetMapping("/musicas/{id}/historico")
    public List<HistoricoReproducoes> historicoMusica(@PathVariable Integer id) {
        return historicoService.buscarPorMusica(id);
    }
}