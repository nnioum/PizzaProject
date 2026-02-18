package repository.in_memory;

import model.Crust;
import repository.CrustsInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrustInMemoryRepository implements CrustsInterface {
    private final Map<String, Crust> crusts_by_name = new HashMap<String, Crust>();

    @Override
    public void create(Crust crust) {
        String name = crust.getName();
        crusts_by_name.put(name, crust);

    }

    @Override
    public void update(String name, Crust crust) {
        crusts_by_name.replace(name, crust);

    }

    @Override
    public void delete(String name) {
        crusts_by_name.remove(name);
    }

    @Override
    public Set<String> getAll() {
        return crusts_by_name.keySet();
    }

    @Override
    public Crust getByName(String name) {
        return crusts_by_name.get(name);
    }
}
