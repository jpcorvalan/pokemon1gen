/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.services;

import com.service.pokemon1gen.models.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author juan9
 */
public interface ITypeService {
    public List<Type> getAllTypes();
    public Optional<Type> getType(int id);
    public Type saveType(Type type);
    public Set<Type> saveTypes(Set<Type> types);
    public Type deleteType(int id);
}
