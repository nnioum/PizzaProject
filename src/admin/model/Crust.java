package admin.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Crust extends PricedItem {

    private final Set<String> ingredients;
    private final Set<String> allowedPizzas;

    public Crust(String name, int price) {
        super(name, price);
        this.ingredients = new HashSet<>();
        this.allowedPizzas = new HashSet<>();
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllowedPizzas() {
        return allowedPizzas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Crust crust = (Crust) o;
        return Objects.equals(ingredients, crust.ingredients) && Objects.equals(allowedPizzas, crust.allowedPizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ingredients, allowedPizzas);
    }

    @Override
    public String toString() {
        return "\nИмя - "+getName()+
                "\nЦена - "+getPrice()+
                "\nИнгредиенты - " + ingredients +
                "\nСписок разрешенных пицц - " + allowedPizzas;
    }
}
