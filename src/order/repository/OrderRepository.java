package order.repository;

import order.model.Order;
import order.model.OrderStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private final Map<String, Order> ORDER_BY_ID;
    private static final OrderRepository INSTANCE = new OrderRepository();

    private OrderRepository() {
        this.ORDER_BY_ID = new HashMap<>();
    }

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    public void save(Order order) {
        ORDER_BY_ID.put(order.getOrderId(), order);
    }

    public void delete(String id) {
        ORDER_BY_ID.remove(id);
    }

    public Order getById(String id) {
        return ORDER_BY_ID.get(id);
    }

    public List<Order> getAll() {
        return ORDER_BY_ID.values().stream()
                .toList();
    }

    public void submit(String id) {
        getById(id).setOrderStatus(OrderStatus.SUBMITTED);
    }

    public void editPrice(String id, BigDecimal price) {
        getById(id).setTotalPrice(price);
    }

    public void split(String id, BigDecimal pricePerPerson) {
        Order order = getById(id);
        order.setPricePerPerson(pricePerPerson);
    }

    public void closed(String id) {
        getById(id).setOrderStatus(OrderStatus.CLOSED);
    }
}
