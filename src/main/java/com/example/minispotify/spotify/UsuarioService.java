package com.example.minispotify.spotify;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class UsuarioService {

    private HashMap<String, Usuario> usuarios = new HashMap<>();

    // recebe o usuario e o cadastra
    public Usuario cadastraUsuario(Usuario usuario){
        if (usuarios.containsKey(usuario.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Usuario ja existe");
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    // lista os usuarios cadastrados
    public Collection<Usuario> listaUsuarios() {
        Collection<Usuario> resposta = new ArrayList<>();
        for (Usuario usuario : usuarios.values()) {
            resposta.add(usuario);
        }
        return resposta;
    }

    // pega umusuario
    public Usuario buscaUsuario(String id) {
        return usuarios.get(id);
    }

    // troca informações de um usuario especifico
    public Usuario atualizaUsuario(String id, Usuario usuario) {

        if (!usuarios.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não existe");
        }

        if (usuario.getNome() != null){
            usuarios.get(id).setNome(usuario.getNome());
        }

        if (usuario.getTipoUsuario() != null){
            usuarios.get(id).setTipoUsuario(usuario.getTipoUsuario());
        }

        if (usuario.getEmail() != null){
            usuarios.get(id).setEmail(usuario.getEmail());
        }

        return usuarios.get(id);
    }


    public void deletarUsuario(String id) {
        if (!usuarios.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe");
        }
          usuarios.remove(id);
    }

    public boolean isActive(String idUsuario) {
        return usuarios.get(idUsuario).isAtivo();
    }
}
