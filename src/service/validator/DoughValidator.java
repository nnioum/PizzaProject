package service.validator;

import model.PricedItem;

public abstract class DoughValidator<Dough extends PricedItem> extends Validator<Dough> {
    private final model.Dough classic = new model.Dough("Классическая", 100);

    @Override
    public boolean doValidate(Dough item) {
        double price = item.getPrice() * 0.2;
        return price <= classic.getPrice();
    }
}
