/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.repositories;

import org.springframework.stereotype.Repository;
import com.service.pokemon1gen.models.Type;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author juan9
 */
@Repository
public interface TypeRepository extends PagingAndSortingRepository<Type, Integer> {

    public abstract Optional<Type> findByName(String name);

}
