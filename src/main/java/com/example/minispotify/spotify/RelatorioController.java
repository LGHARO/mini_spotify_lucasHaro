package com.example.minispotify.spotify;

import com.example.minispotify.spotify.musica.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelatorioController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/relatorios/top-musicas")
    public List<TopMusicasDTO> topMusicas() {
        return musicaService.topMusicas();
    }
}