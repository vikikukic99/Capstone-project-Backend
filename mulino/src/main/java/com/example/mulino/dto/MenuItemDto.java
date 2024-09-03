package com.example.mulino.dto;

import com.example.mulino.model.Ingredient;
import com.example.mulino.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuItemDto {
    private Long id;
    private String name;
    private String picture;
    private int price;
    private double rate;
    private int prep;
    private int cook;
    private String description;
    private List<String> ingredients;

    public MenuItemDto(MenuItem menuItem){
        id = menuItem.getId();
        name = menuItem.getName();
        picture = menuItem.getPicture();
        price = menuItem.getPrice();
        rate = menuItem.getRate();
        prep = menuItem.getPrep();
        cook = menuItem.getCook();;
        description = menuItem.getDescription();
        ingredients = new ArrayList<String>();

        for(Ingredient ingredient : menuItem.getIngredients()){
            ingredients.add(ingredient.getName());
        }

    }
}
