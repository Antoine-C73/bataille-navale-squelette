### Description générale de l'architecture

Ce projet consiste en 3 objets principaux :
    - Les bateaux (AbstractShip)
    - Le plateau
    - Les joueurs (IA ou joueur réel)

# Classe Board

Le plateau (classe Board) implémente plusieurs méthodes "basiques" telles que les accesseurs et les mutateurs de ses différents attributs qui permettent entre autres de savoir si une casse du plateau a été ciblée par un tir ennemi, si elle contient un bateau et de mettre à jour ces attributs.

Les attributs de cette classe sont un tableau de "Tiles" qui stockent l'état de chaque case du plateau, c'est-à-dire quel bateau l'occupe (ou null s'il n'y en a pas) ainsi que si un tir ennemi a eu lieu sur la case.

La méthode print() affiche les deux informations contenues dans les Tiles sur deux plateaux juxtaposés horizontalement.

De plus, la méthode sendHit() permet de traiter l'arrivée d'une frappe ennemie sur le plateau du joueur. Pour cela, elle vérifie que si la case contient un bateau, elle n'a pas déjà été touchée. Si de plus le tir coule le bateau (détecté avec la méthode isSunk() du bateau), cette information est renvoyée via la structure "Hit".

# Classe AbstractShip

Les bateaux sont simplement des structures contenant des données sur chaque bateau, notamment sa taille, son orientation, sa position et le nombre de coups qu'il a reçu (sur différentes parties de sa coque). Chaque bateau peut aussi déterminer s'il a été coulé en fonction du nombre d'impacts qu'il a reçus.

# Classe Player

Enfin, les joueurs doivent implémenter deux méthodes : une pour placer des bateaux et une pour tirer sur un bateau ennemi. Les IA font ces choix au hasard pour le placement des bateaux et via une stratégie prédéterminée pour le choix des positions de tir. Pour les joueurs réels, ces opérations s'effectuent par la lecture de saisies clavier.

### Déroulement d'une partie

L'organisation générale de la fonction Game() est la suivante :
    - Initialiser les deux joueurs (IA ou humain)
    - Placer les bateaux de chaque joueur
    - Tant qu'aucun des deux joueurs n'a perdu, alterner le joueur qui tire sur le plateau de son adversaire et afficher le plateau avant chaque tir
    - Annoncer le joueur gagnant

La phase d'initialisation consiste simplement à créer le plateau du joueur 1 ainsi que celui du joueur 2 et d'initialiser les "Player" 1 et 2 avec ces plateaux ainsi que les bateaux par défaut.

Ensuite, chaque joueur place ses bateaux tant qu'ils sont mal placés et le plateau de chaque joueur s'affiche avant chaque placement.

La phase de jeu consiste simplement à afficher le plateau de jeu à l'aide de la fonction printGame() et d'alterner les tirs entre joueurs. La fonction permettant d'afficher le plateau de jeu est une version modifiée de la fonction d'affichage d'un "Board" car elle combine le "Board" du joueur ayant la main et celui de son adversaire (où seules les cases visées sont affichées). Cette fonction prend en argument les joueurs 1 et 2 dans le bon ordre (c'est-à-dire que le joueur ayant la main doit être passé en argument comme le joueur 1).

Les joueurs se voient alors proposer chacun leur tour de donner une position sur laquelle tirer. Si le tir touche un bateau, celui-ci ajoute 1 au nombre de frappes qu'il a reçues et le joueur concerné rejoue. Une fois que ce joueur rate un tir, c'est au tour de l'autre joueur d'effectuer les mêmes actions. Avant chaque tir, le plateau du jeu propre au joueur ayant la main est affiché et après chaque tir, la position touchée et le résultat du tir sont affichés.

Enfin, dès que le nombre de bateaux coulés de l'un des joueurs égale le nombre de bateaux qu'il possède, la partie est finie et son adversaire est désigné vainqueur.

En mode multijoueur, les deux joueurs sont initialisés comme des joueurs humains, mais la classe PlayerAI héritant de la classe de base Player, les méthodes utilisées ne changent pas.
