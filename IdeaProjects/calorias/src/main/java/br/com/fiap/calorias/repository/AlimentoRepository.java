package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Alimento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a WHERE a.nome = :nome")
    Optional<Alimento> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT a FROM Alimento a WHERE nome LIKE CONCAT(:letra, '%')")
    List<Alimento> buscarPorInicial(@Param("letra") String letra, Pageable paginacao);

    @Query("SELECT a FROM Alimento a WHERE quantidadeGorduras <= :valor")
    List<Alimento> buscarPorQtdGordurasInferior(@Param("valor") Double valor, Pageable paginacao);
}
