package controller;

import model.Ingredient;
import service.IngredientService;

import java.util.List;

public class IngredientController {
    private final IngredientService ingredientService = new IngredientService();

    public void create(String name, String stringPrice) {
        if (stringPrice == null) {
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Ingredient ingredient = new Ingredient(name, price);
        ingredientService.create(ingredient);
    }

    public void update(String name, String newName, String stringPrice) {
        if (stringPrice == null) {
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Ingredient ingredient = new Ingredient(newName, price);
        ingredientService.update(name, ingredient);
    }

    public void delete(String name) {
        ingredientService.delete(name);
    }

    public List<String> getAllNames() {
        return ingredientService.getAllNames();
    }
}
