package order.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Order {

    private String orderId; //обязательное
    private OrderStatus orderStatus; //обязательное
    private BigDecimal totalPrice; //обязательное
    private String comment;
    private LocalDateTime createdDate; //обязательное
    private LocalDateTime scheduledDate;
    private BigDecimal pricePerPerson;

    public Order() {
        this.totalPrice = new BigDecimal("0.00").setScale(2, ROUND_HALF_UP);
        this.pricePerPerson = new BigDecimal("1.00").setScale(2, ROUND_HALF_UP);
        this.orderStatus = OrderStatus.OPEN;
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

    public BigDecimal getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(BigDecimal pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public String toString() {
        return "id - " + orderId +
                "\nСтатус заказа - " + orderStatus +
                "\nЦена для одного - " + pricePerPerson +
                "\nЦена заказа - " + totalPrice +
                "\nКомметарий - " + comment +
                "\nВремя создание заказа - " + createdDate +
                "\nБудет готов в - " + scheduledDate;
    }
}
