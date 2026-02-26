package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pizza extends PricedItem{
    public Dough dough;
    public Set<Ingredient> ingredients = new HashSet<>();

    public Pizza(String name) {
        super(name, 0);
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(dough, pizza.dough) && Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dough, ingredients);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "crust=" + dough +
                ", description=" + ingredients +
                '}';
    }
}
