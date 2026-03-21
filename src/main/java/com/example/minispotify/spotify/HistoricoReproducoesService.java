package com.example.minispotify.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class HistoricoReproducoesService {


    @Autowired
    private UsuarioService usuarioService;


    HashMap<String, HistoricoReproducoes> historicos = new HashMap<>();


    public Collection<HistoricoReproducoes> getHistoricoMusica(String musicaId) {
        Collection<HistoricoReproducoes> resposta = new ArrayList<>();

        for (HistoricoReproducoes historico : historicos.values()){
            if (historico.getMusica().getId().equals(musicaId)){
                resposta.add(historico);
            }
        }
        return resposta;
    }

    public Collection<HistoricoReproducoes> getHistoricoUsuario(String usuarioId) {
        Collection<HistoricoReproducoes> resposta = new ArrayList<>();

        for (HistoricoReproducoes historico : historicos.values()){
            if (historico.getUsuario().getId().equals(usuarioId)){
                resposta.add(historico);
            }
        }
        return resposta;
    }

    public void cadastrarHistorico(Usuario usuario, Musica musica ){
        String novoId = String.valueOf(historicos.size() + 1);
        LocalDateTime horaCriacao = LocalDateTime.now();

        HistoricoReproducoes historicoReproducoes = new HistoricoReproducoes(novoId, usuario, musica, horaCriacao);
        historicos.put(novoId, historicoReproducoes);
    }
}
