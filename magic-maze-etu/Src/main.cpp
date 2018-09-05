#include "tuile.hpp"
#include "melangeur.hpp"
#include "couleurs.hpp"
#include "direction.hpp"
#include "graphe.hpp"

#include <iostream>
#include <cstdlib>
#include <vector>
#include <unistd.h>

using namespace MMaze ;

int main() {
  srand(time(NULL));

  Melangeur mInteret (sizeof(int)); // Melangeur pour les objectifs et sorties sur les tuiles

  for (int i = 0; i < 8; i++) {
    mInteret.inserer(&i);
  }

  int n;
  system("clear");
  std::cout << "Debut de partie !!" << std::endl;
  do {
    std::cout << "Avec combien de tuiles voulez-vous jouer ? (minimun 8 tuiles) ";
    std::cin >> n;
  }
  while (n < 8);

  Melangeur mTuile (sizeof(int)); // Melangeur pour tirer les tuiles aléatoirement

  for (int i = 0; i < n; i++) {
    mTuile.inserer(&i);
  }

  std::vector <Tuile> vect; // vecteur qui va stocker les tuiles 

  for (int i = 0; i < n; i ++) { // Creation des tuiles 
    int x;
    if (i < 8) { // Creation des tuiles avec un point d'interet (objectif ou sortie)
      mInteret.retirer(&x);
    }
    else { // Creation des tuiles de remplissage (sans point d'interet)
      x = 9;
    }
    Tuile t;
     // std::cout << "Tuile n°" << i+1 << std::endl;
    t.creation_tuile(x);
    vect.push_back(t); // Stockage des tuiles dans un vecteur 
  }

  Tuile t;
  t.creation_tuile_depart(); // Creation de la tuile de depart
  std::cout << t << std::endl; // Affichage de la tuile de depart

  for (int i = 0; i < n; i ++) {
    int x;
    mTuile.retirer(&x);
    std::cout << vect[x] << std::endl; // Affichage des tuiles aleatoirement
  }
  
  Case c = Case (4);
  t.rotation_automatique(vect[0],c); // rotation automatique de la tuile vect[0] en fonction de 
  std::cout << vect[0] << std::endl; // la tuile de depart
  

  // Test sur le plus court chemin, pas au point
  Graphe g;
  g.creation_graphe(vect[1]);
  /*
  Case c13 = Case (13);
  Case c11 = Case (11);
  std::vector <int> chemin = g.distance_minimale(c13,c11);

  std::cout << "Chemin de 13 a 11 : ";
  for (int i = 0; i<chemin.size(); i++) {
    std::cout << chemin[i] << " ";
  }
  std::cout << std::endl;
  */

  return 0 ;
}