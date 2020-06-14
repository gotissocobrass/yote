/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_yote;

/**
 *
 * @author Léa LACOMBE
 */
import javax.swing.*;
import java.awt.*;
import static java.awt.Color.black;

/**
 *
 * @author Léa LACOMBE
 */
public class StockPion extends JPanel 
{
    private Case[][] monStockPion ;
    private CouleurPion couleur;

    /**
     *
     * @param couleur  du fond
     */
    public StockPion(CouleurPion couleur) 
    {
        // banck de piont que des joueurs
	this.couleur = couleur;
        Pion piont1 = new Pion(couleur);
        // cree les casses
        this.setLayout(new GridLayout (6,2));
        this.monStockPion = new Case [6][2]; // tableau de case
        // remplie le tableau de case
        for (int longueur = 0; longueur< this.monStockPion.length;longueur++)
        {
            for (int largueur = 0; largueur< this.monStockPion[longueur].length;largueur++)
            {
                Case variable = new Case (black,largueur,longueur,1);
                variable.setPion(piont1);
                
                this.monStockPion[longueur][largueur] = variable;
                this.add(variable);
            } 
        }  
    }
}
