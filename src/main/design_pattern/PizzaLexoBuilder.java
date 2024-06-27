package main.design_pattern;
import java.util.*;

public class PizzaLexoBuilder extends PizzaBuilder {
    Pate pate = new Pate("moelleuse", 10.0);
    Sauce sauce = new Sauce("creme", 7.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaLexoBuilder() {
        contenus.add(new Contenu("Mozzarella", 13.0));
        contenus.add(new Contenu("chorizo", 10.0));
        contenus.add(new Contenu("poulet", 20.0));
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
        return "Pizza Lexo";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}
