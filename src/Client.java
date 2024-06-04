import java.util.Scanner;
import process.SysGestion;
import java.util.List;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Client {
    private SysGestion gestion;
    private Scanner scanner = new Scanner(System.in);

    private Serveur pb = new Serveur();
    private List<Commande> commandes = new ArrayList<Commande>();
    private List<PizzaBuilder> pizzaBuilders = new ArrayList<PizzaBuilder>();

    public Client() {
        pizzaBuilders.add(new PizzaHawaienneBuilder());
        pizzaBuilders.add(new PizzaNorvegienneBuilder());
    }

    public void interfaceEntree() {
        String entree = "";
        gestion.clear();

        while (!entree.equals("q")) {
            System.out.println("BIENVENUE CHEZ PizzaMania, Appuyez sur Entree pour commander...");
            entree = scanner.nextLine().toLowerCase();

            if (entree.equals("q")) {
                System.out.println("Au revoir...");
            } else if (entree.equals("admin")) {
                System.out.println("MODE ADMIN");
            } else {
                interfaceCommande();
            }
        }
    }

    private void interfaceCommande() {
        String entree = "";
        Commande commande = new Commande();

        while (true) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
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
                gestion.wait(2000);
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

                        System.out.println("Votre commande est passé en état validé. Vous pouvez toujours le modifier en appelant un staff.");
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
                                System.out.println("Erreur lors de la suppression de la pizza: Le nombre donné est invalide.");
                            } catch (NumberFormatException e) {
                                System.out.println("Erreur lors de la suppression de la pizza: Le nombre donné est incorrect.");
                            } catch (Exception e) {
                                System.out.println("Erreur lors de la suppression de la pizza: Une erreur est survenue.");
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
}
