/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;

/**
 *
 * @author Brandon-pc
 */

// Classe qui gère le tirage aleatoire d'une grille
public class Tirage {
    
    ArrayList <Case> a = new ArrayList();
    
    Tirage(){
        
            a.add(new CaseSymbol(0,0,ListSymbol.VIDE));
            a.add(new CaseSymbol(0,1,ListSymbol.VIDE));
            a.add(new CaseSymbol(0,2,ListSymbol.VIDE));
            a.add(new CaseSymbol(1,0,ListSymbol.VIDE));
            a.add(new CaseSymbol(1,1,ListSymbol.VIDE));
            a.add(new CaseSymbol(1,2,ListSymbol.VIDE));
            a.add(new CaseSymbol(2,0,ListSymbol.VIDE));
            a.add(new CaseSymbol(2,1,ListSymbol.VIDE));
            a.add(new CaseSymbol(2,2,ListSymbol.VIDE));
    }
    
    ArrayList<Case> Tirer4(){
        
        ArrayList <Case> arrayr = new ArrayList();
        
        
        ArrayList <Integer> arrayS = new ArrayList();
        for(int i=0;i<ListSymbol.values().length-11;i++){
            arrayS.add(i);
        }
        
        int r1=(int)(Math.random()*arrayS.size());
        if (r1 == arrayS.size())
            r1 =arrayS.size()-1;
          r1=arrayS.get(r1);
          arrayS.remove(r1);
        
        
        
        int r2=(int)(Math.random()*arrayS.size());
        if (r2 == arrayS.size())
            r2 =arrayS.size()-1;
          r2=arrayS.get(r2);
          arrayS.remove(r2);
        
        
        for(int i=0;i<2;i++){
        int random=(int)(Math.random()*a.size());
        if (random == a.size())
            random =a.size()-1;
        arrayr.add(new CaseSymbol(a.get(random).x,a.get(random).y,ListSymbol.values()[r1]));
        a.remove(random);
        }
        
        for(int i=0;i<2;i++){
        int random=(int)(Math.random()*a.size());
        if (random == a.size())
            random =a.size()-1;
        arrayr.add(new CaseSymbol(a.get(random).x,a.get(random).y,ListSymbol.values()[r2]));
        a.remove(random);
        }
        
        return arrayr;
    }
    
}
