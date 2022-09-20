package com.luisafonso.people.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luisafonso.people.dto.request.AfinidadeRequestDTO;
import com.luisafonso.people.dto.response.AfinidadeResponseDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.model.Afinidade;
import com.luisafonso.people.repository.AfinidadeRepository;
import com.luisafonso.people.service.AfinidadeService;

@ExtendWith(MockitoExtension.class)
public class AfinidadeServiceTest {

    @Mock
    private AfinidadeRepository afinidadeRepository;

    @InjectMocks
    private AfinidadeService afinidadeService;

    private AfinidadeRequestDTO afinidadeRequestDTO;

    @BeforeEach
    public void setup() {
        afinidadeRequestDTO = AfinidadeRequestDTO.builder()
                .regiao("sudeste")
                .estados(List.of("SP", "RJ"))
                .build();
    }

    @DisplayName("JUnit test for saveAfinidade method")
    @Test
    public void givenAfinidadeObject_whenSaveAfinidade_thenThrownException() {
        List<Afinidade> afinidades = new ArrayList<>();
        afinidades.add(new Afinidade("sudeste", "SP"));
        when(afinidadeRepository.findByRegiao(afinidadeRequestDTO.getRegiao()))
                .thenReturn(afinidades);

        Throwable exception = assertThrows(AlreadyExistsException.class,
                () -> afinidadeService.saveAfinidades(afinidadeRequestDTO));
        assertEquals("Afinidade já cadastrada", exception.getMessage());

    }

    @DisplayName("JUnit test for saveAfinidade method")
    @Test
    public void givenAfinidadeObject_whenSaveAfinidade_thenSave() {
        try {
            List<Afinidade> afinidades = new ArrayList<>();

            when(afinidadeRepository.findByRegiao(afinidadeRequestDTO.getRegiao()))
                    .thenReturn(afinidades);

            Afinidade saved = new Afinidade("sudeste", "SP");

            when(afinidadeRepository.save(Mockito.any()))
                    .thenReturn(saved);

            AfinidadeResponseDTO response = afinidadeService.saveAfinidades(afinidadeRequestDTO);

            assertNotNull(response);

        } catch (Exception e) {
        }

    }

    @DisplayName("JUnit test for getEstadosByRegiao method")
    @Test
    public void givenAfinidadeObject_whenGetEstadosByRegiao_thenReturnNotNull() {
        try {

            List<Afinidade> afinidades = List.of(new Afinidade("sudeste", "SP"), new Afinidade("sudeste", "RJ"));

            when(afinidadeRepository.findByRegiao(anyString()))
                    .thenReturn(afinidades);

            List<String> response = afinidadeService.getEstadosByRegiao(anyString());

            assertNotNull(response);

        } catch (Exception e) {
        }
    }

    @DisplayName("JUnit test for saveAfinidade method")
    @Test
    public void givenAfinidadeObject_whenGetEstadosByRegiao_thenReturnNull() {
        try {

            List<Afinidade> afinidades = new ArrayList<>();

            when(afinidadeRepository.findByRegiao(anyString()))
                    .thenReturn(afinidades);

            List<String> response = afinidadeService.getEstadosByRegiao(anyString());

            assertNull(response);

        } catch (Exception e) {
        }

    }

}
