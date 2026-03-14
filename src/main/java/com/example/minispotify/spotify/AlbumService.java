package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class AlbumService {
    HashMap<String, Album> albums =  new HashMap<>();

    public Album cadastrarAlbum( Album album) {
        if (albums.containsKey(album.getId())){
            throw new RuntimeException("album ja existe");
        }
        albums.put(album.getId(), album);
        return album;
    }

    public Collection<Album> buscarAlbums() {
        Collection<Album> resposta =  new ArrayList<>();
        for (Album album: albums.values()){
            resposta.add(album);
        }
        return resposta;
    }

    public Album buscaAlbum(String id) {
        return albums.get(id);
    }


    public Album atualizaAlbum(String id, Album album) {
        if (album.getTitulo() != null){
            albums.get(id).setTitulo(album.getTitulo());
        }
        if (album.getArtista() != null){
            albums.get(id).setArtista(album.getArtista());
        }
        if (album.getDataLancamento() != null){
            albums.get(id).setDataLancamento(album.getDataLancamento());
        }
        return albums.get(album.getId());

    }

    public void deletarAlbum(String id) {
        albums.remove(id);
    }
}
