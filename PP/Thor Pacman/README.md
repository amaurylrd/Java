# Tore Pacman

Encapsule l'ensemble des sources qui forment un jeu pacman-like tour par tour.

## Notions exploitées

Il s'agit d'un projet qui avant tout a été contraint par le temps. Néanmoins, évalué à l'échelle du temps passé au code et à la recherche (quelques heures), il faut retenir que nombreux ont été les bénéfices.

+ Pacman est une application du concepte de *tore*. Un tore est un solide géométrique représentant un tube courbé refermé sur lui-même. C'est-à-dire que si on voulait modéliser en volume le jeu, ça donnerait un genre de donut qui permetrait les jointures.

+ Pour ce qui est de la construction du jeu, c'est grâce à un bitmap que le plateau est généré. Il s'agit d'un format d'image matricielle dont les pixels représentent les coefficients.

> **Note** : On pourrait imaginer au menu un bouton `editing` pour construire un niveau en place chaques élèments de la grille.

+ Dans la continuité d'une réflexion sur les algorithmes de recherche du plus court chemin, celui choisit est similaire à a*.

## Description

L'utilisateur contrôle pacman qui se déplace dans une grille sur laquelle sont aussi présents des fantômes, des bonbons et des super pac-gommes.

### But

Le but est de manger tous les bonbons sans se faire soi-même manger par un des fantômes. Au niveau 1, la partie est gagnée lorsque pacman a dévoré les 200 bonbons. En revanche, si un fantôme rencontre en un point pacman, la partie est perdue.

### Avatar et IA

Avec la transposition d'un bitmap en grille de jeu, on peut imaginer des niveaux qui s'intensifient, de plus en plus en stratégiques. Sur cette base, la décision a été prise de n'injecter que trois fantômes sur les quatre originaux.

> **Note cf.** : documentation sur le comportement des fantômes [ici](http://gameinternals.com/post/2072558330/understanding-pac-man-ghost-behavior)

### **Blinky**

![I'm a relative reference to a repository file](./Asset/ghost0.png) Le fantôme rouge connait en permanence la position du joueur et s'y dirige.

### **Clyde**

![I'm a relative reference to a repository file](./Asset/ghost1.png) Le fantôme vert avance aléatoirement jusqu'à être informé de la position du joueur quand il le voit et le chase alors avant de le perdre de vue à nouveau. 

### **Pinky**

![I'm a relative reference to a repository file](./Asset/ghost2.png) Le fantôme rose prépare une embuscade au pacman et vise le carrefour le plus proche de ce dernier. 

### **Pacman**

![I'm a relative reference to a repository file](./Asset/right.png) La direction du pacman dépend des inputs générés par le joueur. 

### **Vulnerable Ghost**

![I'm a relative reference to a repository file](./Asset/panic.png) Si pacman mange une pac-gomme (point rouge) les déplacements de tous les fantômes sont affectés et deviennent vulnérables pour 30 tours. 

### **Dead Ghost**

![I'm a relative reference to a repository file](./Asset/eye_only.png) Si pacman mange un fantôme, ce dernier ne change rien à son comportement, ses mouvements restent aléatoires, en revanche il change d'apparence. 

***

## Explication - 1 Tour
