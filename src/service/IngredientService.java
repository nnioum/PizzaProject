package service;

import model.Ingredient;
import repository.in_memory.IngredientInMemoryRepository;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class IngredientService {
    private final Validator<Ingredient> ingredientValidator = new Validator<>();
    private final IngredientInMemoryRepository ingredientInMemoryRepository = new IngredientInMemoryRepository();

    public boolean create(Ingredient ingredient) {
        if (ingredientValidator.validate(ingredient)) {
            ingredientInMemoryRepository.create(ingredient);
            return true;
        }
        return false;
    }

    public boolean update(String name, Ingredient ingredient) {
        List<String> stringList = getByName();
        if (name != null && stringList.contains(name) && ingredientValidator.validate(ingredient)) {
            ingredientInMemoryRepository.update(name, ingredient);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        List<String> stringList = getByName();
        if (name != null && stringList.contains(name)) {
            ingredientInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public List<String> getByName() {
        List<Ingredient> ingredientList = ingredientInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            stringList.add(ingredient.getName());
        }
        return stringList;
    }

    public List<Integer> getByPrice() {
        List<Ingredient> ingredientList = ingredientInMemoryRepository.getAll();
        List<Integer> stringList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            stringList.add(ingredient.getPrice());
        }
        return stringList;
    }
}
