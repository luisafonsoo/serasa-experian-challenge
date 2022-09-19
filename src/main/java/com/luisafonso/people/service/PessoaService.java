package com.luisafonso.people.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisafonso.people.dto.request.PessoaRequestDTO;
import com.luisafonso.people.dto.response.PessoaResponseDTO;
import com.luisafonso.people.dto.response.PessoasListResponseDTO;
import com.luisafonso.people.model.Pessoa;
import com.luisafonso.people.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private AfinidadeService afinidadeService;

    public Pessoa savePessoa(PessoaRequestDTO pessoaRequestDTO) {
        return pessoaRepository.save(new Pessoa(pessoaRequestDTO));
    }

    public PessoaResponseDTO getPessoaById(Integer id) throws Exception {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (!pessoa.isPresent())
            return null;

        return new PessoaResponseDTO(pessoa.get(), scoreService.getScoreDescricaoByValue(pessoa.get().getScore()),
                afinidadeService.getEstadosByRegiao(pessoa.get().getRegiao()));

    }

    public List<PessoasListResponseDTO> getAllPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        if (pessoas.isEmpty())
            return null;

        List<PessoasListResponseDTO> response = pessoas.stream().map(
                pessoa -> new PessoasListResponseDTO(pessoa,
                        scoreService.getScoreDescricaoByValue(pessoa.getScore()),
                        afinidadeService.getEstadosByRegiao(pessoa.getRegiao())))
                .collect(Collectors.toList());

        return response;
    }

}
