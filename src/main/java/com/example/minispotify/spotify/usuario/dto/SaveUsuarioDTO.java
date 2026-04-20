package com.example.minispotify.spotify.usuario.dto;

import com.example.minispotify.spotify.usuario.TipoUsuarios;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUsuarioDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoUsuarios tipoUsuario;
}