package main.design_pattern;
import java.util.*;

public class PizzaAy0liBuilder extends PizzaBuilder {
    Pate pate = new Pate("fine", 10.0);
    Sauce sauce = new Sauce("tomate", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaAy0liBuilder() {
        contenus.add(new Contenu("Mozzarella", 13.0));
        contenus.add(new Contenu("Chorizo", 10.0));
        contenus.add(new Contenu("Poulet", 20.0));
        contenus.add(new Contenu("steak", 45.0));
        contenus.add(new Contenu("saucisson de cerf", 20.0));
        contenus.add(new Contenu("Aioli", 10.0));
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
        return "Pizza Ay0li";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}
