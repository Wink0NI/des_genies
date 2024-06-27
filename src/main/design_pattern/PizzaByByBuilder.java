package main.design_pattern;
import java.util
.*;
public class PizzaByByBuilder extends PizzaBuilder {
    Pate pate = new Pate("fine", 10.0);
    Sauce sauce = new Sauce("creme", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaByByBuilder() {
        contenus.add(new Contenu("steak", 45.0));
        contenus.add(new Contenu("Blue d'Auvergne", 10.0));
        contenus.add(new Contenu("feuille d'or", 100.0));
        contenus.add(new Contenu("Ementale", 16.0));
    }

    public void buildPate() {
        pizza.setPate(pate);
    }

    public void buildSauce() {
        pizza.setSauce(sauce);
    }

    public void buildContenu() {
        for (Contenu contenu : contenus) {
            pizza.addContenu(contenu);
        }
    }

    public String toString() {
        return "Pizza ByBy";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}
