package admin.service;

import exception.NotFoundException;
import exception.ValidationException;
import admin.model.Dough;
import admin.repository.in_memory.DoughInMemoryRepository;
import admin.service.validator.DoughBaseValidator;
import admin.service.validator.Validator;

import java.util.ArrayList;
import java.util.List;

import static admin.data.GlobalData.CLASSIC_DOUGH;


public class DoughService {

    private final Validator<Dough> doughValidator = new DoughBaseValidator<>() {
    };

    private final DoughInMemoryRepository doughInMemoryRepository = DoughInMemoryRepository.getInstance();

    public Dough create(String name, String priceStr) throws ValidationException, NotFoundException {
        Dough dough = buildDough(name, priceStr);
        doughValidator.validate(dough);
        if (doughInMemoryRepository.getByName(name) != null) {
            throw new ValidationException("Основа для пиццы с именем " + name + " уже существует");
        }
        doughInMemoryRepository.create(dough);
        return dough;
    }

    public Dough update(String name, String priceStr, String newName) throws ValidationException, NotFoundException {
        Dough dough = buildDough(name, priceStr, true, newName);
        doughValidator.validate(dough);
        doughInMemoryRepository.update(name, dough);
        return dough;
    }

    public void delete(String name) throws NotFoundException, ValidationException {
        getByName(name);
        if (name.equals(CLASSIC_DOUGH.getName())) {
            throw new ValidationException("Классическую основу для пиццы удалить нельзя");
        }
        doughInMemoryRepository.delete(name);
    }

    public Dough getByName(String name) throws NotFoundException {
        Dough dough = doughInMemoryRepository.getByName(name);
        if (dough == null) {
            throw new NotFoundException("Тесто с именем " + name + " не найдено");
        }
        return dough;
    }

    public List<String> getAllNames() {
        List<Dough> doughList = doughInMemoryRepository.getAll();
        List<String> nameList = new ArrayList<>();
        for (Dough dough : doughList) {
            nameList.add(dough.getName());
        }
        return nameList;
    }

    private Dough buildDough(String name, String priceStr) throws ValidationException, NotFoundException {
        return buildDough(name, priceStr, false, null);
    }

    private Dough buildDough(String name, String priceStr, boolean isExisting, String newName) throws ValidationException, NotFoundException {
        if (name == null) {
            throw new ValidationException("Имя основы для пиццы - обязательное поле");
        }
        Integer price = parsePrice(priceStr);
        if (isExisting) {
            Dough existingDough = doughInMemoryRepository.getByName(name);
            if (existingDough == null) {
                throw new NotFoundException("Основа для пиццы с именем " + name + " не найден");
            }
            newName = newName == null ? existingDough.getName() : newName;
            price = price == null ? existingDough.getPrice() : price;
            if (name.equals(CLASSIC_DOUGH.getName())) {
                newName = name;
            }
            return new Dough(newName, price);
        }
        if (price == null) {
            throw new ValidationException("Цена - обязательное поле и должна быть положительным числом");
        }
        return new Dough(name, price);
    }

    private Integer parsePrice(String priceStr) throws ValidationException {
        if (priceStr == null) {
            return null;
        }
        try {
            return Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            throw new ValidationException("Цена должна быть валидным положительным числом");
        }
    }
}
