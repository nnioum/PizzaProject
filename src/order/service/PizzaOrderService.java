package order.service;

import admin.model.Ingredient;
import admin.model.Pizza;
import admin.service.DoughService;
import admin.service.IngredientService;
import admin.service.PizzaService;
import exception.NotFoundException;
import exception.ValidationException;
import order.model.pizza.*;
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
        pizzaOrder.setPrice(BigDecimal.valueOf(doughService.getByName(pizzaOrder.getDoughName()).getPrice())
                .multiply(pizzaOrder.getPizzaSize().getPriceMultiplier()));
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
        BigDecimal price = new BigDecimal("0.00");
        price = price.add(BigDecimal.valueOf(doughService.getByName(pizzaOrder.getDoughName()).getPrice()));
        switch (pizzaOrder.getPizzaType()) {
            case READY:
                ReadyPizzaOrder readyPizzaOrder = (ReadyPizzaOrder) pizzaOrder;
                Pizza readyPizza = pizzaService.getByName(readyPizzaOrder.getName());
                price = price.add(BigDecimal.valueOf(readyPizza.getPrice() - readyPizza.getDough().getPrice()));

                Set<String> doubledIngredients = readyPizzaOrder.getDoubledIngredients();
                if (doubledIngredients != null) {
                    for (String nameIngredient : doubledIngredients) {
                        price = price.add(BigDecimal.valueOf(ingredientService.getByName(nameIngredient).getPrice()));
                    }
                }
                break;

            case CUSTOM:
                CustomPizzaOrder customPizzaOrder = (CustomPizzaOrder) pizzaOrder;

                Set<String> customIngredients = customPizzaOrder.getIngredients();
                if (customIngredients != null) {
                    for (String nameIngredient : customIngredients) {
                        price = price.add(BigDecimal.valueOf(ingredientService.getByName(nameIngredient).getPrice()));
                    }
                }
                break;

            case HALVED:
                HalvedPizzaOrder halvedPizzaOrder = (HalvedPizzaOrder) pizzaOrder;
                Pizza pizzaRight = pizzaService.getByName(halvedPizzaOrder.getRightHalf().getName());
                Pizza pizzaLeft = pizzaService.getByName(halvedPizzaOrder.getLeftHalf().getName());

                price = price.add(BigDecimal.valueOf((pizzaRight.getPrice() - pizzaRight.getDough().getPrice()) / 2));
                if (halvedPizzaOrder.getRightHalf().getDoubledIngredients() != null) {
                    for (String doubleIngredient : halvedPizzaOrder.getRightHalf().getDoubledIngredients()) {
                        price = price.add(BigDecimal.valueOf((ingredientService.getByName(doubleIngredient).getPrice()) / 2));
                    }
                }

                price = price.add(BigDecimal.valueOf((pizzaLeft.getPrice() - pizzaLeft.getDough().getPrice()) / 2));
                if (halvedPizzaOrder.getRightHalf().getDoubledIngredients() != null) {
                    for (String doubleIngredient : halvedPizzaOrder.getLeftHalf().getDoubledIngredients()) {
                        price = price.add(BigDecimal.valueOf((ingredientService.getByName(doubleIngredient).getPrice()) / 2));
                    }
                }
                break;

            case SLICED:
                SlicedPizzaOrder slicedPizzaOrder = (SlicedPizzaOrder) pizzaOrder;
                if (slicedPizzaOrder.getPizzaSize() != null) {
                    for (SlicedPizzaOrder.Slice slice : slicedPizzaOrder.getSlices()) {
                        if (slice.getIngredients() != null) {
                            for (String ingredientSlice : slice.getIngredients()) {
                                Ingredient ingredient = ingredientService.getByName(ingredientSlice);
                                price = price.add(BigDecimal.valueOf(ingredient.getPrice() / pizzaOrder.getPizzaSize().getSliceNumber()));
                            }
                        }
                    }
                }
                break;
        }
        pizzaOrder.setPrice(price.multiply(pizzaOrder.getPizzaSize().getPriceMultiplier()));
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
                String name = params.get("name");
                String doubledIngredients = params.get("doubled-ingredients");
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
                String customName = params.get("name");
                String ingredients = params.get("ingredients");
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
                    existingIngredients.clear();
                    existingIngredients.addAll(ingredientSet);
                    isCustomChanged = true;
                }
                custom.setValid(!custom.getIngredients().isEmpty());
                if (isCustomChanged) {
                    calculatePrice(custom);
                }
                break;
            case HALVED:
                boolean isHalvedChanged = false;
                HalvedPizzaOrder halved = (HalvedPizzaOrder) pizzaOrder;

                String halvedNameRight = params.get("right-half-name");
                String ingredientsDoubleRight = params.get("right-half-doubled-ingredients");
                if (halvedNameRight == null) {
                    halvedNameRight = halved.getRightHalf().getName();
                }

                String halvedNameLeft = params.get("right-half-name");
                String ingredientsDoubleLeft = params.get("right-half-doubled-ingredients");
                if (halvedNameLeft == null) {
                    halvedNameLeft = halved.getLeftHalf().getName();
                }
                isHalvedChanged = buildPizzaHalf(halved, params, halvedNameRight, ingredientsDoubleRight, true) ||
                        buildPizzaHalf(halved, params, halvedNameLeft, ingredientsDoubleLeft, true);

                if (isHalvedChanged) {
                    calculatePrice(halved);
                }

                break;
            case SLICED:

                break;
            default:
                break;
        }
    }

    private boolean buildPizzaHalf(HalvedPizzaOrder halved, Map<String, String> params,
                                   String halvedName, String ingredientsDouble, boolean sizeRight) throws NotFoundException {
        boolean isHalvedChanged = false;
        Set<String> existingIngredients;
        String name;

        Set<Ingredient> ingredientsPizza = pizzaService.getByName(halvedName).getIngredients();
        if (ingredientsDouble != null && !ingredientsDouble.isEmpty()) {
            Set<String> ingredients = Arrays.stream(ingredientsDouble.split(",")).collect(Collectors.toSet());
            for (String ingredient : ingredients) {
                Ingredient existing = ingredientService.getByName(ingredient);
                if (!ingredientsPizza.contains(existing)) {
                    throw new NotFoundException("Ингредиент " + ingredient + " не найден в пицце " + halvedName);
                }
            }
            if (sizeRight) {
                existingIngredients = halved.getRightHalf().getDoubledIngredients();
                name = halved.getRightHalf().getName();
            } else {
                existingIngredients = halved.getLeftHalf().getDoubledIngredients();
                name = halved.getLeftHalf().getName();
            }
            existingIngredients.clear();
            existingIngredients.addAll(ingredients);
            isHalvedChanged = true;
        }
        name = halvedName;
        return isHalvedChanged;
    }
}
