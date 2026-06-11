import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class accgen extends JFrame{
    JLabel l1,l2,l3,l4;
    JTextField tf1,tf2,tf3;
    JButton b1,b2;
    int formno;
    public accgen(int formno){
        this.formno=formno;
        System.out.println("Received Form="+formno);
        JFrame f=new JFrame();
        f.setTitle("Account No. Generation Page");
        f.setSize(800,400);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font =new Font("Oswald",Font.BOLD,24);


        ImageIcon i1 =new ImageIcon("Nobanklogo.png");//      Logo
        Image i2 = i1.getImage();
        Image img=i2.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon i3 =new ImageIcon(img);


        l1 = new JLabel(i3);//     Label
        l1.setBounds(20,10,150,150);
        l2 = new JLabel("Account No.");
        l2.setBounds(220,40,150,30);
        l2.setFont(font);
        tf1=new JTextField();
        tf1.setBounds(400,40,150,30);
        l3=new JLabel("Enter Pin");
        l4=new JLabel("Re-Enter Pin");
        l3.setBounds(220,170,150,30);
        l4.setBounds(220,230,150,30);
        l3.setFont(font);
        l4.setFont(font);
        tf2=new JTextField();
        tf2.setBounds(400,170,150,30);
        tf3=new JTextField();
        tf3.setBounds(400,230,150,30);
        

        b1=new JButton("Generate");
        b1.setBounds(400,100,150,20);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                long id = generatedId();
                tf1.setText(String.valueOf(id));
                b1.setEnabled(false);
            }
        });
        b2=new JButton("Submit");
        b2.setBounds(400,300,150,20);
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){


                 // pin checking   //

                String tft1=tf2.getText().trim();
                String tft2=tf3.getText().trim();

                if(tft1.isBlank()||tft2.isBlank()||tft1.isEmpty()&&tft2.isBlank()){
                JOptionPane.showMessageDialog(null,"Please fill all the Fields!");
                return;
                }
        
                if(tft1.isEmpty() || tft2.isEmpty() || tft1.isEmpty() && tft2.isEmpty()){
                JOptionPane.showMessageDialog(null,"Please enter Pin!");
                }
        
                if(!tft1.equals(tft2)){
                JOptionPane.showMessageDialog(null,"The Pins are not Matching!");
                return;
                }
                else{
                JOptionPane.showMessageDialog(null,"The Pin is Accepted");
                }
                long acc_no=Long.parseLong(tf1.getText());
                int pin=Integer.parseInt(tft1);
                try {
                    Connection con=connection.getcon();
                    String sql="INSERT INTO accounts (acc_no,form_no,pin) VALUES(?,?,?)";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setLong(1,acc_no);
                    pst.setInt(2,formno);
                    pst.setInt(3,pin);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Saved");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new Login();
                dispose();
            }

        });


        f.setVisible(true);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(b1);
        f.add(tf1);
        f.add(tf2);
        f.add(tf3);
        f.add(b2);

    }

    public static long generatedId(){
        return System.currentTimeMillis();
    }

   /* public static void main(String args[]){
        new accgen(formno);
    }*/
    
}