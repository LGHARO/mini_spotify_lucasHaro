package com.example.minispotify.spotify.historico;

import com.example.minispotify.spotify.musica.Musica;
import com.example.minispotify.spotify.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoReproducoes, Integer> {
    List<HistoricoReproducoes> findByMusica(Musica musica);

    List<HistoricoReproducoes> findByUsuario(Usuario usuario);
}