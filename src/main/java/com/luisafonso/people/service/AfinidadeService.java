package com.luisafonso.people.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisafonso.people.dto.request.AfinidadeRequestDTO;
import com.luisafonso.people.dto.response.AfinidadeResponseDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.repository.AfinidadeRepository;
import com.luisafonso.people.model.Afinidade;

@Service
public class AfinidadeService {

    @Autowired
    private AfinidadeRepository afinidadeRepository;

    public AfinidadeResponseDTO saveAfinidades(AfinidadeRequestDTO afinidadeRequestDTO) throws AlreadyExistsException {
        AfinidadeResponseDTO response = new AfinidadeResponseDTO();

        if (afinidadeRepository.findByRegiao(afinidadeRequestDTO.getRegiao()).isEmpty())
            throw new AlreadyExistsException("Afinidade já cadastrada");

        List<String> estados = new ArrayList<>();

        afinidadeRequestDTO.getEstados().forEach(estado -> {
            Afinidade saved = afinidadeRepository.save(new Afinidade(afinidadeRequestDTO.getRegiao(), estado));
            estados.add(saved.getEstado());
        });

        response.setRegiao(afinidadeRequestDTO.getRegiao());

        response.setEstados(estados);

        return response;
    }

    public List<String> getEstadosByRegiao(String regiao) {
        List<Afinidade> afinidade = afinidadeRepository.findByRegiao(regiao);

        if (afinidade.isEmpty())
            return null;

        List<String> estados = new ArrayList<>();

        afinidade.forEach(estado -> {
            estados.add(estado.getEstado());
        });

        return estados;
    }

}
