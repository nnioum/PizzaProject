package repository.in_memory;

import model.Dough;
import repository.DoughRepository;

import java.util.*;

public class DoughInMemoryRepository implements DoughRepository {
    private final Map<String, Dough> DOUGH_BY_NAME = new HashMap<String, Dough>();

    @Override
    public void create(Dough dough) {
        String name = dough.getName();
        DOUGH_BY_NAME.put(name, dough);

    }

    @Override
    public void update(String name, Dough dough) {
        delete(name);
        create(dough);

    }

    @Override
    public void delete(String name) {
        DOUGH_BY_NAME.remove(name);
    }

    @Override
    public List<Dough> getAll() {
        return new ArrayList<Dough>(DOUGH_BY_NAME.values());
    }

    @Override
    public Dough getByName(String name) {
        return DOUGH_BY_NAME.get(name);
    }
}
