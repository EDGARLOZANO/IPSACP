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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author edgar
 */
public class Areas {
      Connection cn;
      Puestos puesto;
      
       public Areas(){
    Conexion con = new Conexion();
         puesto=new Puestos();
        cn = con.Connect();
    }
        public void InsertArea(int id_depart,String area) {
        try {

           String sql = "insert into areas values(null,"+id_depart+",'"+area+"')";
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
         public void ReadArea(DefaultTableModel modelo,String idDepart) {

        try {
            String sql = "SELECT id_area,area FROM areas WHERE id_departamento='"+idDepart+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
            while (rs.next()) {
                Object[] datos = new Object[4];
                for (int i = 0; i <2; i++) {
                    datos[i] = rs.getString(i + 1);
                     }
                datos[2]=puesto.CantPuesto(datos[0].toString());
                modelo.addRow(datos);
            }
            cmd.close();
            //  cn.close();

        } catch (Exception ex) {}
    }
public String ReadArea(DefaultComboBoxModel modelo,String idDepart) {

        try {
            String sql = "SELECT id_area,area FROM areas WHERE id_departamento='"+idDepart+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            String id="";
             while (rs.next()) {
             
                for (int i = 0; i <1; i++) {
                    //datos[i] = rs.getString(i + 1);
                    //modelo.addItem(rs.getString(i + 1));
                    modelo.addElement(rs.getString(i + 2));
                    id=id+rs.getString(i + 1)+",";
                     }  
            }
            cmd.close();
            //System.out.println(id);
           return id;

        } catch (Exception ex) {}
        return "s";
    }
        
        public int CantArea (String idDepart ) {
          int cant=0;
        try {

            String sql = "SELECT COUNT(area) FROM areas  WHERE id_departamento='"+idDepart+"'";
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
}
