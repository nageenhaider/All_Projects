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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Order {
    
      private int orderid;
    private int customerid;
    private Boolean status;
    private int num_of_items;
    private int total_amount;
    private Date order_date;
    private Date receive_date;
    
    public Order( int customerid, Boolean status, int num_of_items, int total_amount, Date order_date, Date receive_date) {
       this.orderid=0;
        this.customerid = customerid;
        this.status = status;
        this.num_of_items = num_of_items;
        this.total_amount = total_amount;
        this.order_date = order_date;
        this.receive_date = receive_date;
    }
    
     public Order() {
        this.customerid = -1; // Set a dummy value for customerId
        this.status = false; // Set a dummy value for status
        this.num_of_items = 0; // Set a dummy value for numOfItems
        this.total_amount = 0; // Set a dummy value for totalAmount
        this.order_date = new Date(); // Set a dummy value for orderDate
        this.receive_date = new Date(); // Set a dummy value for receiveDate
    }

     public int getorderid() {
        return orderid;
    }

    public void setorderid(int orderid) {
        this.orderid = orderid;
    }
     
    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getNum_of_items() {
        return num_of_items;
    }

    public void setNum_of_items(int num_of_items) {
        this.num_of_items = num_of_items;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

       public int placeOrder(int customerId, Boolean status, int numOfItems, int totalAmount, Date orderDate, String orderType) {
        int orderId = -1;

        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "INSERT INTO orders (customerid, status, num_of_items, total_amount, order_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, customerId);
                preparedStatement.setBoolean(2, status);
                preparedStatement.setInt(3, numOfItems);
                preparedStatement.setInt(4, totalAmount);
                preparedStatement.setDate(5, new java.sql.Date(orderDate.getTime()));

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Order placed successfully.");

                    // If needed, retrieve the generated order ID
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            orderId = generatedKeys.getInt(1);
                            
                            // Call makePayment method from PaymentManager
                            Payment1 pay = new Payment1();
                            pay.makePayment(orderId, totalAmount, orderType);
                        }
                    }
                } else {
                    System.out.println("Failed to place order.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return orderId;
    }
     public List<Integer> fetchOrders() {
        List<Integer> orderIds = new ArrayList<>();
        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "SELECT orderid FROM orders";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("orderid");
                        orderIds.add(orderId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return orderIds;
    }
     
     public List<Integer> fetchCustomerOrders(int customerId) {
        List<Integer> customerOrderIds = new ArrayList<>();
        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "SELECT orderid FROM orders WHERE customerid = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderId = resultSet.getInt("orderid");
                        customerOrderIds.add(orderId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return customerOrderIds;
    }
     
     public List<Order> CustomerOrders(int customerId) {
    List<Order> customerOrders = new ArrayList<>();
    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT * FROM orders WHERE customerid = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("orderid");
                    boolean status = resultSet.getBoolean("status");
                    int numOfItems = resultSet.getInt("num_of_items");
                    int totalAmount = resultSet.getInt("total_amount");
                    Date orderDate = resultSet.getDate("order_date");
                    Date receiveDate = resultSet.getDate("receive_date");

                    Order order = new Order(customerId, status, numOfItems, totalAmount, orderDate, receiveDate);
                    order.setorderid(orderId);
                    customerOrders.add(order);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
    return customerOrders;
}
public Order getCustomerSingleOrder(int customerId, int orderId) {
    Order order = null;

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT * FROM orders WHERE customerid = ? AND orderid = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean status1 = resultSet.getBoolean("status");
                    int numOfItems = resultSet.getInt("num_of_items");
                    int totalAmount = resultSet.getInt("total_amount");
                    Date orderDate = resultSet.getDate("order_date");
                    Date receiveDate = resultSet.getDate("receive_date");

                    order = new Order(customerId, status1, numOfItems, totalAmount, orderDate, receiveDate);
                    order.setorderid(orderId);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return order;
}
     
     
     public void setReceiveDate(Date receiveDate, int orderId) {
        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "UPDATE orders SET receive_date = ? WHERE orderid = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setDate(1, new java.sql.Date(receiveDate.getTime()));
                preparedStatement.setInt(2, orderId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Receive date updated successfully.");
                } else {
                    System.out.println("Failed to update receive date.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
     
     
      public void changeStatus(int orderId, String status) {
        try (Connection con = ConnectionProvider.getCon()) {
            String sql = "UPDATE orders SET status = ? WHERE orderid = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, orderId);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Status updated successfully.");
                } else {
                    System.out.println("Failed to update status.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
     
    
}
