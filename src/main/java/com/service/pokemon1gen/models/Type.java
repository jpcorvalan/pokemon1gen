/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author juan9
 */
@Entity
@Table(name = "types")
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "types")
    @JsonIgnore
    private List<Pokemon> pokemon;

    public Type() {
    }

    public Type(Integer id, String name, List<Pokemon> pokemon) {
        this.id = id;
        this.name = name;
        this.pokemon = pokemon;
    }

}
