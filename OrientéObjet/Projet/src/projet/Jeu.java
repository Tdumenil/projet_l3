/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import static javafx.application.Application.launch;

/**
 *
 * @author Brandon-pc
 */
public class Jeu {
    //contient grille
    Grille jeuGrille;

    public Jeu() {
        jeuGrille = new Grille();
    }
    
    public Grille getJeuGrille() {
        return jeuGrille;
    }
        
  
}
