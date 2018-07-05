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
public class CaseSymbol extends Case {
    ListSymbol symbol;

    public CaseSymbol(int i, int j,ListSymbol symbol) {
        super(i,j);
        this.symbol = symbol;
    }

    public ListSymbol getSymbol() {
        return symbol;
    }
    
    @Override 
    public String toString(){
        return symbol.toString();
    }
     
    public boolean estLibre () {
        if (symbol == ListSymbol.VIDE) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
