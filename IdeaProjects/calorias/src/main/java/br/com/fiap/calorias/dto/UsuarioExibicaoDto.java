package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole())
        ;
    }
}
