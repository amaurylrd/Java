# Thor Pacman

Encapsule l'ensemble des sources qui forment un jeu pacman-like tour par tour.

## Notion exploitée

> **Note cf.** : On pourrait imaginer au menu un bouton `editing` pour construire un niveau en place chaques élèments de la grille.

## Description

L'utilisateur contrôle pacman qui se déplace dans une grille sur laquelle sont aussi présents des fantômes, des bonbons et des super pac-gommes.

### But

Le but est de manger tous les bonbons sans se faire soi-même manger par un des fantômes. Au niveau 1, la partie est gagnée lorsque pacman a dévoré les 200 bonbons. En revanche, si un fantôme rencontre en un point pacman, la partie est perdue.

### Avatar et IA

[I'm a relative reference to a repository file](./Asset/ghost0.png)
ont été disposé arbitrairement en dure un point de départ et un point d'arrivé. Il peut à l'aide du clic droit placer et retirer à discrétion des "murs", soit bloquer des zones de sorte que l'application ait l'apparence d'un labyrinthe. On peut en se jouant distinguer un mur d'une case asseptisé grâce à leur couleur de fond. Aussi disponible, le clic gauche, déclanche quant à lui la recherche d'un chemin le plus court et l'affiche. On peut redémarrer l'exercice, en cliquant à nouveau.



## Explication - 1 Tour
