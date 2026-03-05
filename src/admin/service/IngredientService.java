package admin.service;

import exception.NotFoundException;
import exception.ValidationException;
import admin.model.Ingredient;
import admin.repository.in_memory.IngredientInMemoryRepository;
import admin.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class IngredientService {
    private final Validator<Ingredient> ingredientValidator = new Validator<>() {
    };
    private final IngredientInMemoryRepository ingredientInMemoryRepository = IngredientInMemoryRepository.getInstance();

    public Ingredient create(String name, String priceStr) throws ValidationException, NotFoundException {
        Ingredient ingredient = buildIngredient(name, priceStr);
        ingredientValidator.validate(ingredient);
        if (ingredientInMemoryRepository.getByName(ingredient.getName()) != null) {
            throw new ValidationException("Ингредиент с именем " + name + " уже существует");
        }
        ingredientInMemoryRepository.create(ingredient);
        return ingredient;
    }

    public Ingredient update(String name, String priceStr, String newName) throws ValidationException, NotFoundException {
        Ingredient ingredient = buildIngredient(name, priceStr, true, newName);
        ingredientValidator.validate(ingredient);
        ingredientInMemoryRepository.update(name, ingredient);
        return ingredient;
    }

    public void delete(String name) throws NotFoundException {
        getByName(name);
        ingredientInMemoryRepository.delete(name);
    }

    public Ingredient getByName(String name) throws NotFoundException {
        Ingredient byName = ingredientInMemoryRepository.getByName(name);
        if (byName == null) {
            throw new NotFoundException("Ингредиент с именем " + name + " не найден");
        }
        return byName;
    }

    public List<String> getAllNames() {
        List<Ingredient> ingredientList = ingredientInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            stringList.add(ingredient.getName());
        }
        return stringList;
    }

    private Ingredient buildIngredient(String name, String priceStr) throws ValidationException, NotFoundException {
        return buildIngredient(name, priceStr, false, null);
    }

    private Ingredient buildIngredient(String name, String priceStr, boolean isExisting, String newName) throws ValidationException, NotFoundException {
        if (name == null) {
            throw new ValidationException("Имя ингредиента - обязательное поле");
        }
        Integer price = parsePrice(priceStr);
        if (isExisting) {
            Ingredient existingIngredient = ingredientInMemoryRepository.getByName(name);
            if (existingIngredient == null) {
                throw new NotFoundException("Ингредиент с именем " + name + " не найден");
            }
            newName = newName == null ? existingIngredient.getName() : newName;
            price = price == null ? existingIngredient.getPrice() : price;
            return new Ingredient(newName, price);
        }
        if (price == null) {
            throw new ValidationException("Цена - обязательное поле и должна быть положительным числом");
        }
        return new Ingredient(name, price);
    }

    private Integer parsePrice(String priceStr) throws ValidationException {
        if (priceStr == null) {
            return null;
        }
        try {
            return Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            throw new ValidationException("Цена должна быть валидным положительным числом");
        }
    }
}
