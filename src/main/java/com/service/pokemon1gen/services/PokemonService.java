/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.pokemon1gen.services;

import com.service.pokemon1gen.models.Pokemon;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juan9
 */
public interface PokemonService {
    public List<Pokemon> getAllPokemon();
    public Pokemon getOnePokemon(Long id);
    public Pokemon savePokemon(Pokemon pokemon);
    public Pokemon deletePokemon(Long id);
}
