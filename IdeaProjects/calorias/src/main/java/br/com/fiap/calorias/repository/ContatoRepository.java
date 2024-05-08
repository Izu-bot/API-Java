package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Contato;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("SELECT c FROM Contato c WHERE c.nome = :nome ")
    Optional<Contato> bucarContatoPeloNome(@Param("nome") String nome);

    Optional<Contato> findByEmail(String email);

    @Query("SELECT c FROM Contato c WHERE c.dataNascimento BETWEEN :dataInicio AND :dataFim")
    List<Contato> listarAniversariantes(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, Pageable paginacao);

    //substring(c.email, locate('@', c.email) + 1) retorna uma parte de uma string após um determinado caractere.
    @Query("SELECT c FROM Contato c WHERE substring(c.email, locate('@', c.email) + 1) IN :dominio")
    List<Contato> buscarDominioEmail(@Param("dominio") String dominio, Pageable paginacao);
}
