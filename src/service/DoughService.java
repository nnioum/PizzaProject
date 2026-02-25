package service;

import model.Dough;
import repository.in_memory.DoughInMemoryRepository;
import service.validator.DoughBaseValidator;
import service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class DoughService {
    public static final Dough CLASSIC_DOUGH = new Dough("Классическая", 100);

    private final Validator<Dough> doughValidator = new DoughBaseValidator<>() {
    };

    private final DoughInMemoryRepository doughInMemoryRepository = new DoughInMemoryRepository();

    public boolean create(Dough dough) {
        if (doughInMemoryRepository.getByName(dough.getName()) == null && doughValidator.validate(dough)) {
            doughInMemoryRepository.create(dough);
            return true;
        }
        return false;
    }

    public boolean update(String name, Dough dough) {
        if (name == null) {
            return false;
        }
        if (name.equals(CLASSIC_DOUGH.getName())) {
            dough.setName(name);
        }
        if (doughInMemoryRepository.getByName(name) != null && doughValidator.validate(dough)) {
            doughInMemoryRepository.update(name, dough);
            return true;
        }
        return false;
    }

    public boolean delete(String name) {
        if (name == null) {
            return false;
        }
        if (name.equals(CLASSIC_DOUGH.getName())) {
            return false;
        }
        if (doughInMemoryRepository.getByName(name) != null) {
            doughInMemoryRepository.delete(name);
            return true;
        }
        return false;
    }

    public Dough getByName(String name) {
        return doughInMemoryRepository.getByName(name);
    }

    public List<String> getByAll() {
        List<Dough> doughList = doughInMemoryRepository.getAll();
        List<String> nameList = new ArrayList<>();
        for (Dough dough : doughList) {
            nameList.add(dough.getName());
        }
        return nameList;
    }
}
