package com.example.minispotify.spotify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopMusicasDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String artista;

    @NotNull
    private long totalReproducoes;

    public TopMusicasDTO(String titulo, String artista, long totalReproducoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.totalReproducoes = totalReproducoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public long getTotalReproducoes() {
        return totalReproducoes;
    }
}