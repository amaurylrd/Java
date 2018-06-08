# Thor Pacman

Encapsule l'ensemble des sources qui forment un jeu pacman-like tour par tour.

## Notion exploitée

> **Note cf.** : On pourrait imaginer au menu un bouton `editing` pour construire un niveau en place chaques élèments de la grille.

## Description

L'utilisateur contrôle pacman qui se déplace dans une grille sur laquelle sont aussi présents des fantômes, des bonbons et des super pac-gommes.

### But

Le but est de manger tous les bonbons sans se faire soi-même manger par un des fantômes. Au niveau 1, la partie est gagnée lorsque pacman a dévoré les 200 bonbons. En revanche, si un fantôme rencontre en un point pacman, la partie est perdue.

### Avatar et IA

Avec la transposition d'un bitmap en grille de jeu, on peut imaginer des niveaux qui s'intensifient, de plus en plus en stratégiques. Sur cette base, la décision a été prise de n'injecter que trois fantômes sur les quatre originaux.

**Blinky**

![I'm a relative reference to a repository file](./Asset/ghost0.png) Le fantôme rouge connait en permanence la position du joueur et s'y dirige.

**Clyde**

![I'm a relative reference to a repository file](./Asset/ghost1.png)  Le fantôme vert est informé de la position du joueur quand il le voit




***

## Explication - 1 Tour
