package com.example.minispotify.spotify.historico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveHistoricoDTO {

    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer musicaId;
}