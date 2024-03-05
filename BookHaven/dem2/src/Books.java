
import com.mysql.cj.jdbc.Blob;
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
public class Books {
     private int id;
    private String title;
    private String author;
    private String phrase;
    private String description1;
    private int price;
    private int quantity;
    private int pages;
    private Date publication;
    private String genre;
    private int ISBN;
    private String coverImg;
    private String  smallImg;
    
     public Books(int id, String title, String author, String phrase, String description1, int price,int quantity, int pages, Date publication, String genre, int ISBN, String  coverImg, String  smallImg) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.phrase = phrase;
        this.description1 = description1;
        this.price = price;
        this.quantity = quantity;
        this.pages = pages;
        this.publication = publication;
        this.genre = genre;
        this.ISBN = ISBN;
        this.coverImg = coverImg;
        this.smallImg = smallImg;
    }

    // Getter methods for the new attributes (you can generate them using your IDE)
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getDescription1() {
        return description1;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPages() {
        return pages;
    }

    public Date getPublication() {
        return publication;
    }

    public String getGenre() {
        return genre;
    }

    public int getISBN() {
        return ISBN;
    }

    public String  getCoverImg() {
        return coverImg;
    }

    public String  getSmallImg() {
        return smallImg;
    }

    public static Books getBookById(int bookId) {
    Books book = null;
    System.out.println("in func");

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT i.item_id, i.description1, i.price, i.quantity, i.type_i, " +
                     "b.id, b.title, b.author, b.phrase, " +
                     "b.pages, b.publication, " +
                     "b.genre, b.ISBN, b.cover_img, b.small_img " +
                     "FROM items i " +
                     "JOIN books b ON i.item_id = b.id " +
                     "WHERE i.item_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result is found
            if (resultSet.next()) {
                book = new Books(
        resultSet.getInt("b.id"),              // Assuming "b.id" is the correct alias
        resultSet.getString("b.title"),         // Assuming "b.title" is the correct alias
        resultSet.getString("b.author"),
        resultSet.getString("b.phrase"),
        resultSet.getString("i.description1"),  // Assuming "i.description1" is the correct alias
        resultSet.getInt("i.price"),            // Assuming "i.price" is the correct alias
        resultSet.getInt("i.quantity"),
        resultSet.getInt("b.pages"),
        resultSet.getDate("b.publication"),
        resultSet.getString("b.genre"),
        resultSet.getInt("b.ISBN"),
        resultSet.getString("b.cover_img"),
        resultSet.getString("b.small_img")
);
            } else {
                System.out.println("No book found with ID: " + bookId);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }

    return book;
}

    
}
