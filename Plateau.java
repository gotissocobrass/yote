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
import static java.awt.Color.white;
import static java.awt.Color.gray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 *
 * @author Léa  LACOMBE
 */
public class Plateau extends JPanel 
{
 
    private Case[][] monPlateau;

    /**
     * Cree un plateu de jeu 
     */
    public Plateau() 
    {
        this.setLayout(new GridLayout (6,5)); //Pour affecter une nouvelle mise en page
        this.monPlateau = new Case[6][5]; // cree le tableau 
        // on a plus qu'a remplire le tableau
        for (int longueur  = 0; longueur< this.monPlateau.length;longueur++)
        {
            for (int  largueur= 0; largueur< this.monPlateau[longueur].length;largueur++)
            {
                Case variable = new Case (gray,largueur,longueur,0);
                this.monPlateau[longueur][largueur] = variable;
                this.add(variable); // permet d'ajouter sur IHM
            } 
        }
    }
    
    /**
     * 
     * @param c
     * @return
     */
    public boolean isIn(Case c)
    {
        // verifier que la casse c appartient au plateau
        for (int longeur_tableau = 0;longeur_tableau < this.monPlateau.length;longeur_tableau++ )
        {
            for (int largeur_tableau = 0;largeur_tableau< this.monPlateau[longeur_tableau].length;largeur_tableau++)
            {
                if (this.monPlateau[longeur_tableau][largeur_tableau].getAbscisse() == c.getAbscisse() && this.monPlateau[longeur_tableau][largeur_tableau].getOrdonnee()== c.getOrdonnee())
                {
                    return true;     
                }
            }
        }
        return false;
    }
    
    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean verifieDeplacementHor(Case dep, Case arr)
    {// verifier si le deplacement horizontal d'un pion entre la casse dep et arr est possible
	
        int Casse_voulons_se_deplacer;
        if (isIn(arr)) // verifier que la casse arriver est sur le plateau 
        {
            // savoir le nombre de casse de deplacement 
            
            if (dep.getAbscisse()> arr.getAbscisse()) //dep se trouve bas par rapport arr
            {
                Casse_voulons_se_deplacer = dep.getAbscisse() - arr.getAbscisse();
                
            }
            else if ((dep.getAbscisse() < arr.getAbscisse()))//dep se trouve haut par rapport arr
            {
                Casse_voulons_se_deplacer =  arr.getAbscisse()- dep.getAbscisse();
            }
            else // meme case
            {
                return false;
            }
            // compare au nombre de deplacement autorisé
            
            if (Casse_voulons_se_deplacer == dep.getPion().getMvt()) // pour ce deplacer
            {
                // savoir si c'est ocuper "arr.isOccupe()"
                return !arr.isOccupe();
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean verifieDeplacementVert(Case dep, Case arr)
    {// verifier si le deplacement vertical d'un pion entre la casse dep et arr est possible
        
        int Casse_voulons_se_deplacer;
        if (isIn(arr))
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getOrdonnee()> arr.getOrdonnee()) // dep a gauche par rapport a arr
            {
                Casse_voulons_se_deplacer = dep.getOrdonnee() - arr.getOrdonnee();
            }
            else if ((dep.getOrdonnee() < arr.getOrdonnee())) // dep a droit par rapport a arr
            {
                Casse_voulons_se_deplacer =  arr.getOrdonnee()- dep.getOrdonnee();
            }
            else // meme case
            {
                return false;
            }
            // compare au nombre de deplacement autorisé
            if (Casse_voulons_se_deplacer == dep.getPion().getMvt()) // pour ce deplacer
            {
                // savoir si c'est ocuper "arr.isOccupe()"
                return !arr.isOccupe();
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean verifieDeplacementHorPrise(Case dep, Case arr)
    {// deplacement pour manger un pion  horisontale
	
        int Casse_voulons_se_deplacer;
        int valeur_abscique_casse_pion_manger;
        if (isIn(arr)) // est bien dans le plateau 
        {
            // savoir le nombre de casse de deplacement 
            
            if (dep.getAbscisse()> arr.getAbscisse())  //dep se trouve bas par rapport arr
            {
                Casse_voulons_se_deplacer = dep.getAbscisse() - arr.getAbscisse();
                valeur_abscique_casse_pion_manger = arr.getAbscisse()+1;
                
            }
            else if ((dep.getAbscisse() < arr.getAbscisse())) //dep se trouve haut par rapport arr
            {
                Casse_voulons_se_deplacer =  arr.getAbscisse()- dep.getAbscisse();
                valeur_abscique_casse_pion_manger = arr.getAbscisse()-1;
            }
            else // le meme
            { // Casse_voulons_se_deplacer = 0 donc pas deplacement
                return false;
            }
            // compare au nombre de deplacement autorisé
            
            if (Casse_voulons_se_deplacer == dep.getPion().getMvtPrise())
            {
                // savoir si c'est ocuper " arr.isOccupe()"
                if (arr.isOccupe())
                {
                    return false;
                }
                else //sinon 
                {
                    return this.monPlateau[arr.getOrdonnee()][valeur_abscique_casse_pion_manger].isOccupe();
                    // return true // si est occuper
                    // return false // pas de pion
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean verifieDeplacementVertPrise(Case dep, Case arr)
    {
	// deplacement pour manger un pion  horisontale
	// A completer
        int Casse_voulons_se_deplacer;
        int valeur_Ordonnee_casse_pion_manger;
        if (isIn(arr)) // est bien dans le plateau 
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getOrdonnee()> arr.getOrdonnee()) // dep a gauche par rapport a arr
            {
                Casse_voulons_se_deplacer = dep.getOrdonnee() - arr.getOrdonnee();
                valeur_Ordonnee_casse_pion_manger = arr.getOrdonnee()+1;
            }
            else if ((dep.getOrdonnee() < arr.getOrdonnee()))  // dep a droit par rapport a arr
            {
                Casse_voulons_se_deplacer =  arr.getOrdonnee()- dep.getOrdonnee();
                valeur_Ordonnee_casse_pion_manger = arr.getOrdonnee()-1;
            }
            else
            {
                return false;
            }
            // compare au nombre de deplacement autorisé
            if (Casse_voulons_se_deplacer == dep.getPion().getMvtPrise())
            {
                // savoir si c'est ocuper
                if (arr.isOccupe())
                {
                    return false;
                }
                else
                {
                    return this.monPlateau[valeur_Ordonnee_casse_pion_manger][arr.getAbscisse()].isOccupe();
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public int coupValide(Case dep, Case arr) // deplacementValide
    {//Teste si la Pion qui est en dep peut de deplacer en arr : renvoie 0 si deplacement interdit, 1 si deplacement valide, 2 si prise valide

        // verifier que les casse sont sur le plateau 
        if (isIn(arr))
        {
            // savoir si le cout est un deplacement de la reserve au plateau
            //rajouter pour les deplacements 
            if  (dep.getTypeCase() == 1 && arr.getTypeCase()==0)
            {
                return 1;
            }
            
            // savoir si c'est un deplacement horizontale ou pas 
            else if (dep.getAbscisse() == arr.getAbscisse())
            {
                // savoir manger ou deplacer
                if (Yote.abs(dep.getOrdonnee() - arr.getOrdonnee())>= 2) // supose que si deplacement sup 2 cest pour manger
                {
                    if (verifieDeplacementVertPrise(dep,arr))
                    {
                        return 2;
                    }
                    else
                    {
                        return 0;
                    }
                }
                else // ce deplacer
                {
                    if (verifieDeplacementVert(dep,arr))
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }  
            }
            else if (dep.getOrdonnee() == arr.getOrdonnee()) // deplacement vertical
            {
                if (Yote.abs(dep.getAbscisse()- arr.getAbscisse())>= 2) // supose que si deplacement sup 2 cest pour manger
                {
                   if (verifieDeplacementHorPrise(dep,arr))
                    {
                        return 2;
                    }
                    else
                    {
                        return 0;
                    } 
                }
                else // deplacer
                {
                    if (verifieDeplacementHor(dep,arr))
                    {
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }  
            }
            else
            {
                return 0;
            }           
        }
        else
        {
            return 0;
        }
    }

    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean jouerCoup(Case dep, Case arr) 
    {//la méthode jouerCoup joue le coup, i.e. déplace le pions si le coup est valide 
	if (coupValide (dep, arr) == 1) // savoir si notre coup est un deplacement
        {
            // deplacer le pion
            
            // sauvegarde pour pouvoir annuler
            Yote.derniercoup.casearriver = arr;
            Yote.derniercoup.casedepart = dep ;
            Yote.derniercoup.encien_eta = Yote.etat;
            //
            // deplacer le pion
            arr.setPion(dep.getPion());
            dep.setPion(null);
            return true;
        }
        else
        {
            return false;
        }
    }
 
    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public boolean jouerCoupPrise(Case dep, Case arr) 
   {
       if (coupValide (dep, arr) == 2)
        {
            
                     
            
            // mange le pion 
            // save pion manger
            Case pion_manger = casePrise(dep, arr); // bizar yote. la case manger / on a donc deux variable
            // savegarde les donnee pour annuler le coup
            Yote.derniercoup.casearriver = arr;
            Yote.derniercoup.casedepart = dep ;
            Yote.derniercoup.pionmanger = pion_manger.getPion();
            Yote.derniercoup.casePrise = pion_manger;
            //
            // deplacer le pion et manger le pion entre
            arr.setPion(dep.getPion());
            dep.setPion(null);
            
            pion_manger.setPion(null); // pion est manger
            return true;
        }
        else
        {
            return false;
        }
       
   }

    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     */
    public void annulerCoup(Case dep, Case arr) 
    {
        // revenir à la casse d'avant
        if (Yote.derniercoup.encien_eta == 1) // si on deplace un piont 
        {
            // recuper les valeurs des cases
            Yote.caseDep = Yote.derniercoup.casearriver;
            Yote.caseArr =  Yote.derniercoup.casedepart;
            // changer
            Yote.caseArr.setPion(Yote.caseDep.getPion());
            Yote.caseDep.setPion(null);
            
            // on change de joueur pour avoir le joueu precedent (le joueur qui veux annuler son coup)
            if (Yote.joueur == CouleurPion.blanc)
            {
                Yote.joueur = CouleurPion.noir;
            }
            else
            {
               Yote.joueur = CouleurPion.blanc;
            }
            Yote.message.setText("C'est au joueur "+Yote.joueur+" de jouer");
            Fenetre.boutonAnnuler.setEnabled(false); // peux plus annuler avant le prochain coup
        }
        else if (Yote.derniercoup.encien_eta == 2) // si on mange un piont
        {
            // recuper les valeurs des cases
            Yote.caseDep = Yote.derniercoup.casearriver;
            Yote.caseArr =  Yote.derniercoup.casedepart;
            // changer
            Yote.caseArr.setPion(Yote.caseDep.getPion());
            Yote.caseDep.setPion(null);
            Yote.derniercoup.casePrise.setPion(Yote.derniercoup.pionmanger);
            Yote.casePrise.setPion(Yote.derniercoup.pionsurmanger);
            // on change de joueur pour avoir le joueu precedent (le joueur qui veux annuler son coup)
            if (Yote.joueur == CouleurPion.blanc)
            {
                Yote.joueur = CouleurPion.noir;
                Yote.nbPionBlanc+=2; // recupere les pionts
            }
            else
            {
               Yote.joueur = CouleurPion.blanc;
               Yote.nbPionNoir+=2;// recupere les pionts
            }
            Yote.message.setText("C'est au joueur "+Yote.joueur+" de jouer");
            Fenetre.boutonAnnuler.setEnabled(false);
            Yote.scoreBlanc.setText("Score Joueur Blanc \n "+ valueOf(Yote.nbPionBlanc));
            Yote.scoreNoir.setText("Score Joueur Noir \n" + valueOf(Yote.nbPionNoir)); 
        }
    }

    /**
     *
     */
    public void reinitialiser() 
    {
        Yote.initialisation_Jeu(); // vide la fenetre pour replacer
    }

    /**
     *
     * @param dep case de depart
     * @param arr case d arriver
     * @return
     */
    public Case casePrise(Case dep, Case arr)
    {//elle retourne la case où le pion a été pris lors d'une prise
	if (dep.getOrdonnee() == arr.getOrdonnee())
        {
            if ((dep.getAbscisse()- arr.getAbscisse())>0)
            {
                Yote.casePrise =this.monPlateau[dep.getOrdonnee()][arr.getAbscisse()+1];
                return this.monPlateau[dep.getOrdonnee()][arr.getAbscisse()+1]; 
            }
            else if ((dep.getAbscisse()- arr.getAbscisse())<0)
            {
                Yote.casePrise = this.monPlateau[dep.getOrdonnee()][arr.getAbscisse()-1];
                return this.monPlateau[dep.getOrdonnee()][arr.getAbscisse()-1]; 
            }
            else
            {
                return null;
            }
        }
        else if ((dep.getAbscisse()== arr.getAbscisse()))
        {
            if ((dep.getOrdonnee() - arr.getOrdonnee())>0)
            {
                Yote.casePrise =this.monPlateau[arr.getOrdonnee()+1][dep.getAbscisse()];
                return this.monPlateau[arr.getOrdonnee()+1][dep.getAbscisse()]; 
            }
            else if ((dep.getOrdonnee() - arr.getOrdonnee())<0)
            {
                Yote.casePrise =this.monPlateau[arr.getOrdonnee()-1][dep.getAbscisse()]; 
                return this.monPlateau[arr.getOrdonnee()-1][dep.getAbscisse()]; 
            }
            else
            {
                return null;
            }
        }
        return null;
    }
}
