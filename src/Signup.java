import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Signup extends JFrame {
    JComboBox c1,c2,c3;
    JTextField fn,ln,dob,adrs,an,pn,mob,nominee,email,occ;
    JRadioButton r1,r2,r3,r4;
    JButton b1;

    Signup() {

        setTitle("Sign-up Page");
        setSize(800, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Watermark panel
        JPanel p = new JPanel() {

            ImageIcon icon = new ImageIcon("Nobanklogo.png");
            Image img = icon.getImage();

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                // Watermark transparency
                g2d.setComposite(
                    AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, 0.09f
                    )
                );

                // Watermark image
                g2d.drawImage(img, 50, 20, 640, 680, this);

                // Restore opacity
                g2d.setComposite(
                    AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, 1.0f
                    )
                );
            }
        };

        p.setLayout(null);
        p.setBackground(Color.WHITE);

        Font f = new Font("Osward", Font.BOLD, 18);

        // Labels

        JLabel l1 = new JLabel("Honorific");
        l1.setBounds(40,10,120,30);
        l1.setFont(f);

        JLabel l2 = new JLabel("First Name");
        l2.setBounds(40,50,120,30);
        l2.setFont(f);

        JLabel l3 = new JLabel("Last Name");
        l3.setBounds(40,90,120,30);
        l3.setFont(f);

        JLabel l4 = new JLabel("Date of Birth");
        l4.setBounds(40,130,150,30);
        l4.setFont(f);

        JLabel l5 = new JLabel("Gender");
        l5.setBounds(40,170,120,30);
        l5.setFont(f);

        JLabel l6 = new JLabel("Address");
        l6.setBounds(40,210,150,30);
        l6.setFont(f);

        JLabel l7 = new JLabel("Aadhaar No");
        l7.setBounds(40,250,120,30);
        l7.setFont(f);

        JLabel l8 = new JLabel("Pan No");
        l8.setBounds(40,290,120,30);
        l8.setFont(f);

        JLabel l9=new JLabel("Marital Status");
        l9.setBounds(40,330,120,30);
        l9.setFont(f);

        JLabel l10=new JLabel("Nominee");
        l10.setBounds(40,370,120,30);
        l10.setFont(f); 

        JLabel l11 = new JLabel("Mobile No");
        l11.setBounds(40,410,120,30);
        l11.setFont(f);

        JLabel l12 = new JLabel("Email ID");
        l12.setBounds(40,450,120,30);
        l12.setFont(f);

        JLabel l13 = new JLabel("Occupation");
        l13.setBounds(40,490,120,30);
        l13.setFont(f);

        JLabel l14 = new JLabel("Account Type");
        l14.setBounds(40,530,150,30);
        l14.setFont(f);

        JLabel l15 = new JLabel("Existing Acc");
        l15.setBounds(40,570,120,30);
        l15.setFont(f);
        
        JLabel l16=new JLabel("Existing Acc No");
        l16.setBounds(40,610,160,30);
        l16.setFont(f);

        JLabel eaccno=new JLabel();
        eaccno.setBounds(220,610,250,30);
        eaccno.setFont(f);

        l16.setVisible(false);
        eaccno.setVisible(false);
        
        // Dropdown

        String hnr[] = {"Select Honorific",
            "Shri",
            "Shmt",
            "Kumar",
            "Kumari",
            "Mister",
            "Miss"
        };

        c1 = new JComboBox(hnr);
        c1.setBounds(200,10,150,30);

        // Text fields

        fn = new JTextField();
        fn.setBounds(200,50,250,30);

        ln = new JTextField();
        ln.setBounds(200,90,250,30);

        // DOB Placeholder Field

        dob = new JTextField("YYYY-MM-DD");

        dob.setBounds(200,130,250,30);

        dob.setForeground(Color.GRAY);

        dob.addFocusListener(new FocusAdapter() {

            public void focusGained(FocusEvent e) {

                if(dob.getText().equals("YYYY-MM-DD")) {

                    dob.setText("");
                    dob.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {

                if(dob.getText().equals("")) {

                    dob.setText("DD/MM/YYYY");
                    dob.setForeground(Color.GRAY);
                }
            }
        });

        String gender[]={"Select Gender","Male","Female","Gay","Lesbian","Bisexual","Transgender","Other"};
        c2=new JComboBox<>(gender);
        c2.setBounds(200,170,250,30);

        adrs = new JTextField();
        adrs.setBounds(200,210,250,30);

        an = new JTextField();
        an.setBounds(200,250,250,30);

        pn = new JTextField();
        pn.setBounds(200,290,250,30); 

        r1=new JRadioButton("Married");
        r1.setBounds(220,335,90,20);
        r2=new JRadioButton("Bachelor");
        r2.setBounds(320,335,90,20);
        r1.setBackground(Color.white);
        r1.setOpaque(true);
        r2.setBackground(Color.white);
        r2.setOpaque(true);
        ButtonGroup bg1 =new ButtonGroup();
        bg1.add(r1);
        bg1.add(r2);

        r3=new JRadioButton("Yes");
        r4=new JRadioButton("No");
        r3.setBounds(220,575,80,20);
        r4.setBounds(320,575,80,20);
        ButtonGroup bg2 =new ButtonGroup();
        bg2.add(r3);
        bg2.add(r4);


        r3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String accno=JOptionPane.showInputDialog(Signup.this,"Enter the EXisting Acc No. :");
                if(accno !=null && !accno.trim().isEmpty()){
                    eaccno.setText(accno);
                    l16.setVisible(true);
                    eaccno.setVisible(true);
                }
            } 
        });

        r4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                l16.setVisible(false);
                    eaccno.setVisible(false);
            }
        });

        mob=new JTextField();
        mob.setBounds(200,410,250,30);
        email=new JTextField();
        email.setBounds(200,450,250,30);
        occ=new JTextField();
        occ.setBounds(200,490,250,30);
 
        String acctype[]={"Select Acc Type","Savings","Current"};
        c3=new JComboBox<>(acctype);
        c3.setBounds(200,530,250,30);

        nominee=new JTextField();
        nominee.setBounds(200,370,250,30);

        b1=new JButton("Next");
        b1.setBounds(300,650,150,40);
        b1.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {

        // Honorific
        if(c1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select an Honorific");
            return;
        }

        // First Name
        if(fn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter First Name");
            return;
        }

        // Last Name
        if(ln.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Last Name");
            return;
        }

        // DOB
        if(dob.getText().trim().isEmpty()
                || dob.getText().equals("DD/MM/YYYY")) {

            JOptionPane.showMessageDialog(null,
                    "Please enter Date of Birth");
            return;
        }

        // Gender
        if(c2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select Gender");
            return;
        }

        // Address
        if(adrs.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Address");
            return;
        }

        // Aadhaar
        if(an.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Aadhaar Number");
            return;
        }

        if(!an.getText().matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null,
                    "Aadhaar Number must contain 12 digits");
            return;
        }

        // PAN
        if(pn.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter PAN Number");
            return;
        }

        // Marital Status
        if(!r1.isSelected() && !r2.isSelected()) {
            JOptionPane.showMessageDialog(null,
                    "Please select Marital Status");
            return;
        }

        // Nominee
        if(nominee.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Nominee Name");
            return;
        }

        // Mobile
        if(mob.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Mobile Number");
            return;
        }

        if(!mob.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null,
                    "Mobile Number must contain 10 digits");
            return;
        }

        // Email
        if(email.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Email ID");
            return;
        }

        // Occupation
        if(occ.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter Occupation");
            return;
        }

        // Account Type
        if(c3.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select Account Type");
            return;
        }

        // Existing Account
        if(!r3.isSelected() && !r4.isSelected()) {
            JOptionPane.showMessageDialog(null,
                    "Please select Existing Account option");
            return;
        }

        // Existing Account Number
        if(r3.isSelected()
                && eaccno.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "Please enter Existing Account Number");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "All validations passed successfully!");


        String honorific = c1.getSelectedItem().toString();
        String fname = fn.getText();
        String lname = ln.getText();
        String dobText = dob.getText();
        String gender = c2.getSelectedItem().toString();
        String address = adrs.getText();
        String aadhaar = an.getText();
        String pan = pn.getText();

        String maritalStatus = "";
                if(r1.isSelected())
                    maritalStatus = "Married";
                else if(r2.isSelected())
                    maritalStatus = "Bachelor";

        String nomineeName = nominee.getText();
        String mobile = mob.getText();
        String emailId = email.getText();
        String occupation = occ.getText();
        String accType = c3.getSelectedItem().toString();

        boolean existingAcc = r3.isSelected();

        try{
            Connection con=connection.getcon();
            String sql = "INSERT INTO users "
           + "(honorific, first_name, last_name, dob, address, gender, "
           + "aadhaar, pan, marital_status, nominee, mobile_no, "
           + "email_id, occupation, acc_type, existing_acc) "
           + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

           PreparedStatement pst =con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

           pst.setString(1, honorific);
            pst.setString(2, fname);
            pst.setString(3, lname);
            pst.setDate(4, java.sql.Date.valueOf(dobText));

            pst.setString(5, address);
            pst.setString(6, gender);

            pst.setString(7, aadhaar);
            pst.setString(8, pan);

            pst.setString(9, maritalStatus);
            pst.setString(10, nomineeName);

            pst.setString(11, mobile);
            pst.setString(12, emailId);

            pst.setString(13, occupation);
            pst.setString(14, accType);

            pst.setBoolean(15, existingAcc);

            pst.executeUpdate();
            ResultSet rs =pst.getGeneratedKeys();
            int formno=0;
            if(rs.next()){
                formno=rs.getInt(1);
            }
            System.out.println(""+formno);
            JOptionPane.showMessageDialog(null,"Data Saved");

             new accgen(formno);
                dispose();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
               
    }
});

        // Add components

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(l8);
        p.add(l11);
        p.add(l12);
        p.add(l13);
        p.add(l14);
        p.add(l15);
        p.add(l16);
        p.add(eaccno);

        p.add(l9);
        p.add(r1);
        p.add(r2);

        p.add(l10);
        p.add(r3);
        p.add(r4);

        p.add(c1);

        p.add(fn);
        p.add(ln);
        p.add(dob);
        p.add(c2);
        p.add(adrs);
        p.add(an);
        p.add(pn);
        p.add(mob);
        p.add(email);
        p.add(occ);
        p.add(c3);
        p.add(nominee);

        p.add(b1);

        add(p);

        setVisible(true);
    }

    public static void main(String args[]) {

        new Signup();
    }
}