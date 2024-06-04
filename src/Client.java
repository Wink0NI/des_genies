import java.util.Scanner;

import process.SysGestion;
import process.DBProcess;

import java.util.List;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Client {
    private SysGestion gestion;
    private Scanner scanner = new Scanner(System.in);

    private Serveur pb = new Serveur();
    private DBProcess dbProcess = new DBProcess();

    private List<Commande> commandes = new ArrayList<Commande>();
    private List<PizzaBuilder> pizzaBuilders = new ArrayList<PizzaBuilder>();

    public Client() {
        pizzaBuilders.add(new PizzaHawaienneBuilder());
        pizzaBuilders.add(new PizzaNorvegienneBuilder());
    }

    public void interfaceEntree() {
        String entree = "";

        while (!entree.equals("q")) {
            gestion.clear();

            System.out.println("BIENVENUE CHEZ PizzaMania, Appuyez sur Entree pour commander...");
            entree = scanner.nextLine().toLowerCase();

            if (entree.equals("q")) {
                System.out.println("Au revoir...");
            } else if (entree.equals("admin")) {
                System.out.println(
                        "----------------------------------------------------------------------------------------------------------------------");
                System.out.println("MODE ADMIN");

                System.out.println("Nom admin:");
                String nom = scanner.nextLine();

                System.out.println("Mot de passe:");
                String mdp = scanner.nextLine();

                if (dbProcess.connexion(nom, mdp)) {
                    interfaceAdmin(nom);
                } else {
                    System.out.println("Erreur: Identifiant incorrect.");
                }
            } else {
                interfaceCommande();
            }
            gestion.wait(2000);
        }
    }

    private void interfaceCommande() {
        String entree = "";
        Commande commande = new Commande();

        while (true) {
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Commande:");

            for (int i = 0; i < pizzaBuilders.size(); i++) {
                PizzaBuilder pb = pizzaBuilders.get(i);
                System.out.println(
                        String.format(
                                "%d - %s (%.2f$)", i + 1, pb.toString(), pb.getPrix()));
            }

            System.out.println("R - Enlever une pizza");
            if (commande.pizzas.size() > 0) {
                System.out.println("F - Finaliser la commande");
            }
            System.out.println("A - Annuler la commande");

            entree = scanner.nextLine().toLowerCase();

            if (entree.equals("a")) {
                System.out.println("Au revoir...");
                return;
            } else if (entree.equals("f")) {
                if (commande.pizzas.size() == 0) {
                    System.out.println("Erreur de commande: Une erreur est survenue.");
                } else {
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Résumé de la commande...");
                    commande.affiche();

                    System.out.println("Confirmer l'achat ?? o/n");

                    entree = scanner.nextLine().toLowerCase();
                    if (entree.equals("o")) {
                        commande.etatSuivant();
                        commandes.add(commande);

                        System.out.println(
                                "Votre commande est passé en état validé. Vous pouvez toujours le modifier en appelant un staff.");
                        gestion.wait(2000);
                        return;
                    }
                }
            } else if (entree.equals("r")) {
                while (true) {
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Votre commande:");
                    if (commande.pizzas.size() == 0) {
                        System.out.println("Vide...");
                        gestion.wait(2000);
                        break;
                    } else {
                        commande.afficheWNbre();
                        System.out.println("Sélectionner la pizza que vous souhaitez supprimer");
                        System.out.println("R -  Retour");

                        entree = scanner.nextLine().toLowerCase();
                        if (entree.equals("r")) {
                            System.out.println("Retour...");
                            gestion.wait(2000);
                            break;
                        } else {
                            try {
                                Pizza pizza = commande.pizzas.get(Integer.parseInt(entree) - 1);

                                commande.pizzas.remove(pizza);

                                System.out.println(String.format("%s supprimé avec succès.", pizza.toString()));
                                gestion.wait(2000);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(
                                        "Erreur lors de la suppression de la pizza: Le nombre donné est invalide.");
                            } catch (NumberFormatException e) {
                                System.out.println(
                                        "Erreur lors de la suppression de la pizza: Le nombre donné est incorrect.");
                            } catch (Exception e) {
                                System.out
                                        .println("Erreur lors de la suppression de la pizza: Une erreur est survenue.");
                            }
                        }
                    }
                }

            } else {
                try {
                    PizzaBuilder pizza = pizzaBuilders.get(Integer.parseInt(entree) - 1);

                    pb.setPizzaBuilder(pizza);
                    pb.constructPizza();

                    commande.ajouteProduit(pb.getPizza());

                    System.out.println(String.format("%s ajoutée dans la commande.", pizza.toString()));
                    gestion.wait(2000);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Erreur de commande: Le nombre donné est invalide.");
                } catch (NumberFormatException e) {
                    System.out.println("Erreur de commande: Le nombre donné est incorrect.");
                } catch (Exception e) {
                    System.out.println("Erreur de commande: Une erreur est survenue.");
                }
            }
        }

    }

    public void interfaceAdmin(String user) {
        String entree = "";

        gestion.clear();
        System.out.println("Bienvenue " + user + " !!!");
        gestion.wait(2000);

        while (true) {
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Menu administrateur");

            System.out.println("C - Changer de mot de passe");
            System.out.println("S - Gérer les commandes");
            System.out.println("G - Voir les commandes");
            System.out.println("O - Se déconnecter");

            switch (scanner.nextLine().toLowerCase()) {
                case "o":
                    System.out.println("Au revoir...");
                    return;
                case "s":
                    int index = 0;
                    while (true) {
                        System.out.println(
                                "----------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Liste des commandes à traiter...");
                        List<Commande> commands = getCommandesValidee();

                        if (commands.size() == 0) {
                            System.out.println("Aucune commande à effectuer...");
                            gestion.wait(2000);

                            break;
                        }
                        if (index == commands.size())
                            index = commands.size() - 1;

                        Commande commande = commands.get(index);

                        System.err.println(String.format(
                                "Commande %d:", commandes.indexOf(commande)));
                        commande.affiche();

                        System.out.println(
                                "\nAppuyer sur n'importe touche pour changer le statut de la commande en livrée.");
                        System.out.println("Sauf sur R si vous souhaitez partir de ce menu.");
                        System.out.println("Sauf sur P si vous souhaitez passer cette commande.");

                        entree = scanner.nextLine().toLowerCase();

                        if (entree.equals("r")) {
                            System.out.println("Retour au menu administrateur...");
                            gestion.wait(2000);

                            break;
                        } else if (entree.equals("p")) {
                            if (commands.size() == 1)
                                System.out.println("Impossible de passer, il n'y a qu'unse seule commande.");
                            else {
                                if (index == commands.size() - 1)
                                    index = 0;
                                else
                                    index++;
                                System.out.println("Commande passée");
                            }
                        } else {
                            commande.etatSuivant();

                            System.out.println("Commande modifié avec succès...");
                        }
                        gestion.wait(1000);
                    }
                    break;
                case "g":
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------------");
                    List<Commande> commands = getCommandesValidee();
                    System.out.println("Commandes en cours");
                    if (commands.size() == 0)
                        System.out.println("Aucune commande en cours...");
                    else {
                        for (index = 0; index < commands.size(); index++) {
                            Commande commande = commands.get(index);

                            System.err.println(String.format(
                                    "Commande %d:", commandes.indexOf(commande)));
                            commande.affiche();
                        }
                    }

                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------------");

                    commands = getCommandesLivree();
                    System.out.println("Commandes livrées");
                    if (commands.size() == 0)
                        System.out.println("Aucune commande livrées...");
                    else {
                        for (index = 0; index < commands.size(); index++) {
                            Commande commande = commands.get(index);

                            System.err.println(String.format(
                                    "Commande %d:", commandes.indexOf(commande)));
                            commande.affiche();
                        }
                    }

                    System.out.println("Appuyer sur Entree pour revenir au menu Administrateur...");
                    scanner.nextLine();

                    break;

                case "c":
                    System.out.println(
                            "----------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Mot de passe actuel:");

                    String ancien_mdp = dbProcess.getMDP(user);
                    if (scanner.nextLine().equals(ancien_mdp)) {
                        System.out.println("Nouveau mot de passe:");
                        String nouveau_mdp = scanner.nextLine();

                        System.out.println("Confirmer le nouveau mot de passe:");
                        if (scanner.nextLine().equals(nouveau_mdp)) {
                            if (!nouveau_mdp.equals(ancien_mdp)) {
                                dbProcess.changerMDP(user, nouveau_mdp);
                                System.out.println("Mot de passe modifié avec succès.");
                            } else {
                                System.out.println(
                                        " Le nouveau mot de passe correspond à l'ancien mot de passe, aucun changement éffectué...");
                            }
                        } else {
                            System.out.println("ERREUR: Mot de passe non confirmé, aucun changement éffectué...");
                        }
                    } else {
                        System.out.println("ERREUR: Mot de passe actuel invalide...");
                    }

                    gestion.wait(2000);
                    break;

                default:
                    System.out.println("Commande invalide...");
                    gestion.wait(2000);
                    break;
            } 

            gestion.clear();
        }

    }

    private List<Commande> getCommandesValidee() {
        List<Commande> commands = new ArrayList<Commande>();
        for (Commande commande : commandes) {
            if (commande.getEtat().equals("validee"))
                commands.add(commande);
        }
        return commands;
    }

    private List<Commande> getCommandesLivree() {
        List<Commande> commands = new ArrayList<Commande>();
        for (Commande commande : commandes) {
            if (commande.getEtat().equals("livree"))
                commands.add(commande);
        }
        return commands;
    }
}
