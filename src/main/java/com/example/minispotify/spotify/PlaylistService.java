package com.example.minispotify.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Service
public class PlaylistService {

    HashMap<String, Playlist> playlists = new HashMap<>();

    @Autowired
    private MusicaService musicaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired

    private AlbumService albumService;

    public Playlist cadastrarPlaylist(Playlist playlist){

        // verifica se a playlist ja existe
        if (playlists.containsKey(playlist.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Playlist ja existe");
        }

        // verifica se o usuario existe
        if (playlist.getUsuario() == null ||
                usuarioService.buscaUsuario(playlist.getUsuario().getId()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não existe");
        }

        // verifica se todas as musicas existem
        for (Musica musica : playlist.getMusicas()){
            if (musicaService.buscaMusica(musica.getId()) == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Musica com id " + musica.getId() + " não existe");
            }
        }


        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    public Collection<Playlist> buscarPlaylists(){

        Collection<Playlist> resposta = new ArrayList<>();

        for(Playlist playlist : playlists.values()){
            resposta.add(playlist);
        }

        return resposta;
    }

    public Playlist buscarPlaylist(String id){
        if(!playlists.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"playlist não existe");
        }
        return playlists.get(id);
    }

    public Playlist atualizaPlaylist(String id, Playlist playlist){

        Playlist playlistExistente = playlists.get(id);

        if (playlistExistente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada");
        }

        if (playlist.getNome() != null){
            playlistExistente.setNome(playlist.getNome());
        }

        // se a nova playlist for public troca a original pra
        playlistExistente.setPublica(playlist.isPublica());

        if (playlist.getDataCriacao() != null){
            playlistExistente.setDataCriacao(playlist.getDataCriacao());
        }

        return playlistExistente;
    }

    public void deletarPlaylist(String id){
        if(!playlists.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist não existe");
        }
        playlists.remove(id);
    }

    public Playlist addMusica(String playlistId, String musicaId, String usuarioId){

        // verifica se a playlist existe
        if (!playlists.containsKey(playlistId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Playlist não existe");
        }

        Playlist playlist = playlists.get(playlistId);

        // verifica se o usuario é dono da playlist
        if (!playlist.getUsuario().getId().equals(usuarioId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Apenas donos da playlist podem adicionar musicas a ela");
        }

        // verifica se a musica ja está na playlist
        if (buscarMusicaPlaylist(playlistId, musicaId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"A musica ja esta na playlist");
        }

        Musica musica = musicaService.buscaMusica(musicaId);

        playlist.getMusicas().add(musica);

        return playlist;
    }

    public boolean buscarMusicaPlaylist(String playlistId, String musicaId) {
        Playlist playlist = playlists.get(playlistId);

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