/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanly;


import java.sql.*;
import java.lang.*;

/**
 *
 * @author Admin
 */
public class ConnectDB {
     
     public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {
        String hostName = "CUBANG2001";
        String sqlInstancaName = "SQLEXPRESS";
        String databaseName = "CuaHangLapTop";
        String userName = "sa";
        String password = "21062001";
        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        // Ví dụ: jdbc:jtds:sqlserver://localhost:1433/simplehr;instance=SQLEXPRESS
        String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/"+ databaseName + ";instance=" + sqlInstancaName;

        Connection conn =java.sql.DriverManager.getConnection(connectionURL, userName,password);
        return conn;
    }
     
     public boolean InsertSQL(String tmp){
         try {
             Connection conn = ConnectDB.getSQLServerConnection();
             Statement statement= conn.createStatement();
             statement.executeUpdate(tmp);
             return true;
         } catch (Exception e) {      
             System.out.println(e); 
             return false;
         }
     }   
}
