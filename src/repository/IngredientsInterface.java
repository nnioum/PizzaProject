package repository;

import model.Ingredient;

import java.util.Map;
import java.util.Set;

public interface IngredientsInterface {
    public void create(Ingredient ingredient);
    public void update(String name, Ingredient ingredient);
    public void delete(String name);
    public Set<String> getAll();
    public Ingredient getByName(String name);
}
