package divers;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import principal.*;
import bDD.*;

@SuppressWarnings("serial")
public class DialogConnect extends JFrame {
	private Connection bdd;
	private JFrame parent, himself;
	private Container contain;
	private JLabel info, password, login ;
	private JTextField loginIN;
	private JPasswordField passwordIN;
	private JButton exit, connection;
	private JPanel panel;
	
	public DialogConnect(JFrame par)
	{
		super("Connexion à une BDD");
		parent=par;
		himself = this;
		//placer le container
		contain = getContentPane( );
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));//bordure noire autour du JPanel
		panel.setLayout(new GridBagLayout());
		panel.setSize(400,200);
		//creation du BorderLayout
		contain.setLayout(null);
		//place au milieux de l'ecran
		setBounds((int)((parent.getX()+parent.getWidth())-400)/2,(int)((parent.getY()+parent.getHeight())-200)/2,400,200);
		//Init des elements
		info = new JLabel("Entrez les informations de connexion à la base de données:");
		login = new JLabel("Nom de la BDD:");
		password = new JLabel("Mot de passe");
		passwordIN = new JPasswordField("popol");
		passwordIN.setPreferredSize(new Dimension(100,20));
		loginIN = new JTextField("BDD");
		loginIN.setPreferredSize(new Dimension(100,20));
		exit = new JButton("Quitter");
		exit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});
		connection = new JButton("Connection");
		requestFocus();
		connection.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						connect();
					}
				});
		//Pour co lors de l'appui sur enter
		KeyAdapter enter = new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				{
					connect();
				}
			}					
		};
		passwordIN.addKeyListener(enter);
		loginIN.addKeyListener(enter);
		//contraintes du GridBag
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridwidth = GridBagConstraints.REMAINDER; // seul composant de sa colonne, il est donc le dernier.
		gbc.gridheight = 1; // valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 10, 10, 10);//marge entre les composants
		//ajout au panel
		panel.add(info , gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		panel.add(login,gbc);
		gbc.gridx = 1;
		panel.add(loginIN,gbc); 
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(password , gbc);
		gbc.gridx = 1;
		panel.add(passwordIN , gbc);
		gbc.gridy = 3;
		gbc.gridx=0;
		panel.add( connection, gbc);
		gbc.gridx = 1;
		panel.add(exit , gbc);
		contain.add(panel);
		//Place la fenetre au dessus, toujours visible , taille fixe et sans bordure systeme
		setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setVisible(true);
	}
	private void connect()
	{
		try
		{
			bdd = AccessBDGen.connecter ( loginIN.getText(), " " , new String(passwordIN.getPassword()));
			if(bdd !=null)
			{
				JOptionPane.showMessageDialog(himself,"Connexion réussie","Victoire" ,JOptionPane.INFORMATION_MESSAGE);
				((Fenetre) parent).setBDD(bdd);
				((Fenetre) parent).setMarkCo();
				((Fenetre) parent).actualiser();
				himself.dispose();
			}
		}
		catch(SQLException exc)
		{
			JOptionPane.showMessageDialog(himself,exc ,"Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
}
