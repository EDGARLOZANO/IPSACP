/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author edgar
 */
public class Empleados {
     Connection cn;
    
   public Empleados(){
    Conexion con = new Conexion();
        cn = con.Connect();
    }
public void InsertEmpleado(File foto,String Nombre,String apeP,String apeM,int depart,int area,int puesto,
        String contrato,String status) {
        try {

           FileInputStream is = new FileInputStream(foto);
            PreparedStatement cmd = cn.prepareStatement("insert into empleado values(?,?,?,?,?,?,?,?,?,?)");
            
            cmd.setInt(1,ultimoidEmpleado()+1);
            cmd.setString(2,Nombre);
            cmd.setString(3,apeP);
            cmd.setString(4,apeM);
            cmd.setInt(5,depart);
            cmd.setInt(6,area);
            cmd.setInt(7,puesto);
            cmd.setString(8,contrato);
            cmd.setString(9,status);
            cmd.setBlob(10,is);
            
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
     }
  public void ReadEmpleado(JLabel label,String idEmpleado) {

        try {
            String sql = "SELECT id_empleado,imagen FROM empleado WHERE id_empleado='"+idEmpleado+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
             while (rs.next()) {
                
                for (int i = 0; i <1; i++) {
       
                    java.sql.Blob blob = rs.getBlob (i+2);
                     byte[] imageByte = blob.getBytes(1, (int) blob.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image img = imag;
  	                    img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    img = img.getScaledInstance(130,130,Image.SCALE_SMOOTH);
  	                	ImageIcon icon =new ImageIcon(img);
  	                	label.setIcon(icon) ;
                     }  
                
            }
            cmd.close();
           

        } catch (Exception ex) {System.out.println(ex);}
    }
   public void ReadEmpleado(DefaultTableModel modelo) {

        try {
            String sql = "SELECT * FROM emplado";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
           // System.out.println(sql);
            while (rs.next()) {
                Object[] datos = new Object[9];
                for (int i = 0; i < 9; i++) {
                    datos[i] = rs.getString(i + 1);
                     }
                modelo.addRow(datos);
            }
            cmd.close();
         } catch (Exception ex) {}
    }
   public int ultimoidEmpleado() {
    int id=0;
        try {
            String sql = "SELECT MAX(id_empleado) FROM empleado";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
              
                for (int i = 0; i < 1; i++) {
                    id= rs.getInt(i + 1);
                     }
            }
            cmd.close();
            return id;
         } catch (Exception ex) {}
     return id=0; 
   }
}