/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Nageen
 */
public class ConnectionProvider {
    public static Connection getCon(){
    try{
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dem2?useSSL=false", "root", "pass123");

     return con;
    }
    catch(ClassNotFoundException | SQLException e){
      System.out.println(e);
      return null;
    }
    
    }
}
