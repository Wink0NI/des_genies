class CommandeLivree extends EtatCommande {
    public void ajouteProduit(Commande commande, Pizza pizza) {
        return;
    }

    public void retireProduit(Commande commande, Pizza pizza) {
        return;
    }

    public void efface(Commande commande) {
        return;
    }

    public EtatCommande etatSuivant() {
        return this;
    }

    public String getEtat() {
        return "livree";
    }
}