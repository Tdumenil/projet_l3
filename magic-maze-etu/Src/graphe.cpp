#include "graphe.hpp"
#include "sommet.hpp"
#include "case.hpp"

#include <vector>

namespace MMaze {

    Graphe::Graphe() {} 

    Graphe::~Graphe() {}


    void Graphe::recuperer_voisin_haut (Case c, int n, std::vector <int> v, Tuile t) {
        try {
            Case c1 =c.voisine(HAUT);
            Mur m = Mur(c,c1);
            while (!t.mur_debout[m.index()]) {
                g[n].push_back(c1.index());
                c1 = c1.voisine(HAUT);
                m = Mur(m.index()-4);
            }
        }
        catch (const std::domain_error& e)  {}            
    }

    void Graphe::recuperer_voisin_gauche (Case c, int n, std::vector <int> v, Tuile t) {
        try {
            Case c1 =c.voisine(GAUCHE);
            Mur m = Mur(c,c1);
            while (!t.mur_debout[m.index()]) {
                g[n].push_back(c1.index());
                c1 = c1.voisine(GAUCHE);
                m = Mur(m.index()-1);
            }
        }
        catch (const std::domain_error& e)  {}            
    }

    void Graphe::recuperer_voisin_droite (Case c, int n, std::vector <int> v, Tuile t) {
        try {
            Case c1 =c.voisine(DROITE);
            Mur m = Mur(c,c1);
            while (!t.mur_debout[m.index()]) {
                g[n].push_back(c1.index());
                c1 = c1.voisine(DROITE);
                m = Mur(m.index()+1);
            }
        }
        catch (const std::domain_error& e)  {}            
    }

    void Graphe::recuperer_voisin_bas (Case c, int n, std::vector <int> v, Tuile t) {
        try {
            Case c1 =c.voisine(BAS);
            Mur m = Mur(c,c1);
            while (!t.mur_debout[m.index()]) {
                g[n].push_back(c1.index());
                c1 = c1.voisine(BAS);
                m = Mur(m.index()+4);
            }
        }
        catch (const std::domain_error& e)  {}            
    }

    void Graphe::creation_graphe(Tuile t) {
        std::vector <int> v;
        int n = t.union_find[13];
        for (int i = 0; i<16; i++) {
            if (t.union_find[i]==n) {
                v.push_back(i);
            }
        }
        g.resize(v.size());
        for (unsigned int i = 0; i < v.size(); i++) {
            Case c = Case(v[i]);
            g[i].push_back(v[i]);
            recuperer_voisin_haut(c,i,v,t);
            recuperer_voisin_gauche(c,i,v,t);
            recuperer_voisin_droite(c,i,v,t);
            recuperer_voisin_bas(c,i,v,t);
        }
        for (unsigned int i = 0; i < g.size(); i ++) {
            for (unsigned int j = 0; j < g[i].size(); j++) {
                std::cout << g[i][j] << ":";
            }
            std::cout << std::endl;
        }
    }


    std::vector <int> Graphe::distance_minimale(Case depart,Case fin) {
        std::vector <int> file;
        std::vector <int> dist;
        std::vector <int> prec;
        bool end = false;

        for (unsigned int i = 0; i < g.size(); i++) {
            dist.push_back(0);
        }

        file.push_back(depart.index());
        prec.resize(g.size());


        while (!file.empty() && !end) {
            int c = file.front();
            std::cout << "file = " << c << std::endl;
            file.erase(file.begin());
            for (int i = 0; i < g.size(); i++) {
                if (dist[i] == 0) {
                    dist[i] = dist[c] + 1;
                    prec [i] = c;
                    file.push_back(i);
                }
                if (i == fin.index()) {
                    end = true;
                }
            }
            std::vector <int> pile;
            std::vector <int> chemin;

            int x = prec[fin.index()];
            while (x != depart.index()) {
                pile.push_back(x);
                std::cout << x << " ";
                x = prec[x];
            }
            std::cout << std::endl;

            chemin.push_back(depart.index());
            while (!pile.empty()) {
                chemin.push_back(pile.front());
                chemin.erase(chemin.begin());
            }
            chemin.push_back(fin.index());

            return chemin;
        }
    } 

} // end of namespace MMaze 