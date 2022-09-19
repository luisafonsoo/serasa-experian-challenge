package com.luisafonso.people.dto.response;

import java.util.List;

import com.luisafonso.people.model.Pessoa;
import com.luisafonso.people.model.Score;

import lombok.Data;

@Data
public class PessoaResponseDTO {

    private String nome;

    private String telefone;

    private Integer idade;

    private String scoreDescricao;

    private List<String> estados;

    public PessoaResponseDTO(Pessoa pessoa, String score, List<String> estados) {
        this.nome = pessoa.getNome();
        this.telefone = pessoa.getTelefone();
        this.idade = pessoa.getIdade();
        this.scoreDescricao = score;
        this.estados = estados;

    }

}
