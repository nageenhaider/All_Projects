
import dao.ConnectionProvider;
import java.sql.*;
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
public class Items {
     private int item_id;
    private String description1;
    private int price;
    private int quantity;
    private String type_i;
    
     // Parameterized constructor
    public Items(int item_id, String description1, int price, int quantity, String type_i) {
        this.item_id = item_id;
        this.description1 = description1;
        this.price = price;
        this.quantity = quantity;
        this.type_i = type_i;
    }

    public Items() {
        // Initialize default values or perform any setup if needed
        this.item_id = 0; // Example default value
        this.description1 = "";
        this.price = 0;
        this.quantity = 0;
        this.type_i = "";
    }
    // Getters and setters

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType_i() {
        return type_i;
    }

    public void setType_i(String type_i) {
        this.type_i = type_i;
    }
    
    public Books displayBookbyid(int bookId) {
        Books book = Books.getBookById(bookId);
        

        if (book != null) {
            System.out.println("Book ID: " + book.getId());
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
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }

        return book;
    }
    
    
    public static List<Books> getAllBooks() {
    List<Books> allBooks = new ArrayList<>();
    System.out.println("in func");

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT i.item_id, i.description1, i.price, i.quantity, i.type_i, " +
                     "b.id, b.title, b.author, b.phrase, " +
                     "b.pages, b.publication, " +
                     "b.genre, b.ISBN, b.cover_img, b.small_img " +
                     "FROM items i " +
                     "JOIN books b ON i.item_id = b.id";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

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
                allBooks.add(book);
                
                 System.out.println("Book ID: " + book.getId());
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
                System.out.println("------------------------------");
                
            }

            if (allBooks.isEmpty()) {
                System.out.println("No books found in the database.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return Collections.emptyList(); // Return an empty list in case of an exception
    }

    return allBooks;
}

    public List<Items> getBooksByOrderId(int orderId) {
    List<Items> orderItems = new ArrayList<>();

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT i.item_id, i.description1, i.price, i.quantity, i.type_i " +
                     "FROM items i " +
                     "JOIN order_items oi ON i.item_id = oi.itemid " +
                     "WHERE oi.orderid = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set and add each item to the list
            while (resultSet.next()) {
                Items item = new Items(
                    resultSet.getInt("i.item_id"),
                    resultSet.getString("i.description1"),
                    resultSet.getInt("i.price"),
                    resultSet.getInt("i.quantity"),
                    resultSet.getString("i.type_i")
                );
                orderItems.add(item);

                System.out.println("Item ID: " + item.getItem_id());
                System.out.println("Description: " + item.getDescription1());
                System.out.println("Price: " + item.getPrice());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Type: " + item.getType_i());
                System.out.println("------------------------------");
            }

            if (orderItems.isEmpty()) {
                System.out.println("No items found for order ID: " + orderId);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return Collections.emptyList(); // Return an empty list in case of an exception
    }

    return orderItems;
}

    
    
    
    
    
}
