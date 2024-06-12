package main.design_pattern;

import java.util.List;
import java.util.ArrayList;

public class PizzaNorvegienneBuilder extends PizzaBuilder {
    Pate pate = new Pate("cuite", 10.0);
    Sauce sauce = new Sauce("Huile  d'olive", 10.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaNorvegienneBuilder() {
        contenus.add(new Contenu("saumon", 10.0));
        contenus.add(new Contenu("mozzarella", 10.0));
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
        return "Pizza Norvégienne";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}

