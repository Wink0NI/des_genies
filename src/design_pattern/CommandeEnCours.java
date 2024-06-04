public class CommandeEnCours extends EtatCommande {
    public void ajouteProduit(Commande commande, Pizza pizza) {
        commande.pizzas.add(pizza);
    }

    public void retireProduit(Commande commande, Pizza pizza) {
        commande.pizzas.remove(pizza);
    }

    public void efface(Commande commande) {
        commande.pizzas.clear();
    }

    public EtatCommande etatSuivant() {
        return new CommandeValidee();
    }
}