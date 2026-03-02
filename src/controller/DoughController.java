package controller;

import model.Dough;
import service.DoughService;

import java.util.List;

public class DoughController {
    private final DoughService doughService = new DoughService();

    public void create(String name, String stringPrice) {
        if (stringPrice == null) {
            System.out.println("Такого теста нету");
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Dough dough = new Dough(name, price);
        if(!doughService.create(dough)){
            System.out.println("Некорректные данные");
            return;
        }
    }

    public void update(String name, String newName, String stringPrice) {
        if (stringPrice == null) {
            System.out.println("Такого теста нету");
            return;
        }
        int price = Integer.parseInt(stringPrice);
        Dough dough = new Dough(newName, price);
        if(!doughService.update(name, dough)){
            System.out.println("Некорректные данные");
        }
    }

    public void delete(String name) {
        if(!doughService.delete(name)){
            System.out.println("Такого теста нету");
        }
    }

    public Dough getByName(String name){
        return doughService.getByName(name);
    }

    public List<String> getAllNames() {
        return doughService.getAllNames();
    }

}
