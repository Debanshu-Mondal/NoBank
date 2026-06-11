//package bankingsystem.Login;//

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame{
    JButton b1,b2,b3;
    JTextField tj1;
    JPasswordField tj2;
    Login(){
        JFrame f = new JFrame();//         Frame 

        
        ImageIcon i1 =new ImageIcon("Nobanklogo.png");//      Logo
        Image i2 = i1.getImage();
        Image img=i2.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon i3 =new ImageIcon(img);

        JLabel l1 = new JLabel(i3);//     Label
        l1.setBounds(20,10,150,150);

        JLabel l2 = new JLabel("Welcome to No-Bank");
        l2.setFont(new Font("Osward",Font.BOLD,45));
        l2.setBounds(250,10,450,100);

        JLabel acc = new JLabel("Acc No");
        acc.setFont(new Font("Ariel",Font.BOLD,30));
        acc.setBounds(230,145,400,40);

        JLabel pin = new JLabel("Pin No");
        pin.setFont(new Font("Ariel",Font.BOLD,30));
        pin.setBounds(238,195,400,40);

        tj1=new JTextField();// textfield
        tj1.setBounds(380,145,200,35);

        tj2= new JPasswordField();
        tj2.setBounds(380,195,200,35);

        b1=new JButton("Sign in");//    Buttons
        b1.setBackground(new Color(0,0,139));
        b1.setForeground(Color.WHITE);
        b1.setBounds(380,250,90,40);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                long accNo=Long.parseLong(tj1.getText().trim());
                int p=Integer.parseInt(tj2.getText().trim());
                try{
                    Connection con=connection.getcon();
                    String sql="SELECT form_no FROM accounts WHERE acc_no=? AND pin =?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setLong(1,accNo);
                    pst.setInt(2,p);
                    ResultSet rs=pst.executeQuery();
                    if(rs.next()){
                        int formno=rs.getInt("form_no");
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        new dashboard(formno);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid data entered!");

                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        b2=new JButton("Clear");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(0,0,139));
        b2.setBounds(490,250,90,40);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                tj1.setText("");
                tj2.setText("");
            }
        });

        b3=new JButton("Sign up");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(0,0,139));
        b3.setBounds(380,300,200,40);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
            dispose();
            new Signup();
            }
        });


        f.setLayout(null);
        f.setTitle("LOGIN PAGE");
        f.setSize(800,400);
        f.setLocationRelativeTo(null);
        f.add(l1);
        f.add(l2);
        f.add(acc);
        f.add(tj1);
        f.add(pin);
        f.add(tj2);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new Login();
    }
}
