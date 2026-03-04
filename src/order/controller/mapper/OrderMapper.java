package order.controller.mapper;

import order.controller.view.OrderView;
import order.model.Order;
import order.model.pizza.PizzaOrder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMapper {
    public OrderView toView(Order order, List<PizzaOrder> pizzaOrders) {
        if (order == null) {
            return null;
        }
        return new OrderView(
                order.getOrderId(),
                order.getOrderStatus(),
                order.getTotalPrice(),
                order.getComment(),
                order.getCreatedDate(),
                order.getScheduledDate(),
                pizzaOrders
        );
    }

    public List<OrderView> toViews(List<Order> orders, List<PizzaOrder> pizzaOrders){
        Map<String, List<PizzaOrder>> pizzaOrdersByOrderId = pizzaOrders.stream()
                .collect(Collectors.groupingBy(PizzaOrder::getOrderId));
        return orders.stream()
                .map(e->toView(e, pizzaOrdersByOrderId.get(e.getOrderId())))
                .toList();
    }
}
