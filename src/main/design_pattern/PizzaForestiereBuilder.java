package main.design_pattern;
import java.util.*;

public class PizzaForestiereBuilder extends PizzaBuilder {
    Pate pate = new Pate("fine", 10.0);
    Sauce sauce = new Sauce("tomate", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaForestiereBuilder() {
        contenus.add(new Contenu("jambon", 5.0));
        contenus.add(new Contenu("champignon", 7.0));
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
        return "Pizza Forestiere";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}