package main.design_pattern;

public class Sauce implements Ingredient {
    private String name = "";
    private double prix;

    public Sauce(String name, double prix) {
        this.name = name;
        this.prix = prix;
    }

    public String getName() {
        return name;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return name;
    }

}
