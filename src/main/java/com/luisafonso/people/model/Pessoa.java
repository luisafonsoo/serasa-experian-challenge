package com.luisafonso.people.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.luisafonso.people.dto.request.PessoaRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dataInclusao;

    private String nome;

    private String telefone;

    private Integer idade;

    private Integer score;

    private String cidade;

    private String estado;

    private String regiao;

    public Pessoa(PessoaRequestDTO pessoaRequestDTO) {
        this.nome = pessoaRequestDTO.getNome();
        this.telefone = pessoaRequestDTO.getTelefone();
        this.idade = pessoaRequestDTO.getIdade();
        this.cidade = pessoaRequestDTO.getCidade();
        this.estado = pessoaRequestDTO.getEstado();
        this.score = pessoaRequestDTO.getScore();
        this.regiao = pessoaRequestDTO.getRegiao();
        this.dataInclusao = new Date();
    }

}
