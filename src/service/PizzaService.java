package service;

import model.Pizza;
import repository.in_memory.PizzaInMemoryRepository;
import service.validator.InterfaceValidator;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class PizzaService {
    private final InterfaceValidator<Pizza> pizzaValidator = new Validator<Pizza>() {};
    private final PizzaInMemoryRepository pizzaInMemoryRepository = new PizzaInMemoryRepository();

    public boolean create(Pizza pizza) {
        if (pizzaInMemoryRepository.getByName(pizza.getName())==null&&pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.create(pizza);
            return true;
        }
        return false;
    }

    public boolean update(String name, Pizza pizza) {
        if(name==null){
            return false;
        }
        if (pizzaInMemoryRepository.getByName(name)!=null&& pizzaValidator.validate(pizza)) {
            pizzaInMemoryRepository.update(name, pizza);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if(name==null){
            return false;
        }
        if (pizzaInMemoryRepository.getByName(name)!=null) {
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
