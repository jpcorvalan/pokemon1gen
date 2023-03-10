/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.suppliers;

import com.service.pokemon1gen.models.Pokemon;
import java.util.function.Supplier;

/**
 *
 * @author juan9
 */
public class MissignoSupplier implements Supplier<Pokemon> {

    @Override
    public Pokemon get() {        
        Pokemon missigno = new Pokemon(Long.MIN_VALUE, "MissingNo", Double.NaN, null, "The ID of Pokémon you inserted doesn't exists");
                
        return missigno;
    }

}
