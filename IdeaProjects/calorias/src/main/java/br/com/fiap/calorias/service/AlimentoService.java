package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.AlimentoCadastroDto;
import br.com.fiap.calorias.dto.AlimentoExibicaoDto;
import br.com.fiap.calorias.model.Alimento;
import br.com.fiap.calorias.repository.AlimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public AlimentoExibicaoDto salvarAlimento(AlimentoCadastroDto alimentoDTO){

        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoDTO, alimento);

        alimento.setTotalCalorias(
                calcularCalorias(
                        alimento.getQuantidadeProteina(),
                        alimento.getQuantidadeCarboidrato(),
                        alimento.getQuantidadeGorduras()
                )
        );

        Alimento alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibicaoDto(alimentoSalvo);

    }

    public AlimentoExibicaoDto buscarPorId(Long id){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            return new AlimentoExibicaoDto(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não existe!");
        }
    }

    public AlimentoExibicaoDto buscarPorNome(String nome){
        Optional<Alimento> alimentoOptional = alimentoRepository.buscarPorNome(nome);

        if(alimentoOptional.isPresent()){
            return new AlimentoExibicaoDto(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }


    public List<AlimentoExibicaoDto> listarTodos(){
        return alimentoRepository
                .findAll()
                .stream()
                .map(AlimentoExibicaoDto::new)
                .toList();
    }

    public void excluir(Long id){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.findById(id);

        if (alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

    public AlimentoExibicaoDto atualizar(AlimentoCadastroDto alimentoDTO){
        Optional<Alimento> alimentoOptional =
                alimentoRepository.findById(alimentoDTO.alimentoId());

        if (alimentoOptional.isPresent()){
            Alimento alimento = new Alimento();
            BeanUtils.copyProperties(alimentoDTO, alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGorduras()
                    )
            );

            return new AlimentoExibicaoDto(alimentoRepository.save(alimento));
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

    public Double calcularCalorias(Double proteinas, Double carboidratos, Double gorduras){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gorduras * 9);
        return calorias;
    }

    public List<AlimentoExibicaoDto> buscarInicial(String letra, Pageable paginacao){
        return alimentoRepository
                .buscarPorInicial(letra, paginacao) // Metodo que queremos implementar
                .stream()// Este método converte a lista de Alimento em um fluxo (stream) de elementos, permitindo operações adicionais em cada elemento da lista.
                .map(AlimentoExibicaoDto::new)// Mapeamos cada objeto Alimento para o AlimentoExibicaoDto, o AlimentoExibicaoDto::new é um referencia do construtor e efetiva uma instancia para cada objeto Alimento na lista
                .toList();// Converte todo o fluxo de elementos em uma lista, contendo os objetos AlimentoExibicaoDto

//        Este padrão de uso de stream, map e toList é uma forma comum em Java moderno para processar e transformar coleções de elementos de forma eficiente e concisa.
    }

    public List<AlimentoExibicaoDto> buscarPorQtdGordurasInferior(Double valor, Pageable paginacao){
        return alimentoRepository
                .buscarPorQtdGordurasInferior(valor, paginacao)
                .stream()
                .map(AlimentoExibicaoDto::new)
                .toList();
    }
}