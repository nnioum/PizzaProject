package order.service;

import exception.NotFoundException;
import exception.ValidationException;
import order.model.Order;
import order.model.OrderStatus;
import order.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class OrderService {
    private final OrderRepository orderRepository = OrderRepository.getInstance();

    public void create(Order order) throws ValidationException {
        Order orderSystems = orderRepository.getById(order.getOrderId());
        if (orderSystems != null) {
            throw new ValidationException("Заказ с id: " + order.getOrderId() + " уже создан");
        }
        if (order.getCreatedDate().isAfter(order.getScheduledDate())) {
            throw new ValidationException("Должно быть указано будующее время");
        }
        orderRepository.save(order);
    }

    public void update(Order order) throws ValidationException, NotFoundException {
        Order orderSystems = orderRepository.getById(order.getOrderId());
        if (orderSystems != null && orderSystems.getOrderStatus() == OrderStatus.SUBMITTED) {
            throw new NotFoundException("Заказ " + orderSystems.getOrderId() + " уже завершен\nИзменить невозможно");
        }
        if (order.getCreatedDate().isAfter(order.getScheduledDate())) {
            throw new ValidationException("Должно быть указано будующее время");
        }
        if (orderSystems == null) {
            throw new NotFoundException("Заказ с id: " + order.getOrderId() + " не найден");
        }
        orderRepository.save(order);
    }

    public void delete(String id) {
        orderRepository.delete(id);
    }

    public Order getById(String id) throws NotFoundException {
        Order byId = orderRepository.getById(id);
        if (byId == null) {
            throw new NotFoundException("Заказ с id: " + id + " не найден");
        }
        return byId;
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public void submit(String id) {
        orderRepository.submit(id);
    }

    public void editPrice(String id, BigDecimal price){
        orderRepository.editPrice(id, price);
    }

    public void split(String id, BigDecimal count) throws NotFoundException, ValidationException {
        Order order = getById(id);
        if(order.getOrderStatus()==OrderStatus.CLOSED){
            throw new ValidationException("Разделить счет возможно только на этапе submit");
        }
        orderRepository.split(id, order.getTotalPrice().divide(count, ROUND_HALF_UP));
    }

    public void closed(String id){
        orderRepository.closed(id);
    }
}
