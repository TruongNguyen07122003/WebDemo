/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author GIGABYTE
 */
public class DBHelper implements Serializable {

    public static Connection createConnection()
            throws ClassNotFoundException, SQLException, NamingException {
        try{
            //1.Load Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.Create connection String url
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1708;instanceName=DESKTOP-VE4BH9T";
            //3.Open connection
            Connection con = DriverManager.getConnection(url,"sa","12345");

            return con;
        }
        catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
        return null;
            

    }
}
