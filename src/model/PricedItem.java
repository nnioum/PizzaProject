package model;

import java.util.Objects;

public class PricedItem {
    private String name;
    private int price;

    public PricedItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PricedItem pricedItem = (PricedItem) o;
        return price == pricedItem.price && Objects.equals(name, pricedItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Base{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}