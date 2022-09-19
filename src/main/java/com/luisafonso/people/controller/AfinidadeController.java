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

import com.luisafonso.people.dto.request.AfinidadeRequestDTO;
import com.luisafonso.people.dto.response.AfinidadeResponseDTO;
import com.luisafonso.people.exception.AlreadyExistsException;
import com.luisafonso.people.service.AfinidadeService;

@RestController
@RequestMapping(value = "api/afinidade")
public class AfinidadeController {

    @Autowired
    private AfinidadeService afinidadeService;

    @PostMapping
    public ResponseEntity<AfinidadeResponseDTO> saveAfinidade(
            @Valid @RequestBody AfinidadeRequestDTO afinidadeRequestDTO) {
        try {
            return new ResponseEntity<>(afinidadeService.saveAfinidades(afinidadeRequestDTO), HttpStatus.CREATED);
        } catch (AlreadyExistsException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Afinidade já cadastradada", ex);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar afinidade", exc);
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
