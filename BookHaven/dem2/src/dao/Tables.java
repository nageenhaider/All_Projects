package dao;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Tables class creates a table in the database.
 * 
 * @author Nageen
 */
public class Tables {
    public static void main(String[] arg) {
        try {
            
            // Get a connection to the 'dem2' database
            Connection con = ConnectionProvider.getCon();
            
            if (con != null) {
                // Create a table in the 'dem2' database
                Statement st = con.createStatement();
                st.executeUpdate("create table login(user_id INT primary key, Username varchar(50), password varchar(50))");
                JOptionPane.showMessageDialog(null, "Table created!");
            } else {
                JOptionPane.showMessageDialog(null, "Connection failed!");
            }
        } catch (HeadlessException | SQLException  e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
}
