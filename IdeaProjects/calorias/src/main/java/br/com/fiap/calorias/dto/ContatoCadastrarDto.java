package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// Classe para cadastro não precisará de constructor no corpo da função, apenas no parametro.
// Pois vamos construi-lo com a classe de exibição dto
public record ContatoCadastrarDto(
        Long id,

        @NotBlank(message = "O nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "O E-mail é obrigátorio!")
        @Email(message = "O E-mail está com formato inválido!")
        String email,

        @NotBlank(message = "A senha é obrigátorio!")
        @Size(min = 6, max = 12, message = "A senha deve conter entre 6 e 12 caracteres!")
        String senha,

        @NotNull(message = "A data de nascimento é obrigrátorio!")
        LocalDate dataNascimento
) {

}
