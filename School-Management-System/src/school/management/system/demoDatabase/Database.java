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
    public static Connection getConnection() throws SQLException{
        Connection connection =DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CHMS;integratedSecurity=true");
        if(connection != null){
                System.out.println("Connected to database");
            }else{
            System.out.println("Not Connected");
        }
        return connection;
         
    }
   
   
    public static void main(String[] args) throws SQLException {
       getConnection();
    }
}
