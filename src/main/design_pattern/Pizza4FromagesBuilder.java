package main.design_pattern;
import java.util.*;

public class Pizza4FromagesBuilder extends PizzaBuilder {
    Pate pate = new Pate("fine", 10.0);
    Sauce sauce = new Sauce("tomate", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public Pizza4FromagesBuilder() {
        contenus.add(new Contenu("Mozzarella", 13.0));
        contenus.add(new Contenu("Blue d'Auvergne", 10.0));
        contenus.add(new Contenu("Raclette", 12.0));
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
        return "Pizza 4 Fromages";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}