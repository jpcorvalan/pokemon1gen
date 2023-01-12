/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.services;

import com.service.pokemon1gen.helperClasses.pokemonDataOne.PokemonDataOne;
import com.service.pokemon1gen.helperClasses.pokemonDataTwo.PokemonDataTwo;
import com.service.pokemon1gen.helperClasses.StringHelper;
import com.service.pokemon1gen.models.Pokemon;
import com.service.pokemon1gen.models.Type;
import com.service.pokemon1gen.repositories.PokemonRepository;
import com.service.pokemon1gen.repositories.TypeRepository;
import com.service.pokemon1gen.suppliers.MissignoSupplier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author juan9
 */
@Service
public class PokemonServiceImpl implements IPokemonService {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonServiceImpl.class);

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private RestTemplate restClient;

    @Override
    public List<Pokemon> getAllPokemon() {
        return (List<Pokemon>) pokemonRepository.findAll();
    }

    @Override
    public Pokemon getOnePokemon(Long id) {
        return pokemonRepository.findById(id).orElseGet(new MissignoSupplier());
    }

    @Override
    public Pokemon getPokemonAndSaveIfNotExists(long id) {
        Optional<Pokemon> pkmnFinded = this.pokemonRepository.findById(id);

        if (pkmnFinded.isPresent()) {
            return pkmnFinded.get();
        }

        PokemonDataOne pokemonData1 = null;
        PokemonDataTwo pokemonData2 = null;

        try {
            pokemonData1 = restClient.getForObject("https://pokeapi.co/api/v2/pokemon-species/" + id, PokemonDataOne.class);
            pokemonData2 = restClient.getForObject("https://pokeapi.co/api/v2/pokemon/" + id, PokemonDataTwo.class);
        } catch (Exception e) {
            return new MissignoSupplier().get();
        }

        String pokedexDescription = pokemonData1.getFlavorTextEntries().get(0).getFlavorText();

        List<Type> types = new ArrayList<>();

        String typeNameUpper = StringHelper.upperFirstLetter(pokemonData2.getTypes().get(0).getType().getName());

        Optional<Type> findedType = typeRepository.findByName(typeNameUpper);

        if (findedType.isPresent()) {
            types.add(findedType.get());
        }

        Pokemon pokemonToSave = new Pokemon(id, StringHelper.upperFirstLetter(pokemonData1.getName()), pokemonData2.getWeight().doubleValue(), types, pokedexDescription);

        return pokemonRepository.save(pokemonToSave);
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon deletePokemon(Long id) {
        Optional<Pokemon> optPokemon = pokemonRepository.findById(id);

        if (optPokemon.isPresent()) {
            Pokemon pokemonToDelete = optPokemon.get();
            pokemonRepository.deleteById(pokemonToDelete.getId());

            return pokemonToDelete;
        }

        return optPokemon.orElseGet(new MissignoSupplier());
    }
    
    

    @Override
    public List<Pokemon> findPokemonByName(String name) {
        return pokemonRepository.findByName(name);
    }

    @Override
    public void deleteAllPokemon() {
        this.pokemonRepository.deleteAll();
    }

}
