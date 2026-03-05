package order.controller;

import exception.NotFoundException;
import exception.ValidationException;
import order.controller.factory.PizzaOrderFactory;
import order.model.pizza.PizzaOrder;
import order.service.PizzaOrderService;

import java.util.List;
import java.util.Map;

public class PizzaOrderController {
    private final PizzaOrderService pizzaOrderService = new PizzaOrderService();
    private final PizzaOrderFactory pizzaOrderFactory = new PizzaOrderFactory();

    public String create(String orderId, String typePizza, String size, String doughName) throws ValidationException, NotFoundException {
        PizzaOrder pizzaOrder = pizzaOrderFactory.buildPizzaOrder(typePizza, size, doughName, orderId);
        pizzaOrderService.create(pizzaOrder);
        return  pizzaOrder.getId();
    }

    public void update(String id, Map<String, String> params) throws NotFoundException, ValidationException {
        pizzaOrderService.update(id, params);
    }

    public void delete(String id) throws ValidationException, NotFoundException {
        pizzaOrderService.delete(id);
    }

    public PizzaOrder getById(String id) throws NotFoundException {
        return pizzaOrderService.getById(id);
    }

    public List<PizzaOrder> getAllByOrderId(String orderId) throws NotFoundException {
        return pizzaOrderService.getAllByOrderId(orderId);
    }
}
