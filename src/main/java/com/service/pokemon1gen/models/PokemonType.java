/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author juan9
 */
@Entity
@Table(name = "pokemon_types")
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "pokemon_id")
    private Long idPokemon;

    @Column(name = "types_id")
    private Integer idType;

    public PokemonType() {
    }

    public PokemonType(Long id, Long idPokemon, Integer idType) {
        this.id = id;
        this.idPokemon = idPokemon;
        this.idType = idType;
    }

}
