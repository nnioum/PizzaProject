package admin.repository;

import admin.model.Crust;
import admin.model.Dough;

import java.util.List;

public interface CrustRepository {

    void create(Crust crust);
    void update(String name, Crust crust);
    void delete(String name);
    List<Crust> getAll();
    Crust getByName(String name);
}
