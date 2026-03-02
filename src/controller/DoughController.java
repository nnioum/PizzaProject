package controller;

import exception.NotFoundException;
import exception.ValidationException;
import model.Dough;
import service.DoughService;

import java.util.List;

public class DoughController {
    private final DoughService doughService = new DoughService();

    public Dough create(String name, String priceStr) throws ValidationException, NotFoundException {
        return doughService.create(name, priceStr);
    }

    public Dough update(String name, String newName, String stringPrice) throws ValidationException, NotFoundException {
        return doughService.update(name, stringPrice, newName);
    }

    public void delete(String name) throws ValidationException, NotFoundException {
        doughService.delete(name);
    }

    public Dough getByName(String name) throws NotFoundException {
        return doughService.getByName(name);
    }

    public List<String> getAllNames() {
        return doughService.getAllNames();
    }

}
