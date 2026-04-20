package com.example.minispotify.spotify.musica;

import com.example.minispotify.spotify.TopMusicasDTO;
import com.example.minispotify.spotify.album.Album;
import com.example.minispotify.spotify.album.AlbumService;
import com.example.minispotify.spotify.artista.Artista;
import com.example.minispotify.spotify.artista.ArtistaService;
import com.example.minispotify.spotify.historico.HistoricoService;
import com.example.minispotify.spotify.historico.dto.SaveHistoricoDTO;
import com.example.minispotify.spotify.musica.dto.SaveMusicaDTO;
import com.example.minispotify.spotify.usuario.Usuario;
import com.example.minispotify.spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistoricoService historicoService;

    public Musica cadastrarMusica(SaveMusicaDTO dto) {
        Album album = albumService.buscarAlbum(dto.getAlbumId());
        if (album == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album não existe");
        }

        Artista artista = artistaService.buscarArtista(dto.getArtistaId());
        if (artista == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não existe");
        }

        Musica musica = Musica.toModel(dto, album, artista);
        return musicaRepository.save(musica);
    }

    public Collection<Musica> buscarMusicas() {
        return musicaRepository.findAll();
    }

    public Musica buscaMusica(Integer id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não existe"));
    }

    public Musica atualizarMusica(Integer id, Musica musica) {
        Musica existente = buscaMusica(id);

        if (musica.getTitulo() != null) {
            existente.setTitulo(musica.getTitulo());
        }

        if (musica.getDuracaoSegundos() != null) {
            existente.setDuracaoSegundos(musica.getDuracaoSegundos());
        }

        if (musica.getNumeroFaixa() != null) {
            existente.setNumeroFaixa(musica.getNumeroFaixa());
        }

        return musicaRepository.save(existente);
    }

    public void deletarMusica(Integer id) {
        if (!musicaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não existe");
        }
        musicaRepository.deleteById(id);
    }

    public List<TopMusicasDTO> topMusicas() {
        return musicaRepository.findAllByOrderByTotalReproducoesDesc()
                .stream()
                .map(m -> new TopMusicasDTO(
                        m.getTitulo(),
                        m.getArtista().getNome(),
                        m.getTotalReproducoes()
                ))
                .toList();
    }

    public Musica reproduzirMusica(Integer musicaId, Integer usuarioId) {

        Musica musica = buscaMusica(musicaId);

        Usuario usuario = usuarioService.buscaUsuario(usuarioId);

        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe");
        }

        if (!usuario.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario inativo");
        }

        musica.reproduzirMusica();

        SaveHistoricoDTO dto = new SaveHistoricoDTO();
        dto.setUsuarioId(usuarioId);
        dto.setMusicaId(musicaId);

        historicoService.registrarReproducao(dto);

        return musicaRepository.save(musica);
    }
}