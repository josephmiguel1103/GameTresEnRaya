/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tresenraya.conx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tresenraya.util.UtilsX;

/**
 *
 * @author josp
 */
public class ConnS {
    private static volatile ConnS instance;
    private static volatile Connection connection;
    UtilsX util = new UtilsX();
    private ConnS() {

        try {
            //Llama al drive de sqlite
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnS.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }

        if (connection != null) {
            throw new RuntimeException("Use getConnection() method to create");
        }

    }

    public static ConnS getInstance() {
        if (instance == null) {
            synchronized (ConnS.class) {
                if (instance == null) {
                    instance = new ConnS();
                    System.out.println("Primera instancia");
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            synchronized (ConnS.class) {
                if (connection == null) {
                    try {
                        String dbFilePath = util.getFileExterno("data", "db_oxo.db").getAbsolutePath();
                        String dbUrl = "jdbc:sqlite:" + dbFilePath;
                        connection = DriverManager.getConnection(dbUrl);
                        System.out.println("tconexion exitosa");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
        public static void main(String[] args) {
        ConnS con=getInstance();
        con.getConnection();
    }
}
