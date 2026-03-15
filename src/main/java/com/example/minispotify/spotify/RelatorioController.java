package com.example.minispotify.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RelatorioController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("relatorios/top-musicas")
    public List<TopMusicasDTO> topMusicas(){
        // classe TopMusicasDTO ("data-transfer-object) pra facilitar o manejo dos dados
        return musicaService.topMusicas();
    }
}
