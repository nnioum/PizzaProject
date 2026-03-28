package order.model.pizza;

import java.math.BigDecimal;

public enum PizzaSize {
    SMALL(6, new BigDecimal("1.0")),
    MEDIUM(8, new BigDecimal("1.2")),
    LARGE(12, new BigDecimal("1.5"));

    private final int sliceNumber;
    private final BigDecimal priceMultiplier;

    PizzaSize(int sliceNumber, BigDecimal priceMultiplier) {
        this.sliceNumber = sliceNumber;
        this.priceMultiplier = priceMultiplier;
    }

    public int getSliceNumber() {
        return sliceNumber;
    }

    public BigDecimal getPriceMultiplier() {
        return priceMultiplier;
    }

    public static PizzaSize fromString(String pizzaSize) {
        for (PizzaSize size : PizzaSize.values()) {
            if (size.name().equalsIgnoreCase(pizzaSize)) {
                return size;
            }
        }
        return null;
    }
}
