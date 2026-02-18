package repository.in_memory;

import model.Pizza;
import repository.PizzasInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PizzaInMemoryRepository implements PizzasInterface {

    private final Map<String, Pizza> pizzas_by_name = new HashMap<String, Pizza>();


    @Override
    public void create(Pizza pizza) {
        String name = pizza.getName();
        pizzas_by_name.put(name, pizza);
    }

    @Override
    public void update(String name, Pizza pizza) {
        pizzas_by_name.replace(name, pizza);
    }

    @Override
    public void delete(String name) {
        pizzas_by_name.remove(name);
    }

    @Override
    public Set<String> getAll() {
        return pizzas_by_name.keySet();
    }

    @Override
    public Pizza getByName(String name) {
        return pizzas_by_name.get(name);
    }
}

