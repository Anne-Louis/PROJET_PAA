package main.algorithmes;

import main.components.*;
import java.util.List;
import java.util.ArrayList;

public class Algorithme1 {
    /**
     * @param epsilonInit la tolerance d'équilibrage de la repartion des charges entre les générateurs.
     */
    public static double epsilonInit = 0.8;
    /**
     * Alloue les maisons au générateurs par :
     * par tri des maisons par ordres decroisante de consommation.
     * charge en prémier les générateurs de plus grandes capacités disponible.
     * equilibre la repartition en veillant sur l'écart entre la moyenne des taux d'utilisation des generateurs
     * et le taux d'utilisation d'un générateur g avec une tolearan epsilon.
     * @param S       le réseau à resoudre automatiquement
     * @param epsilon la tolerance pour l'équilibrage de la repartion des charges entre les générateurs
     */

    public static void resoudreReseau(Reseau S, double epsilon) {
        int maxIter = S.getGenerateurs().size() / 2;
        int iter = 0;

        do {
            List<Maison> maisons = new ArrayList<>(S.getMaisons());
            List<Generateur> generateurs = new ArrayList<>(S.getGenerateurs());

            for (Generateur g : generateurs) {
                g.getMaisons().clear();
            }

            maisons.sort((m1, m2) -> Double.compare(m2.getConsommation(), m1.getConsommation())
            );

            for (Maison m : maisons) {
                double moyenneTaux = calculerMoyenneTauxUtilisation(generateurs);
                List<Generateur> sortedG = getGenerateursParCapaciteDisponibleDecroissant(generateurs);

                boolean alloue = false;

                for (Generateur g : sortedG) {
                    double capaciteRestante = g.getCapacite() - g.calculCharge();
                    if (capaciteRestante >= m.getConsommation() &&
                        Math.abs(g.calculTauxUtilisation() - moyenneTaux) <= epsilon) {
                        g.ajouterConnexion(new Connexion(g, m));
                        alloue = true;
                        break;
                    }
                }

                // si aucun generateur ne respecte la regulation:
                if (!alloue) {
                    for (Generateur g : sortedG) {
                        double capaciteRestante = g.getCapacite() - g.calculCharge();
                        if (capaciteRestante >= m.getConsommation()) {
                            g.ajouterConnexion(new Connexion(g, m));
                            break;
                        }
                    }
                }
            }

            System.out.println("le cout du reseau actuel:"+ S.calculerCoutReseau());
            epsilon = Math.max(epsilon * 0.99, 1e-3);
            iter++;

        } while (iter < maxIter);
    }


    //////////////////////////////////////fonctions utilitaires///////////////////////////////////////////////
    /**
     * Calcule la moyenne des taux d'utilisation de tous les générateurs pour extimer la repartition.
     * @param G la liste des generateurs
     * @return  la  moyenne taux d'utilisations des generateurs.
     */
    private static double calculerMoyenneTauxUtilisation(List<Generateur> G) {
        double sum = 0;
        for (Generateur g : G)
            sum += g.calculTauxUtilisation();
        return sum / G.size();
    }

    /**
     * tri la liste des generateurs par capacité disponible restante dans l'ordre decroissant.
     * @param G liste de générateurs
     * @return  liste trée par decroissant des generateurs en fonction de la capacité disponible.
     */

    private static List<Generateur> getGenerateursParCapaciteDisponibleDecroissant(List<Generateur> G) {
        List<Generateur> list = new ArrayList<>(G);
        list.sort((g1, g2) -> {
            double d1 = g1.getCapacite() - g1.calculCharge();
            double d2 = g2.getCapacite() - g2.calculCharge();
            return Double.compare(d2, d1);
        });
        return list;
    }
}