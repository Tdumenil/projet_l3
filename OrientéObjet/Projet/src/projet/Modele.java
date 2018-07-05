/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Observable;
/**
 *
 * @author Brandon-pc
 */

// Classe modele, qui s'occupe de modifier la grille de jeu avant la mise
// a jour graphique
public class Modele extends Observable {
    
    boolean err = false;
    Chemin lastChemin;
    Grille gModele = new Grille();
    
    // Si le chemin testé est faux
    public void Faux (Grille g) {
        gModele = g;
        while (gModele.ch.chemin.empty() != true) {
            CaseSymbol c = (CaseSymbol) gModele.ch.chemin.pop();
            g.Tab[c.x][c.y] = new CaseSymbol (c.x,c.y,c.symbol);
        }
        
        g = gModele;
        System.out.println(g.toString());
        System.out.println("Echec du coup");
    }
    
    // Si le chemin testé est juste -> creation du chemin
    public void creationChemin (Grille g) {
        gModele = g;
        lastChemin = gModele.ch;
        Chemin sChemin = lastChemin;
        
        CaseSymbol cAvant = (CaseSymbol) lastChemin.chemin.pop();
        while (lastChemin.chemin.size() >= 2) {
            System.out.println("size : " + lastChemin.chemin.size());
            System.out.println("size chemin : "+g.ch.chemin.size());
            System.out.println("cAvant " + cAvant.coordonne());
            CaseSymbol c = (CaseSymbol) lastChemin.chemin.pop();
            System.out.println("c " + c.coordonne());
            CaseSymbol cApres = (CaseSymbol) lastChemin.chemin.peek(); 
            System.out.println("cApres "+ cApres.coordonne());
            if (c.symbol != ListSymbol.VIDE) {
                
            }
            else {
                if ((cAvant.x == cApres.x-2) && (cAvant.y == cApres.y)){
                    gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.HautBas);
                }
                else if ((cAvant.x == cApres.x+2) && (cAvant.y == cApres.y)){
                    gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.HautBas);
                }
                else if ((cAvant.x+1== cApres.x) && (cAvant.y+1 == cApres.y)){
                    if (c.x == cAvant.x) {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.GaucheBas);
                    }
                    else {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.HautDroite);
                    }
                }
                else if ((cAvant.x-1 == cApres.x) && (cAvant.y-1 == cApres.y)){
                    if (c.x == cAvant.x) {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.DroiteHaut);
                    }
                    else {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.BasGauche);
                    }
                }
                else if ((cAvant.x+1 == cApres.x) && (cAvant.y-1 == cApres.y)){
                    if (c.x == cAvant.x) {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.DroiteBas);
                    }
                    else {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.HautGauche);
                    }
                }
                else if ((cAvant.x-1 == cApres.x) && (cAvant.y+1== cApres.y)){
                    if (c.x == cAvant.x) {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.GaucheHaut);
                    }
                    else {
                        gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.BasDroite);
                    }
                }
                else if ((cAvant.x == cApres.x) && (cAvant.y == cApres.y-2)){
                    gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.DroiteGauche);
                }
                else {   
                    gModele.Tab[c.x][c.y] = new CaseSymbol(c.x,c.y,ListSymbol.DroiteGauche);    
                }
            }
            System.out.println("gModele : "+ c.x + " " + c.y + " "
                    + gModele.Tab[c.x][c.y].toString());
            cAvant = c;
        }
        System.out.println(gModele.toString());
        
        setChanged();
        notifyObservers();   
    }
    
    // Verification de la grille pour la fin du jeu
    public boolean fin(Grille g) {
        for (int i =0; i<3;i++) {
            for (int j = 0; j<3;j++) {
                if (g.Tab[i][j].toString() == ListSymbol.VIDE.toString()) {
                    return false;
                }
            } 
        }
        System.out.print("Fin du jeu : Victoire !");
        return true;
    }
    
    @Override    
    public String toString(){
        String chaine="";
        for(int i=0;i<gModele.longeur;i++){
            for(int j=0;j<gModele.largeur;j++){
                chaine +=" "+gModele.Tab[i][j].toString();
            }
        chaine+="\n";
        }
        return chaine;
    }
    
        
    public boolean getErr() {
        return err;
    }
    
    public Chemin getChemin() {
        return lastChemin;
    }
    
    public Grille getGrille() {
        return gModele;
    }
    
}
