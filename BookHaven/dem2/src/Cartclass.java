
import dao.ConnectionProvider;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nageen
 */
public class Cartclass {
    private int customerId;
    private  ArrayList<Integer> bookIds;

    public Cartclass(int customerId) {
       this.customerId = customerId;
        this.bookIds = new ArrayList<>();
    }
    public Cartclass() {
       this.customerId=-1;
        this.bookIds = new ArrayList<>();
    }

    public void addBookId(int bookId) {
        bookIds.add(bookId);
    }

   public ArrayList<Integer> getBookIds() {
        return bookIds;
    }

    public int getNumberOfBooks() {
        return bookIds.size();
    }

    public int getCustomerId() {
        return customerId;
    }

    void addToCart(int itemId, int quantity) {
        bookIds.add(itemId);
        System.out.println("Cust id in cart class is "+customerId);
        int cartId = getcartId(customerId);
         System.out.println("Cart id in cart class is "+cartId);
         
         
          try (Connection con = ConnectionProvider.getCon()) {
            String sql = "INSERT INTO cart_items (cart_id, item_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, cartId);
                preparedStatement.setInt(2, itemId);
                preparedStatement.setInt(3, quantity);

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

    public List<Books> getCartItems() {
      List<Books> CartItems = new ArrayList<>();
      System.out.println("get cart items cust id is "+customerId);
     int cartId = getcartId(customerId);
       System.out.println("get cart items cart_id is "+cartId);
     try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT b.*, i.* " +
             "FROM cart_items ci " +
             "JOIN books b ON ci.item_id = b.id " +
             "JOIN items i ON i.item_id = b.id " +
             "WHERE ci.cart_id = ? " +
             "AND EXISTS (SELECT 1 FROM Cart c WHERE c.cart_id = ci.cart_id AND c.cust_id = ?)";


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
                CartItems.add(book);
                
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

            if (CartItems.isEmpty()) {
                System.out.println("No books found in the database.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return Collections.emptyList(); // Return an empty list in case of an exception
    }
      
      return CartItems;
    
    }
    
    public int getcartId(int customer) {
    int cartId = -1; // Default value if not found

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT cart_id FROM Cart WHERE cust_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cartId = resultSet.getInt("cart_id");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return cartId;
   }
    
    
    
    int getQuantity(int cartId, int itemId) {
    int quantity = 0;

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND item_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, itemId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    quantity = resultSet.getInt("quantity");
                } else {
                    System.out.println("Item not found in cart_items.");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return quantity;
}
  
    void removeitem( int cartid, int itemid) {
       try (Connection con = ConnectionProvider.getCon()) {
            String sql = "DELETE FROM cart_items WHERE cart_id = ? AND item_id = ?";
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
    
    void copyCartToOrder(int cartId, int orderId) {
    try (Connection con = ConnectionProvider.getCon()) {
        // First, retrieve the items from the cart_items table
        String selectSql = "SELECT item_id, quantity FROM cart_items WHERE cart_id = ?";
        try (PreparedStatement selectStatement = con.prepareStatement(selectSql)) {
            selectStatement.setInt(1, cartId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("item_id");
                    int quantity = resultSet.getInt("quantity");

                    // Then, insert the items into the order_items table
                    String insertSql = "INSERT INTO order_items (orderid, itemid, quantity) VALUES (?, ?, ?)";
                    try (PreparedStatement insertStatement = con.prepareStatement(insertSql)) {
                        insertStatement.setInt(1, orderId);
                        insertStatement.setInt(2, itemId);
                        insertStatement.setInt(3, quantity);

                        int rowsAffected = insertStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Item copied to order_items successfully.");
                        } else {
                            System.out.println("Failed to copy item to order_items.");
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    void removeItemsFromCart(int cartid) {
    try (Connection con = ConnectionProvider.getCon()) {
        // Fetch the quantity of items in the cart
        String fetchQuantitySql = "SELECT item_id, quantity FROM cart_items WHERE cart_id = ?";
        try (PreparedStatement fetchQuantityStatement = con.prepareStatement(fetchQuantitySql)) {
            fetchQuantityStatement.setInt(1, cartid);

            try (ResultSet resultSet = fetchQuantityStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemid = resultSet.getInt("item_id");
                    int removedQuantity = resultSet.getInt("quantity");

                    // Update the quantity in the items table
                    String updateQuantitySql = "UPDATE items SET quantity = quantity - ? WHERE item_id = ?";
                    try (PreparedStatement updateQuantityStatement = con.prepareStatement(updateQuantitySql)) {
                        updateQuantityStatement.setInt(1, removedQuantity);
                        updateQuantityStatement.setInt(2, itemid);

                        int updateRows = updateQuantityStatement.executeUpdate();
                        if (updateRows > 0) {
                            System.out.println("Item quantity updated in items table for itemid: " + itemid);
                        } else {
                            System.out.println("Failed to update item quantity in items table for itemid: " + itemid);
                        }
                    }
                }
            }
        }

        // Remove items from the cart_items table
        String deleteSql = "DELETE FROM cart_items WHERE cart_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, cartid);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Items removed from cart_items successfully.");
            } else {
                System.out.println("No items found in cart_items for the specified cartid.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
}

    
    public int getTotalItemsInCart(int cartid) {
    int totalItems = 0;

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT SUM(quantity) AS total_items FROM cart_items WHERE cart_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalItems = resultSet.getInt("total_items");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return totalItems;
}

    
    
    
    public int getTotalPriceInCart(int cartid) {
    int totalPrice = 0;

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT SUM(i.price * ci.quantity) AS total_price FROM cart_items ci " +
                     "JOIN items i ON ci.item_id = i.item_id " +
                     "WHERE ci.cart_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, cartid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalPrice = resultSet.getInt("total_price");
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return totalPrice;
}

}
