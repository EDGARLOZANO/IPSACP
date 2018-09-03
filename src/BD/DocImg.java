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
public class DocImg {
      Connection cn;
    
     public DocImg(){
    Conexion con = new Conexion();
        cn = con.Connect();
    }
    
    public void InsertImg(String idEmpleado,File imgActa,File imgAnte,
            File imgDomi,File imgEstudios, File imgContrato, File imgCurp,
            File pdfCV, File imgIfe) {
        try {
            FileInputStream img1= new FileInputStream(imgActa);
            FileInputStream img2 = new FileInputStream(imgAnte);
            FileInputStream img3= new FileInputStream(imgDomi);
            FileInputStream img4 = new FileInputStream(imgEstudios);
            FileInputStream img5= new FileInputStream(imgContrato);
            FileInputStream img6 = new FileInputStream(imgCurp);
            FileInputStream img7= new FileInputStream(pdfCV);
            FileInputStream img8 = new FileInputStream(imgIfe);
          
           
           PreparedStatement cmd = cn.prepareStatement("insert into doc_img values(?,?,?,?,?,?,?,?,?)");
            
            cmd.setString(1,idEmpleado);
            cmd.setBlob(2,img1);
            cmd.setBlob(3,img2);
            cmd.setBlob(4,img3);
            cmd.setBlob(5,img4);
            cmd.setBlob(6,img5);
            cmd.setBlob(7,img6);
            cmd.setBlob(8,img7);
            cmd.setBlob(9,img8);
           
            
            cmd.execute();
            cmd.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en metodo Insertar: " + ex);
        }
        
    }
}
