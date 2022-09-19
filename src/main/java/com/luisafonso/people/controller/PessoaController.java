package com.luisafonso.people.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.luisafonso.people.dto.request.PessoaRequestDTO;
import com.luisafonso.people.dto.response.PessoaResponseDTO;
import com.luisafonso.people.dto.response.PessoasListResponseDTO;
import com.luisafonso.people.model.Pessoa;
import com.luisafonso.people.service.PessoaService;

@RestController
@RequestMapping(value = "api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> savePessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO) {
        try {
            return new ResponseEntity<>(pessoaService.savePessoa(pessoaRequestDTO), HttpStatus.CREATED);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar pessoa", exc);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaResponseDTO> getPessoa(@PathVariable Integer id) {
        try {
            PessoaResponseDTO response = pessoaService.getPessoaById(id);
            if (response == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar pessoa", exc);
        }
    }

    @GetMapping
    public ResponseEntity<List<PessoasListResponseDTO>> getAll() {
        try {
            List<PessoasListResponseDTO> response = pessoaService.getAllPessoas();
            if (response == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar pessoa", exc);
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
