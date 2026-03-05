package admin.repository.in_memory;

import admin.model.Crust;
import admin.model.Dough;
import admin.repository.CrustRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrustInMemoryRepository implements CrustRepository {

    private final Map<String, Crust> CRUST_BY_NAME;
    private static final CrustInMemoryRepository INSTANCE = new CrustInMemoryRepository();

    private CrustInMemoryRepository() {
        CRUST_BY_NAME = new HashMap<>();
    }

    public static CrustInMemoryRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(Crust crust) {
        String name = crust.getName();
        CRUST_BY_NAME.put(name, crust);

    }

    @Override
    public void update(String name, Crust crust) {
        delete(name);
        create(crust);

    }

    @Override
    public void delete(String name) {
        CRUST_BY_NAME.remove(name);
    }

    @Override
    public List<Crust> getAll() {
        return new ArrayList<Crust>(CRUST_BY_NAME.values());
    }

    @Override
    public Crust getByName(String name) {
        return CRUST_BY_NAME.get(name);
    }
}
