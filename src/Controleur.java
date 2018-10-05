import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controleur implements MouseListener, ActionListener{

private Partie grille;
private JFrame fenetre;
private Affichage affiche;
private JFrame menu;

	public Controleur(Partie g,JFrame f,JFrame m,Affichage a)
	{
		grille=g;
		fenetre=f;
		affiche=a;
		menu=m;

	}
	
	/**
	* Efectue ce qui est ce qui est marqu&eacute; sur le bouton cliqu&eacute; dans le menu.
	*
	* @param e case sur la quelle on a cliqu&eacute;
	*/
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String chaine = e.getActionCommand();
		if(chaine=="Sauvegarder et quitté")
		{
			new Save(grille.getLignes(), grille.getColonnes(), grille);
			System.exit(0);

		}else if(chaine=="Nouvelle partie")
		{

			fenetre.dispose();
			menu.dispose();

			new MenuTest();

		}else if(chaine=="Quitté")
		{
			System.exit(0);
		}
		
	}

	/**
	*Ne fais rien.
	*
	*/
	public void mouseClicked(MouseEvent e){
	}			// un bouton cliqué

	/**
	*Ne fais rien.
	*
	*/
	public void mouseEntered(MouseEvent e){
	}			// debut du survol

	/**
	*Ne fais rien.
	*
	*/
	public void mouseExited(MouseEvent e){
	}			// fin du survol

	/**
	*Ne fais rien.
	*
	*/
	public void mousePressed(MouseEvent e){
	}			// un bouton appuyé

	/**
	* Passe la case de cach&eacute; &agrave; r&eacute;v&eacute;l&eacute;
	* si on relache le clic gauche ou ajoute, change, retire le marqueur 
	* si on relache le clic droit.
	*
	* @param e case sur la quelle on a cliqu&eacute;
	*/
	public void mouseReleased(MouseEvent e)
	{
		int x=Integer.parseInt(e.getComponent().getName());

		for (int i=0; i<grille.getLignes(); i++) 
		{
			for (int k=0; k<grille.getColonnes(); k++) 
			{
				int var1 = i*100+k;
				int var2 = i*1000+k;
				
				if(x==var1 || x==var2)
				{
					if(e.getButton() == MouseEvent.BUTTON1)	//Clic Gauche
					{	
						if (grille.getPartie(i,k)==0) 
						{
							Changement s = new Changement();
							s.Changements(i,k,grille);				
						}							
						
					}
					else	//Clic droit
					{
						grille.Drapeau(i,k);
					}
					affiche.Afficher();					
				}
			}
		}

	}		// un bouton est relaché
}