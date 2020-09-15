/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingfx;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hoxha
 */
public class ConnectSql {

    private static String url = "jdbc:mysql://127.0.0.1/stockdb";
    private static String drivername = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static Connection con;
    private static String urlstring;

    public Connection getconnection() throws ClassNotFoundException, SQLException {

        Class.forName(drivername);
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("connected..");
        } catch (Exception e) {
            System.out.println("something went wrong..." + e);
        }

        return con;
    }

    public static void cloaseconnection() throws SQLException {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("something wrong" + e);
        }
    }
}
