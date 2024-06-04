public class Contenu implements Ingredient {
    private String name = "";
    private double prix;

    public Contenu(String name, double prix) {
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
