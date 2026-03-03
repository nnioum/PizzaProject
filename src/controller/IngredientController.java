package controller;

import exception.NotFoundException;
import exception.ValidationException;
import model.Ingredient;
import service.IngredientService;

import java.util.List;

public class IngredientController {
    private final IngredientService ingredientService = new IngredientService();

    public Ingredient create(String name, String priceStr) throws ValidationException, NotFoundException {
        return ingredientService.create(name, priceStr);
    }

    public Ingredient update(String name, String newName, String priceStr) throws ValidationException, NotFoundException {
        return ingredientService.update(name, priceStr, newName);
    }

    public void delete(String name) throws NotFoundException {
        ingredientService.delete(name);
    }

    public Ingredient getByName(String name) throws NotFoundException {
        return ingredientService.getByName(name);
    }

    public List<String> getAllNames() {
        return ingredientService.getAllNames();
    }
}
