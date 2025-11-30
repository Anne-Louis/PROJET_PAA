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

Le fichier principal du programme se trouve dans le dossier `src/test/`. Vous pouvez trouver le point d'entrée du programme dans le fichier `Menu.java`.

## Comment Exécuter

Assurez-vous d'avoir Java installé sur votre système.

1. Clonez ce dépôt.
2. Naviguez jusqu'au dossier source du projet (cd PROJET_PAA)
3. Exécutez la commande : javac -d bin src/main/*.java src/test/Test.java
4. Puis la commande : java -cp bin src/test/Test.java

## Fonctionnalités (Partie 1)

Au lancement, le programme affiche un menu principal :

**Menu principal**

- Ajouter un générateur
- Ajouter une maison
- Ajouter une connexion entre une maison et un générateur
- Terminer la configuration

**Menu secondaire**

Une fois la configuration terminée :
- Calculer le coût du réseau électrique actuel
- Modifier une connexion
- Afficher le réseau électrique
- Quitter le programme


## Structure du projet 

```
src/
 ├── main/
 │    ├── algorithmes/
 │    │    ├── Algo1.java                      # Algorithme d'optimisation du coût
 │    │    ├── Algo2.java                      # Algorithme d'optimisation du coût
 |    |    ---
 │    ├── components/
 │    |    ├── Connexion.java                  # Gestion des connexions
 │    |    ├── Generateur.java                 # Gestion des générateurs
 │    |    ├── Maison.java                     # Gestion des maisons
 │    |    ├── NiveauConsommation.java         # Enumération des niveaux de consommation des maisons
 │    |    └── Reseau.java                     # Gestion du réseau
 │    ├── exceptions/
 │    │    ├── FormatInvalideException         # Exception personnalisée mauvais format de fichier
 │    │    ├── GenerateurManquantException     # Exception personnalisée générateur manquant
 │    │    └── MaisonManquantException         # Exception personnalisée maison manquant
 |    ├── io/
 │    |    ├── ParseFile.java                  # Création d'un réseau via un fichier
 │    |    └── FileSaver.java                  # Sauvegarde d'un réseau via un fichier
 │    └── menus/
 │         ├── Menu1.java                      # Gestion menu pour créer manuellement le réseau
 │         ├── Menu2.java                      # Gestion menu pour modifier manuellement le réseau
 │         └── Menu3.java                      # Gestion menu lire un fichier et optimiser le coût
 ├── ressources/
 │    ├── configurations                       
 |    |    ├── instance1.txt                   # Fichier à lire pour créer un réseau
 │    │    └── instance2.txt                   # Fichier à lire pour créer un réseau
 │    ├── modelisations/                   
 │    │    ├── modelisation1.pdf               # Première modélisation avec diagramme de classes
 │    │    └── modelisation2.pdf               # Deuxième modélisation avec diagramme de classes
 │    └── solutions
 |         ├── solution1.txt                   # Fichier contenant une solution
 │         └── solution2.txt                   # Fichier contenant une solution           
 └── test/
      └── Test1.java                           # Test unitaire
      └── Test2.java                           # Test unitaire
      ...

```


## Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 :

- Laye Fode Keita

- Anne-Louis Vojinovic

- Mohammed Aali
