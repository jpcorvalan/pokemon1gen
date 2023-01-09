/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.services;

import com.service.pokemon1gen.models.Type;
import com.service.pokemon1gen.repositories.TypeRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juan9
 */
@Service
public class TypeServiceImpl implements ITypeService{
    
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getAllTypes() {
        return (List<Type>) this.typeRepository.findAll();
    }

    @Override
    public Optional<Type> getType(int id) {
        return this.typeRepository.findById(id);
    }

    @Override
    public Type saveType(Type type) {
        return this.typeRepository.save(type);
    }

    @Override
    public Type deleteType(int id) {
        Optional<Type> optType = this.typeRepository.findById(id);
        
        if(optType.isPresent()) {
            Type typeToDelete = optType.get();
            this.typeRepository.deleteById(id);
            
            return typeToDelete;            
        }
        
        return null;
    }

    @Override
    public Set<Type> saveTypes(Set<Type> types) {
        types.forEach((item) -> this.typeRepository.save(item));
        
        return types;
    }
        
}
