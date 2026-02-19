package repository;

import model.Pizza;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PizzasInterface {
    void create(Pizza pizza);
    void update(String name, Pizza pizza);
    void delete(String name);
    List<Pizza> getAll();
    Pizza getByName(String name);
}
