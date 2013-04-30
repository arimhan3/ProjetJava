/*
 * 
 * Classe Fenetre qui creer la fenetre principale, creer le container et ecoute l'event de fermeture
 * Et afficher le message d'acceuil
 * 
 */

package principal;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

import bDD.*;
import divers.*;
import paneau.*;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

	private Container contain;
	private JMenuBar menu;
	private JButton retour;
	private ArrayList <JMenu> items;
	private ArrayList <BaseJPanel> panel;
	private MessageAcc message;
	private JLabel textbienvenue, etatTXT, etatColor;
	private Connection bdd;
	private JPanel etat;
	private class MessageAcc extends BaseJPanel
	{
		public MessageAcc(Fenetre f) {super(f);}
		public void actualiser() {}
		public void vider() {}
	}
	public Fenetre(String titre, int x, int y, int h, int l ) 
	{
		//Appel au constructeur de JFrame
		super(titre);
		//taille de la fenetre
		setBounds(x,y,h,l);
		setMinimumSize(new Dimension(950,700));
		//Listener d'event pour fermer la fenetre
		this.addWindowListener( 
					new WindowAdapter( )
					{ 	public void windowClosing( WindowEvent e)
						{ 
							System.exit(0); 
						} 
					} );
		//Sinon erreur lors de la creation du Component Listener
		add(new JPanel());
		bdd = null;
		//redimensionner les JPanel quand la fenetre change de taille
		this.addComponentListener(
				new ComponentAdapter()
				{
					public void componentResized(ComponentEvent e)
					{
						(contain.getComponent(1)).setSize(getWidth()-26,getHeight()-108);
					}       
				});
		//creation du menu
		menu=new JMenuBar();
		items = new ArrayList <JMenu>();
		//Bouton retour
		retour = new JButton("Retour");
		retour.setBounds(0,0,100,30);
		retour.addActionListener( new ActionListener()
				{
					//Bouton retour
					public void actionPerformed(ActionEvent e)
					{
						contain.removeAll();
						contain.add(panel.get(5));
						validate();
						repaint();
					}		
				});
		//Etat de la Co
		etat = new JPanel();
		etat.setBounds(5, 5, 600, 30);
		etat.setLayout(null);
		etatTXT = new JLabel("Etat de la BDD :");
		etatTXT.setBounds(110, 0, 100, 30);
		etatColor = new JLabel("");
		etatColor.setBounds(200, 5, 20, 20);
		etatColor.setBackground(new Color(250,80,80));
		etatColor.setOpaque(true);
		etat.add(retour);
		etat.add(etatTXT);
		etat.add(etatColor);
		//creation et ajout des JMenu et JMenuItems
		JMenuItem connexion= new JMenuItem("Connexion");
		JMenuItem decon = new JMenuItem("Déconnexion");
		JMenuItem list = new JMenuItem("Afficher le listing");
		JMenuItem inter = new JMenuItem("Ajout d'une intervention");
		JMenuItem supp = new JMenuItem("Suppression d'une intervention");
		JMenuItem declass = new JMenuItem("PC déclassé");
		JMenuItem classPers = new JMenuItem("Par personne");
		//ajout des Event
		//Connexion
		connexion.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			connexionBDD();
		}});
		//deconnexion
		decon.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			decoBDD();
		}});
		list.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			affichePanel(2);
			panel.get(2).actualiser();
		}});
		inter.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			affichePanel(1);
			panel.get(1).actualiser();
		}});
		supp.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			affichePanel(4);
			panel.get(4).actualiser();
		}});
		declass.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			affichePanel(0);
			panel.get(0).actualiser();
		}});
		classPers.addActionListener(new ActionListener()
		{public void actionPerformed(ActionEvent e){
			affichePanel(3);
			panel.get(3).actualiser();
		}});
		items.add(new JMenu("BDD"));
			(items.get(0)).add(connexion);			
			(items.get(0)).add(decon);		    
		items.add(new JMenu("Listing"));
			(items.get(1)).add(list);
			(items.get(1)).add(inter);
			(items.get(1)).add(supp);
		items.add(new JMenu("Recherche"));
			(items.get(2)).add(declass);
			(items.get(2)).add(classPers);		
		//Ajouter les menuitems au menubar
		for(int i=0; i < items.size();i++)
		{
			menu.add(items.get(i));
		}
		//ajouter la menubar dans le container
		this.setJMenuBar(menu);
		//placer le container
		contain = getContentPane( );
		//creation du BorderLayout
		contain.setLayout(null);
		//Message d'acceuil
		message = new MessageAcc(this);
		textbienvenue = new JLabel("<html>Bienvenue dans GI: gestionnaire d’intervention.<br><br>Ce programme vous permet de vous connecter<br> à une base de donnée contenant toutes les interventions effectuées.<br> Vous pouvez les consulter, en créer ou même en supprimer.<br>Pour cela vous devez utiliser la barre de menu se trouvant ci-dessus. </html>");
		message.add(textbienvenue);
		contain.add(message);
		//creation des JPanel
		panel = new ArrayList <BaseJPanel>();
		panel.add(new Declass(this));
		panel.add(new Encodage(this));
		panel.add(new Listing(this));
		panel.add(new Personne(this));
		panel.add(new Suppression(this));
		panel.add(message);
		//afficher a l'ecran
		setVisible(true);
	}
	
	public void connexionBDD()
	{
		new DialogConnect(this);
	}
	
	public void decoBDD() //Deconnection de la BDD active
	{
		if( bdd != null)
		{
			if( JOptionPane.showConfirmDialog(this, "Voulez-vous vous déconnecter ?", "Déconnexion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)== 0)
			{
				try
				{
					AccessBDGen.deconnecter ( bdd );
					bdd=null;//plus de co
					for(int i = 0; i < panel.size(); i++)//vider les JPanels
					{
						panel.get(i).vider();
					}
					JOptionPane.showMessageDialog(this,"Deconnexion réussie","Victoire",JOptionPane.INFORMATION_MESSAGE);
					etatColor.setBackground(new Color(250,80,80));//met l'indicateur de co en rouge
					
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(this,e,"Erreur de déconnexion",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}	
	public void affichePanel(int id)
	{
		contain.removeAll();
		contain.add(etat);
		contain.add(panel.get(id));
		(contain.getComponent(1)).setBounds(5,40,getWidth()-26,getHeight()-108);
		this.validate();
		this.repaint();
	}
	public void setBDD(Connection co)
	{
		bdd = co;
	}
	public Connection getBDD()
	{
		return bdd;
	}
	public void setMarkCo() //met l'indicateur de connection en vert
	{
		etatColor.setBackground(new Color(80,250,80));
	}
	public void actualiser()
	{
		for(int i = 0; i < panel.size(); i++)
		{
			panel.get(i).actualiser();
		}
	}
}
