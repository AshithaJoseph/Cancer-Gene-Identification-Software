/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UserProfileNew.java
 *
 * Created on Mar 31, 2013, 3:16:46 PM
 */

package cancer;
import com.mysql.jdbc.ResultSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Seban
 */
public class UserProfileNew extends javax.swing.JFrame {

    /** Creates new form UserProfileNew */
    public UserProfileNew() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(570, 500));
        getContentPane().setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(280, 76, 180, 110);

        jLabel1.setText("Enter your PPI data: ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(89, 90, 160, 16);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Eg: BRCA1 TP53", "PMA BRCA2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(100, 120, 127, 43);

        jButton2.setText("Input Statistics");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(350, 390, 120, 25);

        jLabel2.setText("Percentage of Sampling");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 280, 190, 16);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(310, 280, 70, 22);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 13)); // NOI18N
        jLabel3.setText("CANCER GENE IDENTIFICATION");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(170, 30, 210, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    static int tot_intr;
    int inv=0;
    static int tot_pro;
    static String perc2;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       
            String val = jTextArea1.getText();
            perc2=jTextField2.getText();
            FileWriter f_out = null;
            try {
            
            File f = new File("D:/input.txt"); //inserting user input into input.txt file
                if (f.exists()) 
                {
                }
                else
                {
                    f.createNewFile();
                }
                f_out = new FileWriter(f);
                f_out.write(val);
                f_out.close();
                Dbconnect db = new Dbconnect();
                int flag = 0;
                
                String[] ar = val.split("\\n");
                tot_intr=ar.length;
               System.out.println(ar);
                db.truncate();
                
                String[] strs = val.split("\\n");

                for (int j = 0; j < strs.length; j++)
                {
                    String tab_cut[]=strs[j].split("\t");
                     // javax.swing.JOptionPane.showMessageDialog(null, "!!!!"+tab_cut.length);
                    if(tab_cut.length==2)
                    {
                    int di,ch;
                    java.sql.ResultSet rs;
                   // javax.swing.JOptionPane.showMessageDialog(null, "eeeeeee!");
                    rs=db.hprd_check(tab_cut);
                    if(rs.next())
                    {
                        di = db.insertSingle(tab_cut);
                    }
                    else
                      {
                        javax.swing.JOptionPane.showMessageDialog(null, "Invalid Input!!");
                        inv=1;
                        break;

                    }
                    }
                    else
                    {
                        javax.swing.JOptionPane.showMessageDialog(null, "Invalid Input!!!!");
                        inv=1;
                        break;
                    }

                }

                if(inv==0)
                {
                

                ArrayList arr = db.getDistinct();
                tot_pro=arr.size();
                for (int i = 0; i < arr.size(); i++) {
                    java.sql.ResultSet rs1 = db.getcancer();
                    String value;
                    int check = 0;
                    value = (String) arr.get(i);
                    //System.out.println("jiiiii"+value);

                    while (rs1.next()) {
                        //System.out.println("WHILE"+rs1.getString(2));
                        if (value.equals(rs1.getString(2))) {
                             
                            check = 1;
                        }
                    }
                    if (check == 1) {
                        db.insertprotein(value, "C");
                    } else {
                        db.insertprotein(value, "N");
                    }
                    
                }
                Find obj2=new Find();
                    obj2.Find_fun();
                    
                    }

            } catch (IOException ex) {
                Logger.getLogger(UserProfileNew.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserProfileNew.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    f_out.close();
                } catch (IOException ex) {
                    Logger.getLogger(UserProfileNew.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserProfileNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
