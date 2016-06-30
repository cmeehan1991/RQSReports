/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cmeehan
 */
public class DBConnection {

    private Connection DBConnection;

    public Connection Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Connection Fail " + cnfe);
        }

        try {
            InetAddress addr;
            Socket sock = new Socket("100.3.79.178", 3306);
            addr = sock.getInetAddress();
            sock.close();

        } catch (java.io.IOException e) {
            System.out.println("Error: " + e);
        }

        String url = "jdbc:mysql://100.3.79.178:3306/kline";
        String username = "pccrate";
        String password = "pccror)1";
        try {
            DBConnection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("No Database " + se);

        }

        return DBConnection;
    }
}
