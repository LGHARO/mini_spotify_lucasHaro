package com.example.minispotify.spotify.musica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMusicaDTO {

    @NotBlank
    private String titulo;

    @NotNull
    private Integer duracaoSegundos;

    @NotNull
    private Integer numeroFaixa;

    @NotNull
    private Integer albumId;

    @NotNull
    private Integer artistaId;
}