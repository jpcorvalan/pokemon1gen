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
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan9
 */
@Service
public class PokemonServiceImpl implements PokemonService{
    
    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public List<Pokemon> getAllPokemon() {
        return (List<Pokemon>) pokemonRepository.findAll();
    }

    @Override
    public Optional<Pokemon> getOnePokemon(Long id) {
        Optional optPokemon = Optional.ofNullable(pokemonRepository.findById(id));
        
        return optPokemon.or(new MissignoSupplier());
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Optional<Pokemon> deletePokemon(Long id) {
        Optional optPokemon = Optional.ofNullable(pokemonRepository.findById(id));
        
        if(optPokemon.isPresent()) {
            Pokemon pokemonToDelete = (Pokemon) optPokemon.get();
            pokemonRepository.deleteById(pokemonToDelete.getId());
            
            return Optional.of(pokemonToDelete);
        }
        
        return optPokemon.or(new MissignoSupplier());
    }
    
}
