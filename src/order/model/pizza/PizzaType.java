package order.model.pizza;

public enum PizzaType {
    READY("ready"),
    CUSTOM("custom"),
    HALVED("halved"),
    SLICED("sliced");

    private final String name;

    PizzaType(String name) {
        this.name = name;
    }

    public PizzaOrder buildPizzaOrder(String pizzaSize, String doughName) {
        return switch (this) {
            case READY -> new ReadyPizzaOrder(pizzaSize, doughName);
            case CUSTOM -> new CustomPizzaOrder(pizzaSize, doughName);
            case HALVED -> new HalvedPizzaOrder(pizzaSize, doughName);
            case SLICED -> new SlicedPizzaOrder(pizzaSize, doughName);
        };
    }

    public static PizzaType fromString(String name) {
        for (PizzaType type : PizzaType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
