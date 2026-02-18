package repository.in_memory;

import model.Ingredient;
import repository.IngredientsInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IngredientInMemoryRepository implements IngredientsInterface {
    private final Map<String, Ingredient> ingredients_by_name = new HashMap<String, Ingredient>();


    @Override
    public void create(Ingredient ingredient) {
        String name = ingredient.getName();
        ingredients_by_name.put(name, ingredient);
    }

    @Override
    public void update(String name, Ingredient ingredient) {
        ingredients_by_name.replace(name, ingredient);
    }

    @Override
    public void delete(String name) {
        ingredients_by_name.remove(name);
    }

    @Override
    public Set<String> getAll() {
        return ingredients_by_name.keySet();
    }

    @Override
    public Ingredient getByName(String name) {
        return ingredients_by_name.get(name);
    }
}
