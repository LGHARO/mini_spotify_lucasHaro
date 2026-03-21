package com.example.minispotify.spotify;

import java.time.LocalDateTime;

public class HistoricoReproducoes {

    private String id;
    private Usuario usuario;
    private Musica musica;
    private LocalDateTime horaReproducao;

    public Usuario getUsuario() {
        return usuario;
    }

    public HistoricoReproducoes(String id, Usuario usuario, Musica musica, LocalDateTime horaReproducao) {
        this.id = id;
        this.usuario = usuario;
        this.musica = musica;
        this.horaReproducao = horaReproducao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public LocalDateTime getHoraReproducao() {
        return horaReproducao;
    }

    public void setHoraReproducao(LocalDateTime horaReproducao) {
        this.horaReproducao = horaReproducao;
    }
}
