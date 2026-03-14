package com.example.minispotify.spotify;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ArtistaService {

    HashMap<String, Artista> artistas = new HashMap<>();

    public Artista cadastrarArtista(Artista artista) {
        if (artistas.containsKey(artista.getId()))
            throw new RuntimeException("Artista ja existe");

        artistas.put(artista.getId(), artista);
        return artista;

    }

    public Collection<Artista> listaArtistas() {
        Collection<Artista> resposta = new ArrayList<>();
        for (Artista artista : artistas.values()){
            resposta.add(artista);
        }
        return resposta;
    }

    public Artista buscaArtista(String id) {
        return artistas.get(id);
    }

    public Artista atualizaArtista(String id, Artista artista) {
        if (artista.getNome() != null){
            artistas.get(id).setNome(artista.getNome());
        }
        if (artista.getGeneroMusical() != null){
            artistas.get(id).setGeneroMusical(artista.getGeneroMusical());
        }
        if (artista.getPaisOrigem() != null){
            artistas.get(id).setPaisOrigem(artista.getPaisOrigem());
        }
        return artistas.get(artista.getId());

    }

    public void deletarArtista(String id) {
        artistas.remove(id);
    }
}
