package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.ContatoCadastrarDto;
import br.com.fiap.calorias.dto.ContatoExibicaoDto;
import br.com.fiap.calorias.exception.ContatoNaoEncontradoException;
import br.com.fiap.calorias.model.Contato;
import br.com.fiap.calorias.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    /*
    * A lógica aqui é passar um ContatoCadastrarDto como parametro, construit um novo Contato
    * Que primeiramente estará vazio, depois compor esse contato vazio com a função
    * BeanUtils.copyProperties e passar por parametro o contato que terá o objeto para copiar
    * e o objeto para persistir os dados copiados, no caso passaremos o contatoCadastroDto como primeiro parametro
    * e depois o contato como segundo. Retornamos um novo ContatoExibicaoDto passando o save do repository e o contato como parametro.
    * */
    public ContatoExibicaoDto gravar(ContatoCadastrarDto contatoCadastrarDto) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastrarDto, contato);
        return new ContatoExibicaoDto(contatoRepository.save(contato));
    }

    public ContatoExibicaoDto buscarPorId(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Contato não encontrado");
        }
    }

    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return contatoRepository
                .findAll(paginacao)
                .map(ContatoExibicaoDto::new);
    }

    public void excluir(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado.");
        }

    }

    public ContatoExibicaoDto atualizar(ContatoCadastrarDto contatoCadastrarDto) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastrarDto, contato);
        return new ContatoExibicaoDto(contatoRepository.save(contato));
    }

    public ContatoExibicaoDto buscarPeloNome(String nome) {
        Optional<Contato> contatoOptional = contatoRepository.bucarContatoPeloNome(nome);

        if(contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("O contato não existe!");
        }
    }

    public List<ContatoExibicaoDto> listarAniversariantes(LocalDate dataInicio, LocalDate dataFim, Pageable paginacao){
        return contatoRepository
                .listarAniversariantes(dataInicio, dataFim, paginacao)
                .stream()
                .map(ContatoExibicaoDto::new)
                .toList();
    }

    public ContatoExibicaoDto buscarContatoEmail(String email) {
        Optional<Contato> contatoOptional = contatoRepository.findByEmail(email);

        if(contatoOptional.isPresent()){
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new ContatoNaoEncontradoException("Contato não encontrado!");
        }
    }

    public List<ContatoExibicaoDto> buscarDominioEmail(String dominio, Pageable paginacao){
        return contatoRepository
                .buscarDominioEmail(dominio, paginacao)
                .stream()
                .map(ContatoExibicaoDto::new)
                .toList();
    }
}
