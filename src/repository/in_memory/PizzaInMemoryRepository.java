package repository.in_memory;

import model.Pizza;
import repository.PizzasInterface;

import java.util.Map;

public class PizzaInMemoryRepository implements PizzasInterface {
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
    public Pizza getByName() {
        return null;
    }
}
