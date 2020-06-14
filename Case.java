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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 *
 * @author Léa LACOMBE
 */
public class Case extends JButton implements ActionListener {

    private int typeCase;  // si typeCase == 0, case du plateau, si typeCase ==1 case du stock

    private ImageIcon imagePion;
    private Pion pion;
    private Color couleurFond;


    private boolean occupe;
    private int abscisse;
    private int ordonnee;

    /**
     *
     * @param couleur de fond
     * @param abs absice
     * @param ord ordonais
     * @param typeCase soit 0 = case de jeu ou 1 = case de reserve de pion
     */
    public Case(Color couleur, int abs, int ord, int typeCase) 
    {
        this.occupe = false;
	this.setBackground(couleur);
        if (typeCase == 0)
            this.setPreferredSize(new Dimension(100, 100));
        else
            this.setPreferredSize(new Dimension(50, 50));
        
        addActionListener(this); // pour ajouter sur IHM
        this.couleurFond = couleur;
        this.abscisse = abs;
        this.ordonnee = ord;
        this.typeCase = typeCase;
    }

    /**
     *
     * @return pour nous permetre de savoir la valeur du pion sur la case
     */
    public Pion getPion() 
    {
        return this.pion;
    }

    /**
     *
     * @param p pion
     */
    public void setPion(Pion p) 
    {// permet de modier la valeur du pion sur la case
        this.pion = p;
        if (p != null) 
        {
            this.pion = p;
            this.occupe = true;
            // metre une icone sur les pions
            if (p.getCouleur() == CouleurPion.blanc)
            {
                // a modifier le chemin
                imagePion = new ImageIcon("C:\\Users\\Public\\Documents\\projet_java\\source_projet\\source_projet\\Icones\\zebre.png");
            }
            else
            {
                // a modifier le chemin
                imagePion = new ImageIcon("C:\\Users\\Public\\Documents\\projet_java\\source_projet\\source_projet\\Icones\\guepard.png");
            }
            this.setIcon(imagePion);
        } 
        else 
        {
            this.occupe = false;
            this.imagePion = null;
            setIcon(imagePion);
            this.pion = null;
        }
    }

    /**
     *
     * @return savoir  Abscisse de la case
     */
    public int getAbscisse() 
    {
        return this.abscisse;
    }
    
    /**
     *
     * @return savoir  TypeCase de la case
     */
    public int getTypeCase() 
    {
        return this.typeCase;
    }
    
    /**
     *
     * @return savoir  Ordonnee de la case
     */
    public int getOrdonnee() 
    {
        return this.ordonnee;
    }

    /**
     *
     * @return savoir  si la case est occupe
     */
    public boolean isOccupe() 
    {
        return this.occupe;
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if (Yote.etat == 0) 
        {
            // reactiver le bouton annuler
            Fenetre.boutonAnnuler.setEnabled(false);

            // si on n'a pas encore selectionne de case de depart
            Yote.caseDep = ((Case) e.getSource());
            
           
            if (Yote.caseDep.isOccupe()) 
            {
                if (Yote.caseDep.getPion().getCouleur() == Yote.joueur) 
                {
                    // si la case selectionne pour le depart du deplacement est valide : elle est occupee par une Pion de la couleur du joueur dont c'est le tour, on peut selectionner la case d'arrivee
                    Yote.caseDep.setBorder(Yote.redline);
                    Yote.etat = 1;
                }
            }
        } 
        else 
        {
            if (Yote.etat == 1) 
            {
		// identification de la case possible d'arrivée
                Yote.caseArr = ((Case) e.getSource());
                if (Yote.unPlateau.isIn(Yote.caseArr)) 
                {
		    // la case selectionnee est differente de la case de depart
                    if (Yote.caseDep.getTypeCase() == 1)  //est de la reserve de pion
                    {
                        if (!Yote.caseArr.isOccupe())
                        {
                            Yote.unPlateau.jouerCoup(Yote.caseDep, Yote.caseArr);
                            
                            Fenetre.boutonAnnuler.setEnabled(true);
                            ChangementJoueur(0);
                            
                        }
                        else 
                        {
                            if (Yote.caseArr.getPion().getCouleur() == Yote.joueur) 
                            {
                                Yote.caseDep.setBorder(Yote.empty);
                                Yote.caseArr.setBorder(Yote.redline);
                                Yote.caseDep = Yote.caseArr;
                                Yote.etat = 1;
                            }
                        }
                    }
		    else 
                    {	
                        if (Yote.unPlateau.coupValide(Yote.caseDep, Yote.caseArr) == 1) 
                        {
			    
                            Yote.unPlateau.jouerCoup(Yote.caseDep, Yote.caseArr);
                            Fenetre.boutonAnnuler.setEnabled(true);
                            ChangementJoueur(0);// laisser la main a lautre joueur
                            
			}
			else 
                        {
                            // pour manger
                            if (Yote.unPlateau.coupValide(Yote.caseDep, Yote.caseArr) == 2) 
                            {
                                if (Yote.joueur == CouleurPion.noir)
                                {
                                    Yote.nbPionBlanc--;
                                }
                                else
                                {
                                    Yote.nbPionNoir--;
                                }
				Yote.unPlateau.jouerCoupPrise(Yote.caseDep, Yote.caseArr);
                                Yote.scoreBlanc.setText("Score Joueur Blanc \n "+ valueOf(Yote.nbPionBlanc));
                                Yote.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Yote.nbPionNoir));
                                Yote.etat = 2; // pour rejouer
                                Yote.caseDep.setBorder(Yote.empty);// retire le cadre rouge

                                Yote.caseDep = null;
                                Yote.caseArr = null;
                                System.out.println("casePrise " +Yote.casePrise);
                                
                            }
			    else 
                            {
                                // cela fait quoi ?
                                if (Yote.unPlateau.coupValide(Yote.caseDep, Yote.caseArr) == 0 && (Yote.caseArr.isOccupe()) && Yote.caseArr.getPion().getCouleur() == Yote.caseDep.getPion().getCouleur()) 
                                {
				    // si la case d'arrivee est de la meme couleur que celle de depart
				    Yote.caseDep.setBorder(Yote.empty);
                                    Yote.caseArr.setBorder(Yote.redline);
                                    Yote.caseDep = Yote.caseArr;
                                    Yote.etat = 1;
                                }
                            }
                        }
                    }
                }
		else 
                {
                    //la case d arrivee sélectionnée n est pas dans le plateau

                    if (Yote.caseArr.isOccupe()) 
                    {
			if (Yote.caseArr.getPion().getCouleur() == Yote.caseDep.getPion().getCouleur()) 
                        {
			    // si la case d'arrivee est de la meme couleur que celle de depart, on la prend comme case de départ
                            Yote.caseDep.setBorder(Yote.empty);
                            Yote.caseArr.setBorder(Yote.redline);
                            Yote.caseDep = Yote.caseArr;
                            Yote.etat = 1;
                        }

                    }
                }
            }
	    else 
            {
                if (Yote.etat == 2) 
                {
                    Yote.derniercoup.encien_eta = Yote.etat;
     		    // Choix du deuxième pion à prendre
		    boolean finPArtie = false;
                    Yote.casePrise = ((Case) e.getSource());
                    CouleurPion couleurSuiv = CouleurPion.blanc;
                    if (Yote.joueur == CouleurPion.blanc)
                        couleurSuiv = CouleurPion.noir;
                    
		    // si sur la case cliquée la pièce est de la bonne couleur, on décremente le nombre de pion du joueur et on test si la partie est finie 
                    if (Yote.casePrise.isOccupe() && Yote.casePrise.getPion().getCouleur() == couleurSuiv) 
                    {
                        Yote.derniercoup.pionsurmanger = Yote.casePrise.getPion();
                        Yote.casePrise.setPion(null);
                        if (Yote.joueur == CouleurPion.blanc) 
                        {
                            Yote.nbPionNoir--;
                            if (Yote.nbPionBlanc == 0)
                                finPArtie = true;
                        } 
                        else 
                        {
                            Yote.nbPionBlanc--;
                            if (Yote.nbPionBlanc == 0)
                            {
                                finPArtie = true;
                            }
                        }
                        
                        if (finPArtie) 
                        {
                            //ouvrir une boite de dialogue pour signifier que le partie est finie : 2 choix fermer le jeu ou recommencer
                            JOptionPane d = new JOptionPane(); // les textes figurant // sur les boutons
                            String lesTextes[] = {"Recommencer", "Fermer le jeu"}; // indice du bouton qui a été // cliqué ou CLOSED_OPTION
                            int retour = d.showOptionDialog(this, "Partie terminée. Le joueur " + Yote.joueur + " a perdu !", "Fin de jeu", 1, 1, new ImageIcon(), lesTextes, lesTextes[0]);
                            if (retour == 0) 
                            {
                                Yote.unPlateau.reinitialiser();
                            } 
                            else 
                            {
                                Yote.fenetrePrincipale.dispose();
                            }
                        }
                        Yote.scoreBlanc.setText("Score Joueur Blanc \n "+ valueOf(Yote.nbPionBlanc));
                        Yote.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Yote.nbPionNoir));

                        Yote.fenetrePrincipale.repaint();
                        Yote.fenetrePrincipale.validate();

                        
                        Fenetre.boutonAnnuler.setEnabled(true);

			Yote.joueur = couleurSuiv;
                        Yote.message.setText("C'est au joueur "+Yote.joueur+" de jouer");
			// on retourne dans l'état 0
			Yote.etat = 0;
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String message = "La case "+ this.abscisse+" "+this.ordonnee+"\n";
        message = message + "Elle est "+ this.isOccupe()+"\n";
        message = message + "C'est une casse type "+this.getTypeCase()+"\n";
        return message;
    }

    /**
     *
     * @param etat
     */
    public void ChangementJoueur (int etat)
    {
        
        Yote.etat = etat;
        
        Yote.caseDep.setBorder(Yote.empty);// retire le cadre rouge
        
        Yote.caseDep = null;
        Yote.caseArr = null;
        
        if (Yote.joueur == CouleurPion.blanc)
        {
            Yote.joueur = CouleurPion.noir;
        }
        else
        {
           Yote.joueur = CouleurPion.blanc;
        }
        Yote.message.setText("C'est au joueur "+Yote.joueur+" de jouer");
    }
}


