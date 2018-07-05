/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static javafx.application.Application.launch;

/**
 *
 * @author dumenil
 */

public class VueControleur extends Application {
    
    // modèle qui gère la grille
    Modele m;

    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
       
        
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;
        
        // Initialise la grille graphiquement, avec les symboles
        Grille g = new Grille (); 
        for (int i = 0; i < 3;i++){
            for (int j = 0; j < 3; j++){
                
                ImageView iv1 = new ImageView();
                iv1.setFitWidth(70);
                iv1.setFitHeight(70);
               
                Case c = g.Tab[i][j];
                System.out.print(c + " ");
                if(c.toString() == ListSymbol.ROND.toString()){
                    iv1.setImage(new Image("img/rond.png"));
                    }
                else  if(c.toString() == ListSymbol.CARRE.toString()){
                    iv1.setImage(new Image("img/carre.png"));
                    }
                else  if(c.toString() == ListSymbol.TRIANGLE.toString()){
                    iv1.setImage(new Image("img/triangle.png"));
                    }
                 else  if(c.toString() == ListSymbol.CROIX.toString()){
                    iv1.setImage(new Image("img/croix.png"));
                    }
                else  if(c.toString() == ListSymbol.ETOILE.toString()){
                    iv1.setImage(new Image("img/etoile.png"));
                    }
                else {
                    iv1.setImage(new Image("img/vide.png"));
                }
                
                final Text t = new Text(iv1.toString());
                
                gPane.add(iv1, column++, row);
                
                // Demarage du DragAndDrop
                iv1.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        
                        
                        System.out.println("Rentrer dans le DragAndDrop");
                        /* drag was detected, start a drag-and-drop gesture*/
                        /* allow any transfer mode */
                        Dragboard db = iv1.startDragAndDrop(TransferMode.ANY);

                        /* Put a string on a dragboard */
                        ClipboardContent content = new ClipboardContent();
                        content.putString(c.toString());
                        db.setContent(content);
                        System.out.println(c.coordonne()+ " + " +c.toString());
                        event.consume();
                    }
                });
                
                // Si on lache le DragAndDrop
                iv1.setOnDragDone(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        /* the drag and drop gesture ended */
                        /* if the data was successfully moved, clear it */
                        System.out.println("Lache le DragAndDrop");
                        System.out.println("Symbole du chemin : "+g.ch.symbol.toString());
                        Case d = (Case) g.ch.chemin.peek();
                        System.out.println("Symbole de la case : "+d.toString());
                        System.out.println("Coordonne de la case : "+d.coordonne());
                        if (d.toString()== g.ch.symbol.toString() && 
                                d.toString() != ListSymbol.VIDE.toString() ) {
                            System.out.println("Fonction reussite");
                            m.creationChemin(g);
                            //m.err=m.fin(g);
                            g.ch.Valide = true;
                            g.ch.chemin.removeAllElements();  
                        }
                        else {
                            System.out.println("Fonction echec");
                           m.Faux(g);
                           g.ch.Valide = true;
                           g.ch.chemin.removeAllElements();
                        }

                        event.consume();
                    }
                });
                
                // Quand le DragAndDrop rentre dans une nouvelle case
                // on change l'image qu'il y a dans cette case
                iv1.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        /* mouse moved away, remove the graphical cues */
                        iv1.setImage(new Image("img/DragAndDrop.png"));
                        g.chemin((CaseSymbol) c);
                        System.out.println(c.coordonne()+" + "+c.toString());                       
                        
                        event.consume();
                    }
                });
                
                // Lorsque le DragAndDrop sort de la case
                // on remet l'image qu'il y avait avant dans cette case
                // se qui donne l'effet d'un curseur sur chaque case
                iv1.setOnDragExited(new EventHandler<DragEvent>() {
                    @Override
                    public void handle (DragEvent event) {
                        System.out.println(c.coordonne()+" + "+c.toString());
                        if(c.toString() == ListSymbol.ROND.toString()){
                            iv1.setImage(new Image("img/rond.png"));
                            }
                        else  if(c.toString() == ListSymbol.CARRE.toString()){
                            iv1.setImage(new Image("img/carre.png"));
                            }
                        else  if(c.toString() == ListSymbol.TRIANGLE.toString()){
                            iv1.setImage(new Image("img/triangle.png"));
                            }
                         else  if(c.toString() == ListSymbol.CROIX.toString()){
                            iv1.setImage(new Image("img/croix.png"));
                            }
                        else  if(c.toString() == ListSymbol.ETOILE.toString()){
                            iv1.setImage(new Image("img/etoile.png"));
                            }
                        else {
                            iv1.setImage(new Image("img/vide.png"));
                        }
                    }
                });
                
                // mise a jour de la grille par le modele
                m.addObserver(new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        if (!m.getErr()) {
                            for (int i = 0;i<3;i++){
                                for (int j=0;j<3;j++){
                                    if (g.Tab[c.x][c.y].toString() == ListSymbol.HautBas.toString()) {
                                        iv1.setImage(new Image("img/HautBas.png"));
                                    }
                                    else if (g.Tab[c.x][c.y].toString() == ListSymbol.DroiteHaut.toString() ||
                                            g.Tab[c.x][c.y].toString() == ListSymbol.HautDroite.toString()) {
                                        iv1.setImage(new Image("img/HautDroite.png"));
                                    }
                                    else if (g.Tab[c.x][c.y].toString() == ListSymbol.GaucheHaut.toString() ||
                                            g.Tab[c.x][c.y].toString() == ListSymbol.HautGauche.toString()) {
                                        iv1.setImage(new Image("img/HautGauche.png"));
                                    }
                                    else if (g.Tab[c.x][c.y].toString() == ListSymbol.GaucheBas.toString() ||
                                            g.Tab[c.x][c.y].toString() == ListSymbol.BasGauche.toString()) {
                                        iv1.setImage(new Image("img/GaucheBas.png"));
                                    }
                                    else if (g.Tab[c.x][c.y].toString() == ListSymbol.DroiteBas.toString() ||
                                            g.Tab[c.x][c.y].toString() == ListSymbol.BasDroite.toString()) {
                                        iv1.setImage(new Image("img/DroiteBas.png"));
                                    }
                                    else if(g.Tab[c.x][c.y].toString() == ListSymbol.ROND.toString()){
                                        iv1.setImage(new Image("img/rond.png"));
                                        }
                                    else  if(g.Tab[c.x][c.y].toString() == ListSymbol.CARRE.toString()){
                                        iv1.setImage(new Image("img/carre.png"));
                                        }
                                    else  if(g.Tab[c.x][c.y].toString() == ListSymbol.TRIANGLE.toString()){
                                        iv1.setImage(new Image("img/triangle.png"));
                                        }
                                    else  if(g.Tab[c.x][c.y].toString() == ListSymbol.CROIX.toString()){
                                        iv1.setImage(new Image("img/croix.png"));
                                        }
                                    else  if(g.Tab[c.x][c.y].toString() == ListSymbol.ETOILE.toString()){
                                        iv1.setImage(new Image("img/etoile.png"));
                                        }
                                    else if (g.Tab[c.x][c.y].toString() == ListSymbol.VIDE.toString()) {
                                        iv1.setImage(new Image("img/vide.png"));
                                    }
                                    else {
                                        iv1.setImage(new Image("img/GaucheDroite.png"));
                                    }
                                }
                            }
                            m.err=m.fin(g);
                        } else { 
                            // Fermeture du programme en cas de victoire
                            final Text t = new Text ("Victoire");
                            iv1.setAccessibleText(t.toString());
                            primaryStage.close();
                        }
                    }
                });
           
            }
            System.out.println("");
            column = 0;
            row ++;

        }
        
        
        // Affichage de la scène créé
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        
        primaryStage.setTitle("Casse-Tête");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
} 
