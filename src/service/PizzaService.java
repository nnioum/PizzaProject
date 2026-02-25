package service;

import model.Dough;
import model.Ingredient;
import model.Pizza;
import repository.in_memory.PizzaInMemoryRepository;
import service.validator.Validator;
import service.validator.BaseValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PizzaService {
    private final Validator<Pizza> pizzaValidator = new BaseValidator<Pizza>() {
    };
    private final PizzaInMemoryRepository pizzaInMemoryRepository = new PizzaInMemoryRepository();
    private final IngredientService ingredientService = new IngredientService();

    public boolean create(String name, Dough dough, Set<Ingredient> ingredients) {
        Pizza pizza = new Pizza(name);
        pizza.setDough(dough);
        pizza.setIngredients(ingredients);

        if (pizzaInMemoryRepository.getByName(pizza.getName()) == null && pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.create(pizza);
            return true;
        }
        return false;
    }

    public boolean update(String name, String newName, Dough dough, Set<Ingredient> ingredients) {
        if (name == null) {
            return false;
        }

        Pizza pizza = new Pizza(newName);
        pizza.setDough(dough);
        pizza.setIngredients(ingredients);

        if (pizzaInMemoryRepository.getByName(name) != null && pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.update(name, pizza);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if (name == null) {
            return false;
        }
        if (pizzaInMemoryRepository.getByName(name) != null) {
            pizzaInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public Pizza getByName(String name) {
        return pizzaInMemoryRepository.getByName(name);
    }

    public List<String> getByAll() {
        List<Pizza> pizzaList = pizzaInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            stringList.add(pizza.getName());
        }
        return stringList;
    }
}
