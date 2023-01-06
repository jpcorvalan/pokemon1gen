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
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author juan9
 */
@Entity
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double weight;

    @Column(length = 2, nullable = false)
    private String[] types;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String pokedexShortDescription;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Double weight, String[] types, String pokedexShortDescription) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.types = types;
        this.pokedexShortDescription = pokedexShortDescription;
    }

}
