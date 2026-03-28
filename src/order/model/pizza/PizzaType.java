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

    public static PizzaType fromString(String name) {
        for (PizzaType type : PizzaType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
