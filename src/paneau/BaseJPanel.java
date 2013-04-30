package paneau;

import java.sql.*;
import javax.swing.*;
import principal.*;

@SuppressWarnings("serial")
public abstract class BaseJPanel extends JPanel {
	//Fenetre pour stocker la fenetre parent
	protected Fenetre parent;
	protected JPanel himself;
	public BaseJPanel(Fenetre f )
	{
		//taille et position du JPanel
		parent=f;
		himself=this;
		setBounds(0,10,0,0);
	}
	public abstract  void actualiser();
	public abstract void vider();
	public void erreurSQL(SQLException e)
	{
		JOptionPane.showMessageDialog(this,e, "Erreur de BDD",JOptionPane.ERROR_MESSAGE);
	}
}
