package admin.service.validator;

import admin.model.Crust;
import exception.ValidationException;

public class CrustValidator extends Validator<Crust> {

    @Override
    public void doValidate(Crust item) throws ValidationException {
        super.doValidate(item);
        if (item.getIngredients().isEmpty()) {
            throw new ValidationException("Бортик должен содержать хотя бы один ингредиент");
        }
        if (item.getAllowedPizzas().isEmpty()) {
            throw new ValidationException("Бортик должен быть доступен для хотя бы одной пиццы");
        }
    }
}
