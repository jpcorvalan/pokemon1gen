/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.controllers;

import com.service.pokemon1gen.models.Type;
import com.service.pokemon1gen.services.ITypeService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author juan9
 */
@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/all")
    public List<Type> listTypes() {
        return this.typeService.getAllTypes();
    }
    
    @GetMapping("/{idType}")
    public ResponseEntity<Object> getType(@PathVariable("idType") int id) {
        return new ResponseEntity<>(typeService.getType(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveType(@RequestBody Type type) {
        return new ResponseEntity<>(this.typeService.saveType(type), HttpStatus.CREATED);
    }
    
    @PostMapping("/saveMany")
    public ResponseEntity<Object> saveManyTypes(@RequestBody Set<Type> types) {
        return new ResponseEntity<>(this.typeService.saveTypes(types), HttpStatus.CREATED);
    }

}
