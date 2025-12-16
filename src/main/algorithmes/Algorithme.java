package main.algorithmes;

import main.components.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Algorithme heuristique d'allocation des maisons aux générateurs.
 * L'objectif est de minimiser le coût global du réseau tout en
 * équilibrant la charge des générateurs.
 */
public class Algorithme {
    public static double  epsilonInit = 0.5;

    /**
     * Alloue les maisons au générateurs par :
     * par tri des maisons par ordres decroisante de consommation.
     * charge en prémier les générateurs de plus grandes capacités disponible.
     * equilibre la repartition en veillant sur l'écart entre la moyenne des taux d'utilisation des generateurs
     * et le taux d'utilisation d'un générateur g avec une toleraran epsilon.
     * @param S       le réseau à resoudre automatiquement
     * @param epsilon la tolerance pour l'équilibrage de la repartion des charges entre les générateurs
     * @return réseau optimisé
     */
    public static Reseau resoudreReseau(Reseau S, double epsilon) {

        Reseau minReseau = S.copierReseau();
        double meilleurCout = minReseau.calculerCoutReseau();

        do {
            Reseau courant = minReseau.copierReseau();
            courant.clear();

            // Tri des maisons par consommation décroissante
            courant.getMaisons().sort(
                (m1, m2) -> Double.compare(m2.getConsommation(), m1.getConsommation())
            );

            List<Maison> maisonsNonAllouees = new ArrayList<>();

            for (Maison m : courant.getMaisons()) {

                double moyenneTaux =
                    calculerMoyenneTauxUtilisation(courant.getGenerateurs());

                List<Generateur> generateurs =
                    getGenerateursParCapaciteDisponibleDecroissante(
                        courant.getGenerateurs()
                    );

                boolean alloue = false;

                /* Allocation régulée */
                for (Generateur g : generateurs) {
                    double capaciteRestante = g.getCapacite() - g.calculCharge();

                    if (capaciteRestante >= m.getConsommation()
                        && Math.abs(g.calculTauxUtilisation() - moyenneTaux) <= epsilon) {

                        courant.ajouterConnexion(new Connexion(g, m));
                        alloue = true;
                        break;
                    }
                }

                if (!alloue) {
                    maisonsNonAllouees.add(m);
                }
            }

            /* Optimisation locale des maisons non allouées */
            for (Maison m : maisonsNonAllouees) {

                double meilleurCoutLocal = Double.POSITIVE_INFINITY;
                Reseau meilleurReseauLocal = null;

                for (Generateur g : courant.getGenerateurs()) {

                    Reseau test = courant.copierReseau();
                    Maison mc = test.trouverMaisonParNom(m.getNom());
                    Generateur gc = test.trouverGenerateurParNom(g.getNom());

                    test.ajouterConnexion(new Connexion(gc, mc));

                    double cout = test.calculerCoutReseau();
                    if (cout < meilleurCoutLocal) {
                        meilleurCoutLocal = cout;
                        meilleurReseauLocal = test;
                    }
                }

                if (meilleurReseauLocal != null) {
                    courant = meilleurReseauLocal;
                }
            }

            double coutCourant = courant.calculerCoutReseau();
            if (coutCourant < meilleurCout) {
                meilleurCout = coutCourant;
                minReseau = courant;
            }

            epsilon *= 0.99;

        } while (epsilon > 1e-2);

        return minReseau;
    }

    /**
     * Calcule la moyenne des taux d'utilisation des générateurs.
     *
     * @param G liste des générateurs
     * @return moyenne des taux d'utilisation
     */
    private static double calculerMoyenneTauxUtilisation(List<Generateur> G) {
        double somme = 0.0;
        for (Generateur g : G) {
            somme += g.calculTauxUtilisation();
        }
        return somme / G.size();
    }

    /**
     * Trie les générateurs par capacité restante décroissante.
     *
     * @param G liste de générateurs
     * @return liste triée par capacité disponible
     */
    private static List<Generateur>
    getGenerateursParCapaciteDisponibleDecroissante(List<Generateur> G) {

        List<Generateur> list = new ArrayList<>(G);
        list.sort((g1, g2) -> {
            double r1 = g1.getCapacite() - g1.calculCharge();
            double r2 = g2.getCapacite() - g2.calculCharge();
            return Double.compare(r2, r1);
        });

        return list;
    }
}
