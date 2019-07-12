/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school.management.system.demoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JOYOUS
 */
public class Database {
    Connection con;
    public void dbConnect() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CHMS;integratedSecurity=true";
            con = DriverManager.getConnection(connectionUrl);
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

}
