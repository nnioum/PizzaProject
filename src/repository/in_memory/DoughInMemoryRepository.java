package repository.in_memory;

import model.Dough;
import repository.DoughsInterface;

import java.util.*;

public class DoughInMemoryRepository implements DoughsInterface {
    private final Map<String, Dough> Dough_BY_NAME = new HashMap<String, Dough>();

    @Override
    public void create(Dough dough) {
        String name = dough.getName();
        Dough_BY_NAME.put(name, dough);

    }

    @Override
    public void update(String name, Dough dough) {
        Dough_BY_NAME.replace(name, dough);

    }

    @Override
    public void delete(String name) {
        Dough_BY_NAME.remove(name);
    }

    @Override
    public List<Dough> getAll() {
        return new ArrayList<Dough>(Dough_BY_NAME.values());
    }

    @Override
    public Dough getByName(String name) {
        return Dough_BY_NAME.get(name);
    }
}
