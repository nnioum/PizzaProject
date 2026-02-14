package repository;

import model.Pizza;

import java.util.Map;

public interface PizzasInterface {
    public void create();
    public void update();
    public void delete();
    public Map<String, Pizza> getAll();
    public Pizza getByName();
}
