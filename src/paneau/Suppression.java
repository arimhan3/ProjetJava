package paneau;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;
import principal.*;
import bDD.*;

@SuppressWarnings("serial")
public class Suppression extends BaseJPanel {
	private JLabel info,local;
	private JComboBox listeLocal;
	private JButton supp;
	private JScrollPane scrollpane;
	private JTable table;
	public Suppression (Fenetre f)
	{
		super(f);
		//Bouton et textField
		info = new JLabel("Veuillez encoder le local dans lequel l'intervention est à supprimer");
		info.setPreferredSize(new Dimension(400,20));
		local = new JLabel("Local : ");
		local.setPreferredSize(new Dimension(40,20));
		listeLocal = new JComboBox();
		listeLocal.setEditable(false);
		listeLocal.setPreferredSize(new Dimension(100,20));
		scrollpane = new JScrollPane();
		scrollpane.setPreferredSize(new Dimension(840,450));
		supp = new JButton("suppression");
		supp.setPreferredSize(new Dimension(120,30));
		supp.setBackground(new Color(250,80,80));
		supp.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						
							if(parent.getBDD() != null && listeLocal.getSelectedItem()!=null)
							{
									if(JOptionPane.showConfirmDialog(himself, "Voulez vous vraiment supprimer l'intervention : "+table.getModel().getValueAt( table.getSelectedRow(),0), "Confirmation de suppression", JOptionPane.YES_NO_OPTION)==0)
									{
										String instSQL = "DELETE FROM Intervention WHERE NoInterv=";
										instSQL +=table.getModel().getValueAt( table.getSelectedRow(),0);
										try
										{										
											int temp = AccessBDGen.executerInstruction (parent.getBDD(), instSQL);
											if(temp == 0)
											{
												JOptionPane.showMessageDialog(himself, "0 ligne supprimée", "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
											}
											else
											{
												JOptionPane.showMessageDialog(himself, temp+" lignes supprimées", "Suppression réussie", JOptionPane.PLAIN_MESSAGE);
												actualiser();
											}
										}
										catch(SQLException exc)
										{
											JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
										}
									}
							}
					}				
				});
		listeLocal.addActionListener(new ActionListener()
			{				
				public void actionPerformed(ActionEvent e)
				{
					if(parent.getBDD()!=null)
					{
						try
						{				
							scrollpane.getViewport().removeAll();
							String instSQL = "SELECT * FROM Intervention WHERE FkPcUnit IN (SELECT IdPcUnit FROM PcUnit WHERE Local='";
							instSQL+=listeLocal.getSelectedItem();
							instSQL+="') AND résultat='en Suspens'";
							MyTableModel model = AccessBDGen.creerTableModel(parent.getBDD(),instSQL );
							table = new JTable(model);
							scrollpane.getViewport().add(table);
						}
						catch(SQLException exc)
						{
							JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
		//ajout du Layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridwidth = GridBagConstraints.REMAINDER; // seul composant de sa colonne, il est donc le dernier.
		gbc.gridheight = 1; // valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.LINE_START;//positionne par la gauche
		gbc.insets = new Insets(5, 5, 0, 0);//marge entre les composants
		//Ajout au panneau
		add(info,gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		add(local,gbc);
		gbc.gridx = 1;
		add(listeLocal,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 2;
		gbc.gridx = 0;
		add(scrollpane,gbc);
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(supp,gbc);
	}
	
	public void actualiser()
	{
		String instSQL = "SELECT Local FROM PcUnit GROUP BY Local";
		if(parent.getBDD()!=null)
		{
			try
			{										
				table = new JTable(AccessBDGen.creerTableModel(parent.getBDD() , instSQL ));
				table.setSelectionMode ( ListSelectionModel.SINGLE_SELECTION );
				TableModel temp = table.getModel();
				listeLocal.removeAllItems();
				for(int i = 0; i< temp.getRowCount();i++)
				{
					listeLocal.addItem(temp.getValueAt(i, 0));
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
		listeLocal.removeAllItems();
		this.validate();
		this.repaint();
	}
}
