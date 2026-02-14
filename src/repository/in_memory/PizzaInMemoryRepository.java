package repository.in_memory;

import model.Pizza;
import repository.PizzasInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PizzaInMemoryRepository implements PizzasInterface {

    private Map<String, Pizza> pizzaMap = new HashMap<String, Pizza>();

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Map<String, Pizza> getAll() {
        return Map.of();
    }

    @Override
    public Pizza getByName() { return null; }
}

