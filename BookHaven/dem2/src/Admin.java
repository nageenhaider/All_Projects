
import dao.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nageen
 */
public class Admin {
    private int adminid;
    
   public Admin(){
    adminid=0;
    }
   
   public void AddBook(String title, String author, String phrase, int pages, String publicationDate, String Description,
                        String genre, int isbn, String coverImg, String smallImg,int price, int quan){
   
        try {
        Connection con = ConnectionProvider.getCon(); // Obtain a database connection
          // Insert into items table
            String itemsSql = "INSERT INTO items (description1, price, quantity, type_i) VALUES (?, ?, ?, ?)";
            try (PreparedStatement itemsStatement = con.prepareStatement(itemsSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                itemsStatement.setString(1, Description); 
                itemsStatement.setInt(2, price);
                itemsStatement.setInt(3, quan); // Assuming 'quantity' is an integer, use your desired default value
                itemsStatement.setString(4, "Book"); // Assuming 'type_i' is a constant for books, adjust as needed

                itemsStatement.executeUpdate();

                // Retrieve the generated item_id
                try (ResultSet generatedKeys = itemsStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int itemId = generatedKeys.getInt(1);

                        // Insert into books table using the generated item_id
                        String booksSql = "INSERT INTO books (id, title, author, phrase, pages, publication, genre, ISBN, cover_img, small_img) " +
                                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement booksStatement = con.prepareStatement(booksSql)) {
                            booksStatement.setInt(1, itemId);
                            booksStatement.setString(2, title);
                            booksStatement.setString(3, author);
                            booksStatement.setString(4, phrase);
                            booksStatement.setInt(5, pages);
                            booksStatement.setString(6, publicationDate);
                            booksStatement.setString(7, genre);
                            booksStatement.setInt(8, isbn);
                            booksStatement.setString(9, coverImg);
                            booksStatement.setString(10, smallImg);

                            // Execute the insert statement for books table
                            booksStatement.executeUpdate();
                        }
                    } else {
                        // Handle the case where no keys were generated
                        System.err.println("Failed to retrieve generated item_id for book insertion");
                    }
                }
            }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
   
   
   
   }
   public void addBookmark(String title, String theme, String coverImg, String smallImg, int price, int quan, String Description) {
    try {
        Connection con = ConnectionProvider.getCon(); // Obtain a database connection

        // Insert into items table
        String itemsSql = "INSERT INTO items (description1, price, quantity, type_i) VALUES (?, ?, ?, ?)";
        try (PreparedStatement itemsStatement = con.prepareStatement(itemsSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            itemsStatement.setString(1, Description);
            itemsStatement.setInt(2, price);
            itemsStatement.setInt(3, quan); // Assuming 'quantity' is an integer, use your desired default value
            itemsStatement.setString(4, "Bookmark"); // Assuming 'type_i' is a constant for bookmarks, adjust as needed

            itemsStatement.executeUpdate();

            // Retrieve the generated item_id
            try (ResultSet generatedKeys = itemsStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int itemId = generatedKeys.getInt(1);

                    // Insert into bookmarks table using the generated item_id
                    String bookmarksSql = "INSERT INTO bookmarks (id, title, theme, cover_img, small_img) " +
                                          "VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement bookmarksStatement = con.prepareStatement(bookmarksSql)) {
                        bookmarksStatement.setInt(1, itemId);
                        bookmarksStatement.setString(2, title);
                        bookmarksStatement.setString(3, theme);
                        bookmarksStatement.setString(4, coverImg);
                        bookmarksStatement.setString(5, smallImg);

                        // Execute the insert statement for bookmarks table
                        bookmarksStatement.executeUpdate();
                    }
                } else {
                    // Handle the case where no keys were generated
                    System.err.println("Failed to retrieve generated item_id for bookmark insertion");
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
} 
   public void DeleteBook(String textFieldValue){
   Connection con = ConnectionProvider.getCon();
               int pkValue;
        try {
            pkValue = Integer.parseInt(textFieldValue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        try  {
            // Prepare the SQL statement to delete the item
            String sql = "DELETE FROM items WHERE item_id = ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, pkValue);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                      JOptionPane.showMessageDialog(null,  "Bookmark deleted successfully");
                    System.out.println("Item with items_pk " + pkValue + " deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, pkValue +  " not found.");
                    System.out.println("Item with items_pk " + pkValue + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while deleting the item.");
        }
   
   }
   
   public void deleteBookmark(String textFieldValue){
         Connection con = ConnectionProvider.getCon(); // Obtain a database connection
       int pkValue;
        try {
            pkValue = Integer.parseInt(textFieldValue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        try  {
            // Prepare the SQL statement to delete the item
            String sql = "DELETE FROM items WHERE items_id = ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, pkValue);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null,  "Book deleted successfully");
                    System.out.println("Item with items_pk " + pkValue + " deleted successfully.");
                } else {
                      JOptionPane.showMessageDialog(null,  "Book not found");

                    System.out.println("Item with items_pk " + pkValue + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while deleting the item.");
        }
       
       
   }
   
    public void updateItemPrice(int itemId, int newPrice) {
        String sql = "UPDATE items SET price = ? WHERE item_id = ?";
        
        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, newPrice);
            statement.setInt(2, itemId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void updateItemQuantity(int itemId, int newQuantity) {
        String sql = "UPDATE items SET quantity = ? WHERE item_id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, newQuantity);
            statement.setInt(2, itemId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void updateBookmarkUrl(int itemId, String newImageUrl) {
        String sql = "UPDATE bookmarks SET cover_img = ? WHERE id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, newImageUrl);
            statement.setInt(2, itemId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void updateBookImageUrl(int itemId, String newImageUrl) {
        String sql = "UPDATE books SET cover_img = ? WHERE id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, newImageUrl);
            statement.setInt(2, itemId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    
    
    
    
    public int getItemPrice(int itemId) {
        String sql = "SELECT i.price FROM items as i WHERE i.item_id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, itemId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("i.price");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if no record is found or handle it as appropriate
    }
    
    public int getItemQuantity(int itemId) {
        String sql = "SELECT quantity FROM items WHERE item_id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, itemId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("quantity");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if no record is found or handle it as appropriate
    }
    
     public String getBookURL(int itemId) {
        String sql = "SELECT i.cover_img FROM books as i WHERE i.id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, itemId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("cover_img");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "no image"; // Return -1 if no record is found or handle it as appropriate
    }
     
     public String getBookmarkURL(int itemId) {
        String sql = "SELECT cover_img FROM bookmarks  WHERE id = ?";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, itemId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("cover_img");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "no image"; // Return -1 if no record is found or handle it as appropriate
    }
    
}
