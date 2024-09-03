package com.example.mulino.service;

import com.example.mulino.model.Ingredient;
import com.example.mulino.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository ;
    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }


}

