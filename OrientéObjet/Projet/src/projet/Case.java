/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author Brandon-pc
 */

// Classe Case, qui permettra de creer une grille
abstract class Case {
    int x,y;
    protected String etat;
    //var qui appelle la valeur

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
        
    @Override 
    public String toString(){
        return super.toString();
    }
    
    public String coordonne () {
        String coor = String.valueOf(x);
        coor += "-";
        coor += String.valueOf(y);
        return coor;
    }
    

    
}
