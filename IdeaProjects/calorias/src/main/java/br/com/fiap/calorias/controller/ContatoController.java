package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.ContatoCadastrarDto;
import br.com.fiap.calorias.dto.ContatoExibicaoDto;
import br.com.fiap.calorias.model.Contato;
import br.com.fiap.calorias.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastrarDto contatoCadastrarDto) {
        return service.gravar(contatoCadastrarDto);
    }

    @GetMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsContatos(paginacao);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto atualizar(@RequestBody @Valid ContatoCadastrarDto contatoCadastrarDto) {
        return service.atualizar(contatoCadastrarDto);
    }

//    @GetMapping("/contatos/nome/{nome}")
//    @ResponseStatus(HttpStatus.OK)
//    public ContatoExibicaoDto buscarPorNome(@PathVariable String nome) {
//        return service.buscarPeloNome(nome);
//    }

//    /api/contatos?nome=Pedro
    @GetMapping(value = "/contatos", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoPorNome(@RequestParam String nome){
        return service.buscarPeloNome(nome);
    }

//    /api/contatos?dataInicio=2001-10-01&dataFinal=2000-10-31
    @GetMapping(value = "/contatos", params = {"dataInicio", "dataFim"})
    public List<ContatoExibicaoDto> listarAniversariantes(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim, Pageable paginacao){
        return service.listarAniversariantes(dataInicio, dataFim, paginacao);
    }

//    /api/contatos?email=email@gmail.com
    @GetMapping(value = "/contatos", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoEmail(@RequestParam String email){
        return service.buscarContatoEmail(email);
    }

//    /api/contatos?dominioEmail=dominio
    @GetMapping(value = "/contatos", params = "dominio")
    @ResponseStatus(HttpStatus.OK)
    public List<ContatoExibicaoDto> buscarDominioEmail(@RequestParam String dominio, Pageable paginacao){
        return service.buscarDominioEmail(dominio, paginacao);
    }
}
