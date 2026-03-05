package admin.repository.in_memory;

import admin.model.Dough;
import admin.repository.DoughRepository;

import java.util.*;

import static admin.data.GlobalData.CLASSIC_DOUGH;

public class DoughInMemoryRepository implements DoughRepository {
    private final Map<String, Dough> DOUGH_BY_NAME;
    private static final DoughInMemoryRepository INSTANCE = new DoughInMemoryRepository();

    private DoughInMemoryRepository() {
        DOUGH_BY_NAME = new HashMap<>();
        DOUGH_BY_NAME.put(CLASSIC_DOUGH.getName(), CLASSIC_DOUGH);
    }

    public static DoughInMemoryRepository getInstance() {
        return INSTANCE;
    }

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
