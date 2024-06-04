import java.util.List;
import java.util.ArrayList;

public class PizzaHawaienneBuilder extends PizzaBuilder {
    Pate pate = new Pate("moelleuse", 10.0);
    Sauce sauce = new Sauce("douce", 5.0);
    List<Contenu> contenus = new ArrayList<Contenu>();

    public PizzaHawaienneBuilder() {
        contenus.add(new Contenu("jambon", 5.0));
        contenus.add(new Contenu("ananas", 5.0));
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
        return "Pizza Hawaienne";
    }

    public double getPrix() {
        double contenu_somme = 0.0;
        for (Contenu ct : contenus) {
            contenu_somme += ct.getPrix();
        }
    
        return pate.getPrix() + sauce.getPrix() + contenu_somme;
    }
}