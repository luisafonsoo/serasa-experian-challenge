package com.luisafonso.people.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class AfinidadeResponseDTO {

    private String regiao;

    private List<String> estados;
}
