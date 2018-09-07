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
    public void ReadDocumentos(DefaultTableModel modelo) {

        try {
            String sql = "SELECT d.id_empleado,imagen,concat_ws(' ',nombre,ape_pat,ape_mat),d.acta_nac,d.ante_pena, d.comp_domicilio,d.comp_estudios,d.contrato,d.curp,d.cv,d.ife FROM empleado e INNER JOIN documentacion d ON d.id_empleado=e.id_empleado ";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
           // System.out.println(sql);
            while (rs.next()) {
                Object[] datos = new Object[13];
                for (int i = 0; i < 11; i++) {
                    datos[i] = rs.getString(i + 1);
                     if(datos[i].equals("Entregado")){
                        
                        datos[i]=new javax.swing.ImageIcon(getClass().getResource("/img/exito.png"));
                    }
                    if(datos[i].equals("Faltante")){
                        
                        datos[i]=new javax.swing.ImageIcon(getClass().getResource("/img/error.png"));
                    }
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
                    
                modelo.addRow(datos);
            }
            cmd.close();
         } catch (Exception ex) {JOptionPane.showMessageDialog(null, "Error al leer: " + ex);}
    }
   

    
}
