package com.example.minispotify.spotify;

import java.time.LocalDateTime;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private TipoUsuarios tipoUsuario;
    private boolean ativo;
    private LocalDateTime dataCriacao;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public TipoUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
