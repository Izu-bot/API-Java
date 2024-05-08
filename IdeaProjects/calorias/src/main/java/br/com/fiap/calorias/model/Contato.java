package br.com.fiap.calorias.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_contatos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Contato {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contatos_seq"
    )
    @SequenceGenerator(
            name = "contatos_seq",
            sequenceName = "contatos_seq",
            allocationSize = 1 // Cache local com até 1
    )
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}
