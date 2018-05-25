au départ de k 0 i 0 et j 0
on veux atteindre l'autre case rouge

autour de (i,j)
on vérifie
- pas la case de départ
- pas un mur
- pas une case déjà balisé (background ou valeur)
on modifie l'apparence et les attributs
si case finale on break

cmp = le total d'étiquettes k
pour chaque case signature k
on regarder le voisinage et décrémente cmp
on incrémente k