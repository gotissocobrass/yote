/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_yote;

/**
 *
 * @author Léa
 */
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Yote
{
    public static int etat; // 0 au tour du joueur de selectionner sa piece,  1 en attente de la destination, 2 en attente de la selection du deuxième pion à prendre
    public static CouleurPion joueur ; // 0 au joueur blanc, 1 au joueur noir

    public static Case caseDep; // case de depart
    public static Case caseArr; // case de arrive
    public static Case casePrise; // case manger

    

    public static Plateau unPlateau;
    public static StockPion stockBlanc;
    public static StockPion stockNoir;

    public static Border redline = BorderFactory.createLineBorder(Color.red, 10, false);
    public static Border empty = BorderFactory.createEmptyBorder();
    
    
    public static Fenetre fenetrePrincipale;
    public static JLabel scoreBlanc;
    public static JLabel scoreNoir;

    public static int nbPionBlanc = 12;
    public static int nbPionNoir = 12;
    

    
    
    public static int abs(int x) 
    {//Retourne la valeur absolue de x
        return (x<0 ? -x : x);
    }

    public static void main(String[] args) 
    {

	//Creation de la fenetre
        fenetrePrincipale = new Fenetre("Jeu du Yoté");
        // On donne la possibilite de fermer la fenetre
        fenetrePrincipale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ajouter les composants de la fenetre
        fenetrePrincipale.ajouterComposants(fenetrePrincipale.getContentPane());
        //affichage de la fenetre.
        fenetrePrincipale.pack();
        fenetrePrincipale.setVisible(true);
        joueur= CouleurPion.blanc;
        etat =0;

    }
}
