package com.example.minispotify.spotify.musica;

import com.example.minispotify.spotify.album.Album;
import com.example.minispotify.spotify.artista.Artista;
import com.example.minispotify.spotify.musica.dto.SaveMusicaDTO;
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
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer duracaoSegundos;

    @Column(nullable = false)
    private Integer numeroFaixa;

    @ManyToOne(optional = false)
    private Album album;

    @ManyToOne(optional = false)
    private Artista artista;

    @Column(nullable = false)
    private long totalReproducoes;

    public void reproduzirMusica() {
        this.totalReproducoes += 1;
    }

    public static @NonNull Musica toModel(SaveMusicaDTO dto, Album album, Artista artista) {
        Musica musica = new Musica();
        musica.setTitulo(dto.getTitulo());
        musica.setDuracaoSegundos(dto.getDuracaoSegundos());
        musica.setNumeroFaixa(dto.getNumeroFaixa());
        musica.setAlbum(album);
        musica.setArtista(artista);
        musica.setTotalReproducoes(0);
        return musica;
    }
}