package service.validator;

import model.PricedItem;

public interface Validator<T extends PricedItem> {
    public boolean validate(T item);
    public boolean doValidate(T item);
}
