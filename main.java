import javax.swing.*;
import java.awt.*;

public class main{
	public static void main(String[] args) {
		int x=Integer.parseInt(args[0]);//nombre ligne
		int y=Integer.parseInt(args[1]);//nombre colonnes
		int z=Integer.parseInt(args[2]);//nombre bombes

		Partie grille = new Partie(x,y,z);
		while(true)
		{}
	}
}