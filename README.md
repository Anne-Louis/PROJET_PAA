# Projet PAA (L3) - Réseau Électrique 

## Description du projet

Ce projet modélise et simule un **réseau électrique** simplifié composé de :

- **Maisons** (consommateurs) avec des niveaux de consommation (basse, normale, forte).

- **Générateurs** (sources d’énergie) ayant une capacité maximale (en kW).

- **Connexions** reliant chaque maison à un générateur unique.


## Objectifs du projet

- Représenter un réseau simplifié avec uniquement des générateurs et des maisons.

- Simuler les connections possibles entre les générateurs et les maisons.

- Calculer le coût d'une solution et le minimiser.


## Fonctionnalités (Partie 2)

Le programme se lance via le terminal avec des arguments (nom de fichier, lambda) dans la ligne de commande. La présence d'une valeur pour le lambda est obligatoire, la présence d'un fichier n'est pas obligatoire.
En fonction du nombre d'arguments, un menu différent s'ouvre :
- 1 : ouvre le menu de création de réseau manuel puis une fois la création terminé, ouvre le menu d'optimisation du réseau
- 2 : ouvre le menu d'optimisation du réseau directement

**Menu création de réseau**

- Ouvrir l'interface graphique
- Ajouter un générateur
- Ajouter une maison
- Ajouter une connexion entre une maison et un générateur
- Supprimer une connexion entre une maison et un générateur
- Terminer la création de réseau

**Menu optimisation du réseau**

Une fois le réseau créé ou chargé depuis un fichier :
- Ouvrir l'interface graphique
- Optimiser via un algorithme le coût du réseau
- Sauvegarder le réseau dans un fichier
- Quitter le programme

## Description de l'algorithme d'optimisation :
L'algorithme optimise le réseau en faisant une reconstruction à chaque itération et en conservant **le réseau ayant le coût minimal obtenu** en suivant ces règles pour chaque itération :
0. on choisit ε = 0.5, le paramètre de régulation de la distribution.

1. l'allocation priorise les maisons de forte consommation.
2. Au générateur g de grande capacité disponible restante (capacité - charge actuelle) et ayant un faible taux d'utilisation, tel que : **|tauxUtilisation(g) - tauxUtilisationMoyenne des générateurs| < ε**. *(Ceci permet de centrer les taux d'utilisation des générateurs autour de la moyenne des taux d'utilisation, ce qui minimise efficacement la distribution : |Ū - Ug|)*.

3. Si aucun générateur ne peut alimenter une maison m en respectant ces conditions, alors celle-ci est rattachée au générateur de telle sorte que le coût du réseau reste minimal.

4. On réduit ε à ε' afin de rechercher une autre construction de distribution faible.

5. A la fin (c'est-à-dire, lorsque ε est suffisamment petit, ε < 0.01), on retourne la meilleure construction du réseau obtenu parmi l'ensemble des itérations effectuées.

*Voir l'algorithme complet dans le fichier src/ressources/modélisation/pseudo_code_algorithme_heuristique.pdf et le pseudo code detaillé dans le fichier Algorithmes/skelton_algorithe.txt*


## Structure du projet 

```
src/
 ├── gui/
 │    ├── controllers/
 │    │    └── Controleur.java                        # Relie l'interface graphique et le réseau
 │    ├── main/
 │    |    └── AppUI.java                             # Point d'entrée de javaFX
 │    ├── views/
 │    │    ├── AffichagePanel.java                    # Panneau d'affichage des options du réseau
 │    │    ├── Alerte.java                            # Gestion des alertes de l'interface graphique
 │    │    ├── ConnexionPanel.java                    # Panneau de création des connexions
 │    │    ├── GenerateurPanel.java                   # Panneau de création des générateurs
 │    │    ├── MainWindows.java                       # Panneau principal
 │    │    ├── MaisonPanel.java                       # Panneau de création des maisons
 │    │    └── ReseauPanel.java                       # Panneau de gestion du réseau
 ├── main/
 │    ├── algorithmes/
 │    │    ├── Algorithme.java                        # Algorithme d'optimisation du coût
 │    │    └── skelton_algorithme.txt                 # Pseudo-code de l'algorithme
 │    ├── components/
 │    |    ├── Connexion.java                         # Gestion des connexions
 │    |    ├── Generateur.java                        # Gestion des générateurs
 │    |    ├── Maison.java                            # Gestion des maisons
 │    |    ├── NiveauConsommation.java                # Enumération niveaux consommation des maisons
 │    |    └── Reseau.java                            # Gestion du réseau
 │    ├── exceptions/
 │    │    ├── ArgumentsException.java                # Exception mauvais nombre arguments
 │    │    ├── ConnexionDejaExistanteException.java   # Exception connexion existant
 │    │    ├── GenerateurInexistantException.java     # Exception générateur inexistant
 │    │    ├── ImportException.java                   # Exception problème d'importation
 │    │    ├── InvalideReseauException.java           # Exception réseau non valide
 │    │    ├── MaisonInexistanteException.java        # Exception maison inexistante
 │    │    ├── NombreDeTermesException.java           # Exception mauvais nombre de termes
 │    │    └── NomDejaPrisException.java              # Exception nom déjà pris
 |    ├── io/
 │    |    ├── ParseFile.java                         # Création d'un réseau via un fichier
 │    |    └── SauvegardeReseau.java                  # Sauvegarde d'un réseau via un fichier
 │    ├── menus/
 │    |    ├── Menu1.java                             # Menu pour créer manuellement le réseau
 │    |    ├── Menu2.java                             # Menu pour modifier manuellement le réseau
 │    |    ├── Menu3.java                             # Menu lire un fichier et optimiser le coût
 │    |    └── UtilMenu.java                          # Classe contenant méthodes pour menus
 │    └── Main.java                                   # Entrée du programme
 ├── ressources/
 │    ├── configurations                       
 |    |    ├── instance1.txt                          # Fichier à lire pour créer un réseau
 │    │    └── instance2.txt                          # Fichier à lire pour créer un réseau
 |    |     ---
 │    ├── modelisations/                   
 │    │    ├── modelisation1.pdf                      # Première modélisation diagramme de classes
 │    │    ├── modelisation2.jpg                      # Deuxième modélisation diagramme de classes
 │    │    └── pseudo_code_alogrithme.pdf             # Pseudo code de l'algorithme implémenté
 │    ├── solutions
 |    |    ├── solution1.txt                          # Fichier contenant une solution
 │    |    └── solution2.txt                          # Fichier contenant une solution   
 |    |     ---  
 │    └── solutionsJavaFx 
 │    |    ├── glass.ddl                              # DDL native interface graphique de bas niveau
 │    |    ├── javafx_font.ddl                        # DDL native rendu des polices
 │    |    ├── prism_d3d.ddl                          # DDL native rendu Direct3D
 │    |    └── prism_sw.ddl                           # DDL native rend Software
 └── test/
      └── components/                                 # Test unitaire
      └── menus/                                      # Test unitaire

```

## Point d'entrée du projet 

Le fichier principal du programme se trouve dans le dossier `src/main/`. Vous pouvez trouver le point d'entrée du programme dans le fichier `Main.java`. Il faudra ajouter des arguments pour pouvoir lancer le programme.

## Comment Exécuter

**Les versions recommandées :**
- Java (JDK 21.0.6.7)
- JavaFX (jfx.version : 21.0.9)
- JUnit5 (5.1.13.0)

1. Clonez ce dépôt.
2. Naviguez jusqu'au dossier source du projet (cd PROJET_PAA)
3. Exécutez la commande pour la compilation :
```
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "./lib/junit.jar" -d ./bin ./src/main/Main.java ./src/main/components/*.java ./src/main/algorithmes/*.java ./src/main/io/*.java ./src/main/menus/*.java ./src/main/exceptions/*.java ./src/gui/main/*.java ./src/gui/controllers/*.java ./src/gui/views/*.java ./src/test/components/*.java .src/test/io/*.java
```

4. Puis la commande pour l'éxecution :
```
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp ./bin main.Main + le nom du fichier + la valeur de lambda.
```
(il n'est pas nécessaire d'ajouter l'extension .txt au nom du fichier, le fichier doit obligatoirement se trouver dans le dossier `src/ressources/configurations` la valeur de lambda doit être supérieure ou égale à 0).

5. (Optionnel) Si l'erreur : 
```
Graphics Device initialization failed for : d3d, sw
Error initializing QuantumRenderer: no suitable pipeline found
``` 
se produit lors du lancement de l'interface graphique, après avoir choisi l'option correspondante dans le menu, une solution possible est de récupérer les 4 fichiers .ddl dans le `dossier src/ressources/solutionJavaFx` et de les ajouter dans le dossier bin de votre jdk.
Une explication de chaque fichier est donné dans l'arborescence du projet.
Il s'agit de fichier natifs au sdk de JavaFx.

## Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 :

- Laye Fode Keita

- Anne-Louis Vojinovic

- Mohammed Aali
