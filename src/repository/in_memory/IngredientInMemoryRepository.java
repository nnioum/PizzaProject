package repository.in_memory;

import model.Ingredient;
import repository.IngredientsInterface;

import java.util.Map;

public class IngredientInMemoryRepository implements IngredientsInterface {
    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Map<String, Ingredient> getAll() {
        return Map.of();
    }

    @Override
    public Ingredient getByName() {
        return null;
    }
}
