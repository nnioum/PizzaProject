package repository;

import model.Ingredient;

import java.util.Map;

public interface IngredientsInterface {
    public void create();
    public void update();
    public void delete();
    public Map<String, Ingredient> getAll();
    public Ingredient getByName();
}
