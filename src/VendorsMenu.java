import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VendorsMenu {
    // this will show the list of vendors
    // vendors are stored in the database in the vendors table
    public void show() {
        // create a new frame to store the list of vendors
        JFrame f = new JFrame("Vendors");
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);

        // create a label
        JLabel title = new JLabel("Vendors");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(250, 30);
        f.add(title);

        // create a table to display the list of vendors
        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setSize(400, 200);
        table.setLocation(100, 100);
        f.add(table);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("v_name");
        model.addColumn("contact");
        table.setModel(model);

        // create a scroll pane for the table
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(500, 200);
        scroll.setLocation(50, 100);
        f.add(scroll);

        // connect to the database and get the list of vendors
        try {
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT * FROM vendors");

            // display the list of vendors
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("ID"), rs.getString("v_name"), rs.getString("contact")});
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // create a button to go back to the home page
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(250, 300);
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // go back to the home page
                StudentHome home = new StudentHome();
                home.show();
                f.dispose();
            }
        });
        f.add(back);

        // set the frame visibility
        f.setVisible(true);
    }
}
