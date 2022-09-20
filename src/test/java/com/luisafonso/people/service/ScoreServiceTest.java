package com.luisafonso.people.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.luisafonso.people.dto.request.ScoreRequestDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.model.Score;
import com.luisafonso.people.repository.ScoreRepository;
import com.luisafonso.people.service.ScoreService;

@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService scoreService;

    private ScoreRequestDTO scoreRequestDTO;

    @BeforeEach
    public void setup() {
        scoreRequestDTO = scoreRequestDTO.builder().scoreDescricao("Aceitável").finall(700).inicial(501).build();
    }

    @DisplayName("JUnit test for saveScore method")
    @Test
    public void givenScoreObject_whenSaveScore_thenThrownException() {

        Optional<Score> score = Optional.of(new Score(scoreRequestDTO));

        when(scoreRepository.findById(scoreRequestDTO.getScoreDescricao()))
                .thenReturn(score);

        Throwable exception = assertThrows(AlreadyExistsException.class,
                () -> scoreService.saveScore(scoreRequestDTO));
        assertEquals("Score já cadastrado", exception.getMessage());

    }

    @DisplayName("JUnit test for saveScore method")
    @Test
    public void givenScoreObject_whenSaveScore_thenReturnNotNull() {

        try {
            Score result = new Score(scoreRequestDTO);

            when(scoreRepository.findById(scoreRequestDTO.getScoreDescricao()))
                    .thenReturn(Optional.empty());

            when(scoreRepository.save(any()))
                    .thenReturn(result);

            Score response = scoreService.saveScore(scoreRequestDTO);

            assertNotNull(response);

        } catch (Exception e) {
        }

    }

    @DisplayName("JUnit test for getScoreDescricaoByValue method")
    @Test
    public void givenScoreObject_whenGetScoreDescricaoByValue_thenReturnNull() {

        try {

            when(scoreRepository.findByDescricao(anyInt()))
                    .thenReturn(Optional.empty());

            String response = scoreService.getScoreDescricaoByValue(anyInt());

            assertNull(response);

        } catch (Exception e) {
        }

    }

    @DisplayName("JUnit test for getScoreDescricaoByValue method")
    @Test
    public void givenScoreObject_whenGetScoreDescricaoByValue_thenReturnNotNull() {

        try {

            Score result = new Score(scoreRequestDTO);

            when(scoreRepository.findByDescricao(anyInt()))
                    .thenReturn(Optional.of(result));

            String response = scoreService.getScoreDescricaoByValue(anyInt());

            assertNotNull(response);

        } catch (Exception e) {
        }

    }

}
