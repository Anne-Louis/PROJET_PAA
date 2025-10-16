# Projet PAA (L3) - Réseau Électrique 

---

## Description du projet

Ce projet a pour objectif de modéliser et simuler un **réseau électrique** simplifié composé de :

- **Maisons** (consommateurs) avec des niveaux de consommation (basse, normale, forte).

- **Générateurs** (sources d’énergie) ayant une capacité maximale (en kW)?

- **Connexions** reliant chaque maison à un générateur unique.

---

## Objectifs du projet

- Représenter un réseau simplifié avec uniquement des générateurs et des maisons.

- Simuler les connections possibles entre les génératerus et les maisons.

- Calculer le coût d'une solution et le minimiser.

---

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

---

## Structure du projet 

```
src/
 ├── main/
 │    ├── Main.java
 │    ├── Reseau.java
 │    ├── Generateur.java
 │    ├── Maison.java
 │    └── Connexion.java
 └── test/
      └── Test.java

```

---

## Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 :

- Mohammed Aali

- Anne-Louis Vojinovic

- Laye Fode Keita

---

## Extensions possibles

- **Gestion d’erreurs avancée** sur les saisies utilisateur.

- **Sauvegarde et chargement** d’un réseau depuis un fichier.

- **Interface graphique** (JavaFX).

- **Algorithmes d’optimisation** pour minimiser le coût global du réseau.