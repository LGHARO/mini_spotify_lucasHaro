package com.example.minispotify.spotify;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class MusicaService {
    HashMap<String, Musica> musicas =  new HashMap<>();

    public Musica cadastrarMusica( Musica musica) {
        if (musicas.containsKey(musica.getId())){
            throw new RuntimeException("musica ja existe");
        }
        musicas.put(musica.getId(), musica);
        return musica;
    }

    public Collection<Musica> buscarMusicas() {
        Collection<Musica> resposta =  new ArrayList<>();
        for (Musica musica: musicas.values()){
            resposta.add(musica);
        }
        return resposta;
    }

    public Musica buscaMusica(String id) {
        return musicas.get(id);
    }


    public Musica atualizaMusica(String id, Musica musica) {

        if (musica.getTitulo() != null){
            musica.setTitulo(musica.getTitulo());
        }

        if (musica.getDuracaoSegundos() != null){
            musica.setDuracaoSegundos(musica.getDuracaoSegundos());
        }

        if (musica.getNumerofaixa() != null){
            musica.setNumerofaixa(musica.getNumerofaixa());
        }

        if (musica.getAlbum() != null){
            musica.setAlbum(musica.getAlbum());
        }

        if (musica.getArtista() != null){
            musica.setArtista(musica.getArtista());
        }

        if (musica.getTotalReproducoes() != 0){
            musica.setTotalReproducoes(musica.getTotalReproducoes());
        }

        return musica;
    }

    public void deletarMusica(String id) {
        musicas.remove(id);
    }

    public void reproduzirMusica(String idMusica) {
        musicas.get(idMusica).reproduzirMusica();
    }
}
