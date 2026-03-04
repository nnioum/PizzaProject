package order.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderId; //обязательное
    private OrderStatus orderStatus; //обязательное
    private BigDecimal totalPrice; //обязательное
    private String comment;
    private List<String> pizzaOrderIds; //обязательное
    private LocalDateTime createdDate; //обязательное
    private LocalDateTime scheduledDate;

    public Order() {
        this.totalPrice = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
        this.orderStatus = OrderStatus.OPEN;
        pizzaOrderIds = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getPizzaOrderIds() {
        return pizzaOrderIds;
    }

    public void setPizzaOrderIds(List<String> pizzaOrderIds) {
        this.pizzaOrderIds = pizzaOrderIds;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @Override
    public String toString() {
        String pizzaOrderIdsStr;
        if (pizzaOrderIds == null) {
            pizzaOrderIdsStr = "";
        } else {
            pizzaOrderIdsStr = String.join(", ", pizzaOrderIds);
        }
        return "id - " + orderId +
                "\nСтатус заказа - " + orderStatus +
                "\nЦена - " + totalPrice +
                "\nКомметарий - " + comment +
                "\nId пицц - " + pizzaOrderIdsStr +
                "\nВремя создание заказа - " + createdDate +
                "\nБудет готов в - " + scheduledDate + "\n";
    }
}
