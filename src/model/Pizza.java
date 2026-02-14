package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pizza extends PricedItem{
    public Crust crust;
    public Set<Ingredient> ingredients = new HashSet<>();

    public Pizza(String name) {
        super(name, 0);
    }

    public Crust getCrust() {
        return crust;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
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
        return Objects.equals(crust, pizza.crust) && Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crust, ingredients);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "crust=" + crust +
                ", description=" + ingredients +
                '}';
    }
}
