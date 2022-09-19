package com.luisafonso.people.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisafonso.people.dto.request.ScoreRequestDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.model.Score;
import com.luisafonso.people.repository.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score saveScore(ScoreRequestDTO scoreRequestDTO) throws Exception {
        if (scoreRepository.findById(scoreRequestDTO.getScoreDescricao()).isPresent())
            throw new AlreadyExistsException("Score já cadastrado");
        return scoreRepository.save(new Score(scoreRequestDTO));
    }

    public String getScoreDescricaoByValue(Integer scoreValue) {

        Optional<Score> score = scoreRepository.findByDescricao(scoreValue);

        if (!score.isPresent())
            return null;

        return score.get().getDescricao();
    }

}
