package com.example.minispotify.spotify.artista.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveArtistaDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String generoMusical;

    @NotBlank
    private String paisOrigem;
}