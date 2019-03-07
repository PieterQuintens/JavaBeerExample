package be.pxl.student.model;

import java.util.Objects;

public class Beer {
    int id;
    String name;
    int brewerId;
    int categorieId;
    int stock;
    float alcohol;
    float price;

    public Beer(int id, String name, int brewerId, int categorieId, int stock, float alcohol, float price) {
        this.id = id;
        this.name = name;
        this.brewerId = brewerId;
        this.categorieId = categorieId;
        this.stock = stock;
        this.alcohol = alcohol;
        this.price = price;
    }

    public Beer(int id, String name, float alcohol, float price) {
        this.id = id;
        this.name = name;
        this.alcohol = alcohol;
        this.price = price;
    }

    public Beer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBrewerId() {
        return brewerId;
    }

    public void setBrewerId(int brewerId) {
        this.brewerId = brewerId;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Float.compare(beer.getAlcohol(), getAlcohol()) == 0 &&
                Float.compare(beer.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), beer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAlcohol(), getPrice());
    }
}
