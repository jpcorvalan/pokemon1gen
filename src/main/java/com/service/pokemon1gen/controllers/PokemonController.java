/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.controllers;

import com.service.pokemon1gen.models.Pokemon;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.pokemon1gen.services.IPokemonService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.hibernate.TransientObjectException;
import org.postgresql.util.PSQLException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author juan9
 */
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private IPokemonService pokemonService;

    @GetMapping("/all")
    public List<Pokemon> listPokemon() {
        return pokemonService.getAllPokemon();
    }

    @GetMapping(path = "/{pokemonId}")
    public ResponseEntity<Pokemon> getOnePokemon(@PathVariable("pokemonId") long id) {
        return new ResponseEntity<>(pokemonService.getOnePokemon(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Pokemon> savePokemon(@Valid @RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(this.pokemonService.savePokemon(pokemon), HttpStatus.CREATED);
    }
    
    /* Exceptions Handlers */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map> handleArgumentNotValid(MethodArgumentNotValidException exception) {
        HashMap<String, String> mappedErrors = new HashMap<>();

        for (FieldError validation : exception.getFieldErrors()) {
            mappedErrors.put(validation.getField(), validation.getDefaultMessage());
        }

        return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<Map> handleInvalidFK(PSQLException exception) {
        HashMap<String, String> mappedErrors = new HashMap<>();

        mappedErrors.put("error", "The type id doesn't exists");

        return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransientObjectException.class)
    public ResponseEntity<Map> handleMalformedJson(TransientObjectException exception) {
        HashMap<String, String> mappedErrors = new HashMap<>();

        mappedErrors.put("error", "Invalid type data");

        return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
    }

}
