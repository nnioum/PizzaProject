package admin.repository.in_memory;

import admin.model.Pizza;
import admin.repository.PizzaRepository;

import java.util.*;

public class PizzaInMemoryRepository implements PizzaRepository {
    private final Map<String, Pizza> PIZZAS_BY_NAME;
    private static final PizzaInMemoryRepository INSTANCE = new PizzaInMemoryRepository();

    private PizzaInMemoryRepository(){
        PIZZAS_BY_NAME = new HashMap<>();
    }

    public static PizzaInMemoryRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void create(Pizza pizza) {
        String name = pizza.getName();
        PIZZAS_BY_NAME.put(name, pizza);
    }

    @Override
    public void update(String name, Pizza pizza) {
        delete(name);
        create(pizza);
    }

    @Override
    public void delete(String name) {
        PIZZAS_BY_NAME.remove(name);
    }

    @Override
    public List<Pizza> getAll() {
        return new ArrayList<Pizza>(PIZZAS_BY_NAME.values());
    }

    @Override
    public Pizza getByName(String name) {
        return PIZZAS_BY_NAME.get(name);
    }
}

