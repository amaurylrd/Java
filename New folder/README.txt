au d�part de k 0 i 0 et j 0
on veux atteindre l'autre case rouge

autour de (i,j)
on v�rifie
- pas la case de d�part
- pas un mur
- pas une case d�j� balis� (background ou valeur)
on modifie l'apparence et les attributs
si case finale on break

cmp = le total d'�tiquettes k
pour chaque case signature k
on regarder le voisinage et d�cr�mente cmp
on incr�mente k