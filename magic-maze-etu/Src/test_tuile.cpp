#include "tuile.hpp"
#include "melangeur.hpp"
#include "couleurs.hpp"
#include "direction.hpp"

#include <iostream>
#include <cstdlib>
#include <vector>
#include <unistd.h>

using namespace MMaze ;

int main() {
  srand(time(NULL));

  Melangeur mInteret (sizeof(int));

  for (int i = 0; i < 8; i++) {
    mInteret.inserer(&i);
  }

  std::vector <Tuile> vect;

  for (int i = 0; i < 8; i ++) {
    int x;
    mInteret.retirer(&x);
    Tuile t;
    std::cout << "Tuile n°" << i+1 << std::endl;
    t.creation_tuile(x);
    vect.push_back(t);
  }


  for (int i = 0; i < 8; i ++) {
    std::cout << "Tuile n°" << i+1 << std::endl;
    std::cout << vect[i] << std::endl;
  }

  /*
  Tuile t;
  t.creation_tuile(x);
  std::cout << t << std::endl ;


  Tuile td;
  td.creation_tuile_depart();
  std::cout << td << std ::endl;
  */

  //utilisation des couleurs
  std::cout
    << TXT_JAUNE << "&"
    << TXT_ORANGE << "&"
    << TXT_VERT << "&"
    << TXT_VIOLET << "&"
    << TXT_CLEAR
    << BG_JAUNE << "&"
    << BG_ORANGE << "&"
    << BG_VERT << "&"
    << BG_VIOLET << "&"
    << TXT_CLEAR
    << std::endl ;

  return 0 ;
}
