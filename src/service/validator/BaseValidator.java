package service.validator;

import model.PricedItem;

public abstract class BaseValidator<T extends PricedItem> implements Validator<T> {
    @Override
    public boolean validate(T item) {
        String name = item.getName();
        int price = item.getPrice();

        if (name == null || name.isEmpty()) {
            return false;
        }
        if (price <= 0) {
            return false;
        }
        return doValidate(item);
    }

    @Override
    public boolean doValidate(T item) {
        return true;
    }
}
