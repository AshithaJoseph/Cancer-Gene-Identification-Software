/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegisterNew.java
 *
 * Created on Mar 31, 2013, 3:03:20 PM
 */

package cancer;

/**
 *
 * @author Seban
 */
public class RegisterNew extends javax.swing.JFrame {

    /** Creates new form RegisterNew */
    public RegisterNew() {
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

        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximizedBounds(new java.awt.Rectangle(150, 150, 200, 200));
        setMinimumSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13));
        jLabel5.setText("Job Description");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 260, 110, 20);

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(240, 90, 150, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scientist", "Research Fellow", "Medical Practitioner", "Student" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(240, 260, 150, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13));
        jLabel6.setText("Name of the Instituition");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(60, 330, 140, 16);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 13));
        jLabel4.setText("Password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 210, 70, 20);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(240, 320, 150, 30);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(240, 140, 150, 30);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(180, 430, 110, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 13));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 100, 70, 20);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13));
        jLabel1.setText("Name");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 160, 50, 16);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Registration");
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 200));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 10, 110, 40);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(240, 200, 150, 30);

        setBounds(300, 100, 456, 564);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // String job[]=new String[]{"Scientist","Research fellow","Medical Practitioner"};
        //JComboBox jc=new JComboBox(job);
        //add(jc);// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add youteur handling code here:
}//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String uname=jTextField4.getText();
        String name=jTextField2.getText();
        String pass=jPasswordField1.getText();
        String name_of_inst=jTextField5.getText();
        String type=(String)jComboBox1.getSelectedItem();
        if(uname.equals(""))
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Username!!");
        }
        else if(name.equals(""))
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Name!!");
        }
        else if(pass.equals(""))
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Password!!");
        }
        else if(name_of_inst.equals(""))
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Name of Institute!!");
        }
        else
        {
            Dbconnect obj=new Dbconnect();
            int j=obj.insertDetails(uname, name,pass,name_of_inst,type);
            if(j==1)
            {
                   dispose();
                   new Loginnew().setVisible(true);
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Registration Failed!!");
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterNew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

}
