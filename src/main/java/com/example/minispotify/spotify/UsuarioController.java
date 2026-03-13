package com.example.minispotify.spotify;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/*
    o @RestController serve para tornar a classe alvo possivel de receber requisições HTTP
    alem disso nela agora é possivel retornar as respostas da requisição diretamente no corpo da
    resposta em json
 */

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // troca o response status do default 200 pra 201
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
    @DeleteMapping("/usuario/{id}")
    public void deleteUsuario(@PathVariable String id) {
        usuarioService.deletarUsuario(id);
    }








}
