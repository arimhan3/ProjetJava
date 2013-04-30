package divers;

import java.text.*;
import java.util.*;

import javax.swing.*;

public class Calendrier extends JPanel {
	private JComboBox<String>[] combos = new JComboBox[3];
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    int ranges[][] = {{0,31},{0,12},{2012,2050}};
    public Calendrier() {
        super();
        combos[0] =  new JComboBox();
        combos[1] =  new JComboBox();
        combos[2] =  new JComboBox();
        for (int i = 0; i<combos.length; i++)
            for (int j = ranges[i][0]; j<=ranges[i][1]; j++) 
                combos[i].addItem((j<=9?"0":"")+Integer.toString(j));
        combos[2].insertItemAt("0000", 0);
        combos[2].setSelectedIndex(0);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        for (JComboBox<String> c: combos)
        { 
            this.add(c);
        }

        setDateZero();
    }
    
    public Calendrier(Date date) {
        this();
        setDate(date);
    }

    public void setDate(Date d) {
        String[] date = df.format(d).split("/");
        for (int i=0;i<combos.length; i++)
            combos[i].setSelectedItem(date[i]);
    }
    
    public void setDateZero()
    {
    	String[] date = {"00","00","0000"};
    	for (int i=0;i<combos.length; i++)
            combos[i].setSelectedItem(date[i]);
    }

    public String getDate()
    {
        return new String(combos[2].getSelectedItem()+"-"+combos[1].getSelectedItem()+"-"+combos[0].getSelectedItem());
    }
    
    /**False si date Vide*/  
	public boolean checkDate() 
	{
		if( combos[0].getSelectedIndex()==0 || combos[1].getSelectedIndex()==0 || combos[2].getSelectedIndex()==0) return false;
		else return true;
	}
}
