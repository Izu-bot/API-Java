package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long usuarioId,

        @NotBlank(message = "O nome do usuário é obrigatorio!")
        String nome,

        @NotBlank(message = "O e-mail do usuário é obrigatorio!")
        @Email(message = "O e-mail do usuario não é valido!")
        String email,

        @NotBlank(message = "A senha é obrigatorio!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 a 20 caracteres")
        String senha,

        UsuarioRole role
) {
}
