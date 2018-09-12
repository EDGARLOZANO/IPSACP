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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
  public  int[] ReadEmpleado(JLabel id,JTextField txtNom,
         
          JTextField txtApeP,JTextField txtApeM,
          JComboBox cont,JCheckBox status,JLabel imagen,
          String idEmpleado) {
           int[] ids=new int[3];
        try {
            String sql = "SELECT * FROM empleado WHERE id_empleado='"+idEmpleado+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
           
             while (rs.next()) {
                
                    id.setText(rs.getString (1));
                    txtNom.setText(rs.getString (2));
                    txtApeP.setText(rs.getString (3));
                    txtApeM.setText(rs.getString (4));
                    ids[0]=rs.getInt(5);
                    ids[1]=rs.getInt(6);
                    ids[2]=rs.getInt(7);
                    cont.setSelectedIndex(1);
                    boolean st=(rs.getString(9).equals("A"))?true:false;
                    System.out.println(st);
                    status.setSelected(st);
                    java.sql.Blob blob = rs.getBlob (10);
                   
                     byte[] imageByte = blob.getBytes(1, (int) blob.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image img = imag;
  	                    img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    img = img.getScaledInstance(130,130,Image.SCALE_SMOOTH);
  	                	ImageIcon icon =new ImageIcon(img);
  	                	imagen.setIcon(icon) ;
                  }
            cmd.close();
           
            return ids;

        } catch (Exception ex) {System.out.println(ex);}
     return ids;  
  }
  
    
   public void ReadEmpleado(DefaultTableModel modelo) {

        try {
            String sql = "SELECT id_empleado,imagen,concat_ws(' ',nombre,ape_pat,ape_mat),d.departamento, a.area,p.puesto, status FROM empleado e\n" +
"INNER JOIN departamentos d\n" +
"ON d.id_departamento = e.id_depart\n" +
"INNER JOIN areas a\n" +
"ON a.id_area = e.id_area\n" +
"INNER JOIN puestos p\n" +
"ON p.id_puesto = e.id_puesto\n where e.status='A'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
           // System.out.println(sql);
            while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < 7; i++) {
                    datos[i] = rs.getString(i + 1);
                     }
                  java.sql.Blob blob = rs.getBlob (2);
                     byte[] imageByte = blob.getBytes(1, (int) blob.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image img = imag;
  	                    img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    img = img.getScaledInstance(100,50,Image.SCALE_SMOOTH);
  	                	//ImageIcon icon =new ImageIcon(img);
  	            datos[1]=new ImageIcon(img);
                    datos[6]=(datos[6].equals("A"))?"Activo":"Inactivo";
                modelo.addRow(datos);
            }
            cmd.close();
         } catch (Exception ex) {JOptionPane.showMessageDialog(null, "Error al leer: " + ex);}
    }
   
   public void busqueda(DefaultTableModel modelo, String palabra,String parametro,int tipo) {

        try {
            
            String sql="";
            if(tipo==1){         
            sql= "SELECT id_empleado,imagen,concat_ws(' ',nombre,ape_pat,ape_mat),d.departamento, a.area,p.puesto, status FROM empleado e \n" +
        "INNER JOIN departamentos d\n" +
        "ON d.id_departamento = e.id_depart\n" +
        "INNER JOIN areas a\n" +
        "ON a.id_area = e.id_area\n" +
        "INNER JOIN puestos p\n" +
        "ON p.id_puesto = e.id_puesto\n" +
        "where "+parametro+" LIKE '"+palabra+"%'";}
            if(tipo==2){         
            sql= "SELECT id_empleado,imagen,concat_ws(' ',nombre,ape_pat,ape_mat),d.departamento, a.area,p.puesto, status FROM empleado e \n" +
        "INNER JOIN departamentos d\n" +
        "ON d.id_departamento = e.id_depart\n" +
        "INNER JOIN areas a\n" +
        "ON a.id_area = e.id_area\n" +
        "INNER JOIN puestos p\n" +
        "ON p.id_puesto = e.id_puesto\n" +
        "where "+parametro+" LIKE '"+palabra+"%' AND e.status='A'";}
          if(tipo==3){         
            sql= "SELECT id_empleado,imagen,concat_ws(' ',nombre,ape_pat,ape_mat),d.departamento, a.area,p.puesto, status FROM empleado e \n" +
        "INNER JOIN departamentos d\n" +
        "ON d.id_departamento = e.id_depart\n" +
        "INNER JOIN areas a\n" +
        "ON a.id_area = e.id_area\n" +
        "INNER JOIN puestos p\n" +
        "ON p.id_puesto = e.id_puesto\n" +
        "where "+parametro+" LIKE '"+palabra+"%' AND e.status='I'";}
            
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

             while (rs.next()) {
                Object[] datos = new Object[10];
                for (int i = 0; i < 7; i++) {
                    datos[i] = rs.getString(i + 1);
                     }
                  java.sql.Blob blob = rs.getBlob (2);
                     byte[] imageByte = blob.getBytes(1, (int) blob.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image img = imag;
  	                    img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    img = img.getScaledInstance(100,50,Image.SCALE_SMOOTH);
  	                	//ImageIcon icon =new ImageIcon(img);
  	            datos[1]=new ImageIcon(img);
                    datos[6]=(datos[6].equals("A"))?"Activo":"Inactivo";
                modelo.addRow(datos);
            }
            cmd.close();
        }catch (Exception ex){System.out.println("Error" + ex.getMessage());}
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