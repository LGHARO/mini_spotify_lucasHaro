package com.example.minispotify.spotify.album.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaveAlbumDTO {

    @NotBlank
    private String titulo;

    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    private Integer artistaId;
}