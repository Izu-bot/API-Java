package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Contato;

import java.time.LocalDate;

// Classe record
public record ContatoExibicaoDto(
        // Definindo atributos no construct, para exibir

        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {
    public ContatoExibicaoDto(Contato contato){
        this(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getDataNascimento()
        );
    }
}
