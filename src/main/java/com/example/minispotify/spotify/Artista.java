package com.example.minispotify.spotify;

import jakarta.validation.constraints.NotBlank;

public class Artista {
    @NotBlank
    private String id;
    @NotBlank
    private String nome;
    @NotBlank
    private String generoMusical;
    @NotBlank
    private String paisOrigem;

    public String getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}
