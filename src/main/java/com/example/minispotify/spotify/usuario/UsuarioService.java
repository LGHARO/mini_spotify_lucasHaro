package com.example.minispotify.spotify.usuario;

import com.example.minispotify.spotify.usuario.dto.SaveUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(SaveUsuarioDTO dto) {
        Usuario usuario = Usuario.toModel(dto);
        return usuarioRepository.save(usuario);
    }

    public Collection<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscaUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = buscaUsuario(id);

        if (usuario.getNome() != null) {
            existente.setNome(usuario.getNome());
        }

        if (usuario.getEmail() != null) {
            existente.setEmail(usuario.getEmail());
        }

        if (usuario.getTipoUsuario() != null) {
            existente.setTipoUsuario(usuario.getTipoUsuario());
        }

        existente.setAtivo(usuario.isAtivo());

        return usuarioRepository.save(existente);
    }

    public void deletarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe");
        }
        usuarioRepository.deleteById(id);
    }
}