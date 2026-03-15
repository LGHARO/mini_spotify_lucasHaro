package com.example.minispotify.spotify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopMusicasDTO {
    @NotBlank
    private  String titulo;
    @NotNull
    private String artista;
    @NotNull
    private long totalReproducoes;


    // mesmo que não chamemos diretamente essas funções get o tradutor para json do spring precisa deles pra acessar
    // as informacoes privadas do objeto

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public long getTotalReproducoes() {
        return totalReproducoes;
    }

    public TopMusicasDTO(String titulo, String artista, long totalReproducoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.totalReproducoes = totalReproducoes;
    }

}
