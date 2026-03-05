package order.repository;

import order.model.pizza.PizzaOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaOrderRepository {
    private final Map<String, PizzaOrder> PIZZA_ORDER_BY_ID;
    private static final PizzaOrderRepository INSTANCE = new PizzaOrderRepository();

    private PizzaOrderRepository() {
        PIZZA_ORDER_BY_ID = new HashMap<>();
    }

    public static PizzaOrderRepository getInstance() {
        return INSTANCE;
    }

    public void save(PizzaOrder order) {
        PIZZA_ORDER_BY_ID.put(order.getId(), order);
    }

    public void delete(String id) {
        PIZZA_ORDER_BY_ID.remove(id);
    }

    public PizzaOrder getById(String id) {
        return PIZZA_ORDER_BY_ID.get(id);
    }

    public List<PizzaOrder> getAll() {
        return PIZZA_ORDER_BY_ID.values().stream()
                .toList();
    }

    public List<PizzaOrder> getAllByOrderId(String orderId) {
        return getAll().stream()
                .filter(e -> e.getOrderId().equals(orderId))
                .toList();
    }
}
