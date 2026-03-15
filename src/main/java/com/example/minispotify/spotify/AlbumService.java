package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class AlbumService {

    HashMap<String, Album> albums =  new HashMap<>();

    @Autowired
    private ArtistaService artistaService;

    public Album cadastrarAlbum( Album album) {
        if (albums.containsKey(album.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Album ja existe");
        }
        if (!artistaService.artistas.containsKey(album.getArtista().getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista não existe");
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

        if (albums.get(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album não encontrado");
        }

        if (album.getTitulo() != null){
            albums.get(id).setTitulo(album.getTitulo());
        }

        // troca individualmente as informações do artista
        if (album.getArtista() != null){

            Artista artistaExistente = albums.get(id).getArtista();
            Artista artistaNovo = album.getArtista();

            if (artistaNovo.getNome() != null){
                artistaExistente.setNome(artistaNovo.getNome());
            }

            if (artistaNovo.getGeneroMusical() != null){
                artistaExistente.setGeneroMusical(artistaNovo.getGeneroMusical());
            }

            if (artistaNovo.getPaisOrigem() != null){
                artistaExistente.setPaisOrigem(artistaNovo.getPaisOrigem());
            }
        }

        if (album.getDataLancamento() != null){
            albums.get(id).setDataLancamento(album.getDataLancamento());
        }

        return albums.get(id);
    }

    public void deletarAlbum(String id) {
        if (!albums.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album não existe");
        }
        albums.remove(id);
    }
}
