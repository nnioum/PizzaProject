package service.validator;

import model.PricedItem;

public interface InterfaceValidator<T extends PricedItem> {
    public boolean validate(T item);
    public boolean doValidate(T item);
}
