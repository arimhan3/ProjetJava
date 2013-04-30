package paneau;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;
import principal.*;
import bDD.*;
import divers.*;
import exception.*;

@SuppressWarnings("serial")
public class Encodage extends BaseJPanel {
	//Declaration des elements du JPanel
	private JButton raz, ok;
	private JLabel  infos, noInterv, etatInter, fkPcUnit, fkTypeInterv,preneurEnCharge, tempsInterne, descBrefProb ,suiviFourn, datePrise,signaleurIncident, etatRetour,dateRemiseService,resultat, dateContact, fkFournisseurIntervenant,dateSign, dateretour;
	private JComboBox etatInterIN,fkFournisseurIntervenantIN,etatRetourIN,resultatIN,fkPcUnitIN, fkTypeIntervIN ;
	private JTextField  tempsInterneIN, noIntervIN, signaleurIncidentIN, preneurEnChargeIN;
	private JTextArea descBrefProbIN;
	private Calendrier datePriseIN, dateRemiseServiceIN,dateSignIN, dateContactIN, dateretourIN ;
	private ButtonGroup choix;
	private JRadioButton oui,non;
	
	public Encodage (Fenetre f)
	{
		super(f);
		//Bouton et textField
			//Boutons
		raz = new JButton("Remise à zero du formulaire");
		raz.setBackground(new Color(250,80,80));
		raz.setPreferredSize(new Dimension(200,30));
		ok = new JButton("Accepter");
		ok.setBackground(new Color(80,250,80));
		ok.setPreferredSize(new Dimension(100,30));
			//JLabel
		infos = new JLabel("Entrez les informations pour rajouter dans le listing ( * = obligatoire)");
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
		raz.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						if(JOptionPane.showConfirmDialog(himself, "Voulez-vous vous vider les champs? ", "Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)== 0)
						{
							effacerChamps();
						}
					}						
				});
		ok.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{
							if(parent.getBDD()!=null)
							{	
								try 
								{
									//creation de la requete en fonction des champs
									String instSQL=null;
									instSQL = "INSERT INTO Intervention(NoInterv,DateSignalement,DescriptifBrefProblème,SignaleurIncident,PreneurEnCharge,EtatInterv,SuiviViaFournisseur,DateContact,DatePrise,DateRetour,EtatRetour,DateRemiseService,TempsInterne,Résultat,FkFournisseuIntervenant,FkPcUnit,FkTypeInterv) VALUES(";
									//NoInterv
									if( noIntervIN.getText()==null || noIntervIN.getText().isEmpty()) 
									{
										throw new ChampException("N° d'intervention");
									}
									else
									{
										instSQL+=noIntervIN.getText();
										instSQL+=",";
									}
									//DateSignalement									
									if(!dateSignIN.checkDate())
									{
										throw new ChampException("Date de signalement");
									}
									else
									{
										instSQL+="'";
										instSQL+=dateSignIN.getDate();
										instSQL+="',";
									}
									//DescripftifBrefprobleme
									if( descBrefProbIN.getText()==null || descBrefProbIN.getText().isEmpty() )
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=descBrefProbIN.getText();
										instSQL+="',";
									}
									//SignaleurIncident
									if( signaleurIncidentIN.getText()==null || signaleurIncidentIN.getText().isEmpty())
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=signaleurIncidentIN.getText();
										instSQL+="',";
									}
									//PreneurEnCharge
									if(preneurEnChargeIN.getText() == null || preneurEnChargeIN.getText().isEmpty() )
									{
										 throw new ChampException("Gestionnaire de l'intervention");
									}
									else
									{
										instSQL+="'";
										instSQL+=preneurEnChargeIN.getText();
										instSQL+="',";
									}
									//EtatInterv
									if( etatInterIN.getSelectedItem()==null || etatInterIN.getSelectedItem().equals("blank")) 
									{
										throw new ChampException("Etat de l'intervention");
									}
									else
									{
										instSQL+="'";
										instSQL+=etatInterIN.getSelectedItem();
										instSQL+="',";
									}
									//,SuiviViaFournisseur
									instSQL+="";
									instSQL+=(non.isSelected()==true)? "False":"True";
									instSQL+=",";
									//,DateContact									
									if(!dateContactIN.checkDate())
									{										
										instSQL+="NULL";
										instSQL+=",";										
									}
									else
									{
										instSQL+="'";
										instSQL+=dateContactIN.getDate();
										instSQL+="',";
									}																	
									//DatePrise									
									if(!datePriseIN.checkDate())
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=datePriseIN.getDate();
										instSQL+="',";
									}								
									//DateRetour								
									if(!dateretourIN.checkDate())
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=dateretourIN.getDate();
										instSQL+="',";
									}
									//EtatRetour
									if( etatRetourIN.getSelectedItem()==null || etatRetourIN.getSelectedItem().equals("blank"))
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=etatRetourIN.getSelectedItem();
										instSQL+="',";
									}
									//DateRemiseService
									
									if(!dateRemiseServiceIN.checkDate())
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=dateRemiseServiceIN.getDate();
										instSQL+="',";
									}	
									
									//,TempsInterne
									if( tempsInterneIN.getText()==null || tempsInterneIN.getText().isEmpty())
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=tempsInterneIN.getText();
										instSQL+="',";
									}
									//,Résultat
									if( resultatIN.getSelectedItem()==null || resultatIN.getSelectedItem().equals("blank"))
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										instSQL+="'";
										instSQL+=resultatIN.getSelectedItem();
										instSQL+="',";
									}
									//fkFournisseurIntervenant
									if(fkFournisseurIntervenantIN.getSelectedItem()==null || fkFournisseurIntervenantIN.getSelectedItem().equals("blank"))
									{
										instSQL+="NULL";
										instSQL+=",";
									}
									else
									{
										//requetes secondaires
										String instSQLIDFourn = "SELECT FournisseurID FROM Fournisseur WHERE NomFourn='";
										instSQLIDFourn += fkFournisseurIntervenantIN.getSelectedItem();
										instSQLIDFourn += "'";
										instSQL+="'";
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
										instSQL+="'";
										instSQL+=fkPcUnitIN.getSelectedItem();
										instSQL+="',";
									}
									//,FkTypeInterv,
									if( fkTypeIntervIN.getSelectedItem()==null || fkTypeIntervIN.getSelectedItem().equals("blank"))
									{
										throw new ChampException("Reference du type d'intervention");
									}
									else
									{
										//requetes secondaires
										String instSQLIDinterv = "SELECT CodeTypeInt FROM TypeIntervention WHERE LibelleTypeInt='";
										instSQLIDinterv += fkTypeIntervIN.getSelectedItem();
										instSQLIDinterv += "'";
										instSQL+="'";
										instSQL+=AccessBDGen.creerListe1Colonne(parent.getBDD(),instSQLIDinterv)[0];
										instSQL+="'";
									}
									instSQL += ")";
									AccessBDGen.executerInstruction(parent.getBDD(), instSQL);
									effacerChamps();
									actualiser();
									JOptionPane.showMessageDialog(himself,"Intervention encodée", "Victoire",JOptionPane.INFORMATION_MESSAGE);
								}
								catch (SQLException exc)
								{
									JOptionPane.showMessageDialog(himself,exc, "Erreur de BDD",JOptionPane.ERROR_MESSAGE);
								}
								catch (ChampException e1)
								{
									JOptionPane.showMessageDialog(himself,"Champ obligatoire "+e1+" non rempli", "Erreur de champ",JOptionPane.ERROR_MESSAGE);
								}								
							}
							else
							{
								JOptionPane.showMessageDialog(himself,"Pas de connexion en cours","Erreur de BDD",JOptionPane.ERROR_MESSAGE);
							}
						}				
					});
		//ajout du Layout
		this.setLayout(new GridBagLayout());
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
		gbc.gridwidth=3;
		gbc.gridx = 3;
		add(raz,gbc);
		
	}
	public void actualiser()
	{
		if(parent.getBDD()!=null)
		{
			try
			{		
				String instSQLFournisseur = "SELECT NomFourn FROM Fournisseur GROUP BY NomFourn";
				String instSQLPCUnit = "SELECT IDPcUnit FROM PcUnit GROUP BY IDPcUnit";
				String instSQLInter = "SELECT LibelleTypeInt FROM TypeIntervention GROUP BY LibelleTypeInt";
				String instSQLIDInter = "SELECT NoInterv FROM Intervention";
					//fkFournisseurIntervenantIN
				TableModel temp = AccessBDGen.creerTableModel(parent.getBDD() , instSQLFournisseur );
				fkFournisseurIntervenantIN.removeAllItems();
				fkFournisseurIntervenantIN.addItem("blank");
				for(int i = 0; i< temp.getRowCount();i++)
				{
					fkFournisseurIntervenantIN.addItem(temp.getValueAt(i, 0));
				}
					//fkPcUnitIN
				temp = AccessBDGen.creerTableModel(parent.getBDD() , instSQLPCUnit );
				fkPcUnitIN.removeAllItems();
				fkPcUnitIN.addItem("blank");
				for(int i = 0; i< temp.getRowCount();i++)
				{
					fkPcUnitIN.addItem(temp.getValueAt(i, 0));
				}
					//fkTypeIntervIN
				temp = AccessBDGen.creerTableModel(parent.getBDD() , instSQLInter );
				fkTypeIntervIN.removeAllItems();
				fkTypeIntervIN.addItem("blank");
				for(int i = 0; i< temp.getRowCount();i++)
				{
					fkTypeIntervIN.addItem(temp.getValueAt(i, 0));
				}
				temp = AccessBDGen.creerTableModel(parent.getBDD() , instSQLIDInter );
				noIntervIN.setText( String.valueOf((Integer)temp.getValueAt(temp.getRowCount()-1,0) +1));
			}
			catch(SQLException exc)
			{
				JOptionPane.showMessageDialog(himself, exc, "Erreur de BDD", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void vider() 
	{
		effacerChamps();
		noIntervIN.setText("");
		fkFournisseurIntervenantIN.removeAllItems();
		fkPcUnitIN.removeAllItems();
		fkTypeIntervIN.removeAllItems();
		this.validate();
		this.repaint();
	}
	
	public void effacerChamps()
	{
		tempsInterneIN.setText("");
		descBrefProbIN.setText("");
		signaleurIncidentIN.setText("");
		preneurEnChargeIN.setText("");
		non.setSelected(false);
		oui.setSelected(true);
		datePriseIN.setDateZero();
		dateRemiseServiceIN.setDateZero();
		dateSignIN.setDateZero();
		dateContactIN.setDateZero();
		dateretourIN.setDateZero();
		etatInterIN.setSelectedIndex(0);
		etatRetourIN.setSelectedIndex(0);
		resultatIN.setSelectedIndex(0);
		fkPcUnitIN.setSelectedIndex(0);
		fkTypeIntervIN.setSelectedIndex(0);
		fkFournisseurIntervenantIN.setSelectedIndex(0);
	}

}
