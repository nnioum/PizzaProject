package repository;

import model.Crust;

import java.util.Map;

public interface CrustsInterface {

    public void create();
    public void update();
    public void delete();
    public Map<String, Crust> getAll();
    public Crust getByName();
}
