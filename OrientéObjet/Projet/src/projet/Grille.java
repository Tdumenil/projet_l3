/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Brandon-pc
 */
public class Grille {
    protected int longeur,largeur; // a utiliser plus tard
    Case Tab[][];
    Chemin ch;
    
    // Initialise une nouvelle grille
    public Grille(){
        // Crée une grille, manuellement, en sachant où sont les symboles
        ch = new Chemin();
        longeur = 3;
        largeur = 3;
        Tab = new Case [longeur][largeur];
        for(int i = 0;i < longeur;i++){
            for(int j = 0;j < largeur;j++){
                Tab[i][j] = new CaseSymbol (i,j,ListSymbol.VIDE);
            }
        }
        
        
        Tab[0][0] = new CaseSymbol(0,0,ListSymbol.CARRE);
        Tab[2][1] = new CaseSymbol(2,1,ListSymbol.CARRE);
        Tab[0][2] = new CaseSymbol(0,2,ListSymbol.CROIX);
        Tab[2][2] = new CaseSymbol(2,2,ListSymbol.CROIX);
    }
    
    // Crée une grille aleatoire
    /*public Grille(){
        ch = new Chemin();
            
        Tirage t = new Tirage();
        ArrayList<Case> CaseTiré = new ArrayList();
        CaseTiré=t.Tirer4();
          
        longeur = 3;largeur = 3;
          
          Tab = new Case [largeur][longeur];
            for(int i = 0;i < largeur;i++){
                for(int j = 0;j < longeur;j++){
                    Tab[i][j] = new CaseSymbol (i,j,ListSymbol.VIDE);
                    Tab[CaseTiré.get(0).x][CaseTiré.get(0).y]=CaseTiré.get(0);
                    Tab[CaseTiré.get(1).x][CaseTiré.get(1).y]=CaseTiré.get(1);
                    Tab[CaseTiré.get(2).x][CaseTiré.get(2).y]=CaseTiré.get(2);
                    Tab[CaseTiré.get(3).x][CaseTiré.get(3).y]=CaseTiré.get(3);
                }
            }
            
    }*/
        
    @Override    
    public String toString(){
        String chaine="";
        for(int i=0;i<longeur;i++){
            for(int j=0;j<largeur;j++){
                chaine +=" "+Tab[i][j].toString();
            }
        chaine+="\n";
        }
        return chaine;
    }
    
    
    public Case[][] getTab() {
            return Tab;
    }
    
    // Fonction qui gere le cheminement entre deux symboles
    // en utilisant une pile
    public void chemin (CaseSymbol c) {
        if (c.symbol == ListSymbol.VIDE && ch.chemin.empty()){
            System.out.println("Faux depart");
            StopDD(c);
        }
        else {
            if (ch.chemin.empty()) {
                System.out.println("Symbole de la case 1 : " + c.symbol);
                if (ch.chemin.empty()==true && c.symbol!=ListSymbol.VIDE) {
                    System.out.println("Rentre dans la fct Start");
                    System.out.println("Coordonnées : " + c.coordonne());
                    StartDD(c);
                }
                else if (ch.chemin.empty()==false && c.symbol!=ListSymbol.VIDE){
                    System.out.println("Rentre dans la fct Stop");
                    System.out.println("Coordonnées : " + c.coordonne());
                    StopDD(c);
                }
                else if (ch.chemin.empty() && c.symbol == ListSymbol.VIDE) {
                    System.out.println("Rentre dans la fct Stop");
                    System.out.println("Coordonnées : " + c.coordonne());
                    StopDD(c);                
                }
                else {
                    System.out.println("Rentre dans la fct Enter");
                    System.out.println("Coordonnées : " + c.coordonne());
                    EnterDD(c);
                }
            }
            Case test = (Case) ch.chemin.peek();
            if (c.x != test.x || c.y != test.y) {
                System.out.println("Symbole de la case 2 : " + c.symbol);
                if (ch.chemin.empty()==true && c.symbol!=ListSymbol.VIDE) {
                    System.out.println("Rentre dans la fct Start");
                    System.out.println("Coordonnées : " + c.coordonne());
                    StartDD(c);
                }
                else if (ch.chemin.empty()==false && c.symbol!=ListSymbol.VIDE){
                    System.out.println("Rentre dans la fct Stop");
                    System.out.println("Coordonnées : " + c.coordonne());
                    StopDD(c);
                }
                else {
                    System.out.println("Rentre dans la fct Enter");
                    System.out.println("Coordonnées : " + c.coordonne());
                    EnterDD(c);
                }
            }
        }
    }
    
    // Fonction qui demarre le cheminement (la pile)
    public void StartDD(CaseSymbol c ){
        if(c.symbol != ListSymbol.VIDE){
            ch.symbol = c.symbol;
            ch.chemin.push(c);
            System.out.println("On demarre le chemin");
            System.out.println("Symbole associé au chemin : " + c.symbol);
        }
        else {
            Invalider(ch.chemin);
        }
    }
    
    // Fonction qui poursuit le cheminement (empile sur la pile)
    public void EnterDD(CaseSymbol c) {
        if (c.symbol == ListSymbol.VIDE && Voisin(c,(CaseSymbol) ch.chemin.peek())){
            ch.chemin.push(c);
            System.out.println("Le chemin avance");
        }
        else {
            Invalider(ch.chemin);
        }
    }
    
    // Fonction qui termine le cheminement
    public void StopDD(CaseSymbol c) {
        if (!c.estLibre() && c.symbol== ch.symbol) {
            ch.chemin.push(c);    
            Valider(ch.chemin);
        }
        else {
            Invalider(ch.chemin);
        }
    }
    
    // Fonction qui verifie si les cases dans la pile se suivent
    public boolean Voisin (Case test, Case last) {
        if (test.x-1 == last.x || test.x+1 == last.x ||
                test.y-1 == last.y || test.y+1 == last.y) {
            return true;
        }
        else {
            return false;
        }
    }
    
    // Fonction qui valide le chemin en cours
    public void Valider (Stack chemin) {
        System.out.println("Le chemin testé est valide !");
        //chemin.removeAllElements();
        System.out.print("Le chemin :");
        System.out.println(chemin.size());        
        ch.Validation = true;
    }
    
    // Fonction qui invalide le chemin en cours
    public void Invalider(Stack chemin) {
        System.out.println("Le chemin testé n'est pas valide !");
        //chemin.removeAllElements();
        System.out.print("Le chemin est vide apres remove : ");
        System.out.println(chemin.empty());
        ch.Valide = false;
        System.out.println("Valide : " + ch.Valide);
    }
    
    
    
}




