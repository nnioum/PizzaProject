package order.controller;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.mapper.OrderMapper;
import order.controller.view.OrderView;
import order.helper.DateTimeHelper;
import order.model.Order;
import order.model.pizza.PizzaOrder;
import order.service.OrderService;
import order.service.PizzaOrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderController {
    private final OrderService orderService = new OrderService();
    private final OrderMapper orderMapper = new OrderMapper();
    private final PizzaOrderService pizzaOrderService = new PizzaOrderService();

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("createdDate", "status");

    public String create(String comment, String scheduledDate) throws ValidationException, NotFoundException {
        Order order = buildOrder(comment, scheduledDate);
        orderService.create(order);
        return order.getOrderId();
    }

    public void update(String id, String comment, String scheduledDate) throws ValidationException, NotFoundException {
        Order order = buildOrder(comment, scheduledDate, true, id);
        orderService.update(order);
    }

    public OrderView getById(String id) throws NotFoundException {
        Order order = orderService.getById(id);
        List<PizzaOrder> pizzaOrders = pizzaOrderService.getAllByOrderId(id);
        return orderMapper.toView(order, pizzaOrders);
    }

    public List<OrderView> getAll() {
        return orderMapper.toViews(orderService.getAll(), pizzaOrderService.getAll());
    }

    public List<OrderView> getAll(String status, String createdTimeFrom, String createdTimeTo, String sortBy) throws ValidationException {
        if (status != null && (createdTimeFrom != null && createdTimeTo != null)) {
            throw new ValidationException("Только один фильтр может быть использован одновременно");
        }
        if (sortBy != null && !ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new ValidationException("Недопустимое значение для параметра sortBy. Допустимые значения: " + ALLOWED_SORT_FIELDS);
        }
        List<OrderView> views = getAll();
        if (status != null) {
            views = views.stream()
                    .filter(view -> view.getOrderStatus().name().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        } else {
            if (createdTimeFrom != null) {
                LocalDateTime from = DateTimeHelper.parseToLocalDateTime(createdTimeFrom);
                views = views.stream()
                        .filter(view -> view.getCreatedDate().isAfter(from))
                        .collect(Collectors.toList());
            }
            if (createdTimeTo != null) {
                LocalDateTime to = DateTimeHelper.parseToLocalDateTime(createdTimeTo);
                views = views.stream()
                        .filter(view -> view.getCreatedDate().isBefore(to))
                        .collect(Collectors.toList());
            }
        }
        if (sortBy != null) {
            views = new java.util.ArrayList<>(views);
            if (sortBy.equals("createdDate")) {
                views.sort(Comparator.comparing(OrderView::getCreatedDate));
            } else if (sortBy.equals("status")) {
                views.sort(Comparator.comparing(orderView -> orderView.getOrderStatus().name()));
            }
        }
        return views;
    }

    public void submit(String id) throws NotFoundException {
        BigDecimal price = calculationPrice(getById(id));
        orderService.submit(id);
        orderService.editPrice(id, price);
    }

    public void split(String id, String count) throws NotFoundException, ValidationException {
        if (count == null) {
            throw new ValidationException("Комманда --number обязательный");
        }
        try {
            orderService.split(id, BigDecimal.valueOf(Long.parseLong(count)));
        } catch (NumberFormatException e) {
            throw new ValidationException("Неверный формат number");
        }
    }

    public void closed(String id){
        orderService.closed(id);
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
        if (isExisting) {
            Order orderSystem = orderService.getById(id);
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

    private BigDecimal calculationPrice(OrderView orderView) {
        BigDecimal price = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
        for (PizzaOrder pizzaOrder : orderView.getPizzaOrders()) {
            price = price.add(pizzaOrder.getPrice());
        }
        return price;
    }
}
