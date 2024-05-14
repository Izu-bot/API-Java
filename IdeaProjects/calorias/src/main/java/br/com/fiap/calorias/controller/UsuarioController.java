package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibicaoDto;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UsuarioController {

    private UsuarioService service;

    @GetMapping(value = "/usuarios", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarId(@RequestParam Long id){
        return service.buscarId(id);
    }

    @DeleteMapping(value = "/usuarios", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@RequestParam Long id){
        service.excluir(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        return service.atualizar(usuarioCadastroDto);
    }
}
