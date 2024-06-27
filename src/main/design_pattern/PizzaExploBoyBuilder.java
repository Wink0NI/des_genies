package main.design_pattern;
import java.util.*;

public class PizzaExploBoyBuilder extends PizzaBuilder {
    Pate pate = new Pate("Super epaisse", 30.0);
    Sauce sauce = new Sauce("tomate", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaExploBoyBuilder() {
        contenus.add(new Contenu("Pomme de terre", 3.0));
        contenus.add(new Contenu("Saucisson de cerf", 20.0));
        contenus.add(new Contenu("Vodka", 15.0));
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
        return "Pizza ExploBoy";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}