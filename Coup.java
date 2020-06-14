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
public class Coup {

    /**
     *
     */
    public Case casearriver = null; // permet de enregistre la case arriver du coup presedent

    /**
     *
     */
    public Case casedepart = null; // permet de enregistre la case depart du coup presedent

    /**
     *
     */
    public Case casePrise = null; // permet de enregistre la case prise du coup presedent si manger

    /**
     *
     */
    public Pion pionmanger = null ; // permet de enregistre le pion manger du coup presedent le deuxiemme si manger

    /**
     *
     */
    public Pion pionsurmanger = null; // permet de enregistre le pion manger arriver du coup presedent le premier si manger

    /**
     *
     */
    public int encien_eta = 0; // permet de enregistre l'etat du coup presedent pour differenté coup manger ou deplacement
    
    /**
     *
     */
    public Coup ()
    {
        
    }
    
}
