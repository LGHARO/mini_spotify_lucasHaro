package com.example.minispotify.spotify.album;

import com.example.minispotify.spotify.album.dto.SaveAlbumDTO;
import com.example.minispotify.spotify.artista.Artista;
import com.example.minispotify.spotify.artista.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaService artistaService;

    public Album cadastrarAlbum(SaveAlbumDTO dto) {
        Artista artista = artistaService.buscarArtista(dto.getArtistaId());
        if (artista == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não existe");
        }

        Album album = Album.toModel(dto, artista);
        return albumRepository.save(album);
    }

    public Collection<Album> buscarAlbuns() {
        return albumRepository.findAll();
    }

    public Album buscarAlbum(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album não existe"));
    }

    public Album atualizarAlbum(Integer id, Album album) {
        Album existente = buscarAlbum(id);

        if (album.getTitulo() != null) {
            existente.setTitulo(album.getTitulo());
        }

        if (album.getDataLancamento() != null) {
            existente.setDataLancamento(album.getDataLancamento());
        }

        return albumRepository.save(existente);
    }

    public void deletarAlbum(Integer id) {
        if (!albumRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album não existe");
        }
        albumRepository.deleteById(id);
    }
}