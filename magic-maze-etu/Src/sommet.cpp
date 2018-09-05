#include "sommet.hpp"

namespace MMaze {

    Sommet::Sommet() {
        a = nullptr;
        b = nullptr;
        c = nullptr;
        d = nullptr;
    }

    Sommet::~Sommet() {
        delete a;
        delete b;
        delete c;
        delete d;
    }


} // end of namespace MMaze 