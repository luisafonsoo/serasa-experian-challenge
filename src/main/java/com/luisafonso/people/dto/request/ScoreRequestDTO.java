package com.luisafonso.people.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ScoreRequestDTO {

    @NotBlank(message = "Score não deve ser vazio ")
    private String scoreDescricao;

    @Min(0)
    @Max(1000)
    private Integer inicial;

    @Min(0)
    @Max(1000)
    @JsonProperty("final")
    private Integer finall;

}
