package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.NotBlank;

public record AlimentoCadastroDto(
        Long alimentoId,

        @NotBlank(message = "O nome do alimento é obrigatorio!")
        String nome,

        @NotBlank(message = "A quantidade de poção é obrigatorio!")
        String porcao,

        @NotBlank(message = "A quantidade de proteina é obrigatorio!")
        Double quantidadeProteina,

        @NotBlank(message = "A quantidade de carboidrato é obrigatorio!")
        Double quantidadeCarboidrato,

        @NotBlank(message = "A quantidade de gordura é obrigatorio!")
        Double quantidadeGorduras,

        Double totalCalorias
) {
}
