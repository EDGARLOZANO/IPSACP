/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author edgar
 */
public class DocImg {
      Connection cn;
    
     public DocImg(){
    Conexion con = new Conexion();
        cn = con.Connect();
    }
    
    public void InsertDatos(String id_Empleado,String domi,String lugarNac,
                String fechaNac,int edad,String correo,String nss,String rfc,
                String curp,String tel,String estadoCv) {
        try {

           PreparedStatement cmd = cn.prepareStatement("insert into data_person values(?,?,?,?,?,?,?,?,?,?,?)");
            
            cmd.setString(1,id_Empleado);
            cmd.setString(2,domi);
            cmd.setString(3,lugarNac);
            cmd.setString(4,fechaNac);
            cmd.setInt(5,edad);
            cmd.setString(6,correo);
            cmd.setString(7,nss);
            cmd.setString(8,rfc);
            cmd.setString(9,curp);
            cmd.setString(10,tel);
            cmd.setString(11,estadoCv);
            
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
}
