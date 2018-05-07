import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
public class MenuTest extends JFrame implements ActionListener{
  private JPanel jp =new JPanel();
  private JFrame fenetre;
  private int nb_ligne = 4;
  private int nb_colonne = 4;
  private int nb_bombe= 10;

  public MenuTest()
  {
    super("MenuTest");
    fenetre = new JFrame();
    jp.setBackground(new Color(0,0,0));
    GridLayout gestionnaire = new GridLayout(0,1);
    gestionnaire.setHgap(35); 
    gestionnaire.setVgap(35);             
    fenetre.setLayout(gestionnaire);
    JButton b1 = new JButton("Nouvelle partie");
    JButton b2 = new JButton("Continuer la partie");
    JButton b3 = new JButton("Quitter");
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    fenetre.add(b1);
    fenetre.add(b2);
    fenetre.add(b3);
    fenetre.add(jp);
    fenetre.setSize(400, 300);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fenetre.setVisible(true);
  }
  public void nouvellePartie(){
    JFrame choix = new JFrame();
    GridLayout gestionnaire = new GridLayout(3,5);
    choix.setLayout(gestionnaire);
    JLabel ligne = new JLabel;

    JButton plus_colonne = new JButton("+");
    JButton moins_colonne = new JButton("-");
    plus_colonne.setName("plus_colonne");
    moins_colonne.setName("moins_colonne");

    JButton plus_ligne = new JButton("+");
    JButton moins_ligne = new JButton("-");
    plus_ligne.setName("plus_ligne");
    moins_ligne.setName("moins_ligne");

    JButton max_colonne = new JButton("Max");
    JButton min_colonne = new JButton("Min");
    max_colonne.setName("max_colonne");
    min_colonne.setName("min_colonne");

    JButton max_ligne = new JButton("Max");
    JButton min_ligne = new JButton("Min");
    max_ligne.setName("max_ligne");
    min_ligne.setName("min_ligne");

    JButton plus_bombe = new JButton("+");
    JButton moins_bombe = new JButton("-");
    plus_bombe.setName("plus_bombe");
    moins_bombe.setName("moins_bombe");

    JButton max_bombe = new JButton("Max");
    JButton min_bombe = new JButton("Min");
    max_bombe.setName("max_bombe");
    min_bombe.setName("min_bombe");
    
    if(nb_colonne<30)
    {
      plus_colonne.addActionListener(this);
    }
    if(nb_colonne>4)
    {
      moins_colonne.addActionListener(this);
    }

    if(nb_ligne<30)
    {
      plus_ligne.addActionListener(this);
    }
    if(nb_ligne>4)
    {
      moins_ligne.addActionListener(this);
    }

    min_colonne.addActionListener(this);
    max_colonne.addActionListener(this);

    min_ligne.addActionListener(this);
    max_ligne.addActionListener(this);

    choix.add(min_colonne);
    choix.add(moins_colonne);
    choix.add(plus_colonne);
    choix.add(max_colonne);

    choix.add(min_ligne);
    choix.add(moins_ligne);
    choix.add(plus_ligne);
    choix.add(max_ligne);

    choix.add(min_bombe);
    choix.add(moins_bombe);
    choix.add(plus_bombe);
    choix.add(max_bombe);

    choix.setSize(400, 300);
    choix.setVisible(true);
    choix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand()=="Nouvelle partie")
    {
      jp.setBackground(Color.CYAN);
      fenetre.dispose();
      nouvellePartie();
    }
    else if(e.getActionCommand()=="Continuer la partie")
    {
      jp.setBackground(Color.MAGENTA);

    }
    else if(e.getActionCommand()=="Quitter")
    {
      System.exit(0);
    }

  }


  public static void main(String[] args)
  {
    new MenuTest();
  }

}

