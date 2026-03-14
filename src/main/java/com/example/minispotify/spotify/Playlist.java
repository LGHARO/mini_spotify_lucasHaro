package com.example.minispotify.spotify;

import java.time.LocalDateTime;
import java.util.List;

public class Playlist {
    private String id ;
    private String nome;
    private boolean publica;
    private LocalDateTime dataCriacao;
    private Usuario usuario;
    private List<Musica> musicas;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Musica>  getMusicas() {
        return musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }


    public void addMusicas(List<Musica> musicas) {
        this.musicas.addAll(musicas);
    }
}
