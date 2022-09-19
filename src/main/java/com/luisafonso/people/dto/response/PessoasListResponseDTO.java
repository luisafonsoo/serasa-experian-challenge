package com.luisafonso.people.dto.response;

import java.util.List;

import com.luisafonso.people.model.Pessoa;
import com.luisafonso.people.model.Score;

import lombok.Data;

@Data
public class PessoasListResponseDTO {

    private String nome;

    private String cidade;

    private String estado;

    private String scoreDescricao;

    private List<String> estados;

    public PessoasListResponseDTO(Pessoa pessoa, String score, List<String> estados) {
        this.nome = pessoa.getNome();
        this.cidade = pessoa.getCidade();
        this.estado = pessoa.getEstado();
        this.scoreDescricao = score;
        this.estados = estados;
    }

}
