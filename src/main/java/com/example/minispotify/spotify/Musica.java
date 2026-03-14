package com.example.minispotify.spotify;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Musica {
    @NotBlank
    private String id;
    @NotBlank
    private  String titulo;
    @NotNull
    private Integer duracaoSegundos;
    @NotNull
    private Integer numerofaixa;
    @NotNull
    private Album album;
    @NotNull
    private Artista artista;
    @NotNull
    private long totalReproducoes;

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(Integer duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public Integer getNumerofaixa() {
        return numerofaixa;
    }

    public void setNumerofaixa(Integer numerofaixa) {
        this.numerofaixa = numerofaixa;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public long getTotalReproducoes() {
        return totalReproducoes;
    }

    public void setTotalReproducoes(long totalReproducoes) {
        this.totalReproducoes = totalReproducoes;
    }
}
