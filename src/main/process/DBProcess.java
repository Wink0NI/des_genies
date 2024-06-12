package main.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DBProcess {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "mySecretKey12345"; // Clé de 16 caractères pour AES

    public boolean connexion(String name, String mdp) {
        String name_file = "src/db/admin.csv";
        try {
            Scanner reader = new Scanner(new File(name_file));
            while (reader.hasNextLine()) {

                String[] ligne = reader.nextLine().split(";");

                if (decrypt(ligne[0]).equals(name) && decrypt(ligne[1]).equals(mdp))
                    return true;

            }

        } catch (FileNotFoundException e) {
            System.out.println("ERREUR: Fichier " + name_file + " non trouvé.");
        } catch (Exception e) {
            System.out.println("Erreur: Une erreur est survenue lors du chargement de la bdd.");
        }
        return false;
    }

    public String getMDP(String name) {
        String name_file = "src/db/admin.csv";
        try {
            Scanner reader = new Scanner(new File(name_file));
            while (reader.hasNextLine()) {

                String[] ligne = reader.nextLine().split(";");

                if (decrypt(ligne[0]).equals(name))
                    return decrypt(ligne[1]);

            }

        } catch (FileNotFoundException e) {
            System.out.println("ERREUR: Fichier " + name_file + " non trouvé.");
        } catch (Exception e) {
            System.out.println("Erreur: Une erreur est survenue lors du chargement de la bdd.");
        }
        return "";
    }

    public static void changerMDP(String name, String nouveau_mdp) {
        String name_file = "src/db/admin.csv";
        List<String> lines = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(name_file))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");
                if (decrypt(parts[0]).equals(name)) {
                    // Modify the line with the new password
                    parts[1] = encrypt(nouveau_mdp); // Assuming encrypt() is defined elsewhere
                }
                lines.add(String.join(";", parts));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERREUR: Fichier " + name_file + " non trouvé.");
        } catch (Exception e) {
            System.out.println("Erreur: Une erreur est survenue lors du chargement de la bdd.");
        }

        // Write the modified lines back to the file
        try (PrintWriter writer = new PrintWriter(new File(name_file))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERREUR: Impossible d'écrire dans le fichier " + name_file);
        }
    }

    

    private static String encrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decrypt(String encryptedValue) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
