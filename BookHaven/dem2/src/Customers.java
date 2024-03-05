
import dao.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nageen
 */
public class Customers {
    private int id;
    private String username;
    private String password;
    private String email;
    private int phone;
    private String securityQuestion;
    private String securityAnswer;
    private Cartclass cart;
    private Wish wishlist;

    public Customers(String username, String password, String email,int phone ,String securityQuestion, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.cart = new Cartclass(id); // Assuming Cartclass has a default constructor
        this.wishlist = new Wish(id); // Assuming Wishlist class has a default constructor
    }

    public Customers(int userid) {
        // Assuming you want to initialize the customer with a specific userid
        this.id = userid;
        // Initialize other properties as needed
        this.username = ""; // Example: Replace with actual value
        this.password = ""; // Example: Replace with actual value
        this.email = "";    // Example: Replace with actual value
        this.phone = 0;      // Example: Replace with actual value
        this.securityQuestion = ""; // Example: Replace with actual value
        this.securityAnswer = "";   // Example: Replace with actual value
        this.cart = new Cartclass(userid); // Assuming you have a Cartclass constructor
        this.wishlist = new Wish(userid); // Assuming you have a Wishlist constructor
    }

    // Getters and setters for all attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public int getphone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Cartclass getCart() {
        return cart;
    }

    public void setCart(Cartclass cart) {
        this.cart = cart;
    }

    public Wish getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wish wishlist) {
        this.wishlist = wishlist;
    }
    
    
    public void addToCart(int itemId,int quantity) {
       
        cart.addToCart(itemId, quantity);
    }
    
      public void addToWishlist(int itemId) {
        wishlist.addToWishlist(itemId);
    }
      
      public int placeOrder(int customer_id, int totalItems, boolean status, int totalAmount, Date orderDate, int cart_id,String Type){
                 Order order = new Order();
               int orderId=  order.placeOrder(customer_id, status, totalItems, totalAmount, orderDate,Type);
                 return orderId;
      
      }
      
      public void addAddress(int order_id, int house, int street, String society,String city, String country){
                 Address add = new Address();
                 add.addAddress(order_id, street, house, society, city, country);
      
      }
      
      public void change_email(String text, int cust_id){
          try {
            Connection con = ConnectionProvider.getCon();
            String query = "UPDATE login SET email = ? WHERE id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, text);
                pstmt.setInt(2, cust_id); // Assuming userId is an integer

                

                int updatedRows = pstmt.executeUpdate();

                if (updatedRows > 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Email updated successfully!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Failed to update email!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
      }
      
      public void changePhone(String text, int userid){
          try {
            Connection con = ConnectionProvider.getCon();
            String query = "UPDATE login SET phone = ? WHERE id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, text);
                pstmt.setInt(2, userid); // Assuming userId is an integer

                

                int updatedRows = pstmt.executeUpdate();

                if (updatedRows > 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Phone number updated successfully!");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Failed to update phone number!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
      }
      
      public Customers fetchdetails(int customer){
           
          Customers c = new Customers(customer);
          
          try {
        Connection con = ConnectionProvider.getCon();
        String query = "SELECT Username,Password1, email, phone, security_question, security_answer FROM login WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, customer);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c.setUsername(rs.getString("Username"));
                    c.setPassword(rs.getString("Password1"));
                    c.setPhone(rs.getInt("phone"));
                    c.setEmail(rs.getString("email"));
                    c.setSecurityQuestion(rs.getString("security_question"));
                    c.setSecurityAnswer(rs.getString("security_answer"));
                }
            }
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
      return c;     
      }
}
