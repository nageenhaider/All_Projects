
import dao.ConnectionProvider;
import java.sql.*;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nageen
 */
public class Payment1 {
   
    private int orderId;
    private int totalAmount;
    private String paymentType;

    public Payment1(int orderId, int totalAmount, String paymentType) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
    }
     public Payment1() {
        this.orderId = -1;
        this.totalAmount = -1;
        this.paymentType = "";
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public void makePayment(int orderId, int totalAmount, String paymentType) {
    Payment1 payment = new Payment1(orderId, totalAmount, paymentType);

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "INSERT INTO payments (orderid, total_amount, type) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setInt(2, payment.getTotalAmount());
            preparedStatement.setString(3, payment.getPaymentType());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment made successfully.");

                // If needed, retrieve the generated payment ID
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int paymentId = generatedKeys.getInt(1);
                        
                        // You can use the payment object with updated ID as needed
                    }
                }
            } else {
                System.out.println("Failed to make payment.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
}
    
    
    
     public Payment1 fetchPaymentByOrderId(int orderId) {
    Payment1 payment = null;

    try (Connection con = ConnectionProvider.getCon()) {
        String sql = "SELECT * FROM payments WHERE orderid = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int paymentId = resultSet.getInt("paymentid");
                    int total_Amount = resultSet.getInt("total_amount");
                    String payment_Type = resultSet.getString("type");

                    // Create a Payment object
                    payment = new Payment1(0, totalAmount, paymentType);

                    System.out.println("Payment ID: " + paymentId);
                    System.out.println("Total Amount: " + total_Amount);
                    System.out.println("Payment Type: " + payment_Type);
                    // Add more fields as needed
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        // Handle the exception as needed
    }

    return payment;
}

}
