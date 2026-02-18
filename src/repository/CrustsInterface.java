package repository;

import model.Crust;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrustsInterface {

    public void create(Crust crust);
    public void update(String name, Crust crust);
    public void delete(String name);
    public Set<String> getAll();
    public Crust getByName(String name);
}
