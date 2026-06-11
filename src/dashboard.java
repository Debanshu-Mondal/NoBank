import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class dashboard extends JFrame {

    int formno;
    long accNo;

    JLabel logo;
    JLabel f_name,acc,bal;
    JButton view_bal,depo,with,mini;

    public dashboard(int formno) {

        this.formno = formno;

        setTitle("Dashboard");
        setSize(800, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Logo

        ImageIcon i1 = new ImageIcon("Nobanklogo.png");
        Image i2 = i1.getImage();
        Image img = i2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(img);

        logo = new JLabel(i3);
        logo.setBounds(650, 10, 100, 100);

        // Welcome Label

        f_name = new JLabel("Welcome");
        f_name.setFont(new Font("Arial", Font.BOLD, 24));
        f_name.setBounds(50, 10, 500, 30);

        // Account Number Label

        acc = new JLabel("Account Number");
        acc.setFont(new Font("Arial", Font.PLAIN, 20));
        acc.setBounds(50, 60, 500, 30);

        add(logo);
        add(f_name);
        add(acc);

        // Fetch data from database

        try {

            Connection con = connection.getcon();

            String sql =
            "SELECT u.first_name, a.acc_no " +
            "FROM users u JOIN accounts a " +
            "ON u.form_no = a.form_no " +
            "WHERE u.form_no = ?";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setInt(1, formno);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String firstName =
                rs.getString("first_name");

                accNo =
                rs.getLong("acc_no");

                f_name.setText("Welcome, " + firstName);

                acc.setText(
                "Account Number : " + accNo);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        bal=new JLabel("Current Balance: XXXXX.XXX");
        bal.setBounds(50,110,500,30);
        bal.setFont(new Font("Arial",Font.PLAIN,20));

        view_bal=new JButton("View Balance");
        view_bal.setBounds(70,150,150,30);

        add(bal);
        add(view_bal);

        view_bal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                bal.setText("Current Balance: Rs  "+getBalance());
            }
        });

        depo=new JButton("Deposit");
        depo.setBounds(50,300,150,30);
        depo.setFont(new Font("Arial",Font.PLAIN,20));
        depo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Deposit(formno);
            }
        });
        with=new JButton("Withdraw");
        with.setBounds(275,300,150,30);
        with.setFont(new Font("Arial",Font.PLAIN,20));
        with.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Withdraw(formno);
            }
        });
        mini=new JButton("Mini Statement");
        mini.setBounds(550,300,200,30);
        mini.setFont(new Font("Arial",Font.PLAIN,20));
        mini.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new ministate(accNo);
            }
        });


        add(depo);
        add(with);
        add(mini);


        setVisible(true);
    }

    public double getBalance(){

    double balance = 0;

        try{

        Connection con = connection.getcon();

        String sql =
        "SELECT txn_type, amount FROM transactions WHERE acc_no = ?";

        PreparedStatement pst =
        con.prepareStatement(sql);

        pst.setLong(1, accNo);

        ResultSet rs = pst.executeQuery();

        while(rs.next()){

            String type =
            rs.getString("txn_type");

            double amount =
            rs.getDouble("amount");

            if(type.equals("Deposit"))
                balance += amount;
            else
                balance -= amount;
            }

        }catch(Exception e){
        e.printStackTrace();
        }

    return balance;
    }

    /*public static void main(String args[]) {
        new dashboard(1);
    }*/
}