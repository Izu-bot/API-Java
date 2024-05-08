package br.com.fiap.calorias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Define qual erro que deverá aparecer essa msg, no caso o not found (404)
public class ContatoNaoEncontradoException extends RuntimeException{

    public ContatoNaoEncontradoException(String message){
        super(message);
        // executa o construtor da classe mae atraves da chamada super.
        // Assim a mensagem utilizada na classe mae sera substituida pela menssagem fornecida pelo construtor da classe ContatoNaoEnontradoException
    }
}
