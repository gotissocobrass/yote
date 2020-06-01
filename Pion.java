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
public class Pion
{
    private final int mvt=1;//amplitude mouvement horizontal et vertical autorisé pour un déplacement
    private final int mvtPrise=2;//amplitude mouvement horizontal et vertical autorisé pour une prise

    private CouleurPion couleur;//blanc, noir

    public Pion(CouleurPion couleur)
    {
        this.couleur = couleur;
    }
    
    public int getMvt() 
    {
	//A completer
        return this.mvt;
    }
    public int getMvtPrise() 
    {
	//A completer
        return this.mvtPrise;
    }

    public CouleurPion getCouleur() 
    {
        //A completer
        return this.couleur;
    }

    @Override
    public String toString()
    {
        //A completer
        String message = "Le joueur " + this.getCouleur();
        message = message + "\nMouvement de deplacement autorisisé = " + this.getMvt();
        message = message + "\nMouvement de deplacement pour manger son aversére = " + this.getMvtPrise();
        return message;
    }
}
