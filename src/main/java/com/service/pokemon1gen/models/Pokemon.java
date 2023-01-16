/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.models;

import com.service.pokemon1gen.helperClasses.validators.PokemonDataValidator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author juan9
 */
@Entity
@Getter
@Setter
@Table(name = "pokemon")
public class Pokemon {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = PokemonDataValidator.NAME)
    @Column(unique = true, nullable = false)
    private String name;

    @Positive(message = PokemonDataValidator.WEIGHT)
    @Column(nullable = false)
    private Double weight;

//    @NotNull(message = PokemonDataValidator.TYPES)
    @Column(nullable = false)
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    private List<Type> types;

    @NotEmpty(message = PokemonDataValidator.POKEDEX)
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String pokedexShortDescription;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Double weight, List<Type> types, String pokedexShortDescription) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.types = types;
        this.pokedexShortDescription = pokedexShortDescription;
    }

}
