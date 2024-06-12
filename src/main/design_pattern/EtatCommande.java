
package main.design_pattern;
public abstract class EtatCommande {
    public abstract void ajouteProduit(Commande commande, Pizza pizza);
    public abstract void retireProduit(Commande commande, Pizza pizza);
    public abstract void efface(Commande commande);
    public abstract EtatCommande etatSuivant();
    public abstract String getEtat();
}