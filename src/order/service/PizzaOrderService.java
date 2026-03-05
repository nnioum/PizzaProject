package order.service;

import admin.model.Pizza;
import admin.service.DoughService;
import admin.service.IngredientService;
import admin.service.PizzaService;
import exception.NotFoundException;
import exception.ValidationException;
import order.model.pizza.CustomPizzaOrder;
import order.model.pizza.PizzaOrder;
import order.model.pizza.ReadyPizzaOrder;
import order.repository.PizzaOrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static order.model.pizza.PizzaType.*;

public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository = PizzaOrderRepository.getInstance();
    private final OrderService orderService = new OrderService();
    private final IngredientService ingredientService = new IngredientService();
    private final PizzaService pizzaService = new PizzaService();
    private final DoughService doughService = new DoughService();

    public void create(PizzaOrder pizzaOrder) throws ValidationException, NotFoundException {
        validateRequired(pizzaOrder);
        orderService.getById(pizzaOrder.getOrderId());
        pizzaOrderRepository.save(pizzaOrder);
    }

    public PizzaOrder update(String id, Map<String, String> params) throws NotFoundException {
        PizzaOrder existingPizzaOrder = getById(id);
        setParamsForType(existingPizzaOrder, params);
        pizzaOrderRepository.save(existingPizzaOrder);
        return existingPizzaOrder;
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

    private void validateRequired(PizzaOrder pizzaOrder) throws ValidationException, NotFoundException {
        if (pizzaOrder.getOrderId() == null) {
            throw new ValidationException("Id заказа не должно быть пустым");
        }
        if (pizzaOrder.getPizzaSize() == null) {
            throw new ValidationException("Размер пиццы введен неправильно. Доступные размеры: <small/medium/large>");
        }
        if (pizzaOrder.getDoughName() == null) {
            throw new ValidationException("Основа для теста не должно быть пустым");
        }
        doughService.getByName(pizzaOrder.getDoughName());
    }

    private void calculatePrice(PizzaOrder pizzaOrder) throws NotFoundException {
        BigDecimal price;
        switch (pizzaOrder.getPizzaType()) {
            case READY:
                double readyPrice = 0.0;
                ReadyPizzaOrder readyPizzaOrder = (ReadyPizzaOrder) pizzaOrder;
                Pizza readyPizza = pizzaService.getByName(readyPizzaOrder.getName());
                readyPrice += readyPizza.getPrice() - readyPizza.getDough().getPrice();
                readyPrice += doughService.getByName(readyPizzaOrder.getDoughName()).getPrice();
                Set<String> doubledIngredients = readyPizzaOrder.getDoubledIngredients();
                for (String nameIngredient : doubledIngredients) {
                    readyPrice += ingredientService.getByName(nameIngredient).getPrice();
                }
                price = new BigDecimal(readyPrice).setScale(2, RoundingMode.HALF_UP);
                break;

            case CUSTOM:
                double customPrice = 0.0;
                CustomPizzaOrder customPizzaOrder = (CustomPizzaOrder) pizzaOrder;
                customPrice += doughService.getByName(customPizzaOrder.getDoughName()).getPrice();
                Set<String> customIngredients = customPizzaOrder.getIngredients();
                for(String nameIngredient: customIngredients){
                    customPrice += ingredientService.getByName(nameIngredient).getPrice();
                }
                price = new BigDecimal(customPrice).setScale(2, RoundingMode.HALF_UP);
                break;

            case HALVED:
            case SLICED:
        }
    }

    // Example command: id=<id>,
    // For Ready: --name=<name>, --doubled-ingredients=<1,2>
    // For Custom: --name=<name>, --ingredients=<1,2>
    // For Halved: --left-half-name=<name>, --left-half-doubled-ingredients=<1,2>, --right-half-name=<name>, --right-half-doubled-ingredients=<1,2>
    // For Sliced = --slice-number=<1> --ingredients=<1,2>


    private void setParamsForType(PizzaOrder pizzaOrder, Map<String, String> params) throws NotFoundException {
        switch (pizzaOrder.getPizzaType()) {
            case READY:
                ReadyPizzaOrder ready = (ReadyPizzaOrder) pizzaOrder;
                String name = params.get("--name");
                String doubledIngredients = params.get("--doubled-ingredients");
                boolean isChanged = false;
                if (name != null) {
                    ready.setName(name);
                    ready.setValid(true);
                    isChanged = true;
                }
                if (doubledIngredients != null && !doubledIngredients.isEmpty()) {
                    Set<String> doubled = Arrays.stream(doubledIngredients.split(",")).collect(Collectors.toSet());
                    Set<String> existingDoubled = ready.getDoubledIngredients();
                    existingDoubled.clear();
                    existingDoubled.addAll(doubled);
                    isChanged = true;
                }
                if (isChanged) {
                    calculatePrice(ready);
                }
                break;
            case CUSTOM:
                CustomPizzaOrder custom = (CustomPizzaOrder) pizzaOrder;
                String customName = params.get("--name");
                String ingredients = params.get("--ingredients");
                boolean isCustomChanged = false;
                if (customName != null) {
                    custom.setDisplayName(customName);
                    isCustomChanged = true;
                }
                if (ingredients != null && !ingredients.isEmpty()) {
                    Set<String> ingredientSet = Arrays.stream(ingredients.split(",")).collect(Collectors.toSet());
                    for (String ingredientName : ingredientSet) {
                        ingredientService.getByName(ingredientName);
                    }
                    Set<String> existingIngredients = custom.getIngredients();
                    existingIngredients.clear();// зачем?
                    existingIngredients.addAll(ingredientSet);
                    isCustomChanged = true;
                }
                custom.setValid(!custom.getIngredients().isEmpty());
                if (isCustomChanged) {
                    calculatePrice(custom);
                }
                break;
            case HALVED:

                break;
            case SLICED:

                break;
            default:
                break;
        }
    }
}
