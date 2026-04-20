package com.example.minispotify.spotify.musica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {

    List<Musica> findAllByOrderByTotalReproducoesDesc();

}