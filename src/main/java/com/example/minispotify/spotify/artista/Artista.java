package com.example.minispotify.spotify.artista;

import com.example.minispotify.spotify.artista.dto.SaveArtistaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String generoMusical;

    @Column(nullable = false)
    private String paisOrigem;

    public static @NonNull Artista toModel(SaveArtistaDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.getNome());
        artista.setGeneroMusical(dto.getGeneroMusical());
        artista.setPaisOrigem(dto.getPaisOrigem());
        return artista;
    }
}