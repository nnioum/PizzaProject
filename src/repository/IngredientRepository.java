package repository;

import model.Ingredient;

import java.util.List;

public interface IngredientRepository {
    void create(Ingredient ingredient);
    void update(String name, Ingredient ingredient);
    void delete(String name);
    List<Ingredient> getAll();
    Ingredient getByName(String name);
}
