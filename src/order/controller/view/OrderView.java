package order.controller.view;

import order.model.OrderStatus;
import order.model.pizza.PizzaOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderView {
    private final String orderId;
    private final OrderStatus orderStatus;
    private final BigDecimal totalPrice;
    private final String comment;
    private final LocalDateTime createdDate;
    private final LocalDateTime scheduledDate;
    private final List<PizzaOrder> pizzaOrders;

    public OrderView(String orderId, OrderStatus orderStatus, BigDecimal totalPrice, String comment, LocalDateTime createdDate, LocalDateTime scheduledDate, List<PizzaOrder> pizzaOrders) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.comment = comment;
        this.createdDate = createdDate;
        this.scheduledDate = scheduledDate;
        this.pizzaOrders = pizzaOrders;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public List<PizzaOrder> getPizzaOrders() {
        return pizzaOrders;
    }

    @Override
    public String toString() {
        return "id - " + orderId +
                "\nСтатус заказа - " + orderStatus +
                "\nПиццы - " + pizzaOrders +
                "\nЦена - " + totalPrice +
                "\nКомметарий - " + comment +
                "\nВремя создание заказа - " + createdDate +
                "\nБудет готов в - " + scheduledDate + "\n";
    }
}
