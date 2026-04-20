package com.example.minispotify.spotify.playlist;

import com.example.minispotify.spotify.usuario.Usuario;
import com.example.minispotify.spotify.musica.Musica;
import com.example.minispotify.spotify.playlist.dto.SavePlaylistDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean publica;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void addMusicas(List<Musica> musicas) {
        this.musicas.addAll(musicas);
    }

    public static @NonNull Playlist toModel(SavePlaylistDTO dto, Usuario usuario, List<Musica> musicas) {
        Playlist playlist = new Playlist();
        playlist.setNome(dto.getNome());
        playlist.setPublica(dto.getPublica());
        playlist.setUsuario(usuario);
        playlist.setMusicas(musicas);
        return playlist;
    }
}