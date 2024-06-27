package main.design_pattern;

import java.util.List;
import java.util.ArrayList;

public class PizzaSpecialBuilder extends PizzaBuilder {
    Pate pate = new Pate("Croute epaisse", 15.0);
    Sauce sauce = new Sauce("Creme", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaSpecialBuilder() {
        contenus.add(new Contenu("Noix de Jambon", 10.0));
        contenus.add(new Contenu("mozzarella", 13.0));
        contenus.add(new Contenu("pomme de terre", 3.0));
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
        return "Pizza Spécial";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}

