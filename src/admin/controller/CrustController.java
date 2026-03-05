package admin.controller;

import admin.model.Crust;
import admin.service.CrustService;
import exception.NotFoundException;
import exception.ValidationException;

import java.util.List;
import java.util.Set;

public class CrustController {
    private final CrustService crustService = new CrustService();

    public Crust create(String name, String priceStr, Set<String> ingredients, Set<String> allowedPizzas) throws ValidationException, NotFoundException {
        return crustService.create(name, priceStr, ingredients, allowedPizzas);
    }

    public Crust update(String name, String stringPrice, String newName, Set<String> ingredients, Set<String> allowedPizzas) throws ValidationException, NotFoundException {
        return crustService.update(name, stringPrice, newName, ingredients, allowedPizzas);
    }

    public void delete(String name) throws ValidationException, NotFoundException {
        crustService.delete(name);
    }

    public Crust getByName(String name) throws NotFoundException {
        return crustService.getByName(name);
    }

    public List<String> getAllNames() {
        return crustService.getAllNames();
    }
}
