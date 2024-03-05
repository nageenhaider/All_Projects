import dao.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wish {
    private int customerId;
    private List<Integer> itemIds;

    public Wish(int customerId) {
        this.customerId = customerId;
        this.itemIds = new ArrayList<>();
    }

    public void addItemId(int itemId) {
        itemIds.add(itemId);
    }

    public void removeItemId(int itemId) {
        itemIds.remove(Integer.valueOf(itemId)); // Removes the first occurrence of the specified element
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public int getNumberOfItems() {
        return itemIds.size();
    }

    public int getCustomerId() {
        return customerId;
    }
    void addToWishlist(int itemId) {
        itemIds.add(itemId);
        System.out.println("Cust id in Wish class is "+customerId);
        int wishId = getwishId(customerId);
         System.out.println("Wish id in Wish class is "+wishId);
         
         
          try (Connection con = ConnectionProvider.getCon()) {
            String sql = "INSERT INTO wish_items (wish_id, item_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, wishId);
                preparedStatement.setInt(2, itemId);

                try {
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Item added to cart_items successfully.");
                    } else {
                        System.out.println("Failed to add item to cart_items.");
                    }
                } catch (SQLIntegrityConstraintViolationException e) {
                    // Handle duplicate key error (or other integrity constraint violation)
                    System.out.println("Duplicate key error: Item already exists in cart.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    
    public int getwishId(int customer) {
    int cartId = -1; // Default value if not found

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT wish_id FROM Wishlist WHERE cust_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cartId = resultSet.getInt("wish_id");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return cartId;
   }
    public List<Books> getWishItems() {
      List<Books> WishItems = new ArrayList<>();
      System.out.println("get wish items cust id is "+customerId);
     int cartId = getwishId(customerId);
       System.out.println("get cart items cart_id is "+cartId);
     try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT b.*, i.* " +
             "FROM wish_items ci " +
             "JOIN books b ON ci.item_id = b.id " +
             "JOIN items i ON i.item_id = b.id " +
             "WHERE ci.wish_id = ? " +
             "AND EXISTS (SELECT 1 FROM Wishlist c WHERE c.wish_id = ci.wish_id AND c.cust_id = ?)";


        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, cartId);
                preparedStatement.setInt(2, customerId);


            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set and add each book to the list
            while (resultSet.next()) {
                Books book = new Books(
                    resultSet.getInt("b.id"),
                    resultSet.getString("b.title"),
                    resultSet.getString("b.author"),
                    resultSet.getString("b.phrase"),
                    resultSet.getString("i.description1"),
                    resultSet.getInt("i.price"),
                    resultSet.getInt("i.quantity"),
                    resultSet.getInt("b.pages"),
                    resultSet.getDate("b.publication"),
                    resultSet.getString("b.genre"),
                    resultSet.getInt("b.ISBN"),
                    resultSet.getString("b.cover_img"),
                    resultSet.getString("b.small_img")
                );
                WishItems.add(book);
                
                 System.out.println("Cart Book ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Description: " + book.getDescription1());
                System.out.println("Price: " + book.getPrice());
                System.out.println("Quantity: " + book.getQuantity());
                System.out.println("Pages: " + book.getPages());
                System.out.println("Publication Date: " + book.getPublication());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("ISBN: " + book.getISBN());
                System.out.println("Cover Image: " + book.getCoverImg());
                System.out.println("Small Image: " + book.getSmallImg());
                System.out.println("CAAARRTTT------------------------------");
                
            }

            if (WishItems.isEmpty()) {
                System.out.println("No books found in the database.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return Collections.emptyList(); // Return an empty list in case of an exception
    }
      
      return WishItems;
    
    }
    
    
    
    
    void removeitem( int cartid, int itemid) {
       try (Connection con = ConnectionProvider.getCon()) {
            String sql = "DELETE FROM wish_items WHERE wish_id = ? AND item_id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, cartid);
                preparedStatement.setInt(2, itemid);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item removed from cart_items successfully.");
                } else {
                    System.out.println("Item not found in cart_items.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    
}
