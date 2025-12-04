package main.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import main.components.*;

public class ParserFile {
    public static Reseau readReseau(Scanner sc) {
    Reseau reseau = new Reseau();

    System.out.print("Saisir le numéro de l'instance à charger (ex: 1) : ");
    int n = Integer.parseInt(sc.nextLine());

    String fileName =
        "src/ressources/configurations/instance"
        + n + ".txt";
    
    File file = new File(fileName);
    if (!file.exists()) {
        System.out.println("Fichier de test inexistant");
        return reseau;
    }

    try (BufferedReader myReader = new BufferedReader(new FileReader(file))) {

        String line;
        while ((line = myReader.readLine()) != null) {

            String type = line.split("\\(")[0];

            switch (type) {
                case "generateur": {
                    String[] parts = line.split("\\(")[1].replace(")", "").split(",");
                    reseau.ajouterGenerateur(
                        new Generateur(parts[0], Double.parseDouble(parts[1]))
                    );
                    break;
                }

                case "maison": {
                    String nom = line.split("\\(")[1].split("\\,")[0];
                    String charge =  line.split("\\(")[1].split(",")[1].split("\\)")[0].trim();
                    reseau.ajouterMaison(
                        new Maison(nom, NiveauConsommation.valueOf(charge)));
                    break;
                }

                case "connexion": {
                    String geneNom = line.split("\\(")[1].split("\\,")[0];
                    String maisonNom = line.split("\\(")[1].split("\\,")[1].split("\\)")[0];

                    Generateur gen = null;
                    Maison msn = null;

                    for (Generateur g : reseau.getGenerateurs())
                        if (g.getNom().equals(geneNom)) gen = g;

                    for (Maison m : reseau.getMaisons())
                        if (m.getNom().equals(maisonNom)) msn = m;

                    if (gen != null && msn != null){
                        Connexion c = new Connexion(gen, msn);
                        reseau.ajouterConnexion(c);
                        gen.ajouterConnexion(c);
                        msn.setConnexion(c);
                    }else System.out.println("Maison ou connexion non lue");

                    break;
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(reseau.toString());
    return reseau;
    
}


}
