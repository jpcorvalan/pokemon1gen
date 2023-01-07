/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.controllers;

import com.service.pokemon1gen.models.Pokemon;
import com.service.pokemon1gen.services.PokemonService;
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

/**
 *
 * @author juan9
 */
@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    
    @Autowired
    private PokemonService pokemonService;
    
    @GetMapping("/all")
    public List<Pokemon> listPokemon() {
        return pokemonService.getAllPokemon();
    }
    
    @GetMapping(path = "/{pokemonId}")
    public ResponseEntity<Object> getOnePokemon(@PathVariable("pokemonId") long id) {
        return new ResponseEntity<>(pokemonService.getOnePokemon(id), HttpStatus.OK);
    }
    
    @PostMapping("/save")
    public ResponseEntity<Object> savePokemon(@RequestBody Pokemon pokemon) {
        return new ResponseEntity<>(this.pokemonService.savePokemon(pokemon), HttpStatus.CREATED);
    }
    
}
