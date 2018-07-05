/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Stack;

/**
 *
 * @author dumenil
 */

// La classe qui gère la pile
public class Chemin {
    Stack chemin;
    ListSymbol symbol;
    boolean Validation;
    boolean Valide;
    
    public Chemin () {
        chemin = new Stack();
        Validation = false;
        Valide = true;
        symbol = ListSymbol.VIDE;
    }
    
}
