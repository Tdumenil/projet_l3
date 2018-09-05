#ifndef MMAZE_SOMMET_HPP
#define MMAZE_SOMMET_HPP

namespace MMaze {

class Sommet {
    public :
        Sommet* a;
        Sommet* b;
        Sommet* c;
        Sommet* d;
        int valeur;

        Sommet ();
        ~Sommet();

} ;

} //end of namespace MMaze

#endif