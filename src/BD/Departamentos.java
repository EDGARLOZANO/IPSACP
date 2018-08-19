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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author edgar
 */
public class Departamentos {
      Connection cn;
       Areas area;
      
       public Departamentos(){
    Conexion con = new Conexion();
        cn = con.Connect();
        area= new Areas();
    }
        public void InsertDepart(String nombre) {
        try {

           String sql = "insert into departamentos values(null,'"+nombre+"')";
            PreparedStatement cmd = cn.prepareCall(sql);
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
        
        public void ReadDepart(DefaultTableModel modelo) {

        try {
            String sql = "SELECT * FROM departamentos";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
            while (rs.next()) {
                Object[] datos = new Object[4];
                for (int i = 0; i <2; i++) {
                    datos[i] = rs.getString(i + 1);
                     }
                datos[2]=area.CantArea(datos[0].toString());
                modelo.addRow(datos);
            }
            cmd.close();
         

        } catch (Exception ex) {}
    }
        public String ReadDepart(DefaultComboBoxModel modelo) {

        try {
            String sql = "SELECT * FROM departamentos";
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
           
           return id;
        } catch (Exception ex) { ;}
       return "s";
    }
}
