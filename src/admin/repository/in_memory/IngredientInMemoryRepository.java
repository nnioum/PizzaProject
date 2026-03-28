package admin.repository.in_memory;

import admin.model.Ingredient;
import admin.repository.IngredientRepository;

import java.util.*;

public class IngredientInMemoryRepository implements IngredientRepository {
    private final Map<String, Ingredient> INGREDIENTS_BY_NAME;
    private static final IngredientInMemoryRepository INSTANCE = new IngredientInMemoryRepository();

    private IngredientInMemoryRepository(){
        INGREDIENTS_BY_NAME = new HashMap<>();
    }

    public static IngredientInMemoryRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public void create(Ingredient ingredient) {
        String name = ingredient.getName();
        INGREDIENTS_BY_NAME.put(name, ingredient);
    }

    @Override
    public void update(String name, Ingredient ingredient) {
        delete(name);
        create(ingredient);
    }

    @Override
    public void delete(String name) {
        INGREDIENTS_BY_NAME.remove(name);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<Ingredient>(INGREDIENTS_BY_NAME.values());
    }

    @Override
    public Ingredient getByName(String name) {
        return INGREDIENTS_BY_NAME.get(name);
    }
}
