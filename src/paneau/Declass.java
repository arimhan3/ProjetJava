package paneau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import bDD.AccessBDGen;
import bDD.MyTableModel;
import principal.*;

@SuppressWarnings("serial")
public class Declass extends BaseJPanel {
	private JPanel div,divNorth;
	private JLabel info;
	private JScrollPane scrollpane;
	private JComboBox liste;
	public Declass (Fenetre f)
	{
		super(f);
		//Bouton et textField
		div = new JPanel();
		info = new JLabel("Choissisez le fournisseur :");
		info.setPreferredSize(new Dimension(150,20));
		scrollpane = new JScrollPane();
		liste = new JComboBox();
		liste.setEditable(false);
		liste.setPreferredSize(new Dimension(200,20));
		divNorth = new JPanel();
		divNorth.add(info);
		divNorth.add(liste);
		//Ajout des Listener
		liste.addActionListener(new ActionListener()
		{				
			public void actionPerformed(ActionEvent e)
			{
				if(parent.getBDD()!=null)
				{
					if(liste.getSelectedItem()!=null)
					{
						try
						{				
							String instSQL = "SELECT NoInterv,DescriptifBrefProblème,SuiviViaFournisseur,EtatRetour,Résultat,FkPcUnit,FkTypeInterv,FkFournisseuIntervenant FROM Intervention WHERE FkFournisseuIntervenant IN (SELECT FournisseurId FROM Fournisseur WHERE [NomFourn] ='";
							instSQL+=((String) liste.getSelectedItem()).trim();
							instSQL+="' GROUP BY FournisseurId) AND Résultat='Déclassé'";
							MyTableModel model = AccessBDGen.creerTableModel(parent.getBDD(),instSQL );
							scrollpane.getViewport().removeAll();
							scrollpane.getViewport().add(new JTable(model));							
						}
						catch(SQLException exc)
						{
							JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		//ajout du Layout
		this.setLayout(new BorderLayout());
		//Ajout au panneau
		add(divNorth, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
		add(div, BorderLayout.SOUTH);
	}
	public void actualiser()
	{
		String instSQL = "SELECT NomFourn FROM Fournisseur GROUP BY NomFourn";
		if(parent.getBDD()!=null)
		{
			try
			{										
				JTable table = new JTable(AccessBDGen.creerTableModel(parent.getBDD() , instSQL ));
				TableModel temp = table.getModel();
				liste.removeAllItems();
				for(int i = 0; i< temp.getRowCount();i++)
				{
					liste.addItem(temp.getValueAt(i, 0));
				}
			}
			catch(SQLException exc)
			{
				JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void vider() 
	{
		scrollpane.getViewport().removeAll();
		liste.removeAllItems();
		this.validate();
		this.repaint();
	}

}
