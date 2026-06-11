import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ministate extends JFrame {

    long accNo;

    JTable table;
    JScrollPane sp;

    public ministate(long accNo) {

        this.accNo = accNo;

        setTitle("Mini Statement");
        setSize(700,400);
        setLocationRelativeTo(null);
        setLayout(null);

        String columns[] = {
            "Transaction ID",
            "Type",
            "Amount",
            "Date"
        };

        DefaultTableModel model =
        new DefaultTableModel(columns,0);

        table = new JTable(model);

        sp = new JScrollPane(table);
        sp.setBounds(20,20,650,300);

        add(sp);

        try {

            Connection con =
            connection.getcon();

            String sql =
            "SELECT txn_id, txn_type, amount, txn_time " +
            "FROM transactions " +
            "WHERE acc_no = ? " +
            "ORDER BY txn_time DESC";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setLong(1, accNo);

            ResultSet rs =
            pst.executeQuery();

            while(rs.next()) {

                model.addRow(new Object[] {

                    rs.getInt("txn_id"),

                    rs.getString("txn_type"),

                    rs.getDouble("amount"),

                    rs.getTimestamp("txn_time")
                });
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
}