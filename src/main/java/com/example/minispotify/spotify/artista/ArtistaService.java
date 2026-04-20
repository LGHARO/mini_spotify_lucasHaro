package com.example.minispotify.spotify.artista;

import com.example.minispotify.spotify.artista.dto.SaveArtistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista cadastrarArtista(SaveArtistaDTO dto) {
        Artista artista = Artista.toModel(dto);
        return artistaRepository.save(artista);
    }

    public Collection<Artista> buscarArtistas() {
        return artistaRepository.findAll();
    }

    public Artista buscarArtista(Integer id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não existe"));
    }

    public Artista atualizarArtista(Integer id, Artista artista) {
        Artista existente = buscarArtista(id);

        if (artista.getNome() != null) {
            existente.setNome(artista.getNome());
        }

        if (artista.getGeneroMusical() != null) {
            existente.setGeneroMusical(artista.getGeneroMusical());
        }

        if (artista.getPaisOrigem() != null) {
            existente.setPaisOrigem(artista.getPaisOrigem());
        }

        return artistaRepository.save(existente);
    }

    public void deletarArtista(Integer id) {
        if (!artistaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não existe");
        }
        artistaRepository.deleteById(id);
    }
}