import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Deposit extends JFrame {
    int formno;
    JLabel amt,pin,meth;
    JComboBox c1;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1;

    public Deposit(int formno){
        this.formno=formno;
        setTitle("Deposit");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        amt=new JLabel("Amount");
        amt.setBounds(50,25,150,30);
        amt.setFont(new Font("Arial",Font.PLAIN,20));

        meth=new JLabel("Method");
        meth.setBounds(50,75,150,30);
        meth.setFont(new Font("Arial",Font.PLAIN,20));

        pin=new JLabel("Pin");
        pin.setBounds(50,125,150,30);
        pin.setFont(new Font("Arial",Font.PLAIN,20));

        tf1=new JTextField();
        tf1.setBounds(200,25,150,30);

        String s[]={"Cash","Netbanking","Online"};
        c1=new JComboBox<>(s);
        c1.setBounds(200,75,150,30);

        pf1=new JPasswordField();
        pf1.setBounds(200,125,150,30);

        b1=new JButton("Submit");
        b1.setBounds(165,205,125,30);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try {

                    double amount =
                    Double.parseDouble(tf1.getText().trim());

                    String enteredPin =
                    String.valueOf(pf1.getPassword());

                    Connection con =connection.getcon();

                    String sql1 ="SELECT acc_no FROM accounts " +
                    "WHERE form_no = ? AND pin = ?";

                    PreparedStatement pst1 =con.prepareStatement(sql1);

                    pst1.setInt(1, formno);
                    pst1.setInt(2,Integer.parseInt(enteredPin));

                    ResultSet rs =pst1.executeQuery();

                        if(rs.next()) {

                            long accNo =rs.getLong("acc_no");

                            String sql2 =
                            "INSERT INTO transactions" +
                            "(acc_no,txn_type,amount)" +
                            " VALUES(?,?,?)";

                            PreparedStatement pst2 =con.prepareStatement(sql2);

                            pst2.setLong(1, accNo);
                            pst2.setString(2, "Deposit");
                            pst2.setDouble(3, amount);

                            pst2.executeUpdate();

                            JOptionPane.showMessageDialog( null,
                    "Deposit Success");

                            dispose();

                        } else {
                            JOptionPane.showMessageDialog(null,"Invalid Pin");
                        }

                } catch(Exception e) {
            e.printStackTrace();
                }
            }
        });

        add(amt);
        add(meth);
        add(pin);
        add(tf1);
        add(c1);
        add(pf1);
        add(b1);

        setVisible(true);

    }
}
    /*public static void main(String[] args) {
        new Deposit(1);
    }
}*/
 