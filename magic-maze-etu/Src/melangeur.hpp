#ifndef MMAZE_MELANGEUR_HPP
#define MMAZE_MELANGEUR_HPP

namespace MMaze {

class Melangeur {

  public :

    Melangeur(int octets) ;

    ~Melangeur() ;

    void inserer(const void* elem) ;

    void retirer(void* elem) ;

    void vider() ;

    int taille() ;

    void doubleCapacite();

  /* vos membres ici */

    void * tab;
    char * data;
    int taillet;
    int capacite;
    int octets;


} ;

} //end of namespace MMaze

#endif
