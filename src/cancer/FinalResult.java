/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cancer;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Seban
 */
public class FinalResult extends JFrame{

  Dbconnect db_obj=new Dbconnect();

    public FinalResult()
    {
        setSize(500,400);
        setLayout(null);

        Vector v1=new Vector();
        v1.addElement("Sl No");
        v1.addElement("Protein List");
        v1.addElement("Final score");
        v1.addElement("Type");


        Vector v2=db_obj.getFinal();

        JTable jtab=new JTable(v2,v1);

        JScrollPane jsp=new JScrollPane(jtab);
        jsp.setBounds(50,50,400,300);
        add(jsp);

        setVisible(true);
    }

    public static void main(String args[])
    {
        FinalResult obj=new FinalResult();
    }

}
