/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.pokemon1gen.controllers;

import com.service.pokemon1gen.models.Type;
import com.service.pokemon1gen.services.ITypeService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ResponseEntity<?> getType(@PathVariable("idType") int id) {

        Optional<Type> findedType = typeService.getType(id);

        if (findedType.isPresent()) {
            return new ResponseEntity<>(findedType, HttpStatus.OK);
        }

        HashMap<String, String> mappedErrors = new HashMap<>();
        mappedErrors.put("error", "Type number " + id + " doesn't exists");

        return new ResponseEntity<>(mappedErrors, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Type> saveType(@RequestBody @Valid Type type) {
        return new ResponseEntity<>(this.typeService.saveType(type), HttpStatus.CREATED);
    }

    @PostMapping("/saveMany")
    public ResponseEntity<Map> saveManyTypes(@RequestBody Set<Type> types) {
        Set<Type> addedTypes = new HashSet<>();
        Set<String> errors = new HashSet<>();

        Map<String, Set> typesAndErrors = new HashMap<>();

        for (Type item : types) {
            try {
                this.saveType(item);
            } catch (Exception e) {
                errors.add(e.getMessage());
                continue;
            }
            addedTypes.add(item);
        }

        typesAndErrors.put("addedValues", addedTypes);
        typesAndErrors.put("errors", errors);

        return new ResponseEntity<>(typesAndErrors, HttpStatus.CREATED);
    }
    

    /* Exceptions handlers */
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map> handleArgumentNotValid(MethodArgumentNotValidException exception) {
        HashMap<String, String> mappedErrors = new HashMap<>();

        for (FieldError validation : exception.getFieldErrors()) {
            mappedErrors.put(validation.getField(), validation.getDefaultMessage());
        }

        return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map> handleSaveDuplicatedException(DataIntegrityViolationException exception) {
        HashMap<String, String> mappedErrors = new HashMap<>();

        mappedErrors.put("error", "This type already exists!");

        return new ResponseEntity<>(mappedErrors, HttpStatus.BAD_REQUEST);
    }

}
