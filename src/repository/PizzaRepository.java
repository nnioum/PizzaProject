package repository;

import model.Pizza;

import java.util.List;

public interface PizzaRepository {
    void create(Pizza pizza);
    void update(String name, Pizza pizza);
    void delete(String name);
    List<Pizza> getAll();
    Pizza getByName(String name);
}
