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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    @GetMapping
    public ResponseEntity<?> findPokemonByName(@RequestParam(required = false) String name) {
        if (name.isBlank() || name == null) {
            return new ResponseEntity<>("The pokémon specified doesn't exists", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pokemonService.findPokemonByName(name).orElse(null), HttpStatus.OK);
    }

    @GetMapping(path = "/getAndSave/{pokemonId}")
    public ResponseEntity<Pokemon> getPokemonAndSave(@PathVariable("pokemonId") long id) {
        return new ResponseEntity<>(pokemonService.getPokemonAndSaveIfNotExists(id), HttpStatus.OK);
    }

    @PostMapping(path = "/fillDatabaseWith/{quantity}")
    public ResponseEntity<?> fillDatabase(@PathVariable("quantity") int quantity) {

        if (quantity > 1 && quantity <= 151) {

            List<Pokemon> pokemonAdded = new ArrayList<>();

            for (int i = 1; i <= quantity; i++) {
                pokemonAdded.add(getPokemonAndSave(i).getBody());
            }

            return new ResponseEntity<>(pokemonAdded, HttpStatus.OK);
        }

        Map<String, String> badNumberError = new HashMap<>();

        badNumberError.put("error", "Insert a number between 1 and 150 inclusive");

        return new ResponseEntity<>(badNumberError, HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/{pokemonId}")
    public ResponseEntity<Pokemon> getOnePokemon(@PathVariable("pokemonId") long id) {
        return new ResponseEntity<>(pokemonService.getPokemonAndSaveIfNotExists(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Pokemon> savePokemon(@Valid @RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(this.pokemonService.savePokemon(pokemon), HttpStatus.CREATED);
    }

    @PostMapping("/saveMany")
    public ResponseEntity<List<Pokemon>> saveManyPokemon(@RequestBody List<Pokemon> manyPokemon) {
        manyPokemon.forEach((pokemon) -> {
            this.savePokemon(pokemon);
        });

        return new ResponseEntity<>(manyPokemon, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{pokemonId}")
    public ResponseEntity<Pokemon> deleteOnePokemonById(@PathVariable("pokemonId") long id) {
        return new ResponseEntity<>(this.pokemonService.deletePokemon(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPokemon() {
        this.pokemonService.deleteAllPokemon();
        Map<String, String> response = new HashMap<>();

        response.put("info", "All pokémon succesfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
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
