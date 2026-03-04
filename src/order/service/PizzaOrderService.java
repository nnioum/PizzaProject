package order.service;

import exception.NotFoundException;
import exception.ValidationException;
import order.model.pizza.PizzaOrder;
import order.repository.PizzaOrderRepository;

import java.util.List;

public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository = PizzaOrderRepository.getInstance();
    private final OrderService orderService = new OrderService();

    public void create(PizzaOrder pizzaOrder) throws ValidationException, NotFoundException {
        validateRequired(pizzaOrder);
        orderService.getById(pizzaOrder.getOrderId());
        pizzaOrderRepository.save(pizzaOrder);
    }

    public void update(PizzaOrder pizzaOrder) {
        pizzaOrderRepository.save(pizzaOrder);
    }

    public void delete(String id) throws NotFoundException, ValidationException {
        if (id == null) {
            throw new ValidationException("Id не должен быть пустым");
        }
        getById(id);
        pizzaOrderRepository.delete(id);
    }

    public PizzaOrder getById(String id) throws NotFoundException {
        PizzaOrder order = pizzaOrderRepository.getById(id);
        if (order == null) {
            throw new NotFoundException("Пицца по id " + id + " не существует");
        }
        return order;
    }

    public List<PizzaOrder> getAll() {
        return pizzaOrderRepository.getAll();
    }

    public List<PizzaOrder> getAllByOrderId(String orderId) throws NotFoundException {
        orderService.getById(orderId);
        return pizzaOrderRepository.getAllByOrderId(orderId);
    }

    private void validateRequired(PizzaOrder pizzaOrder) throws ValidationException {
        if (pizzaOrder.getOrderId() == null) {
            throw new ValidationException("Id заказа не должно быть пустым");
        }
        if (pizzaOrder.getPizzaSize() == null) {
            throw new ValidationException("Размер пиццы введен неправильно. Доступные размеры: <small/medium/large>");
        }
        if (pizzaOrder.getDoughName() == null) {
            throw new ValidationException("Основа для теста не должно быть пустым");
        }
    }
}
