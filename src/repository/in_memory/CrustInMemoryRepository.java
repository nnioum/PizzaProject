package repository.in_memory;

import model.Crust;
import repository.CrustsInterface;

import java.util.Map;

public class CrustInMemoryRepository implements CrustsInterface {
    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Map<String, Crust> getAll() {
        return Map.of();
    }

    @Override
    public Crust getByName() {
        return null;
    }
}
