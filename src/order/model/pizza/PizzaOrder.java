package order.model.pizza;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public abstract class PizzaOrder {
    protected final String id;
    protected final PizzaSize pizzaSize;
    protected final String doughName;
    protected final BigDecimal price;
    protected boolean isValid;
    protected final String orderId;
    protected final PizzaType pizzaType;

    public PizzaOrder(String pizzaSize, String doughName, String orderId, PizzaType pizzaType) {
        this.orderId = orderId;
        this.pizzaType = pizzaType;
        this.id = UUID.randomUUID().toString();
        this.pizzaSize = PizzaSize.fromString(pizzaSize);
        this.doughName = doughName;
        this.price = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
    }

    public String getId() {
        return id;
    }

    public PizzaSize getPizzaSize() {
        return pizzaSize;
    }

    public String getDoughName() {
        return doughName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "PizzaOrder{" +
                "id='" + id + '\'' +
                ", pizzaSize=" + pizzaSize +
                ", doughName='" + doughName + '\'' +
                ", price=" + price +
                ", isValid=" + isValid +
                ", orderId='" + orderId + '\'' +
                ", pizzaType=" + pizzaType +
                '}';
    }
}
