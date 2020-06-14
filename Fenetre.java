/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_yote;

/**
 *
 * @author Léa LACOMBE (modifier)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 *
 * @author Léa LACOMBE (modifier)
 */
public class Fenetre extends JFrame 
{

    /**
     *
     */
    public static JButton boutonAnnuler = new JButton("Annuler");

    /**
     *
     */
    public static JButton boutonReinit = new JButton("Reinitialiser");

    /**
     *
     * @param name de la fentre
     */
    public Fenetre(String name) 
    {
        super(name);// metre un nom a la fenetre
        this.setSize(2500, 2500); // taille de la fentre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     *
     * @param pane
     */
    public void ajouterComposants(final Container pane) 
    {
        FlowLayout structureBouton = new FlowLayout(FlowLayout.CENTER);// bouton
        FlowLayout structurePlateau = new FlowLayout(FlowLayout.CENTER);// plateau 
        FlowLayout structureScore = new FlowLayout(FlowLayout.CENTER);// score

        // bouton
        JPanel fondBouton = new JPanel();
        fondBouton.setLayout(structureBouton);
        fondBouton.add(boutonAnnuler);
        Fenetre.boutonAnnuler.setEnabled(false);
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

        Yote.unPlateau = new Plateau(); // cree un plateau de jeu
        Yote.stockBlanc = new StockPion(CouleurPion.blanc); // nombre de poin
        Yote.stockNoir = new StockPion(CouleurPion.noir);
        fondPlateau.add(Yote.stockBlanc);
        fondPlateau.add(Yote.unPlateau);
        fondPlateau.add(Yote.stockNoir);
        pane.add(fondPlateau, BorderLayout.CENTER);

        JPanel score =  new JPanel();
        score.setLayout(structureScore);
        Yote.scoreBlanc = new JLabel("Score Joueur Blanc \n "+ valueOf(Yote.nbPionBlanc));
        Yote.scoreNoir = new JLabel("Score Joueur Noir \n" + valueOf(Yote.nbPionNoir));
        Yote.message = new JLabel("C'est au joueur "+Yote.joueur+" de jouer");
        score.add(Yote.scoreBlanc);
        score.add(Yote.scoreNoir);
        score.add(Yote.message);
        pane.add(score, BorderLayout.SOUTH);
    }

}

