# PROJET_PAA🧮 Réseau Électrique – Projet de Programmation Avancée (Java, L3)
📘 Description du projet

Ce projet a pour objectif de modéliser et simuler un réseau électrique simplifié composé de :

Maisons (consommateurs) avec des niveaux de consommation (basse, normale, forte) ;

Générateurs (sources d’énergie) ayant une capacité maximale (en kW) ;

Connexions reliant chaque maison à un générateur unique.

L’application permet de configurer manuellement un réseau électrique, de visualiser les connexions et de calculer le coût global du réseau selon plusieurs critères d’équilibrage et de surcharge.

Ce projet est réalisé dans le cadre du cours de Programmation Avancée en Java (L3).

🧩 Objectifs pédagogiques

Appliquer les principes de programmation orientée objet (POO) en Java ;

Concevoir un diagramme UML de classes pour modéliser le système ;

Implémenter une interface en ligne de commande (CLI) permettant de gérer dynamiquement le réseau ;

Préparer la base pour des extensions futures (calculs avancés de coût, gestion d’erreurs, optimisation).

⚙️ Fonctionnalités (Phase 1)

Au lancement, le programme affiche un menu principal :

Menu principal

1️⃣ Ajouter un générateur
2️⃣ Ajouter une maison
3️⃣ Ajouter une connexion entre une maison et un générateur
4️⃣ Terminer la configuration

Menu secondaire

Une fois la configuration terminée :
1️⃣ Calculer le coût du réseau électrique actuel
2️⃣ Modifier une connexion
3️⃣ Afficher le réseau électrique
4️⃣ Quitter le programme

🧱 Modélisation (UML)
Classes principales :

Generateur : représente une source d’énergie avec un nom et une capacité maximale.

Maison : représente un consommateur avec un nom et une demande électrique (10, 20 ou 40 kW).

Connexion : relie une maison à un générateur.

Reseau : contient les collections de maisons, générateurs et connexions, et les méthodes de gestion.

Main : point d’entrée du programme, gère les menus et les interactions utilisateur.

Les associations principales :

Une maison est connectée à un unique générateur.

Un générateur peut alimenter plusieurs maisons.

Le réseau contient plusieurs générateurs et maisons.

🧮 Calculs (Phase 2 à venir)

Les prochaines étapes incluront :

Le calcul du taux d’utilisation de chaque générateur (charge / capacité) ;

La mesure de l’équilibrage global entre générateurs ;

L’ajout d’un coût global du réseau, incluant :

La pénalisation des surcharges (λ = 10) ;

La somme des écarts par rapport à la moyenne des taux d’utilisation.

🧑‍💻 Structure du projet (suggestion)
src/
 ├── main/
 │    ├── Main.java
 │    ├── Reseau.java
 │    ├── Generateur.java
 │    ├── Maison.java
 │    └── Connexion.java
 └── utils/ (optionnel)
      └── Menu.java (si gestion séparée des menus)

🧑‍🤝‍🧑 Équipe de développement

Projet réalisé par un groupe de 3 étudiants de L3 Génie Logiciel :

[Nom 1]

[Nom 2]

[Nom 3]

🚀 Lancer le programme
# Compiler
javac src/main/*.java

# Exécuter
java src/main/Main

🔮 Extensions possibles

Gestion d’erreurs avancée sur les saisies utilisateur ;

Sauvegarde et chargement d’un réseau depuis un fichier ;

Interface graphique (JavaFX) ;

Algorithmes d’optimisation pour minimiser le coût global du réseau.