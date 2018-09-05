#ifndef MMAZE_GRAPHE_HPP
#define MMAZE_GRAPHE_HPP

#include "tuile.hpp"
#include "sommet.hpp"

#include <vector> 

namespace MMaze {

class Graphe {

    public :
        std::vector <std::vector <int>> g; 


        Graphe();
        ~Graphe();

        // Recuperation des voisins d'une case
        void recuperer_voisin_haut (Case c, int n, std::vector <int> v, Tuile t);
        void recuperer_voisin_gauche (Case c, int n, std::vector <int> v, Tuile t);
        void recuperer_voisin_droite (Case c, int n, std::vector <int> v, Tuile t);
        void recuperer_voisin_bas (Case c, int n, std::vector <int> v, Tuile t);

        // Creation d'un graphe a partir d'une tuile
        void creation_graphe (Tuile t);

        std::vector <int> distance_minimale(Case depart,Case fin);


} ;

} //end of namespace MMaze

#endif