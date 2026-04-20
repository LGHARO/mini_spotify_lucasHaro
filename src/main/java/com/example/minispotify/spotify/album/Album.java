package com.example.minispotify.spotify.album;

import com.example.minispotify.spotify.artista.Artista;
import com.example.minispotify.spotify.album.dto.SaveAlbumDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    @ManyToOne(optional = false)
    private Artista artista;

    public static @NonNull Album toModel(SaveAlbumDTO dto, Artista artista) {
        Album album = new Album();
        album.setTitulo(dto.getTitulo());
        album.setDataLancamento(dto.getDataLancamento());
        album.setArtista(artista);
        return album;
    }
}