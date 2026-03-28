package admin.controller;

import exception.NotFoundException;
import exception.ValidationException;
import admin.model.Pizza;
import admin.service.DoughService;
import admin.service.IngredientService;
import admin.service.PizzaService;

import java.util.List;

public class PizzaController {
    private final PizzaService pizzaService = new PizzaService();

    private final DoughService doughService = new DoughService();

    private final IngredientService ingredientService = new IngredientService();

    public void create(String name, String doughName, String ingredients) throws ValidationException, NotFoundException {
        pizzaService.create(name, doughName, ingredients);
    }

    public void update(String name, String newName, String doughName, String ingredients) throws ValidationException, NotFoundException {
        pizzaService.update(name, doughName, ingredients, newName);
    }

    public void delete(String name) throws NotFoundException {
        pizzaService.delete(name);
    }

    public Pizza getByName(String name) throws NotFoundException {
        return pizzaService.getByName(name);
    }

    public List<String> getAllNames() {
        return pizzaService.getAllNames();
    }
}
