package com.example.minispotify.spotify;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ArtistaService {

    HashMap<String, Artista> artistas = new HashMap<>();

    public Artista cadastrarArtista(Artista artista) {
        if (artistas.containsKey(artista.getId()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Artista já existe");

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
        if(!artistas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista não existe");
        }
        return artistas.get(id);
    }

    public Artista atualizaArtista(String id, Artista artista) {

        Artista artistaExistente = artistas.get(id);

        if (artistaExistente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista não existe");
        }

        if (artista.getNome() != null){
            artistaExistente.setNome(artista.getNome());
        }

        if (artista.getGeneroMusical() != null){
            artistaExistente.setGeneroMusical(artista.getGeneroMusical());
        }

        if (artista.getPaisOrigem() != null){
            artistaExistente.setPaisOrigem(artista.getPaisOrigem());
        }

        return artistaExistente;
    }

    public void deletarArtista(String id) {
        if(!artistas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista não existe");
        }
        artistas.remove(id);
    }
}
