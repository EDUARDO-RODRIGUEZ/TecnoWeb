/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    public static String HOST = "127.0.0.1";
    public static Integer PORT = 5432;
    public static String user = "postgres";
    public static String pass = "eduardo";
    public static String db = "db_auxiliar";
    public static String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + db;

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("Error al conectar la DB!!!");
            ex.getMessage();
        }
        return conexion;
    }

}
