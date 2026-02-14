package repository.in_memory;

import model.Ingredient;
import repository.IngredientsInterface;

import java.util.HashMap;
import java.util.Map;

public class IngredientInMemoryRepository implements IngredientsInterface {
    private Map<String, Ingredient> ingredientMap = new HashMap<String, Ingredient>();

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
