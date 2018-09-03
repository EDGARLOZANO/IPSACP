/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author edgar
 */
public class Documentos {
    Connection cn;
    
     public Documentos(){
    Conexion con = new Conexion();
        cn = con.Connect();
    }
    
    public void InsertDoc(String idEmpleado,String Acta,String Ante,
            String Domi,String Estudios,String Contrato,String Curp,
            String pdfCV,String Ife) {
        try {
          
           
           PreparedStatement cmd = cn.prepareStatement("insert into documentacion values(?,?,?,?,?,?,?,?,?)");
            
            cmd.setString(1,idEmpleado);
            cmd.setString(2,Acta);
            cmd.setString(3,Ante);
            cmd.setString(4,Domi);
            cmd.setString(5,Estudios);
            cmd.setString(6,Contrato);
            cmd.setString(7,Curp);
            cmd.setString(8,pdfCV);
            cmd.setString(9,Ife );
           
            
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
    
}
