#ifndef MMAZE_TUILE_HPP
#define MMAZE_TUILE_HPP

#include "case.hpp"
#include "mur.hpp"
#include "melangeur.hpp"

#include <vector>
#include <iostream>
#include <cassert>
#include <cstdlib>

namespace MMaze {

class Tuile {

  public :

    bool mur_debout [23];
    int union_find [16];
    bool objectif [16];
    int couleurObjectif [16];
    bool porte [16];
    int couleurPorte [16];
    bool depart [16];
    int couleurDepart [16];
    bool sortie [16];
    int couleurSortie [16];

    Tuile() ;

    void creation_tuile (int x);

    void creation_tuile_depart();

    //tourne une tuile
    void tourner_tuile(int rotation);

    // tourne une tuile automatiquement en fonction de la case de sortie de la tuile précédente
    void rotation_automatique (Tuile &t, Case c);

    // gere la condition d'arret de l'union_find pour la creation d'une tuile normale
    bool condition_while();

    //indique si deux cases voisines sont separees par un mur
    bool mur(Mur m) const ;

    //indique si une case est accessible depuis les portes ou non
    bool accessible(Case c) const ;

    //affichage
    friend std::ostream& operator<<(std::ostream& out, const Tuile& t) ;

  private :

    //affichage
    void afficher_horizontal(std::ostream& out, unsigned int i) const ;
    void afficher_vertical(std::ostream& out, unsigned int i) const ;
} ;

} //end of namespace MMaze

#endif
