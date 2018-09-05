#include "tuile.hpp"
#include "couleurs.hpp"

#include <cassert>
#include <vector>
#include <iostream>
#include <cstdlib>

namespace MMaze {

Tuile::Tuile() {
  /* votre code ici */
  for (int i =0; i < 24; i++) {
    mur_debout[i] = true;
  }
  for (int i =0; i < 16; i++) {
    union_find [i] = i;
    objectif [i] = false;
    couleurObjectif [i] = 0;
    porte [i] = false;
    couleurPorte [i] = 0;
    sortie [i] = false;
    couleurSortie [i] = 0;
    depart[i] = false;
    couleurDepart[i] = 0;
  }
}

void Tuile::creation_tuile (int x) { // creation d'une tuile "normale"
  Melangeur mel (sizeof(int));
  for (int i = 0; i < 24; i++) {
    mel.inserer(&i);
  }
  Melangeur mCouleur (sizeof(int));
  for (int i = 1; i < 5; i++){
    mCouleur.inserer(&i);

  }
  Melangeur mPorte (sizeof(int)); // Gestion des portes {
  mPorte.inserer(&union_find[2]);
  mPorte.inserer(&union_find[4]);
  mPorte.inserer(&union_find[11]);
  int j = (rand()%3)+1;
  for (int i = 0; i < j; i++) {
    int n;
    mPorte.retirer(&n);
    porte[n] = true;
    int c;
    mCouleur.retirer(&c);
    couleurPorte[n] = c;
  }
  // std::cout << "Nombre de porte sur la tuile : " << j+1 << std::endl;
  porte[13] = true; // Gestion des portes }
  if (x < 4) { // Gestion des objectifs et sorties { 
    Melangeur mObjectif (sizeof(int));
    for (int i = 0; i < 16; i ++) { // inserer un if a la place du do while 
    /*
      if (i!=2||i!=4||i!=11||i!=13) {
        mObjectif.inserer(&i);
      } */
      mObjectif.inserer(&i);
    } 
    int o;
    do {
      mObjectif.retirer(&o);
    }
    while (o==2||o==4||o==11||o==13);
    objectif[o] = true;
    couleurObjectif [o] = x+1;
    // std::cout << "Objectif sur la tuile : " << o << std::endl;
  }
  else if (x >= 4 && x < 8) {
    Melangeur mSortie (sizeof(int));
    for (int i = 0; i < 16; i ++) {
      mSortie.inserer(&i);
    }
    int s;
    do {
      mSortie.retirer(&s);
    }
    while (s==2||s==4||s==11||s==13);
    sortie[s] = true;
    couleurSortie [s] = x-3;
    // std::cout << "Sortie sur la tuile :" << s << std::endl;
  } // Gestion des objectifs et sorties }
  while (!(condition_while())) {
    int x;
    mel.retirer(&x);
    Mur mur = Mur(x);
    mur_debout[mur.index()] = false;
    if (mur.index() < 12){
      Case c1 = mur[0];
      Case c2 = c1.voisine(BAS);
      try { // Tentative d'enlever un minimun de mur
        if (union_find[c1.index()]==union_find[c2.index()]&&(union_find[c1.index()]
            ==union_find[c1.voisine(DROITE).index()]||union_find[c1.index()]==
            union_find[c1.voisine(GAUCHE).index()])) {
          mur_debout[mur.index()] = true;
        }
        else if (union_find[c2.index()]==union_find[c1.index()]&&(union_find[c2.index()]
            ==union_find[c2.voisine(DROITE).index()]||union_find[c2.index()]==
            union_find[c2.voisine(GAUCHE).index()])) {
          mur_debout[mur.index()] = true;
        }
        else {
        union_find[c2.index()] = c1.index();
        }
      }
      catch (const std::domain_error& e) {
        union_find[c2.index()] = c1.index();
      }    
    }
    else {
      Case c1 = mur[0];
      Case c2 = c1.voisine(DROITE);
      try {
        if (union_find[c1.index()]==union_find[c2.index()]&&(union_find[c1.index()]
            ==union_find[c1.voisine(HAUT).index()]||union_find[c1.index()]==
            union_find[c1.voisine(BAS).index()])) {
          mur_debout[mur.index()] = true;
        }
        else if (union_find[c2.index()]==union_find[c1.index()]&&(union_find[c2.index()]
            ==union_find[c2.voisine(HAUT).index()]||union_find[c2.index()]==
            union_find[c2.voisine(BAS).index()])) {
          mur_debout[mur.index()] = true;
        }
        else {
          union_find[c2.index()] = c1.index();
        }
      }
      catch (const std::domain_error& e) {
        union_find[c2.index()] = c1.index();
      }  
    }
    for (int i =0 ; i < 16; i++) {
      if (i != union_find[i]) {
        union_find[i] = union_find[union_find[i]];
      }
    }
  }
}

void Tuile::creation_tuile_depart () { // creation d'une tuile de depart
  Melangeur mel (sizeof(int));
  for (int i = 0; i < 24; i++) {
    mel.inserer(&i);
  }
  porte[2] = true; // Gestion des portes {
  couleurPorte[2] = 1;
  porte[4] = true;
  couleurPorte[4] = 2;
  porte[11] = true;
  couleurPorte[11] = 3;
  porte[13] = true;
  couleurPorte[13] = 4;
  std::cout << std::endl; // Gestion des portes }
  depart [5]= true ; // Gestion du depart {
  couleurDepart [5] = 1;
  depart [6] = true;
  couleurDepart [6] = 2;
  depart [9] = true;
  couleurDepart [9] = 3;
  depart [10] = true; 
  couleurDepart [10] = 4;
  mur_debout[5] = false;
  mur_debout[6] = false;
  mur_debout[17] = false;
  mur_debout[18] = false; // Gestion du depart }
  while (!(condition_while())) {
    int x;
    mel.retirer(&x);
    Mur mur = Mur(x);
    mur_debout[mur.index()] = false;
    if (mur.index() < 12){
      Case c1 = mur[0];
      Case c2 = c1.voisine(BAS);
      union_find[c2.index()] = c1.index();
    }
    else {
      Case c1 = mur[0];
      Case c2 = c1.voisine(DROITE);
      union_find[c2.index()] = c1.index();
    }
    for (int i =0 ; i < 16; i++) {
      if (i != union_find[i]) {
        union_find[i] = union_find[union_find[i]];
      }
    }
  }
}

void Tuile::tourner_tuile (int rotation) { // fait la rotation d'une tuile 
  int union_find2 [16];
  int objectif2 [16];
  int couleurObjectif2 [16];
  int porte2[16];
  int couleurPorte2[16];
  int sortie2 [16];
  int couleurSortie2 [16];
  int depart2 [16];
  int couleurDepart2 [16];
  for (int i = 0; i < 16; i++) {
    union_find2[i] = union_find[i];
    objectif2[i] = objectif[i];
    couleurObjectif2[i] = couleurObjectif[i];
    porte2[i] = porte[i];
    couleurPorte2[i] = couleurPorte[i];
    sortie2[i] = sortie[i];
    couleurSortie2[i] = couleurSortie[i];
    depart2[i] = depart[i];
    couleurDepart2[i] = couleurDepart[i];
  }
  int mur_debout2 [24];
  for (int i = 0; i < 24; i++) {
    mur_debout2[i] = mur_debout[i];
  }
  for (int i = 0; i < 16; i++) {
    Case c = Case(i);
    Case ct = c.tourne(rotation);
    union_find [c.index()] = union_find2[ct.index()];
    objectif [c.index()] = objectif2[ct.index()];
    couleurObjectif [c.index()] = couleurObjectif2[ct.index()];
    porte [c.index()] = porte2[ct.index()];
    couleurPorte [c.index()] = couleurPorte2[ct.index()];
    sortie [c.index()] = sortie2[ct.index()];
    couleurSortie [c.index()] = couleurSortie2[ct.index()];
    depart[c.index()] = depart2[ct.index()];
    couleurDepart[c.index()] = couleurDepart2[ct.index()];
  }
  for (int i = 0; i < 24; i++) {
    Mur m = Mur(i);
    Mur mt = m.tourne(rotation);
    mur_debout [m.index()] = mur_debout2[mt.index()];
  }
}

void Tuile::rotation_automatique (Tuile & t, Case c) { // tourne la tuile t en fonction de la sortie c
  if (c.index() == 4) {
    t.tourner_tuile(3);
  }
  else if (c.index() == 11) {
    t.tourner_tuile(1);
  }
}

bool Tuile::condition_while () { // renvoie false tant que tout les points d'interets d'une tuile
  bool cond = true;              // ne sont pas relier
  std::vector<int> t;
  t.push_back(13); // case de reference car porte d'accès
  for (int i = 0; i < 16; i++) {
    if (porte[i] == true) {
      if (i != 13) {
        t.push_back(i);
      }
    }
    else if (objectif[i] == true) {
      t.push_back(i);
    }
    else if (depart[i] == true) {
      t.push_back(i);
    }
    else if (sortie[i] == true) {
      t.push_back(i);
    }
  }
  int x = union_find[t[0]];
  for (unsigned int i = 0; i < t.size(); i ++){
    if (union_find[t[i]] != x) {
      return cond = false;
    }
  }
  return cond;
}


bool Tuile::mur(Mur m) const { // renvoie true si le mur existe sur la tuile
  /* remplacez ce code */
  if (mur_debout[m.index()] == true ) {
    return true;
  }
  else {
    return false;
  }
}

bool Tuile::accessible(Case c) const { // renvoie true si la case c est accessible depuis la porte 13
  /* remplacez ce code */
  if (union_find[c.index()] == union_find[13]) {
    return true;
  }
  else {
    return false ;
  }
}

void Tuile::afficher_horizontal(std::ostream& out, unsigned int i) const {
  assert(i < 5) ;
   if((i == 0) && (porte[2])){
     out<< "+---+---+" << txt_colors[couleurPorte[2]] << " ⇑ " << TXT_CLEAR << "+---+" ;
  }
  else if (i==4 && couleurPorte[13] != 0) {
    out << "+---+" << txt_colors[couleurPorte[13]] << " ↓ " << TXT_CLEAR << "+---+---+";
  }
  else if(i == 4 && porte[13]){
    
    out << "+---+ ⇑ +---+---+" ;
  }
  else if (i==4) {
    out << "+---+---+---+---+";
  }
  else if((i == 0) && (!porte[2])){
    out << "+---+---+---+---+" ;
  }
  else {
    out << "+" ;
    for(unsigned int m = 0; m < 4; ++m) {
      Case up = Case(i-1, m) ;
      Case down = Case(i, m) ;
      if(mur(Mur(up, down))) {
        out << "---+" ;
      } else {
        out << "   +" ;
      }
    }
  }
}


void Tuile::afficher_vertical(std::ostream& out, unsigned int i) const {
  assert(i < 4) ;
  
  if (i==1 && porte[4] && couleurPorte[4] == 0) {
    out << txt_colors[couleurPorte[4]] << "⇒" << TXT_CLEAR;
  }
  else if (i==1 && porte[4]) {
    out << txt_colors[couleurPorte[4]] << "⇐" << TXT_CLEAR;
  }
  else {out << "|" ;}

  for(unsigned int m = 0; m < 4; ++m) {
    Case c = Case(i,m);
    /*
    if (porte[c.index()] == true) {
      out << txt_colors[couleurPorte[c.index()]] << " p " << TXT_CLEAR;
    } */
    if (depart[c.index()] == true) {
      out << txt_colors[couleurDepart[c.index()]] << " @ " << TXT_CLEAR;
    }
    else if (sortie[c.index()] == true) {
      out << txt_colors[couleurSortie[c.index()]] << " s " << TXT_CLEAR;
    }
    else if (objectif[c.index()] == true) {
      out << txt_colors[couleurObjectif[c.index()]] << " o " << TXT_CLEAR;
    }
    else {
      out << "   " ; // Interieur de la tuile
    }
    if(m < 3) {
      Case left = Case(i, m) ;
      Case right = Case(i, m+1) ;
      if(m < 3 && mur(Mur(left, right))) {
        out << "|" ;
      } else {
        out << " " ;
      } 
    }
  }

  if (i==2 && porte[11] && couleurPorte[11] == 0) {
    out << txt_colors[couleurPorte[11]] << "⇐" << TXT_CLEAR;
  }
  else if (i==2 && porte[11]) {
    out << txt_colors[couleurPorte[11]] << "⇒" << TXT_CLEAR;
  }

  else {out << "|" ; }
}

std::ostream& operator<< (std::ostream& out, const Tuile& t) {
  for(unsigned int i = 0; i < 4; ++i) {
    t.afficher_horizontal(out, i) ;
    out << std::endl ;
    t.afficher_vertical(out, i) ;
    out << std::endl ;
  }
  t.afficher_horizontal(out, 4) ;
  return out ;
}

} //end of namespace MMaze
