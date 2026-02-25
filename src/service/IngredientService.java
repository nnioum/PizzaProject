package service;

import model.Ingredient;
import repository.in_memory.IngredientInMemoryRepository;
import service.validator.Validator;
import service.validator.BaseValidator;

import java.util.ArrayList;
import java.util.List;

public class IngredientService {
    private final Validator<Ingredient> ingredientValidator = new BaseValidator<Ingredient>() {};
    private final IngredientInMemoryRepository ingredientInMemoryRepository = new IngredientInMemoryRepository();

    public boolean create(Ingredient ingredient) {
        if (ingredientInMemoryRepository.getByName(ingredient.getName()) == null && ingredientValidator.validate(ingredient)) {
            ingredientInMemoryRepository.create(ingredient);
            return true;
        }
        return false;
    }

    public boolean update(String name, Ingredient ingredient) {
        if (name == null) {
            return false;
        }
        if (ingredientInMemoryRepository.getByName(name) != null && ingredientValidator.validate(ingredient)) {
            ingredientInMemoryRepository.update(name, ingredient);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if (name == null) {
            return false;
        }
        if (ingredientInMemoryRepository.getByName(name) != null) {
            ingredientInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public Ingredient getByName(String name) {
        return ingredientInMemoryRepository.getByName(name);
    }

    public List<String> getByAll() {
        List<Ingredient> ingredientList = ingredientInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            stringList.add(ingredient.getName());
        }
        return stringList;
    }
}
