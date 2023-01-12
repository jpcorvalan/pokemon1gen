/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.repositories;

import com.service.pokemon1gen.models.Pokemon;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author juan9
 */
@Repository
public interface PokemonRepository extends PagingAndSortingRepository<Pokemon, Long> {
    public abstract List<Pokemon> findByName(String name);
}
