/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_yote;

/**
 *
 * @author Léa Lacombe
 */
public class Pion
{
    private final int mvt=1;//amplitude mouvement horizontal et vertical autorisé pour un déplacement
    private final int mvtPrise=2;//amplitude mouvement horizontal et vertical autorisé pour une prise

    private CouleurPion couleur;//blanc, noir determine le joueur

    /**
     *
     * @param couleur // 0 au joueur blanc, 1 au joueur noir
     */
    public Pion(CouleurPion couleur)
    {
        this.couleur = couleur;
    }
    
    /**
     *
     * @return le nombre de mouvement de case autorise pour ce deplacer
     */
    public int getMvt() 
    {
        return this.mvt;
    }

    /**
     *
     * @return le nombre de mouvement de case autorise pour manger
     */
    public int getMvtPrise() 
    {
        return this.mvtPrise;
    }

    /**
     *
     * @return pour conaitre la couleur du pion
     */
    public CouleurPion getCouleur() 
    {
        return this.couleur;
    }

    @Override
    public String toString()
    {
        String message = "Le joueur " + this.getCouleur();
        message = message + "\nMouvement de deplacement autorisisé = " + this.getMvt();
        message = message + "\nMouvement de deplacement pour manger son aversére = " + this.getMvtPrise();
        return message;
    }
}
