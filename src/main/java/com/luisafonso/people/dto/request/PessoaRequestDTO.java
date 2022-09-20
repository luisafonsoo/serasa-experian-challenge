package com.luisafonso.people.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class PessoaRequestDTO {

    @NotBlank(message = "Nome não deve ser vazio")
    private String nome;

    @NotBlank(message = "Telefone não deve ser vazio")
    private String telefone;

    @NotNull(message = "Idade não deve ser vazia")
    private Integer idade;

    @NotBlank(message = "Cidade não deve ser vazio")
    private String cidade;

    @NotBlank(message = "Estado não deve ser vazio")
    private String estado;

    @Max(1000)
    @Min(0)
    private Integer score;

    @NotBlank(message = "Região não deve ser vazia")
    private String regiao;

}
