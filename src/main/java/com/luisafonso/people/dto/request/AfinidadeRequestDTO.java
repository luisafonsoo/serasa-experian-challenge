package com.luisafonso.people.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AfinidadeRequestDTO {

    @NotBlank(message = "Região não deve ser vazia")
    private String regiao;

    @NotEmpty(message = "Estados não devem ser vazios")
    private List<String> estados;

}
