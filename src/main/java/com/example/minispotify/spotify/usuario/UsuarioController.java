package com.example.minispotify.spotify.usuario;

import com.example.minispotify.spotify.historico.HistoricoReproducoes;
import com.example.minispotify.spotify.historico.HistoricoService;
import com.example.minispotify.spotify.usuario.dto.SaveUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistoricoService historicoService;



    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/usuarios")
    public Usuario criarUsuario(@Valid @RequestBody SaveUsuarioDTO dto) {
        return usuarioService.cadastrarUsuario(dto);
    }

    @GetMapping("/usuarios")
    public Collection<Usuario> listarUsuarios() {
        return usuarioService.buscarUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {
        return usuarioService.buscaUsuario(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/usuarios/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/usuarios/{id}/historico")
    public List<HistoricoReproducoes> historicoUsuario(@PathVariable Integer id) {
        return historicoService.buscarPorUsuario(id);
    }
}