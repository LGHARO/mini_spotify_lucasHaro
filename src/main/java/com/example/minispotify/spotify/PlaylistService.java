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

        playlistExistente.setPublica(playlist.isPublica());

        if (playlist.getDataCriacao() != null){
            playlistExistente.setDataCriacao(playlist.getDataCriacao());
        }

        // atualização parcial do usuário
        if (playlist.getUsuario() != null){

            Usuario usuarioExistente = playlistExistente.getUsuario();
            Usuario usuarioNovo = playlist.getUsuario();

            if (usuarioNovo.getNome() != null){
                usuarioExistente.setNome(usuarioNovo.getNome());
            }

            if (usuarioNovo.getEmail() != null){
                usuarioExistente.setEmail(usuarioNovo.getEmail());
            }

            if (usuarioNovo.getTipoUsuario() != null){
                usuarioExistente.setTipoUsuario(usuarioNovo.getTipoUsuario());
            }

            usuarioExistente.setAtivo(usuarioNovo.isAtivo());

            if (usuarioNovo.getDataCriacao() != null){
                usuarioExistente.setDataCriacao(usuarioNovo.getDataCriacao());
            }
        }

        // atualização das músicas
        if (playlist.getMusicas() != null){

            for (Musica musicaNova : playlist.getMusicas()){

                Musica musicaExistente = null;

                for (Musica m : playlistExistente.getMusicas()){
                    if (Objects.equals(m.getId(), musicaNova.getId())) {
                        musicaExistente = m;
                        break;
                    }
                }

                if (musicaExistente != null){

                    if (musicaNova.getTitulo() != null){
                        musicaExistente.setTitulo(musicaNova.getTitulo());
                    }

                    if (musicaNova.getDuracaoSegundos() != null){
                        musicaExistente.setDuracaoSegundos(musicaNova.getDuracaoSegundos());
                    }

                    if (musicaNova.getNumerofaixa() != null){
                        musicaExistente.setNumerofaixa(musicaNova.getNumerofaixa());
                    }

                    if (musicaNova.getTotalReproducoes() != 0){
                        musicaExistente.setTotalReproducoes(musicaNova.getTotalReproducoes());
                    }

                    // artista da música
                    if (musicaNova.getArtista() != null){

                        Artista artistaExistente = musicaExistente.getArtista();
                        Artista artistaNovo = musicaNova.getArtista();

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

                    // álbum da música
                    if (musicaNova.getAlbum() != null){

                        Album albumExistente = musicaExistente.getAlbum();
                        Album albumNovo = musicaNova.getAlbum();

                        if (albumNovo.getTitulo() != null){
                            albumExistente.setTitulo(albumNovo.getTitulo());
                        }

                        if (albumNovo.getDataLancamento() != null){
                            albumExistente.setDataLancamento(albumNovo.getDataLancamento());
                        }
                    }

                }
                else {
                    // se não existir na playlist, adiciona
                    playlistExistente.getMusicas().add(musicaNova);
                }
            }
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