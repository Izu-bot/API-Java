package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibicaoDto;
import br.com.fiap.calorias.exception.ContatoNaoEncontradoException;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioExibicaoDto gravar(UsuarioCadastroDto usuarioCadastroDto) {

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        usuario.setSenha(senhaCriptografada);

        return new UsuarioExibicaoDto(repository.save(usuario));
    }

    public UsuarioExibicaoDto buscarId(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Usuário não encontrado!");
        }
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id);

        if(usuarioOptional.isPresent()){
            repository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDto atualizar(UsuarioCadastroDto usuarioCadastroDto){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        return new UsuarioExibicaoDto(repository.save(usuario));
    }
}
