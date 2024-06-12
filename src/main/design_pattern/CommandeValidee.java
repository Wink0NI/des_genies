
package main.design_pattern;
public class CommandeValidee extends EtatCommande {
    public void ajouteProduit(Commande commande, Pizza pizza) {
        return;
    }

    public void retireProduit(Commande commande, Pizza pizza) {
        commande.pizzas.remove(pizza);
    }

    public void efface(Commande commande) {
        commande.pizzas.clear();
    }

    public EtatCommande etatSuivant() {
        return new CommandeLivree();
    }

    public String getEtat() {
        return "validee";
    }
}