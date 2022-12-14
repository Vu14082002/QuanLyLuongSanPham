/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectionDB;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class ConnectDB {

    public static Connection con = null;
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public void connect() throws Exception {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=THTV_SHOES";
        String user = "sa";
        String password = "sa";
        con = DriverManager.getConnection(url, user, password);

    }

    public void disconect() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void main(String[] args) {
        Date temp = new Date();
        System.out.println(temp.compareTo(new Date()));
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Yes");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
