#include "melangeur.hpp"
#include <iostream>
#include <stdlib.h>  
#include <string.h>
#include <cassert>

namespace MMaze {

Melangeur::Melangeur(int octets) {
  /* votre code ici */
  data = (char*) malloc (octets);
  taillet = 0;
  capacite = 1;
  this->octets = octets;
}

Melangeur::~Melangeur() {
  /* votre code ici */
  free(data);
}

void Melangeur::inserer(const void* elem) {
  /* votre code ici */
  if (capacite == taillet) {
    doubleCapacite();
  }
  memmove(data + octets * taillet,elem,octets);
  ++ taillet;
}

void Melangeur::retirer(void* elem) {
  /* votre code ici */
  int i = rand()%(taillet);
  memmove(elem,data + octets * i,octets);
  memmove(data+octets*i,data+octets*(taillet-1),octets);
  -- taillet;
}

void Melangeur::vider() {
  /* votre code ici */
  data = 0;
  taillet = 0;

}

int Melangeur::taille() {
  /* votre code ici */
  return taillet ;
}

void Melangeur::doubleCapacite() {
  capacite *= 2;
  char * tmp = (char *) realloc (data,capacite*octets);
  assert(tmp);
  data = tmp;
}

} //end of namespace MMaze
