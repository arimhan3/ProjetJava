package divers;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

import principal.*;
import bDD.*;
import exception.*;

public class ModifDial extends JDialog {
	private Fenetre parent;
	private JButton ok;
	private JLabel  infos, noInterv, etatInter, fkPcUnit, fkTypeInterv,preneurEnCharge, tempsInterne, descBrefProb ,suiviFourn, datePrise,signaleurIncident, etatRetour,dateRemiseService,resultat, dateContact, fkFournisseurIntervenant,dateSign, dateretour;
	private JComboBox etatInterIN,fkFournisseurIntervenantIN,etatRetourIN,resultatIN,fkPcUnitIN, fkTypeIntervIN ;
	private JTextField  tempsInterneIN, noIntervIN, signaleurIncidentIN, preneurEnChargeIN;
	private JTextArea descBrefProbIN;
	private Calendrier datePriseIN, dateRemiseServiceIN,dateSignIN, dateContactIN, dateretourIN ;
	private ButtonGroup choix;
	private JRadioButton oui,non;
	public ModifDial(Integer indice, Fenetre par)
	{
		super(par, "modifier intervention n°"+indice);
		setModal(true);
		setMinimumSize(new Dimension(950,600));
		parent = par;
		//Bouton et textField
			//Boutons
		ok = new JButton("Accepter");
		ok.setBackground(new Color(80,250,80));
		ok.setPreferredSize(new Dimension(100,30));
			//JLabel
		infos = new JLabel("Entrez les informations pour modifier l'intervention ( * = obligatoire)");
		infos.setPreferredSize(new Dimension(500,40));
		infos.setHorizontalTextPosition(SwingConstants.CENTER);
		noInterv = new JLabel("N° d'intervention :");
		noInterv.setPreferredSize(new Dimension(200,20));
		noInterv.setHorizontalTextPosition(SwingConstants.RIGHT);
		etatInter = new JLabel("Etat de l'intervention *:");
		etatInter.setHorizontalTextPosition(SwingConstants.RIGHT);
		etatInter.setPreferredSize(new Dimension(200,20));
		fkPcUnit = new JLabel("Reference du PC *:");
		fkPcUnit.setHorizontalTextPosition(SwingConstants.RIGHT);
		fkPcUnit.setPreferredSize(new Dimension(200,20));
		fkTypeInterv = new JLabel("Reference du type d'intervention  *:");
		fkTypeInterv.setHorizontalTextPosition(SwingConstants.RIGHT);
		fkTypeInterv.setPreferredSize(new Dimension(200,20));
		preneurEnCharge = new JLabel("Gestionnaire de l'intervention *:");
		preneurEnCharge.setPreferredSize(new Dimension(200,20));
		preneurEnCharge.setHorizontalTextPosition(SwingConstants.RIGHT);
		tempsInterne = new JLabel("Durée de l'intervention :");
		tempsInterne.setPreferredSize(new Dimension(200,20));
		tempsInterne.setHorizontalTextPosition(SwingConstants.RIGHT);
		descBrefProb = new JLabel("Description du probème :");
		descBrefProb.setPreferredSize(new Dimension(200,20));
		descBrefProb.setHorizontalTextPosition(SwingConstants.RIGHT);
		suiviFourn = new JLabel("Renvoyé au fournisseur? *:");
		suiviFourn.setPreferredSize(new Dimension(200,20));
		suiviFourn.setHorizontalTextPosition(SwingConstants.RIGHT);
		datePrise = new JLabel("Date de prise en charge :");
		datePrise.setPreferredSize(new Dimension(200,20));
		datePrise.setHorizontalTextPosition(SwingConstants.RIGHT);
		etatRetour = new JLabel("Etat de retour :");
		etatRetour.setPreferredSize(new Dimension(200,20));
		etatRetour.setHorizontalTextPosition(SwingConstants.RIGHT);
		dateRemiseService = new JLabel("Date de remise en service :");
		dateRemiseService.setPreferredSize(new Dimension(200,20));
		dateRemiseService.setHorizontalTextPosition(SwingConstants.RIGHT);
		resultat = new JLabel("Résultat");
		resultat.setPreferredSize(new Dimension(200,20));
		resultat.setHorizontalTextPosition(SwingConstants.RIGHT);
		dateSign = new JLabel("Date de signalement *:");
		dateSign.setPreferredSize(new Dimension(200,20));
		dateSign.setHorizontalTextPosition(SwingConstants.RIGHT);
		signaleurIncident = new JLabel("Nom du signaleur :");
		signaleurIncident.setPreferredSize(new Dimension(200,20));
		signaleurIncident.setHorizontalTextPosition(SwingConstants.RIGHT);		
		dateContact = new JLabel("Signalement au fournisseur :");
		dateContact.setPreferredSize(new Dimension(200,20));
		dateContact.setHorizontalTextPosition(SwingConstants.RIGHT);
		fkFournisseurIntervenant = new JLabel("Reference du Fournisseur");
		fkFournisseurIntervenant.setPreferredSize(new Dimension(200,20));
		fkFournisseurIntervenant.setHorizontalTextPosition(SwingConstants.RIGHT);
		dateretour = new JLabel("Date de retour :");
		dateretour.setPreferredSize(new Dimension(200,20));
		dateretour.setHorizontalTextPosition(SwingConstants.RIGHT);
			//JComboBox
		fkPcUnitIN = new JComboBox();
		fkPcUnitIN.setPreferredSize(new Dimension(200,20));
		fkTypeIntervIN = new JComboBox();;
		fkTypeIntervIN.setPreferredSize(new Dimension(200,20));
		etatInterIN = new JComboBox();	
		etatInterIN.addItem("blank");
		etatInterIN.addItem("Signalé");
		etatInterIN.addItem("En cours");
		etatInterIN.addItem("Cloturé");
		etatInterIN.setPreferredSize(new Dimension(200,20));
		etatInterIN.setEditable(false);
		fkFournisseurIntervenantIN = new JComboBox();
		fkFournisseurIntervenantIN.setPreferredSize(new Dimension(200,20));
		fkFournisseurIntervenantIN.setEditable(false);
		etatRetourIN = new JComboBox();
		etatRetourIN.addItem("blank");
		etatRetourIN.addItem("Ok");
		etatRetourIN.addItem("Déclassé");
		etatRetourIN.addItem("en Suspens");
		etatRetourIN.setPreferredSize(new Dimension(200,20));
		etatRetourIN.setEditable(false);
		resultatIN = new JComboBox();
		resultatIN.addItem("blank");
		resultatIN.addItem("Ok");
		resultatIN.addItem("Déclassé");
		resultatIN.addItem("en Suspens");
		resultatIN.setPreferredSize(new Dimension(200,20));
		resultatIN.setEditable(false);
			//JTextField
		tempsInterneIN = new JTextField();
		tempsInterneIN.setPreferredSize(new Dimension(200,20));
		descBrefProbIN = new JTextArea();
		descBrefProbIN.setPreferredSize(new Dimension(200,80));
		noIntervIN = new JTextField();
		noIntervIN.setEditable(false);
		noIntervIN.setPreferredSize(new Dimension(200,20));
		signaleurIncidentIN = new JTextField();
		signaleurIncidentIN.setPreferredSize(new Dimension(200,20));
		preneurEnChargeIN = new JTextField();
		preneurEnChargeIN.setPreferredSize(new Dimension(200,20));
			//calendrier
		datePriseIN = new Calendrier();
		datePriseIN.setPreferredSize(new Dimension(150,20));
		dateRemiseServiceIN = new Calendrier();
		dateRemiseServiceIN.setPreferredSize(new Dimension(150,20));
		dateSignIN = new Calendrier();
		dateSignIN.setPreferredSize(new Dimension(150,20));
		dateContactIN = new Calendrier();
		dateContactIN.setPreferredSize(new Dimension(150,20));
		dateretourIN = new Calendrier();
		dateretourIN.setPreferredSize(new Dimension(150,20));
			//radioButton
		oui = new JRadioButton("oui");
		oui.setSelected(true);
		non = new JRadioButton("non");
		choix = new ButtonGroup();
		choix.add(oui);
		choix.add(non);
		//Ajout des Listener
		ok.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{
							exeSQL();
						}				
					});
		try
		{
			String instSQL = "SELECT * FROM Intervention WHERE NoInterv=";
			instSQL += indice.toString();
			String instSQLFournisseur = "SELECT NomFourn FROM Fournisseur GROUP BY NomFourn";
			String instSQLPCUnit = "SELECT IDPcUnit FROM PcUnit GROUP BY IDPcUnit";
			String instSQLInter = "SELECT LibelleTypeInt FROM TypeIntervention GROUP BY LibelleTypeInt";
			String instSQLTypeInt = "SELECT LibelleTypeInt FROM TypeIntervention WHERE CodeTypeInt='";
			String instSQLFournID = "SELECT NomFourn FROM Fournisseur WHERE FournisseurID='";
			noIntervIN.setText(indice.toString());
				//fkFournisseurIntervenantIN
			Object[] temp = AccessBDGen.creerListe1Colonne(parent.getBDD() , instSQLFournisseur );
			TableModel tempInter = AccessBDGen.creerTableModel(parent.getBDD(), instSQL);
			fkFournisseurIntervenantIN.removeAllItems();
			fkFournisseurIntervenantIN.addItem("blank");
			for(int i = 0; i< temp.length && temp[i]!=null;i++)
			{
				fkFournisseurIntervenantIN.addItem(temp[i]);
			}
			if(tempInter.getValueAt(0,14)!=null)
			{
				instSQLFournID += tempInter.getValueAt(0,14);
				instSQLFournID += "'";
				fkFournisseurIntervenantIN.setSelectedItem(AccessBDGen.creerListe1Colonne(parent.getBDD() , instSQLFournID )[0]);
			}
				//fkPcUnitIN
			temp = AccessBDGen.creerListe1Colonne(parent.getBDD() , instSQLPCUnit );
			fkPcUnitIN.removeAllItems();
			fkPcUnitIN.addItem("blank");
			for(int i = 0; i< temp.length && temp[i]!=null;i++)
			{
				fkPcUnitIN.addItem(temp[i]);
			}
			if(tempInter.getValueAt(0,15)!=null)fkPcUnitIN.setSelectedItem(((String)tempInter.getValueAt(0,15)).trim());
				//fkTypeIntervIN
			temp = AccessBDGen.creerListe1Colonne(parent.getBDD() , instSQLInter );
			fkTypeIntervIN.removeAllItems();
			fkTypeIntervIN.addItem("blank");
			for(int i = 0; i< temp.length && temp[i]!=null;i++)
			{
				fkTypeIntervIN.addItem(temp[i]);
			}
			if(tempInter.getValueAt(0,16)!=null)
			{
				instSQLTypeInt += tempInter.getValueAt(0,16);
				instSQLTypeInt +="'";
				fkTypeIntervIN.setSelectedItem(AccessBDGen.creerListe1Colonne(parent.getBDD(),instSQLTypeInt)[0]);
			}
			//etatInterIN
			if(tempInter.getValueAt(0,5)!=null)etatInterIN.setSelectedItem(tempInter.getValueAt(0,5));
			//etatRetourIN
			if(tempInter.getValueAt(0,10)!=null)etatRetourIN.setSelectedItem(tempInter.getValueAt(0,10));
			//resultatIN
			if(tempInter.getValueAt(0,13)!=null)resultatIN.setSelectedItem(tempInter.getValueAt(0,13));
			//descBrefProbIN
			if(tempInter.getValueAt(0,2)!=null)descBrefProbIN.setText((String) tempInter.getValueAt(0, 2));
			//datePriseIN
			if(tempInter.getValueAt(0,8)!=null) datePriseIN.setDate((Date) tempInter.getValueAt(0,8));
			else datePriseIN.setDateZero();
			//dateRemiseServiceIN
			if(tempInter.getValueAt(0,11)!=null) dateRemiseServiceIN.setDate((Date) tempInter.getValueAt(0,11));
			else dateRemiseServiceIN.setDateZero();
			//dateSignIN
			if(tempInter.getValueAt(0, 1)!=null) dateSignIN.setDate((Date)tempInter.getValueAt(0, 1));
			else dateSignIN.setDateZero();
			//dateContactIN
			if(tempInter.getValueAt(0,7)!=null) dateContactIN.setDate((Date) tempInter.getValueAt(0,7));
			else dateContactIN.setDateZero();
			//dateretourIN
			if(tempInter.getValueAt(0,9)!=null) dateretourIN.setDate((Date) tempInter.getValueAt(0,9));
			else dateretourIN.setDateZero();
			//choix
			if((Boolean)tempInter.getValueAt(0,6)) oui.setSelected(true);
			else non.setSelected(true);
			//tempsInterneIN
			if(tempInter.getValueAt(0,12)!=null) tempsInterneIN.setText(((Integer)tempInter.getValueAt(0,12)).toString());
			//signaleurIncidentIN
			signaleurIncidentIN.setText((String) tempInter.getValueAt(0,3));
			//preneurEnChargeIN
			preneurEnChargeIN.setText((String) tempInter.getValueAt(0,4));
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this,e, "Erreur de BDD",JOptionPane.ERROR_MESSAGE);
		}
		setLayout(new GridBagLayout());
		//init du gbc
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridwidth = GridBagConstraints.REMAINDER; // seul composant de sa colonne, il est donc le dernier.
		gbc.gridheight = 1; // valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.LINE_START;//positionne par la gauche
		gbc.insets = new Insets(5, 5, 0, 0);//marge entre les composants
		//Ajout au panneau
		add(infos,gbc);
			//1ere ligne
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1; 
		add(noInterv,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(noIntervIN,gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 4;
		add(dateSign,gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(dateSignIN,gbc);
		gbc.gridwidth = 1;
			//2eme ligne
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(etatInter,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(etatInterIN,gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 4;
		add(signaleurIncident,gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(signaleurIncidentIN,gbc);
			//3eme ligne
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		add(fkPcUnit,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(fkPcUnitIN,gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 4;
		add(dateContact,gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(dateContactIN,gbc);
			//4eme ligne
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		add(fkTypeInterv,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(fkTypeIntervIN,gbc);
			//5eme ligne
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		add(preneurEnCharge,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(preneurEnChargeIN,gbc);	
			//6eme ligne
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		add(tempsInterne,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(tempsInterneIN,gbc);
			//7eme ligne
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		add(descBrefProb,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 4;
		add(descBrefProbIN,gbc);
			//8eme ligne
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		add(suiviFourn,gbc);
		gbc.gridx = 1;
		add(oui,gbc);
		gbc.gridx = 2;
		add(non,gbc);
		gbc.gridx = 4;
		add(fkFournisseurIntervenant,gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(fkFournisseurIntervenantIN,gbc);
			//9eme ligne
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		add(datePrise,gbc);
		gbc.gridx = 1;
		add(datePriseIN,gbc);
		gbc.gridx = 4;
		add(dateretour,gbc);
		gbc.gridx = 5;
		gbc.gridwidth = 2;
		add(dateretourIN,gbc);
			//10eme ligne
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		add(etatRetour,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(etatRetourIN,gbc);
			//11eme ligne
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		add(dateRemiseService,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(dateRemiseServiceIN,gbc);
			//12eme ligne
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		add(resultat,gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(resultatIN,gbc);
			//13eme ligne
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;//positionne par la droite
		gbc.insets = new Insets(40, 10, 5, 5);//marge entre les composants
		gbc.gridx = 2;
		add(ok,gbc);
		setLocationRelativeTo(null);//place la fenetre au milieu de l'ecran
		setVisible(true);//affiche a l'ecran		
	}
	
	public void exeSQL()
	{
		if(parent.getBDD()!=null)
		{				
			try 
			{
				//creation de la requete en fonction des champs
				String instSQL = "UPDATE Intervention SET ";
				//DateSignalement									
				if(!dateSignIN.checkDate())
				{
					throw new ChampException("Date de signalement");
				}
				else
				{
					instSQL+="DateSignalement='";
					instSQL+=dateSignIN.getDate();
					instSQL+="',";
				}
				//DescripftifBrefprobleme
				if( descBrefProbIN.getText()!=null && !descBrefProbIN.getText().isEmpty() )
				{
					instSQL+="DescriptifBrefProblème='";
					instSQL+=descBrefProbIN.getText().trim();
					instSQL+="',";
				}
				//SignaleurIncident
				if( signaleurIncidentIN.getText()!=null && !signaleurIncidentIN.getText().isEmpty())
				{
					instSQL+="SignaleurIncident='";
					instSQL+=signaleurIncidentIN.getText().trim();
					instSQL+="',";
				}
				//PreneurEnCharge
				if(preneurEnChargeIN.getText() == null || preneurEnChargeIN.getText().isEmpty() )
				{
					 throw new ChampException("Gestionnaire de l'intervention");
				}
				else
				{
					instSQL+="PreneurEnCharge='";
					instSQL+=preneurEnChargeIN.getText().trim();
					instSQL+="',";
				}
				//EtatInterv
				if( etatInterIN.getSelectedItem()==null || etatInterIN.getSelectedItem().equals("blank")) 
				{
					throw new ChampException("Etat de l'intervention");
				}
				else
				{
					instSQL+="EtatInterv='";
					instSQL+=etatInterIN.getSelectedItem();
					instSQL+="',";
				}
				//,SuiviViaFournisseur
				instSQL+="SuiviViaFournisseur=";
				instSQL+=(non.isSelected()==true)? "False":"True";
				instSQL+=",";
				//,DateContact									
				if(dateContactIN.checkDate())
				{										
					instSQL+="DateContact='";
					instSQL+=dateContactIN.getDate();
					instSQL+="',";
				}																	
				//DatePrise									
				if(datePriseIN.checkDate())
				{
					instSQL+="DatePrise='";
					instSQL+=datePriseIN.getDate();
					instSQL+="',";
				}								
				//DateRetour								
				if(dateretourIN.checkDate())
				{
					instSQL+="DateRetour='";
					instSQL+=dateretourIN.getDate();
					instSQL+="',";
				}
				//EtatRetour
				if( etatRetourIN.getSelectedItem()!=null && !etatRetourIN.getSelectedItem().equals("blank"))
				{
					instSQL+="EtatRetour='";
					instSQL+=etatRetourIN.getSelectedItem();
					instSQL+="',";
				}
				//DateRemiseService
				if(dateRemiseServiceIN.checkDate())
				{
					instSQL+="DateRemiseService='";
					instSQL+=dateRemiseServiceIN.getDate();
					instSQL+="',";
				}					
				//TempsInterne
				if( tempsInterneIN.getText()!=null && !tempsInterneIN.getText().isEmpty())
				{
					instSQL+="TempsInterne=";
					instSQL+=tempsInterneIN.getText();
					instSQL+=",";
				}
				//,Résultat
				if( resultatIN.getSelectedItem()!=null && !resultatIN.getSelectedItem().equals("blank"))
				{
					instSQL+="Résultat='";
					instSQL+=resultatIN.getSelectedItem();
					instSQL+="',";
				}
				//fkFournisseurIntervenant
				if(fkFournisseurIntervenantIN.getSelectedItem()!=null && !fkFournisseurIntervenantIN.getSelectedItem().equals("blank"))
				{
					String instSQLIDFourn = "SELECT FournisseurID FROM Fournisseur WHERE NomFourn='";
					instSQLIDFourn += fkFournisseurIntervenantIN.getSelectedItem();
					instSQLIDFourn += "'";
					instSQL+="FkFournisseuIntervenant='";
					instSQL+=AccessBDGen.creerListe1Colonne(parent.getBDD(),instSQLIDFourn)[0];
					instSQL+="',";
				}
				//,FkPcUnit
				if( fkPcUnitIN.getSelectedItem()==null || fkPcUnitIN.getSelectedItem().equals("blank")) 
				{
					throw new ChampException("Reference du PC");
				}
				else
				{
					instSQL+="FkPcUnit='";
					instSQL+=fkPcUnitIN.getSelectedItem();
					instSQL+="',";
				}
				//FkTypeInterv,
				if( fkTypeIntervIN.getSelectedItem()==null || fkTypeIntervIN.getSelectedItem().equals("blank"))
				{
					throw new ChampException("Reference du type d'intervention");
				}
				else
				{
					String instSQLIDinterv = "SELECT CodeTypeInt FROM TypeIntervention WHERE LibelleTypeInt='";
					instSQLIDinterv += fkTypeIntervIN.getSelectedItem();
					instSQLIDinterv += "'";
					instSQL+="FkTypeInterv='";
					instSQL+=AccessBDGen.creerListe1Colonne(parent.getBDD(),instSQLIDinterv)[0];
					instSQL+="'";
				}
				instSQL += " WHERE NoInterv=";
				instSQL += noIntervIN.getText();
				AccessBDGen.executerInstruction(parent.getBDD(), instSQL);
				JOptionPane.showMessageDialog(this,"Intervention encodée", "Victoire",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			catch (SQLException exc)
			{
				JOptionPane.showMessageDialog(this,exc, "Erreur de BDD",JOptionPane.ERROR_MESSAGE);
			}
			catch (ChampException e1)
			{
				JOptionPane.showMessageDialog(this,"Champ obligatoire "+e1+" non rempli", "Erreur de champ",JOptionPane.ERROR_MESSAGE);
			}								
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Pas de connexion en cours","Erreur de BDD",JOptionPane.ERROR_MESSAGE);
		}
	}
}
