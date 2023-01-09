/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.services;

import com.service.pokemon1gen.models.Pokemon;
import com.service.pokemon1gen.repositories.PokemonRepository;
import com.service.pokemon1gen.suppliers.MissignoSupplier;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan9
 */
@Service
public class PokemonServiceImpl implements IPokemonService{
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAllPokemon() {
        return (List<Pokemon>) pokemonRepository.findAll();
    }

    @Override
    public Pokemon getOnePokemon(Long id) {
        return pokemonRepository.findById(id).orElseGet(new MissignoSupplier());
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon deletePokemon(Long id) {
        Optional<Pokemon> optPokemon = pokemonRepository.findById(id);
        
        if(optPokemon.isPresent()) {
            Pokemon pokemonToDelete = optPokemon.get();
            pokemonRepository.deleteById(pokemonToDelete.getId());
            
            return pokemonToDelete;
        }
        
        return optPokemon.orElseGet(new MissignoSupplier());
    }
    
}
