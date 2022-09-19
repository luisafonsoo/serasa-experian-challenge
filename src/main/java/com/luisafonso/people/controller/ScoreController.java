package com.luisafonso.people.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.luisafonso.people.dto.request.ScoreRequestDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.model.Score;
import com.luisafonso.people.service.ScoreService;

@RestController
@RequestMapping(value = "api/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public ResponseEntity<Score> saveScore(
            @Valid @RequestBody ScoreRequestDTO scoreRequestDTO) {
        try {
            return new ResponseEntity<>(scoreService.saveScore(scoreRequestDTO), HttpStatus.CREATED);
        } catch (AlreadyExistsException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Score já cadastrado", ex);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, exc.getMessage(), exc);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
