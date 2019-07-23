/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.demoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JOYOUS
 */
public class Database {
    static Connection con;
    static Statement stmt = null;
    public static void dbConnect() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CHMS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            if (con != null) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    public Connection getConnection(){
        return con;
    }
    
     public static ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException {
        dbConnect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {

        }
        return rs;
    }

}
