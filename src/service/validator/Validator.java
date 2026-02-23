package service.validator;

import model.PricedItem;

public class Validator<T extends PricedItem> {
    public boolean validate(T item) {
        String name = item.getName();
        int price = item.getPrice();

        if (name == null || name.isEmpty()) {
            return false;
        }

        return price > 0;
    }
}
