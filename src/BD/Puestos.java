/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author edgar
 */
public class Puestos {
    Connection cn;
    
     public Puestos(){
    Conexion con = new Conexion();
        cn = con.Connect();
    }
        public void InsertPuesto(int id_depart,int id_area,String puesto) {
        try {

           String sql = "insert into puestos values(null,"+id_depart+","+id_area+",'"+puesto+"')";
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
        
              public int CantPuesto (String idArea ) {
          int cant=0;
        try {

            String sql = "SELECT COUNT(puesto) FROM puestos  WHERE id_area='"+idArea+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[1];
                for (int i = 0; i < 1; i++) {
                    datos[i] = rs.getString(i + 1);
                    
                    cant=Integer.parseInt(datos[0]+"");
                }
               return cant;
            }
            cmd.close();
          } catch (Exception ex){System.out.println("Error" + ex.getMessage());
         return 0;
        }
        return -1;
    } 
              
              
      public void ReadPuesto(DefaultComboBoxModel modelo,String idArea) {

        try {
            String sql = "SELECT id_puesto,puesto FROM puestos WHERE id_area='"+idArea+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
             while (rs.next()) {
                
                for (int i = 0; i <1; i++) {
                    //datos[i] = rs.getString(i + 1);
                    //modelo.addItem(rs.getString(i + 1));
                    modelo.addElement(rs.getString(i + 2));
                    //id=id+rs.getString(i + 1)+",";
                     }  
                
            }
            cmd.close();
           

        } catch (Exception ex) {System.out.println(ex);}
    }
  public int idNombre(String Nombre) {
      int id=0;
        try {
            String sql = "SELECT id_puesto FROM puestos "
                    + "WHERE puesto='"+Nombre+"' ";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            
            
            while (rs.next()) {
               
                for (int i = 0; i <1; i++) {
                    //datos[i] = rs.getString(i + 1);
                    //modelo.addItem(rs.getString(i + 1));
                   
                    id=rs.getInt(i + 1);
                     }
              
              
            }
            cmd.close();
           
           return id;
        } catch (Exception ex) { ;}
       return 0;
    }       
   
  public ArrayList<Integer> ReadID(int iddep, int idarea) {
     
     ArrayList<Integer> a = new ArrayList<Integer>();
        try {
            String sql = "SELECT p.id_puesto FROM puestos p \n" +
            "INNER JOIN departamentos d\n" +
            "ON p.id_departamento= d.id_departamento\n" +
            " where d.id_departamento="+iddep+" and p.id_area="+idarea;
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
            while (rs.next()) {
               
                a.add(rs.getInt(1));
               
            }
            cmd.close();
            System.out.println(a);
         return a;

        } catch (Exception ex) {}
      return null; 
   }
}
