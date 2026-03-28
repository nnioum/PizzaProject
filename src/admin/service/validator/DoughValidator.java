package admin.service.validator;

import admin.model.Dough;
import exception.ValidationException;
import admin.model.PricedItem;

import static admin.data.GlobalData.CLASSIC_DOUGH;


public class DoughValidator extends Validator<Dough> {
    @Override
    public void doValidate(Dough item) throws ValidationException {
        if (item.getPrice() > CLASSIC_DOUGH.getPrice() * 1.2) {
            throw new ValidationException("Цена для новой основы не должна быть более чем на 20% больше классической");
        }
    }
}
