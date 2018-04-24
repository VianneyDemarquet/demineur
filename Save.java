import javax.swing.*;
import java.awt.*;
import java.io.*;

class Save {
	public Save(int l, int c,Partie grille)
	{
		try
		{
			DataOutputStream flux = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("foo.bin"))));
			try
			{
				flux.writeInt(l);
				flux.writeInt(c);
				flux.writeInt(grille.getCase_libre());
				flux.writeInt(grille.getNbbombes());
				flux.writeInt(grille.getDrapeaux());

				//sauvegarde pos bombe
				for (int i=0; i<l; i++) 
				{
					for (int k=0; k<c; k++) 
					{
						flux.writeInt(grille.getBomb(i,k));
					}
				}
				

				//sauvegarde état partie
				for (int i=0; i<l; i++) 
				{
					for (int k=0; k<c; k++) 
					{
						flux.writeInt(grille.getPartie(i,k));
					}
				}
				flux.close();
			}catch(IOException exception)
			{
				Object[] choix = {"Ok"};
 
				int reponse = JOptionPane.showOptionDialog(null,
				"Erreur lors de l'écriture\n"+exception.getMessage(),
				"Erreur",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				choix,
				null);

				if (reponse == 0)
				{
					System.exit(0);
				}
			}

			
		}catch(FileNotFoundException exception)
		{
			Object[] choix = {"Ok"};
 
			int reponse = JOptionPane.showOptionDialog(null,
			"Fichier non trouver\n"+exception.getMessage(),
			"Erreur",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			choix,
			null);
			if (reponse == 0)
			{
				System.exit(0);
			}
		}
	}

	public Save(Partie grille)
	{
		try
		{
			DataInputStream flux = new DataInputStream(new FileInputStream(new File("foo.bin")));
			try
			{
				
				grille.setLignes(flux.readInt());
				grille.setColonnes(flux.readInt());
				grille.setCase_libre(flux.readInt());
				grille.setNbbombes(flux.readInt());
				grille.setDrapeaux(flux.readInt());

				grille.setTables();

				//sauvegarde pos bombe
				for (int i=0; i<grille.getLignes(); i++) 
				{
					for (int k=0; k<grille.getColonnes(); k++) 
					{
						grille.setBombs(i,k,flux.readInt());
					}
				}

				//sauvegarde état partie
				for (int i=0; i<grille.getLignes(); i++) 
				{
					for (int k=0; k<grille.getColonnes(); k++)
					{
						grille.setPartie(i,k,flux.readInt());
					}
				}
				flux.close();
			}catch(IOException exception)
			{
				Object[] choix = {"Ok"};
 
				int reponse = JOptionPane.showOptionDialog(null,
				"Erreur lors de la lecture\n"+exception.getMessage(),
				"Erreur",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				choix,
				null);
				if (reponse == 0)
				{
					System.exit(0);
				}
			}

			
		}catch(FileNotFoundException exception)
		{
			Object[] choix = {"Ok"};
 
			int reponse = JOptionPane.showOptionDialog(null,
			"Fichier non trouver\n"+exception.getMessage(),
			"Erreur",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			choix,
			null);
			if (reponse == 0)
			{
				System.exit(0);
			}
		}
	}

}