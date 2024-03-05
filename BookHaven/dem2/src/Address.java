/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nageen
 */
import dao.ConnectionProvider;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Address {
    private int orderId;
    private int street;
    private int house;
    private String society;
    private String city;
    private String country;

    // Constructors, getters, and setters
        public Address( int orderId, int street, int house, String society, String city, String country) {
        
        this.orderId = orderId;
        this.street = street;
        this.house = house;
        this.society = society;
        this.city = city;
        this.country = country;
    }
        
        public Address() {
        
        this.orderId = -1;
        this.street = -1;
        this.house = -1;
        this.society = "";
        this.city = "";
        this.country = "";
    }
    // Getters and Setters
   

    public int getCustomerid() {
        return orderId;
    }

    public void setCustomerid(int orderId1) {
        this.orderId= orderId1;
    }

    public int getStreet() {
        return street;
    }

    public void setStreet(int street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void addAddress(int orderId, int street, int house, String society, String city, String country) {
        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "INSERT INTO address (orderid, street, house, society, city, country) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, street);
                preparedStatement.setInt(3, house);
                preparedStatement.setString(4, society);
                preparedStatement.setString(5, city);
                preparedStatement.setString(6, country);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Address added successfully.");
                } else {
                    System.out.println("Failed to add address.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    public int fetchAddressId(int orderId) {
        int addressId = -1;

        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "SELECT addid FROM address WHERE orderid = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        addressId = resultSet.getInt("addid");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return addressId;
    }

     public Address fetchAddress(int orderId) {
        Address address = null;

        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "SELECT * FROM address WHERE orderid = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int addressId = resultSet.getInt("addid");
                        int street1 = resultSet.getInt("street");
                        int house1 = resultSet.getInt("house");
                        String society1 = resultSet.getString("society");
                        String city1 = resultSet.getString("city");
                        String country1 = resultSet.getString("country");

                        // Create an Address object
                        address = new Address(orderId, street1, house1, society1, city1, country1);

                        // Print the address details
                        System.out.println("Address ID: " + addressId);
                        System.out.println("Street: " + street1);
                        System.out.println("House: " + house1);
                        System.out.println("Society: " + society1);
                        System.out.println("City: " + city1);
                        System.out.println("Country: " + country1);
                        // Add more fields as needed
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return address;
    }
}