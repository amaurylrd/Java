# *Thor* Pacman

Encapsule l'ensemble des sources qui forment un jeu pacman-like tour par tour.

## Notion exploitée

Il s'agit d'un projet qui avant tout a été contraint par le temps. Néanmoins, évalué à l'échelle du temps passé au code et à la recherche (quelques heures), il faut retenir que nombreux ont été les bénéfices.

+ Pacman est l'application première qui tore 
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

![I'm a relative reference to a repository file](./Asset/ghost1.png)  Le fantôme vert avance aléatoirement jusqu'à être informé de la position du joueur quand il le voit et le chase alors avant de le perdre de vue à nouveau. 




***

## Explication - 1 Tour
