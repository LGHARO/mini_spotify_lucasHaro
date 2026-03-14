package com.example.minispotify.spotify;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class PlaylistService {

    HashMap<String, Playlist> playlists = new HashMap<>();

    public Playlist cadastrarPlaylist(Playlist playlist){

        if(playlists.containsKey(String.valueOf(playlist.getId()))){
            throw new RuntimeException("playlist ja existe");
        }

        playlists.put(String.valueOf(playlist.getId()), playlist);
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
        return playlists.get(id);
    }

    public Playlist atualizaPlaylist(String id, Playlist playlist){

        Playlist playlistExistente = playlists.get(id);

        if(playlistExistente == null){
            throw new RuntimeException("playlist nao encontrada");
        }

        if(playlist.getNome() != null){
            playlistExistente.setNome(playlist.getNome());
        }

        playlistExistente.setPublica(playlist.isPublica());

        if(playlist.getDataCriacao() != null){
            playlistExistente.setDataCriacao(playlist.getDataCriacao());
        }

        if(playlist.getUsuario() != null){
            playlistExistente.setUsuario(playlist.getUsuario());
        }

        if(playlist.getMusicas() != null){
            playlistExistente.addMusicas(playlist.getMusicas());
        }

        return playlistExistente;
    }

    public void deletarPlaylist(String id){
        playlists.remove(id);
    }

    public Playlist addMusica(String playlistId, Musica musica) {
        playlists.get(playlistId).addMusica(musica);
        return playlists.get(playlistId);

    }

    public String getPlaylistUsuarioId(String playlistId) {
        return playlists.get(playlistId).getUsuario().getId();
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