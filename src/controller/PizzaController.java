package controller;

import model.Dough;
import model.Ingredient;
import service.DoughService;
import service.IngredientService;
import service.PizzaService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PizzaController {
    private final PizzaService pizzaService = new PizzaService();

    private final DoughService doughService = new DoughService();

    private final IngredientService ingredientService = new IngredientService();

    public void create(String name, String doughName, String ingredients) {
        if (ingredients == null) {
            return;
        }
        String[] listIngredients = ingredients.split(",");
        Dough dough = doughService.getByName(doughName);
        Set<Ingredient> ingredientSet = new HashSet<>();
        for (String ingredient : listIngredients) {
            ingredientSet.add(ingredientService.getByName(ingredient));
        }
        pizzaService.create(name, dough, ingredientSet);
    }

    public void update(String name, String newName, String doughName, String ingredients) {
        if (ingredients == null) {
            return;
        }
        String[] listIngredients = ingredients.split(",");
        Dough dough = doughService.getByName(doughName);
        Set<Ingredient> ingredientSet = new HashSet<>();
        for (String ingredient : listIngredients) {
            ingredientSet.add(ingredientService.getByName(ingredient));
        }
        pizzaService.update(name, newName, dough, ingredientSet);
    }

    public void delete(String name){
        pizzaService.delete(name);
    }

    public List<String> list(){
        return pizzaService.getByAll();
    }
}
