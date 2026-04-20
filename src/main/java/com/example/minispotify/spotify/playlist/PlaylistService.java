package com.example.minispotify.spotify.playlist;

import com.example.minispotify.spotify.album.AlbumService;
import com.example.minispotify.spotify.musica.Musica;
import com.example.minispotify.spotify.musica.MusicaService;
import com.example.minispotify.spotify.playlist.dto.SavePlaylistDTO;
import com.example.minispotify.spotify.usuario.Usuario;
import com.example.minispotify.spotify.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlbumService albumService;

    public Playlist cadastrarPlaylist(SavePlaylistDTO dto) {
        Usuario usuario = usuarioService.buscaUsuario(dto.getUsuarioId());
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe");
        }

        List<Musica> musicas = new ArrayList<>();
        for (Integer musicaId : dto.getMusicasIds()) {
            Musica musica = musicaService.buscaMusica(musicaId);
            if (musica == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica com id " + musicaId + " não existe");
            }
            musicas.add(musica);
        }

        Playlist playlist = Playlist.toModel(dto, usuario, musicas);
        return playlistRepository.save(playlist);
    }

    public Collection<Playlist> buscarPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist buscarPlaylist(Integer id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "playlist não existe"));
    }

    public Playlist atualizaPlaylist(Integer id, Playlist playlist) {
        Playlist playlistExistente = buscarPlaylist(id);

        if (playlist.getNome() != null) {
            playlistExistente.setNome(playlist.getNome());
        }

        playlistExistente.setPublica(playlist.isPublica());

        return playlistRepository.save(playlistExistente);
    }

    public void deletarPlaylist(Integer id) {
        if (!playlistRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não existe");
        }
        playlistRepository.deleteById(id);
    }

    public Playlist addMusica(Integer playlistId, Integer musicaId, Integer usuarioId) {
        Playlist playlist = buscarPlaylist(playlistId);

        if (!playlist.getUsuario().getId().equals(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas donos da playlist podem adicionar musicas a ela");
        }

        if (buscarMusicaPlaylist(playlistId, musicaId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A musica ja esta na playlist");
        }

        Musica musica = musicaService.buscaMusica(musicaId);
        if (musica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não existe");
        }

        playlist.getMusicas().add(musica);
        return playlistRepository.save(playlist);
    }

    public boolean buscarMusicaPlaylist(Integer playlistId, Integer musicaId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);

        if (playlist == null) {
            return false;
        }

        for (Musica musica : playlist.getMusicas()) {
            if (musica.getId().equals(musicaId)) {
                return true;
            }
        }

        return false;
    }
}