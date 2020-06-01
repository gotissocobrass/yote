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
import java.awt.*;
import static java.awt.Color.white;
import static java.awt.Color.gray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

public class Plateau extends JPanel 
{
 
    private Case[][] monPlateau;

    public Plateau() 
    {
        this.setLayout(new GridLayout (6,5));
        this.monPlateau = new Case[6][5];
        for (int longueur = 0; longueur< this.monPlateau.length;longueur++)
        {
            for (int largueur = 0; largueur< this.monPlateau[longueur].length;largueur++)
            {
                Case variable = new Case (gray,largueur,longueur,0,false);
                this.monPlateau[longueur][largueur] = variable;
                this.add(variable);
            } 
        }
	// A completer
    }
    
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
    
    public boolean verifieDeplacementHor(Case dep, Case arr)
    {// verifier si le deplacement horizontal d'un pion entre la casse dep et arr est possible
	
        int Casse_voulons_se_deplacer;
        if (isIn(arr)) // verifier que la casse arriver est sur le plateau 
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getAbscisse() > arr.getAbscisse())
            {
                Casse_voulons_se_deplacer = dep.getAbscisse() - arr.getAbscisse();
                
            }
            else if ((dep.getAbscisse() < arr.getAbscisse()))
            {
                Casse_voulons_se_deplacer =  arr.getAbscisse()- dep.getAbscisse();
            }
            else
            {
                return false;
            }
            // compare au nombre de deplacement autorisé
            if (Casse_voulons_se_deplacer == dep.getPion().getMvt())
            {
                // savoir si c'est ocuper
                return !arr.isOccupe();
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    public boolean verifieDeplacementVert(Case dep, Case arr)
    {// verifier si le deplacement vertical d'un pion entre la casse dep et arr est possible
        
        int Casse_voulons_se_deplacer;
        if (isIn(arr))
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getOrdonnee() > arr.getOrdonnee())
            {
                Casse_voulons_se_deplacer = dep.getOrdonnee() - arr.getOrdonnee();
                
            }
            else if ((dep.getOrdonnee() < arr.getOrdonnee()))
            {
                Casse_voulons_se_deplacer =  arr.getOrdonnee()- dep.getOrdonnee();
                
            }
            else
            {
                return false;
            }
            // compare au nombre de deplacement autorisé
            if (Casse_voulons_se_deplacer == dep.getPion().getMvt())
            {
                // savoir si c'est ocuper
                return !arr.isOccupe();
                //la fonction
                //return true // personne
                //return false // ocuper
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public boolean verifieDeplacementHorPrise(Case dep, Case arr)
    {// deplacement pour manger un pion  horisontale
	
        int Casse_voulons_se_deplacer;
        int valeur_abscique_casse_pion_manger;
        if (isIn(arr)) // est bien dans le plateau 
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getAbscisse() > arr.getAbscisse())
            {
                Casse_voulons_se_deplacer = dep.getAbscisse() - arr.getAbscisse();
                valeur_abscique_casse_pion_manger = arr.getAbscisse()+1;
                
            }
            else if ((dep.getAbscisse() < arr.getAbscisse()))
            {
                Casse_voulons_se_deplacer =  arr.getAbscisse()- dep.getAbscisse();
                valeur_abscique_casse_pion_manger = arr.getAbscisse()-1;
            }
            else
            { // Casse_voulons_se_deplacer = 0 donc pas deplacement
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
    public boolean verifieDeplacementVertPrise(Case dep, Case arr)
    {
	// deplacement pour manger un pion  horisontale
	// A completer
        int Casse_voulons_se_deplacer;
        int valeur_abscique_casse_pion_manger;
        if (isIn(arr)) // est bien dans le plateau 
        {
            // savoir le nombre de casse de deplacement 
            if (dep.getOrdonnee()> arr.getOrdonnee())
            {
                Casse_voulons_se_deplacer = dep.getOrdonnee() - arr.getOrdonnee();
                valeur_abscique_casse_pion_manger = arr.getOrdonnee()+1;
                
            }
            else if ((dep.getOrdonnee() < arr.getOrdonnee()))
            {
                Casse_voulons_se_deplacer =  arr.getOrdonnee()- dep.getOrdonnee();
                valeur_abscique_casse_pion_manger = arr.getOrdonnee()-1;
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
                    return this.monPlateau[valeur_abscique_casse_pion_manger][arr.getAbscisse()].isOccupe();
                }
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    

    
    public int coupValide(Case dep, Case arr) // deplacementValide
    {//Teste si la Pion qui est en dep peut de deplacer en arr : renvoie 0 si deplacement interdit, 1 si deplacement valide, 2 si prise valide

        // verifier que les casse sont sur le plateau 
        if (isIn(arr))
        {
        	 if(dep.getTypeCase()==1 &&	arr.getTypeCase()==0 )
             {
                 return 1;
             }     
            // savoir si  c'est un deplacement horizontale ou pas 
            if (dep.getOrdonnee() == arr.getOrdonnee())
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
                else // deplacer
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
            else if (dep.getAbscisse() == arr.getAbscisse()) // deplacement vertical
            {
                if (Yote.abs(dep.getAbscisse() - arr.getAbscisse())>= 2) // supose que si deplacement sup 2 cest pour manger
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
                else // deplacer
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
            
            else {
            	return 0;
            }
        }
        else
        {
            return 0;
        }
    }

    public boolean jouerCoup(Case dep, Case arr) 
    {//la méthode jouerCoup joue le coup, i.e. déplace le pions si le coup est valide 
	if (coupValide (dep, arr) == 1)
        {
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
 
    public boolean jouerCoupPrise(Case dep, Case arr) 
   {
       if (coupValide (dep, arr) == 2)
        {
            // deplacer le pion
            this.monPlateau[arr.getOrdonnee()][arr.getAbscisse()] = this.monPlateau[dep.getOrdonnee()][dep.getAbscisse()] ;
            this.monPlateau[dep.getOrdonnee()][dep.getAbscisse()] = null; // vide
            // mange le pion 
            // save pion manger
            Case pion_manger = casePrise(dep, arr);
            // sup
            this.monPlateau[pion_manger.getOrdonnee()][pion_manger.getAbscisse()] = null; // vide
            // va le chercher un deuxieme
                // a modifier
            return true;
        }
        else
        {
            return false;
        }
       
   }


    public void annulerCoup(Case dep, Case arr) 
    {
	// A completer
        // revenir à la casse d'avant
    }

    public void reinitialiser() 
    {
	// A completer
        // revenir au debut 
    }

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
