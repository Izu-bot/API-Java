package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(

        @NotBlank(message = "O email do usuário é obrigatorio!")
        @Email(message = "Verifique se o email está escrito corretamente!")
        String email,

        @NotBlank(message = "A senha do usuário é obrigatoria!")
        @Size(min = 6, max = 20, message = "O tamanho da senha deve conter entre 6 a 20 caracteres")
        String senha
) {
}
