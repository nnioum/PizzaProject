package service;

import exception.NotFoundException;
import exception.ValidationException;
import model.Dough;
import model.Ingredient;
import model.Pizza;
import repository.in_memory.DoughInMemoryRepository;
import repository.in_memory.IngredientInMemoryRepository;
import repository.in_memory.PizzaInMemoryRepository;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PizzaService {
    private final Validator<Pizza> pizzaValidator = new Validator<>() {
    };
    private final PizzaInMemoryRepository pizzaInMemoryRepository = new PizzaInMemoryRepository();

    private final DoughInMemoryRepository doughInMemoryRepository = new DoughInMemoryRepository();

    private final IngredientInMemoryRepository ingredientInMemoryRepository = new IngredientInMemoryRepository();

    public Pizza create(String name, String nameBase, String ingredientsLine) throws ValidationException, NotFoundException {
        Pizza pizza = buildPizza(name, nameBase, ingredientsLine);
        pizzaValidator.validate(pizza);
        if (pizzaInMemoryRepository.getByName(pizza.getName()) != null) {
            throw new ValidationException("Пиццы с именем " + name + " уже существует");
        }
        pizzaInMemoryRepository.create(pizza);
        return pizza;
    }

    public Pizza update(String name, String nameBase, String ingredientsLine, String newName) throws ValidationException, NotFoundException {
        Pizza pizza = buildPizza(name, nameBase, ingredientsLine, true, newName);
        pizzaValidator.validate(pizza);
        pizzaInMemoryRepository.update(name, pizza);
        return pizza;
    }

    public void delete(String name) throws NotFoundException {
        getByName(name);
        pizzaInMemoryRepository.delete(name);
    }

    public Pizza getByName(String name) throws NotFoundException {
        Pizza byName = pizzaInMemoryRepository.getByName(name);
        if (byName == null) {
            throw new NotFoundException("Пицца с именем " + name + " не найдена");
        }
        return byName;
    }

    public List<String> getAllNames() {
        List<Pizza> pizzaList = pizzaInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            stringList.add(pizza.getName());
        }
        return stringList;
    }

    private int calculate(Dough dough, Set<Ingredient> ingredients) {
        int price = 0;
        price += dough.getPrice();
        for (Ingredient ingredient : ingredients) {
            price += ingredient.getPrice();
        }
        return price;
    }

    private Pizza buildPizza(String name, String nameBase, String ingredientLine) throws ValidationException, NotFoundException {
        return buildPizza(name, nameBase, ingredientLine, false, null);
    }

    private Pizza buildPizza(String name, String nameBase, String ingredientLine, boolean isExisting, String newName) throws ValidationException, NotFoundException {
        if (name == null) {
            throw new ValidationException("Имя пиццы - обязательное поле");
        }
        if (ingredientLine == null) {
            throw new ValidationException("Название ингредиентов - обязательное поле");
        }
        Set<String> namesIngredients = Set.copyOf(List.of(ingredientLine.split(",")));
        Set<Ingredient> ingredientList = new HashSet<>();
        Dough dough = doughInMemoryRepository.getByName(nameBase);
        if (dough == null) {
            throw new NotFoundException("Основа для теста с именем " + nameBase + " не найдена");
        }
        for (String ingredient : namesIngredients) {
            Ingredient byName = ingredientInMemoryRepository.getByName(ingredient);
            if (byName == null) {
                throw new NotFoundException("Ингредиента с именем " + ingredient + " не найдено");
            }
            ingredientList.add(byName);
        }
        Pizza pizza = new Pizza(name);
        pizza.setPrice(calculate(dough, ingredientList));
        pizza.setDough(dough);
        pizza.setIngredients(ingredientList);

        if (isExisting) {
            Pizza existingPizza = pizzaInMemoryRepository.getByName(name);
            if (existingPizza == null) {
                throw new NotFoundException("Пицца с именем " + name + " не найдена");
            }
            newName = newName == null ? existingPizza.getName() : newName;
            pizza.setName(newName);
        }
        return pizza;

    }
}
