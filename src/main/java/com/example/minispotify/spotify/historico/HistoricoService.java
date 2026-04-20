package com.example.minispotify.spotify.historico;

import com.example.minispotify.spotify.historico.dto.SaveHistoricoDTO;
import com.example.minispotify.spotify.musica.Musica;
import com.example.minispotify.spotify.musica.MusicaRepository;
import com.example.minispotify.spotify.usuario.Usuario;
import com.example.minispotify.spotify.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public HistoricoReproducoes registrarReproducao(SaveHistoricoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

        Musica musica = musicaRepository.findById(dto.getMusicaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não existe"));

        HistoricoReproducoes historico = new HistoricoReproducoes();
        historico.setUsuario(usuario);
        historico.setMusica(musica);
        historico.setHoraReproducao(LocalDateTime.now());

        return historicoRepository.save(historico);
    }

    public List<HistoricoReproducoes> buscarPorMusica(Integer musicaId) {
        Musica musica = musicaRepository.findById(musicaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Musica não existe"));

        return historicoRepository.findByMusica(musica);
    }

    public List<HistoricoReproducoes> buscarPorUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

        return historicoRepository.findByUsuario(usuario);
    }
}