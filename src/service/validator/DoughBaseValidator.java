package service.validator;

import model.PricedItem;

import static service.DoughService.CLASSIC_DOUGH;


public abstract class DoughBaseValidator<Dough extends PricedItem> extends BaseValidator<Dough> {
    @Override
    public boolean doValidate(Dough item) {
        double price = item.getPrice() * 0.2;
        return price <= CLASSIC_DOUGH.getPrice();
    }
}
