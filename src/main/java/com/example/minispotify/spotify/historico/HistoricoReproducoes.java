package com.example.minispotify.spotify.historico;

import com.example.minispotify.spotify.musica.Musica;
import com.example.minispotify.spotify.usuario.Usuario;
import com.example.minispotify.spotify.historico.dto.SaveHistoricoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HistoricoReproducoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Musica musica;

    @Column(nullable = false)
    private LocalDateTime horaReproducao;

    public static @NonNull HistoricoReproducoes toModel(SaveHistoricoDTO dto, Usuario usuario, Musica musica) {
        HistoricoReproducoes historico = new HistoricoReproducoes();
        historico.setUsuario(usuario);
        historico.setMusica(musica);
        historico.setHoraReproducao(LocalDateTime.now());
        return historico;
    }
}