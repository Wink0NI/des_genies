package main.design_pattern;
import java.util.*;

public class Pizza0niteBuilder extends  PizzaBuilder{
    Pate pate = new Pate("Large étiré", 10.0);
    Sauce sauce = new Sauce("creme", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public Pizza0niteBuilder() {
        contenus.add(new Contenu("Mozzarella", 13.0));
        contenus.add(new Contenu("Saumon", 30.0));
        contenus.add(new Contenu("Crevette", 40.0));
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
        return "Pizza 0nite";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}

