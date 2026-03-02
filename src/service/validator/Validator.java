package service.validator;

import exception.ValidationException;
import model.PricedItem;

public abstract class Validator<T extends PricedItem> {

    public void validate(T item) throws ValidationException {
        String name = item.getName();
        int price = item.getPrice();

        if (name == null || name.isEmpty()) {
            throw new ValidationException("Имя не может быть пустым");
        }
        if (price <= 0) {
            throw new ValidationException("Цена должна быть положительным числом");
        }
        doValidate(item);
    }

    public void doValidate(T item) throws ValidationException {
    }
}
