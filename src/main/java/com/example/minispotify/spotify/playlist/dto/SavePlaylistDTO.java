package com.example.minispotify.spotify.playlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SavePlaylistDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Boolean publica;

    @NotNull
    private Integer usuarioId;

    @NotNull
    private List<Integer> musicasIds;
}