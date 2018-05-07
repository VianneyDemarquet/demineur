import javax.swing.*;
import java.awt.*;
import java.lang.Object;
import  java.awt.event.*;

/**
 * La classe <code>Affichage</code> est utilis&eacute;e pour Afficher
 * la grille de jeux et le menu de sauvegarde.
 *  
 * @author Vianney Demarquet
 * @version 0.1
 */
public class Affichage extends JFrame implements MouseListener, ActionListener {

	private JButton[][] grille_bouton;
	private Partie grille;
	private boolean first=false;

	private Color gray;
	private Color white;
	private Color red;

	private JFrame fenetre;
	private JFrame menu;

	private JButton panel;
	private JButton sauvegarder;
	private JButton quitté;

	private boolean fin=false;

	/**
	* Constructeur destin&eacute; &agrave; la cr&eacute;ation des
	* constantes publiques.
	*
	* @param grille la grille de jeux
	*/

	public Affichage(Partie part)
	{
		grille = part;
		grille_bouton = new JButton[grille.getLignes()][grille.getColonnes()];

		gray = new Color(230, 230, 230);
		white = new Color(255, 255, 255);
		red = new Color(255, 0, 0);

		fenetre = new JFrame("Démineur");
		fenetre.setSize(500, 500);
		fenetre.setLocation(0, 0);
		fenetre.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				if (!fin) 
				{
					new Save(grille.getLignes(),grille.getColonnes(),grille);
				}
				System.exit(0);
			}
		});

		menu = new JFrame("Menu");
		menu.setSize(200,500);
		menu.setLocation(600,0);
		menu.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) 
			{
				if (!fin) 
				{
					new Save(grille.getLignes(),grille.getColonnes(),grille);
				}
				System.exit(0);
			}
		}); 

		this.Afficher();
		this.Menu(grille.getNbbombes());
		first=true;
	}	

	public void setFin()
	{
		fin=true;
	}

	/**
	* Affiche la partie en cour
	*
	*/
	public void Afficher(){
		GridLayout gestionnaire = new GridLayout(grille.getLignes(), grille.getColonnes());
		fenetre.setLayout(gestionnaire);
		
		for(int i = 0; i < (grille.getLignes()); i++)
		{
			for (int k = 0 ;k < grille.getColonnes() ;k++ ) 
			{
				if (first==true) {
					fenetre.remove(grille_bouton[i][k]);
				}
				if (grille.getPartie(i,k) == 0) 
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					if (!fin) {
						grille_bouton[i][k].addMouseListener(this);
					}
					
				}else if(grille.getPartie(i,k)==2)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					grille_bouton[i][k].setIcon(new ImageIcon("image/étoile.png"));

					if (!fin) {
						grille_bouton[i][k].addMouseListener(this);
					}
				}else if(grille.getPartie(i,k)==3)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setName(""+(i*10)+k);
					grille_bouton[i][k].setBackground(gray);
					grille_bouton[i][k].setIcon(new ImageIcon("image/intero.png"));

					if (!fin) {
						grille_bouton[i][k].addMouseListener(this);
					}
				}else if(grille.getPartie(i,k)==5)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(red);
					grille_bouton[i][k].setIcon(new ImageIcon("image/étoile.png"));

				}else if (grille.getBomb(i,k) == 0) 
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(white);
					
				}else if (grille.getBomb(i,k) != 9) 
				{	
					grille_bouton[i][k] = new JButton(""+grille.getBomb(i,k));
					grille_bouton[i][k].setBackground(white);

				}else if(grille.getPartie(i,k) == 4)
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(red);
					grille_bouton[i][k].setIcon(new ImageIcon("image/bomb.png"));
				}else
				{
					grille_bouton[i][k] = new JButton("");
					grille_bouton[i][k].setBackground(white);
					grille_bouton[i][k].setIcon(new ImageIcon("image/bomb.png"));
				}
				fenetre.add(grille_bouton[i][k]);
			}
		}
		
		fenetre.setVisible(true);
	}

	/**
	* Affiche un menu contenant le nombre de mines restante et
	* un bouton sauvegard&eacute; et quit&eacute;
	*
	* @param mine_rest corespond au nombre de mines moins le nombre d'&eacute;toile
	*/
	public void Menu(int mine_rest)
	{
		GridLayout gestionnaire = new GridLayout(2, 1);
		menu.setLayout(gestionnaire);
		if (first == true) 
		{
			menu.remove(panel);
			menu.remove(sauvegarder);
		}
		
		panel = new JButton("Mine \n restante : "+mine_rest);
		sauvegarder = new JButton("Sauvegarder et quitté");

		panel.setBackground(gray);
		sauvegarder.setBackground(gray);

		sauvegarder.addActionListener(this);

		menu.add(panel);
		menu.add(sauvegarder);

		menu.setVisible(true);
	}

	/**
	* Affiche un menu contenant si la partie est gang&eacute; ou perdu,
	* un bouton nouvelle partie et 
	* un bouton quit&eacute;
	*
	* @param pedu donne 1 si la partie est perdue et 0 si elle est gagn&eacute;
	*/
	public void MenuFin(boolean perdu)
	{
		GridLayout gestionnaire = new GridLayout(3, 1);
		menu.setLayout(gestionnaire);
		menu.remove(panel);
		menu.remove(sauvegarder);

		if (perdu) 
		{
			panel = new JButton("Perdu");
			panel.setBackground(red);
			panel.setBackground(red);
		}else
		{
			panel = new JButton("Gagné");
			Color vert = new Color(0,200,0);
			panel.setBackground(vert);
		}
		
		sauvegarder = new JButton("Nouvelle partie");
		quitté = new JButton("Quitté");

		quitté.setBackground(gray);
		sauvegarder.setBackground(gray);

		quitté.addActionListener(this);
		sauvegarder.addActionListener(this);

		menu.add(panel);
		menu.add(sauvegarder);
		menu.add(quitté);
		menu.setVisible(true);
	}

	/**
	* Efectue ce qui est ce qui est marqu&eacute; sur le bouton cliqu&eacute; dans le menu.
	*
	* @param e case sur la quelle on a cliqu&eacute;
	*/
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

			new Partie(grille.getLignes(), grille.getColonnes(),grille.getNbbombes());

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
					this.Afficher();					
				}
			}
		}

	}		// un bouton est relaché
}