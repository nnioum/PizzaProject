package repository;

import model.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IngredientsInterface {
    void create(Ingredient ingredient);
    void update(String name, Ingredient ingredient);
    void delete(String name);
    List<Ingredient> getAll();
    Ingredient getByName(String name);
}
