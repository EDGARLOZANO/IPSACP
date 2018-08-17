/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author edgar
 */
public class Conexion {
     Connection conexion = null;
    Statement comando = null;
    ResultSet registro;
 
    public Connection MySQLConnect() {
 
        try {
            //Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión Conexion
            //panamahitek_text es el nombre que le dimos a la base de datos
            String servidor = "jdbc:mysql://localhost:3306/ipsaCPE";
            //El root es el nombre de usuario por default. No hay contraseña
            String usuario = "root";
            String pass = "";
            //Se inicia la conexión
            conexion = DriverManager.getConnection(servidor, usuario, pass);
            //JOptionPane.showMessageDialog(null, "Conexión Exitosa");
 
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: 1" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos:2 " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: 3" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            
            return conexion;
        }
    }
    public Connection Connect() {
 
        try {
            //Driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión Conexion
            //panamahitek_text es el nombre que le dimos a la base de datos
            String servidor = "jdbc:mysql://localhost:3306/ipsaCPE";
            //El root es el nombre de usuario por default. No hay contraseña
            String usuario = "root";
            String pass = "";
            //Se inicia la conexión
            conexion = DriverManager.getConnection(servidor, usuario, pass);
       } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: 1" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos:2 " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: 3" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            
            return conexion;
        }
    }
     public static void main(String[] args) {
        Conexion db = new Conexion();
        db.MySQLConnect();
    }

}
