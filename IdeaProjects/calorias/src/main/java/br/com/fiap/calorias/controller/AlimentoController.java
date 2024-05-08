package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.AlimentoCadastroDto;
import br.com.fiap.calorias.dto.AlimentoExibicaoDto;
import br.com.fiap.calorias.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDto salvar(@RequestBody AlimentoCadastroDto alimento) {
        return alimentoService.salvarAlimento(alimento);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDto> litarTodos() {
        return alimentoService.listarTodos();
    }

    @GetMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibicaoDto> buscarPorId(@PathVariable Long alimentoId) {
        try {
            return ResponseEntity.ok(alimentoService.buscarPorId(alimentoId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    /api/alimento?nome=nome
    @GetMapping(value = "/alimentos", params = "nome")
    @ResponseStatus(HttpStatus.OK)
    public AlimentoExibicaoDto buscarPorNome(@RequestParam String nome){
        return alimentoService.buscarPorNome(nome);
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alimentoId) {alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibicaoDto> atualizar(@RequestBody AlimentoCadastroDto alimentoDTO) {
        try {
            AlimentoExibicaoDto alimentoExibicaoDTO = alimentoService.atualizar(alimentoDTO);
            return ResponseEntity.ok(alimentoExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//    /api/alimentos?letra=A
    @GetMapping(value = "/alimentos", params = "letra")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDto> buscarPorInicial(@RequestParam String letra, Pageable paginacao){
        return alimentoService.buscarInicial(letra, paginacao);
    }

//    /api/alimentos?gorduras=???
    @GetMapping(value = "/alimentos", params = "gorduras")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDto> buscarPorQtdGordurasInferior(@RequestParam Double valor, Pageable paginacao){
        return alimentoService.buscarPorQtdGordurasInferior(valor, paginacao);
    }
}