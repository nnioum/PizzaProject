package admin.service;

import admin.model.Crust;
import admin.repository.CrustRepository;
import admin.repository.in_memory.CrustInMemoryRepository;
import admin.service.validator.CrustValidator;
import admin.service.validator.Validator;
import exception.NotFoundException;
import exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static admin.data.GlobalData.CLASSIC_DOUGH;

public class CrustService {

    private final Validator<Crust> crustValidator = new CrustValidator();

    private final CrustRepository crustRepository = CrustInMemoryRepository.getInstance();

    private final IngredientService ingredientService = new IngredientService();

    private final PizzaService pizzaService = new PizzaService();

    public Crust create(String name, String priceStr, Set<String> ingredients, Set<String> allowedPizzas) throws ValidationException, NotFoundException {
        Crust crust = buildCrust(name, priceStr, ingredients, allowedPizzas);
        crustValidator.validate(crust);
        if (crustRepository.getByName(name) != null) {
            throw new ValidationException("Бортик с именем " + name + " уже существует");
        }
        crustRepository.create(crust);
        return crust;
    }

    public Crust update(String name, String priceStr, String newName, Set<String> ingredients, Set<String> allowedPizzas) throws ValidationException, NotFoundException {
        Crust crust = buildCrust(name, priceStr, ingredients, allowedPizzas, true, newName);
        crustValidator.validate(crust);
        crustRepository.update(name, crust);
        return crust;
    }

    public void delete(String name) throws NotFoundException, ValidationException {
        getByName(name);
        crustRepository.delete(name);
    }

    public Crust getByName(String name) throws NotFoundException {
        Crust crust = crustRepository.getByName(name);
        if (crust == null) {
            throw new NotFoundException("Бортик с именем " + name + " не найдено");
        }
        return crust;
    }

    public List<String> getAllNames() {
        List<Crust> crustList = crustRepository.getAll();
        List<String> nameList = new ArrayList<>();
        for (Crust crust : crustList) {
            nameList.add(crust.getName());
        }
        return nameList;
    }

    private Crust buildCrust(String name, String priceStr, Set<String> ingredients, Set<String> allowedPizzas) throws ValidationException, NotFoundException {
        return buildCrust(name, priceStr, ingredients, allowedPizzas, false, null);
    }

    private Crust buildCrust(String name,
                             String priceStr,
                             Set<String> ingredients,
                             Set<String> allowedPizzas,
                             boolean isExisting,
                             String newName) throws ValidationException, NotFoundException {
        Integer price = parsePrice(priceStr);
        if (isExisting) {
            Crust existingCrust = crustRepository.getByName(name);
            if (existingCrust == null) {
                throw new NotFoundException("Бортик с именем " + name + " не найден");
            }
            newName = newName == null ? existingCrust.getName() : newName;
            price = price == null ? existingCrust.getPrice() : price;
            Set<String> newIngredients = existingCrust.getIngredients();
            if (ingredients != null && !ingredients.isEmpty()) {
                checkIngredients(ingredients);
                newIngredients = ingredients;
            }
            Set<String> newAllowedPizzas = existingCrust.getAllowedPizzas();
            if (allowedPizzas != null && !allowedPizzas.isEmpty()) {
                checkAllowedPizzas(allowedPizzas);
                newAllowedPizzas = allowedPizzas;
            }
            Crust crust = new Crust(newName, price);
            crust.getIngredients().addAll(newIngredients);
            crust.getAllowedPizzas().addAll(newAllowedPizzas);
            return crust;
        }
        if (price == null) {
            throw new ValidationException("Цена - обязательное поле и должна быть положительным числом");
        }

        checkIngredients(ingredients);
        checkAllowedPizzas(allowedPizzas);
        Crust crust = new Crust(name, price);
        crust.getIngredients().addAll(ingredients);
        crust.getAllowedPizzas().addAll(allowedPizzas);
        return crust;
    }

    private void checkIngredients(Set<String> ingredients) throws NotFoundException, ValidationException {
        if (ingredients != null && !ingredients.isEmpty()) {
            for (String ingredientName : ingredients) {
                ingredientService.getByName(ingredientName);
            }
        } else {
            throw new ValidationException("Набор ингредиентов не может быть пустым");
        }
    }

    private void checkAllowedPizzas(Set<String> allowedPizzas) throws NotFoundException, ValidationException {
        if (allowedPizzas != null && !allowedPizzas.isEmpty()) {
            for (String pizzaName : allowedPizzas) {
                pizzaService.getByName(pizzaName);
            }
        } else {
            throw new ValidationException("Набор разрешенных пицц не может быть пустым");
        }
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
