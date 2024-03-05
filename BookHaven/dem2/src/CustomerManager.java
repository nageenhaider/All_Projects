import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import dao.ConnectionProvider;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customers> customers;

   public CustomerManager() {
        this.customers = new ArrayList<>(); // Initialize as an ArrayList
    }

    public void addCustomer(Customers customer) {
        customers.add(customer);
            try (Connection con = ConnectionProvider.getCon()) {
        // Insert customer record
        String insertCustomerSQL = "INSERT INTO login (Username, Password1, email, phone, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement psCustomer = con.prepareStatement(insertCustomerSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            psCustomer.setString(1, customer.getUsername());
            psCustomer.setString(2, customer.getPassword());
            psCustomer.setString(3, customer.getEmail());
            psCustomer.setInt(4, customer.getphone());
            psCustomer.setString(5, customer.getSecurityQuestion());
            psCustomer.setString(6, customer.getSecurityAnswer());

            int rowsAffectedCustomer = psCustomer.executeUpdate();

            if (rowsAffectedCustomer > 0) {
                // Retrieve the auto-generated customer ID
                try (ResultSet generatedKeys = psCustomer.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int customerId = generatedKeys.getInt(1);

                        // Insert cart record using the customer ID
                        String insertCartSQL = "INSERT INTO Cart (cust_id, status1) VALUES (?, ?)";
                        try (PreparedStatement psCart = con.prepareStatement(insertCartSQL)) {
                            psCart.setInt(1, customerId);
                            psCart.setBoolean(2, false); // Set status1 to false, adjust as needed

                            int rowsAffectedCart = psCart.executeUpdate();

                            if (rowsAffectedCart > 0) {
                                // Insert wishlist record using the customer ID
                                String insertWishlistSQL = "INSERT INTO Wishlist (cust_id) VALUES (?)";
                                try (PreparedStatement psWishlist = con.prepareStatement(insertWishlistSQL)) {
                                    psWishlist.setInt(1, customerId);

                                    int rowsAffectedWishlist = psWishlist.executeUpdate();

                                    if (rowsAffectedWishlist > 0) {
                                        // All insertions successful
                                    } else {
                                        // Wishlist insertion failed
                                    }
                                }
                            } else {
                                // Cart insertion failed
                            }
                        }
                    }
                }
            } else {
                // Customer insertion failed
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
    }
    
    
    public int verifyLogin(String username, String password) {
        System.out.println("in verify");
        try (Connection con = ConnectionProvider.getCon()) {
            String query = "SELECT id FROM login WHERE Username=? AND Password1=?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, password);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    } else {
                        return -1; // Return -1 if no matching user is found
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
            return -1; // Return -1 in case of an exception
        }
    }
    
    

    // Additional methods for managing customers (update, delete, etc.)

    public List<Customers> getAllCustomers() {
        return new ArrayList<>(customers); // Return a copy to prevent external modifications
    }
}
