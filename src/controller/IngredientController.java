package controller;

import model.Ingredient;
import service.IngredientService;

import java.util.List;

public class IngredientController {
    private final IngredientService ingredientService = new IngredientService();

    public void create(String name, String stringPrice) {
        if (stringPrice == null) {
            System.out.println("Нету такого ингредиента");
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Ingredient ingredient = new Ingredient(name, price);
        if(!ingredientService.create(ingredient)){
            System.out.println("Некорректные данные");
        }
    }

    public void update(String name, String newName, String stringPrice) {
        if (stringPrice == null) {
            System.out.println("Нету такого ингредиента");
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Ingredient ingredient = new Ingredient(newName, price);
        if(!ingredientService.update(name, ingredient)){
            System.out.println("Некорректные данные");
        }
    }

    public void delete(String name) {
        ingredientService.delete(name);
    }

    public Ingredient getByName(String name){
        return ingredientService.getByName(name);
    }

    public List<String> getAllNames() {
        return ingredientService.getAllNames();
    }
}
