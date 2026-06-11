import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Withdraw extends JFrame{
    int formno;
    JLabel amt,pin;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1;
    Withdraw(int formno){
        this.formno=formno;
        setTitle("Withdraw");
        setSize(500,300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        amt=new JLabel("Amount");
        amt.setBounds(50,25,150,30);
        amt.setFont(new Font("Arial",Font.PLAIN,20));

        pin=new JLabel("Pin");
        pin.setBounds(50,75,150,30);
        pin.setFont(new Font("Arial",Font.PLAIN,20));

        tf1=new JTextField();
        tf1.setBounds(200,25,150,30);

        pf1=new JPasswordField();
        pf1.setBounds(200,75,150,30);

        b1=new JButton("Next");
        b1.setBounds(165,205,125,30);
        b1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {

        try {

            double amount =
            Double.parseDouble(tf1.getText().trim());

            String enteredPin =
            String.valueOf(pf1.getPassword());

            Connection con =
            connection.getcon();

            String sql1 =
            "SELECT acc_no FROM accounts " +
            "WHERE form_no = ? AND pin = ?";

            PreparedStatement pst1 =
            con.prepareStatement(sql1);

            pst1.setInt(1, formno);
            pst1.setInt(2,
            Integer.parseInt(enteredPin));

            ResultSet rs =
            pst1.executeQuery();

            if(rs.next()) {

                long accNo =
                rs.getLong("acc_no");

                double balance = 0;

                String sql2 =
                "SELECT txn_type, amount " +
                "FROM transactions " +
                "WHERE acc_no = ?";

                PreparedStatement pst2 =
                con.prepareStatement(sql2);

                pst2.setLong(1, accNo);

                ResultSet rs2 =
                pst2.executeQuery();

                while(rs2.next()) {

                    String type =
                    rs2.getString("txn_type");

                    double amt =
                    rs2.getDouble("amount");

                    if(type.equals("Deposit"))
                        balance += amt;
                    else if(type.equals("Withdraw"))
                        balance -= amt;
                }

                if(amount > balance) {

                    JOptionPane.showMessageDialog(
                        null,
                        "Insufficient Balance"
                    );

                    return;
                }

                String sql3 =
                "INSERT INTO transactions " +
                "(acc_no, txn_type, amount) " +
                "VALUES (?, ?, ?)";

                PreparedStatement pst3 =
                con.prepareStatement(sql3);

                pst3.setLong(1, accNo);
                pst3.setString(2, "Withdraw");
                pst3.setDouble(3, amount);

                pst3.executeUpdate();

                JOptionPane.showMessageDialog(null,"Withdrawal Successful");
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "Invalid PIN"
                );
            }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    });
        add(amt);
        add(pin);
        add(tf1);
        add(pf1);
        add(b1);

        setVisible(true);
    }
}