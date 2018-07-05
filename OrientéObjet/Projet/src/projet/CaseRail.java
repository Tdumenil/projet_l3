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
public class CaseRail extends Case {
    Rail symbolRail;

    public CaseRail(int i, int j,Rail symbolRail) {
        super (i,j);
        this.symbolRail = symbolRail;
    }

    public Rail getSymbolRail() {
        return symbolRail;
    }
    
    @Override 
    public String toString(){
        return symbolRail.toString();
    }
}
