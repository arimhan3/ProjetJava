package paneau;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.*;
import bDD.*;
import principal.*;

@SuppressWarnings("serial")
public class Personne extends BaseJPanel {
	private JPanel div, divNorth;
	private JLabel info, liste;
	private JScrollPane scrollpane;
	private JComboBox listeIN,personneIN;
	public Personne (Fenetre f)
	{
		super(f);
		//Bouton et textField
		info = new JLabel("Entrez la personne  :");
		info.setPreferredSize(new Dimension(120,20));
		liste = new JLabel("Choisissez le type d'intervention  :");
		liste.setPreferredSize(new Dimension(200,20));
		scrollpane = new JScrollPane();
		divNorth = new JPanel();
		listeIN = new JComboBox();
		listeIN.setEditable(false);
		listeIN.setPreferredSize(new Dimension(200,20));
		personneIN = new JComboBox();
		personneIN.setPreferredSize(new Dimension(200,20));
		divNorth.add(info);
		divNorth.add(personneIN);
		divNorth.add(liste);
		divNorth.add(listeIN);
		//ajout des listener
		listeIN.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					affiche();
				}	
			});
		personneIN.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					affiche();
				}	
			});
		//ajout du Layout
		this.setLayout(new BorderLayout());
		//Ajout au panneau
		add(divNorth, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
	}
	public void actualiser()
	{
		String instSQL = "SELECT LibelleTypeInt FROM TypeIntervention GROUP BY LibelleTypeInt";
		String instSQLPers = "SELECT PreneurEnCharge FROM Intervention GROUP BY PreneurEnCharge";				
		if(parent.getBDD()!=null)
		{
			try
			{
				//listeIN.setSelectedIndex(0);
				JTable tablePers = new JTable(AccessBDGen.creerTableModel(parent.getBDD() , instSQLPers ));
				TableModel tempPers = tablePers.getModel();
				personneIN.removeAllItems();
				for(int i = 0; i< tempPers.getRowCount();i++)
				{
					personneIN.addItem(tempPers.getValueAt(i, 0));
				}
				JTable table = new JTable(AccessBDGen.creerTableModel(parent.getBDD() , instSQL ));
				TableModel temp = table.getModel();
				listeIN.removeAllItems();
				listeIN.addItem("Toutes");
				for(int i = 0; i< temp.getRowCount();i++)
				{
					listeIN.addItem(temp.getValueAt(i, 0));
				}
			}
			catch(SQLException exc)
			{
				JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
			}
		}	
	}
	public void affiche()
	{
		if(listeIN.getSelectedItem()!=null && personneIN.getSelectedItem()!=null)
		{
			String instSQL = "SELECT * FROM Intervention WHERE [PreneurEnCharge] ='";
			instSQL+= ((String) personneIN.getSelectedItem()).trim();
			if(listeIN.getSelectedIndex()==0)
			{
				instSQL+= "'";
			}
			else
			{
				instSQL+= "' AND FkTypeInterv IN (SELECT CodeTypeInt FROM TypeIntervention WHERE [LibelleTypeInt] ='";
				instSQL+=((String) listeIN.getSelectedItem()).trim();
				instSQL+= "')";
			}
			if(parent.getBDD() != null)
			{
				try
				{
					scrollpane.getViewport().add(new JTable(AccessBDGen.creerTableModel(parent.getBDD(),instSQL )));
				}
				catch(SQLException exc)
				{
					JOptionPane.showMessageDialog(himself,exc,"Erreur de BDD",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public void vider() 
	{
		scrollpane.getViewport().removeAll();
		listeIN.removeAllItems();
		personneIN.removeAllItems();
		this.validate();
		this.repaint();
	}

}
