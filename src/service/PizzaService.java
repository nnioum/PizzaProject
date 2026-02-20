package service;

import model.Pizza;
import repository.in_memory.PizzaInMemoryRepository;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class PizzaService {
    private final Validator<Pizza> pizzaValidator = new Validator<>();
    private final PizzaInMemoryRepository pizzaInMemoryRepository = new PizzaInMemoryRepository();

    public boolean create(Pizza pizza) {
        if (pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.create(pizza);
            return true;
        }
        return false;
    }

    public boolean update(String name, Pizza pizza) {
        List<String> stringList = getByName();
        if (name != null && stringList.contains(name) && pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.update(name, pizza);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        List<String> stringList = getByName();
        if (name != null && stringList.contains(name)) {
            pizzaInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public List<String> getByName() {
        List<Pizza> pizzaList = pizzaInMemoryRepository.getAll();
        List<String> stringList = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            stringList.add(pizza.getName());
        }
        return stringList;
    }

    public List<Integer> getByPrice() {
        List<Pizza> pizzaList = pizzaInMemoryRepository.getAll();
        List<Integer> stringList = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            stringList.add(pizza.getPrice());
        }
        return stringList;
    }
}
