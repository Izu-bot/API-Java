package br.com.fiap.calorias.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Indica ao Spring que esta classe controlará as exceções globais do app
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)// Indica que caso esse status http ocorra, a exceção será lançada
    @ExceptionHandler(MethodArgumentNotValidException.class) // Configura o metodo para controla a exceção especifica
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException err) {

        // Criamos uma mapa para armazenar os erros de validação, o mapa terá o nome do campo como chave e mensagem de erro como valor
        Map<String, String> mapaErro = new HashMap<>();

        // Obtém a lista de erros da exceção MethodArgumentValidException referenciado por err
        List<FieldError> campos = err.getBindingResult().getFieldErrors();

        // Faz uma iteração na lista de erros e colocamos o nome do campo como chave e mensagem de erro como valor
        for (FieldError campo : campos) {
            mapaErro.put(campo.getField(), campo.getDefaultMessage());
        }

        // Retorna o mapa de erros que será serializado como JSON
        return mapaErro;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> nenhumRecursoEncontradoException(NoResourceFoundException err) {
        return new ResponseEntity<>("Recurso não encontrado", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> integridadeDadosVioladosException(DataIntegrityViolationException err) {
        return new ResponseEntity<>(
                "Ocorreu um erro na integridade de dados, por favor," +
                        "verifique se os dados estam sendo preenchidos corretamente e se o usuário já não está cadastrado.",
                HttpStatus.CONFLICT);
    }

    // Solução do processor para DataIntegrityViolationException
//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public Map<String, String> manusearIntegridadeDados(){
//        Map<String, String> mapaErro = new HashMap<>();
//        mapaErro.put("Erro", "Usuário já cadastrado");
//        return mapaErro;
//    }
}
