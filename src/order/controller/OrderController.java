package order.controller;

import exception.NotFoundException;
import exception.ValidationException;
import order.helper.DateTimeHelper;
import order.model.Order;
import order.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private final OrderService orderService = new OrderService();

    public String create(String comment, String scheduledDate) throws ValidationException, NotFoundException {
        Order order = buildOrder(comment, scheduledDate);
        orderService.create(order);
        return order.getOrderId();
    }

    public void update(String id, String comment, String scheduledDate) throws ValidationException, NotFoundException {
        Order order = buildOrder(comment, scheduledDate, true, id);
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

    private Order buildOrder(String comment, String scheduledDate) throws ValidationException, NotFoundException {
        return buildOrder(comment, scheduledDate, false, null);
    }

    private Order buildOrder(String comment, String scheduledDate, boolean isExisting, String id) throws ValidationException, NotFoundException {
        Order order = new Order();
        String idOrder = id == null ? UUID.randomUUID().toString() : id;
        String commentOrder = comment == null ? "" : comment;
        LocalDateTime scheduled = scheduledDate == null ? LocalDateTime.now().plusHours(1) : DateTimeHelper.parseToLocalDateTime(scheduledDate);
        LocalDateTime today = LocalDateTime.now();
        if(isExisting){
            Order orderSystem= orderService.getById(id);
            commentOrder = comment == null ? orderSystem.getComment() : comment;
            scheduled = scheduledDate == null ? orderSystem.getScheduledDate() : DateTimeHelper.parseToLocalDateTime(scheduledDate);
            today = orderSystem.getCreatedDate();
        }
        order.setOrderId(idOrder);
        order.setComment(commentOrder);
        order.setScheduledDate(scheduled);
        order.setCreatedDate(today);
        return order;
    }
}
