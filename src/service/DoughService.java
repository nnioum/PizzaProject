package service;

import model.Dough;
import repository.in_memory.DoughInMemoryRepository;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class DoughService {
    private final Validator<Dough> doughValidator = new Validator<>();
    private final DoughInMemoryRepository doughInMemoryRepository = new DoughInMemoryRepository();

    public boolean create(Dough dough) {
        if (doughValidator.validate(dough)) {
            doughInMemoryRepository.create(dough);
            return true;
        }
        return false;
    }

    public boolean update(String name, Dough dough) {
        List<String> stringList = getByName();
        if (name != null && stringList.contains(name) && doughValidator.validate(dough)) {
            doughInMemoryRepository.update(name, dough);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        List<String> doughList = getByName();
        if (name != null && doughList.contains(name)) {
            doughInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public List<String> getByName() {
        List<Dough> doughList = doughInMemoryRepository.getAll();
        List<String> nameList = new ArrayList<>();
        for (Dough dough : doughList) {
            nameList.add(dough.getName());
        }
        return nameList;
    }

    public List<Integer> getByPrice() {
        List<Dough> doughList = doughInMemoryRepository.getAll();
        List<Integer> priceList = new ArrayList<>();
        for (Dough dough : doughList) {
            priceList.add(dough.getPrice());
        }
        return priceList;
    }
}
