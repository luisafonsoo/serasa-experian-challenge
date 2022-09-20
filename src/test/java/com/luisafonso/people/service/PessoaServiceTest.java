package com.luisafonso.people.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luisafonso.people.dto.request.PessoaRequestDTO;
import com.luisafonso.people.dto.response.PessoaResponseDTO;
import com.luisafonso.people.dto.response.PessoasListResponseDTO;
import com.luisafonso.people.model.Pessoa;
import com.luisafonso.people.repository.PessoaRepository;
import com.luisafonso.people.service.AfinidadeService;
import com.luisafonso.people.service.PessoaService;
import com.luisafonso.people.service.ScoreService;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ScoreService scoreService;

    @Mock
    private AfinidadeService afinidadeService;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    private PessoaRequestDTO pessoaRequestDTO;

    @BeforeEach
    public void setup() {
        pessoa = Pessoa.builder().id(1).nome("Fulano de Tal").telefone("99 99999-9999").idade(99).estado("XX")
                .score(1000)
                .cidade("Cidade de Fulano").build();
        pessoaRequestDTO = PessoaRequestDTO.builder().nome("Fulano de Tal").telefone("99 99999-9999").idade(99)
                .estado("XX").score(1000)
                .cidade("Cidade de Fulano").build();
    }

    @DisplayName("JUnit test for savePessoa method")
    @Test
    public void givenPessoaObject_whenSavePessoa_thenReturnNotNull() {

        when(pessoaRepository.save(any())).thenReturn(pessoa);

        Pessoa rPessoa = pessoaService.savePessoa(pessoaRequestDTO);

        assertNotNull(rPessoa);

    }

    @DisplayName("JUnit test for getPessoaById method")
    @Test
    public void givenId_whenGetPessoaById_thenReturnNull() {

        try {
            when(pessoaRepository.findById(anyInt())).thenReturn(Optional.empty());

            PessoaResponseDTO rPessoa = pessoaService.getPessoaById(1);

            assertNull(rPessoa);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DisplayName("JUnit test for getPessoaById method")
    @Test
    public void givenId_whenGetPessoaById_thenReturnNotNull() {

        try {
            when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

            when(scoreService.getScoreDescricaoByValue(anyInt())).thenReturn("Aceitavel");

            lenient().when(afinidadeService.getEstadosByRegiao(anyString())).thenReturn(List.of("SP", "RJ"));

            PessoaResponseDTO rPessoa = pessoaService.getPessoaById(1);

            assertNotNull(rPessoa);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DisplayName("JUnit test for getAllPessoas method")
    @Test
    public void given_whenGetAllPessoas_thenReturnNull() {

        try {

            List<Pessoa> pessoas = new ArrayList<>();

            when(pessoaRepository.findAll()).thenReturn(pessoas);

            List<PessoasListResponseDTO> rPessoa = pessoaService.getAllPessoas();

            assertNull(rPessoa);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DisplayName("JUnit test for getAllPessoas method")
    @Test
    public void given_whenGetAllPessoas_thenReturnNotNull() {

        try {

            List<Pessoa> pessoas = new ArrayList<>();
            pessoas.add(pessoa);

            when(pessoaRepository.findAll()).thenReturn(pessoas);

            when(scoreService.getScoreDescricaoByValue(anyInt())).thenReturn("Aceitavel");

            lenient().when(afinidadeService.getEstadosByRegiao(anyString())).thenReturn(List.of("SP", "RJ"));

            List<PessoasListResponseDTO> rPessoa = pessoaService.getAllPessoas();

            assertNotNull(rPessoa);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
