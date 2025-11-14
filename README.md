# Projet PAA (L3) - Réseau Électrique 

## Description du projet

Ce projet modélise et simule un **réseau électrique** simplifié composé de :

- **Maisons** (consommateurs) avec des niveaux de consommation (basse, normale, forte).

- **Générateurs** (sources d’énergie) ayant une capacité maximale (en kW)?

- **Connexions** reliant chaque maison à un générateur unique.


## Objectifs du projet

- Représenter un réseau simplifié avec uniquement des générateurs et des maisons.

- Simuler les connections possibles entre les générateurs et les maisons.

- Calculer le coût d'une solution et le minimiser.

## Point d'entrée du projet 

Le fichier principal du programme se trouve dans le dossier 'src/test/'. Vous pouvez trouver le point d'entrée du programme dans le fichier 'Menu.java'.

## Comment Exécuter

Assurez- vous d'avoir Java installé sur votre système.

1. Clonez ce dépôt.
2. Naviguez jusqu'au dossier source du projet (cd PROJET_PAA)
3. Exécutez la commande : javac -d bin src/main/*.java src/test/*.java
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
 │    ├── MainApp.java                    # Gestion des menus
 │    ├── Reseau.java                     # Gestion du réseau
 │    ├── Generateur.java                 # Gestion des générateurs
 │    ├── Maison.java                     # Gestion des maisons
 │    ├── Connexion.java                  # Gestion des connexions
 │    └── NiveauConsommation.java         # Enumération des niveaux de consommation des maisons
 ├── ressources/
 │    ├── Modelisation1.pdf               # Première modélisation avec diagramme de classes
 │    └── reseau_config_test.txt          # Suite d'instructions pour réseau classique
 └── test/
      └── Test.java                       # Lancement du programme

```


## Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 :

- Laye Fode Keita

- Anne-Louis Vojinovic

- Mohammed Aali


## Extensions possibles

- **Gestion d’erreurs avancée** sur les saisies utilisateur.

- **Sauvegarde et chargement** d’un réseau depuis un fichier.

- **Interface graphique** (JavaFX).

- **Algorithmes d’optimisation** pour minimiser le coût global du réseau.