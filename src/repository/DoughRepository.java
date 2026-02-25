package repository;

import model.Dough;

import java.util.List;

public interface DoughRepository {

    void create(Dough dough);
    void update(String name, Dough dough);
    void delete(String name);
    List<Dough> getAll();
    Dough getByName(String name);
}
