package com.example.minispotify.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class MusicaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ArtistaService artistaService;

    HashMap<String, Musica> musicas =  new HashMap<>();

    public Musica cadastrarMusica(Musica musica) {
        if (musicas.containsKey(musica.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Musica ja existe");
        }
        if (!artistaService.artistas.containsKey(musica.getArtista().getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Artista não existe");
        }
        musicas.put(musica.getId(), musica);
        return musica;
    }

    public Collection<Musica> buscarMusicas() {
        Collection<Musica> resposta =  new ArrayList<>();
        for (Musica musica: musicas.values()){
            resposta.add(musica);
        }
        return resposta;
    }

    public Musica buscaMusica(String id) {
        if(!musicas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Musica não existe");
        }
        return musicas.get(id);
    }


    public Musica atualizaMusica(String id, Musica musica) {

        Musica musicaExistente = musicas.get(id);

        if (musicaExistente == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não encontrada");
        }

        if (musica.getTitulo() != null){
            musicaExistente.setTitulo(musica.getTitulo());
        }

        if (musica.getDuracaoSegundos() != null){
            musicaExistente.setDuracaoSegundos(musica.getDuracaoSegundos());
        }

        if (musica.getNumerofaixa() != null){
            musicaExistente.setNumerofaixa(musica.getNumerofaixa());
        }

        if (musica.getTotalReproducoes() != 0){
            musicaExistente.setTotalReproducoes(musica.getTotalReproducoes());
        }

        return musicaExistente;
    }

    public void deletarMusica(String id) {
        if(!musicas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Musica não existe");
        }
        musicas.remove(id);
    }

    public void reproduzirMusica(String idMusica, String idUsuario) {
        if (usuarioService.buscaUsuario(idUsuario) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe");
        }

        if(!musicas.containsKey(idMusica)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Musica não existe");
        }

        if (!usuarioService.isActive(idUsuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuarios inativos não podem reproduzir musicas");
        }

        musicas.get(idMusica).reproduzirMusica();
    }

    public List<TopMusicasDTO> topMusicas() {
        List<TopMusicasDTO> topMusicas = new ArrayList<>();

        // ordena a lista pra sobrar os 10 primeiros em total de reproduções
        List<Musica> top = musicas.values()
                .stream()
                .sorted((m1, m2) -> Long.compare(m2.getTotalReproducoes(), m1.getTotalReproducoes()))
                .limit(10)
                .toList();

        // adicona a lista os objetos só com as informacoes necessárias
        for (Musica musica: top){
            TopMusicasDTO topMusicasDTO = new TopMusicasDTO(musica.getTitulo(), musica.getArtista().getNome(), musica.getTotalReproducoes());
            topMusicas.add(topMusicasDTO);
        }

        return topMusicas;



    }
}
