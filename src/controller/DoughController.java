package controller;

import model.Dough;
import service.DoughService;

import java.util.List;

public class DoughController {
    private final DoughService doughService = new DoughService();

    public void create(String name, String stringPrice) {
        if (stringPrice == null) {
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Dough dough = new Dough(name, price);
        doughService.create(dough);
    }

    public void update(String name, String newName, String stringPrice) {
        if (stringPrice == null) {
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Dough dough = new Dough(newName, price);
        doughService.update(name, dough);
    }

    public void delete(String name) {
        doughService.delete(name);
    }

    public List<String> getAllNames() {
        return doughService.getAllNames();
    }

}
