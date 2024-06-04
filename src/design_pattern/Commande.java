import java.util.ArrayList;
import java.util.List;

public class Commande {
    private EtatCommande etatCommande;
    protected List<Pizza> pizzas = new ArrayList<>();

    public Commande() {
        this.etatCommande = new CommandeEnCours();
    }

    public String getEtat() {
        return etatCommande.getEtat();
    }

    public void ajouteProduit(Pizza pizza) {
        etatCommande.ajouteProduit(this, pizza);
    }

    public void retireProduit(Pizza pizza) {
        etatCommande.retireProduit(this, pizza);
    }

    public void efface() {
        etatCommande.efface(this);
    }

    public void etatSuivant() {
        etatCommande = etatCommande.etatSuivant();
    }

    public void affiche() {
        for (Pizza pizza : pizzas) {
            System.out.println("- " + pizza.toString());
        }
    }

    public void afficheWNbre() {
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            System.out.println(
                    String.format(
                            "%d - %s (%.2f$)", i + 1, pizza.toString(), pizza.getPrix()));
        }
    }

}