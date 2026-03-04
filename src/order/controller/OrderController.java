package order.controller;

import exception.NotFoundException;
import exception.ValidationException;
import order.model.Order;
import order.service.OrderService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private final OrderService orderService = new OrderService();

    public String create(String comment, String scheduledDate) throws ValidationException {
        Order order = buildOrder(comment, scheduledDate);
        orderService.create(order);
        return order.getOrderId();
    }

    public void update(String id, String comment, String scheduledDate) throws ValidationException, NotFoundException {
        Order order = buildOrder(comment, scheduledDate, id);
        orderService.update(order);
    }

    public Order getById(String id) throws NotFoundException {
        return orderService.getById(id);
    }

    public List<Order> getAll() {
        return orderService.getAll();
    }

    public void submit(String id) {
        orderService.submit(id);
    }

    private Order buildOrder(String comment, String scheduledDate) throws ValidationException {
        return buildOrder(comment, scheduledDate, null);
    }

    private Order buildOrder(String comment, String scheduledDate, String id) throws ValidationException {
        Order order = new Order();
        String idOrder = id == null ? UUID.randomUUID().toString() : id;
        String commentOrder = comment == null ? "" : comment;
        OffsetDateTime scheduled = scheduledDate == null ? OffsetDateTime.now().plusHours(1) : OffsetDateTime.parse(scheduledDate);
        OffsetDateTime today = OffsetDateTime.now();
        order.setOrderId(idOrder);
        order.setComment(commentOrder);
        order.setScheduledDate(scheduled);
        order.setCreatedDate(today);
        return order;
    }
}
