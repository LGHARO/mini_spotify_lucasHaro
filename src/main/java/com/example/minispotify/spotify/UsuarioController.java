package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HistoricoReproducoesService historicoReproducoesService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/usuarios")
    public Usuario postPacientes(@Valid @RequestBody Usuario usuario){
        return usuarioService.cadastraUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public Collection<Usuario> getUsuarios(){
        return usuarioService.listaUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable String id){
        return usuarioService.buscaUsuario(id);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable String id ,@RequestBody Usuario usuario){
        return usuarioService.atualizaUsuario(id, usuario);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
    }

    @GetMapping("/usuarios/{usuarioId}/historico")
    public Collection<HistoricoReproducoes> getHistoricoUsuario(@PathVariable String usuarioId ){
        return historicoReproducoesService.getHistoricoUsuario(usuarioId);
    }








}
