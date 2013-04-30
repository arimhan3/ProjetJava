package paneau;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import divers.*;
import principal.*;
import bDD.*;

public class Listing extends BaseJPanel {
	private JPanel div;
	private JButton actual;
	private JLabel info;
	private JScrollPane scrollpane;
	private JButton modif;
	private JTable table;
	public Listing (Fenetre f)
	{
		super(f);
		setBorder(null);
		//Bouton et textField
		info = new JLabel("Listing :");
		info.setPreferredSize(new Dimension(200,20));
		scrollpane = new JScrollPane();
		div = new JPanel();
		modif = new JButton("Modifier");
		modif.setPreferredSize(new Dimension(100,20));
		//Ajout des Listener
		modif.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(table!=null && table.getSelectedRow()>=0)
				{
					modifier((Integer)table.getModel().getValueAt(table.getSelectedRow(),0));
					actualiser();
				}
			}						
		});
		//ajout du Layout
		this.setLayout(new BorderLayout());
		//Ajout au panneau
		div.add(modif);
		add(info, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
		add(div,BorderLayout.SOUTH);
	}
	
	public void actualiser()
	{
		String instSQL = "SELECT * FROM Intervention ORDER BY NoInterv ASC";
		if(parent.getBDD() != null)
		{
			try
			{
				table = new JTable(AccessBDGen.creerTableModel(parent.getBDD(),instSQL ));
				scrollpane.getViewport().add(table);
			}
			catch(SQLException exc)
			{
				erreurSQL(exc);
			}
		}
	}

	public void vider() 
	{
		scrollpane.getViewport().removeAll();
		this.validate();
		this.repaint();
	}
	
	public void modifier(Integer indice)
	{
		new ModifDial(indice, parent);
	}

}
