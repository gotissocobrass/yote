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


public class Fenetre extends JFrame 
{

    public static JButton boutonAnnuler = new JButton("Annuler");
    public static JButton boutonReinit = new JButton("Reinitialiser");

    public Fenetre(String name) 
    {
        super(name);// metre un nom a la fenetre
        this.setSize(2500, 2500); // taille de la fentre
    }


    public void ajouterComposants(final Container pane) 
    {
        FlowLayout structureBouton = new FlowLayout(FlowLayout.CENTER);// bouton
        FlowLayout structurePlateau = new FlowLayout(FlowLayout.CENTER);// plateau 
        FlowLayout structureScore = new FlowLayout(FlowLayout.CENTER);// score

        // bouton
        JPanel fondBouton = new JPanel();
        fondBouton.setLayout(structureBouton);
        fondBouton.add(boutonAnnuler);
        fondBouton.add(boutonReinit);

        //Définition de l'action du bouton Annuler
        boutonAnnuler.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                Yote.unPlateau.annulerCoup(Yote.caseDep, Yote.caseArr);
            }
        });
        //Définition de l'action du bouton Réinitialiser
        boutonReinit.addActionListener(new ActionListener()
        {
          @Override
          public void actionPerformed(ActionEvent event)
          {
            Yote.unPlateau.reinitialiser();
          }
        });
        pane.add(fondBouton, BorderLayout.NORTH);

        JPanel fondPlateau = new JPanel();
        fondPlateau.setLayout(structurePlateau);

        Yote.unPlateau = new Plateau();
        Yote.stockBlanc = new StockPion(CouleurPion.blanc);
        Yote.stockNoir = new StockPion(CouleurPion.noir);
        fondPlateau.add(Yote.stockBlanc);
        fondPlateau.add(Yote.unPlateau);
        fondPlateau.add(Yote.stockNoir);
        pane.add(fondPlateau, BorderLayout.CENTER);

        JPanel score =  new JPanel();
        score.setLayout(structureScore);
        Yote.scoreBlanc = new JLabel("Score Joueur Blanc \n "+ valueOf(Yote.nbPionBlanc));
        Yote.scoreNoir = new JLabel("Score Joueur Noir \n" + valueOf(Yote.nbPionNoir));
        score.add(Yote.scoreBlanc);
        score.add(Yote.scoreNoir);
        pane.add(score, BorderLayout.SOUTH);
    }

}

