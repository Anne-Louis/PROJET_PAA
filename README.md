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

## Point d'entrée du projet 

Le fichier principal du programme se trouve dans le dossier `src/main/`. Vous pouvez trouver le point d'entrée du programme dans le fichier `Main.java`.

## Comment Exécuter

Assurez-vous d'avoir Java installé sur votre système.

1. Clonez ce dépôt.
2. Naviguez jusqu'au dossier source du projet (cd PROJET_PAA)
3. Exécutez la commande : javac -d bin src/*
4. Puis la commande : java -cp bin src/main/Main.java

## Fonctionnalités (Partie 2)

Le programme se lance via le terminal avec des arguments (nom de fichier, lambda) dans la ligne de commande. La présence d'une valeur pour le lambda est obligatoire, la présence d'un fichier n'est pas obligatoire.
En fonction du nombre d'arguments, un menu différent s'ouvre :
- 1 : ouvre le menu de création de réseau manuel puis une fois la création terminé, ouvre le menu d'optimisation du réseau
- 2 : ouvre le menu d'optimisation du réseau directement

**Menu création de réseau**

- Ajouter un générateur
- Ajouter une maison
- Ajouter une connexion entre une maison et un générateur
- Supprimer une connexion entre une maison et un générateur
- Terminer la création de réseau

**Menu optimisation du réseau**

Une fois le réseau créé ou chargé depuis un fichier :
- Optimiser via un algorithme le coût du réseau
- Sauvegarder le réseau dans un fichier
- Quitter le programme


## Structure du projet 

```
src/
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
 │    │    └── modelisation2.jpg                      # Deuxième modélisation diagramme de classes
 │    └── solutions
 |         ├── solution1.txt                          # Fichier contenant une solution
 │         └── solution2.txt                          # Fichier contenant une solution     
 |          ---      
 └── test/
      └── components/                                 # Test unitaire
      └── menus/                                      # Test unitaire

```


## Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 :

- Laye Fode Keita

- Anne-Louis Vojinovic

- Mohammed Aali
