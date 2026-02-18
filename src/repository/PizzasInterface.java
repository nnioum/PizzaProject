package repository;

import model.Pizza;

import java.util.Map;
import java.util.Set;

public interface PizzasInterface {
    public void create(Pizza pizza);
    public void update(String name, Pizza pizza);
    public void delete(String name);
    public Set<String> getAll();
    public Pizza getByName(String name);
}
