/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
    
      public File[] ReadIMG(JLabel labelActa,JLabel labelAnteP,JLabel labelDomi ,JLabel labelEstudios,
           File contrato, JLabel labelCURP,File Cv,JLabel ife,
          String idEmpleado) {
          File[] file2=new File[3];
        try {
            String sql = "SELECT * FROM doc_img WHERE id_empleado='"+idEmpleado+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
             while (rs.next()) {
                
                      RenderizadoImg(rs.getBlob (2),labelActa);
                      RenderizadoImg(rs.getBlob (3),labelAnteP);
                      RenderizadoImg(rs.getBlob (4),labelDomi);
                      RenderizadoImg(rs.getBlob (5),labelEstudios);
                      Pdf(rs.getBlob (6));
                      RenderizadoImg(rs.getBlob (7),labelCURP);
                      Pdf2(rs.getBlob (8));
                      RenderizadoImg(rs.getBlob (9),ife);

                 }
             
            cmd.close();
           return file2;
        } catch (Exception ex) {System.out.println(ex);}
         return null;
      }
      
      private void RenderizadoImg(Blob blo,JLabel label) throws SQLException, IOException{
      byte[] imageByte = blo.getBytes(1, (int) blo.length());
  	                     InputStream is=new ByteArrayInputStream(imageByte);
  	                    BufferedImage imag=ImageIO.read(is);
  	                    Image img = imag;
  	                    img = Toolkit.getDefaultToolkit().createImage(imageByte);
  	                    img = img.getScaledInstance(130,130,Image.SCALE_SMOOTH);
  	                	ImageIcon icon =new ImageIcon(img);
  	                	label.setIcon(icon) ;
      }
      
      private void Pdf(Blob archivo) throws FileNotFoundException{
          
      try{ 
          String pathname ="/home/edgar/pdf_re.pdf";
         File file = new File(pathname);
        FileOutputStream output = new FileOutputStream(file);
       
        InputStream inStream = archivo.getBinaryStream();
        int length = -1;
        int size = (int) archivo.length();
        byte[] buffer = new byte[size];
        while ((length = inStream.read(buffer)) != -1) {
        output.write(buffer, 0, length);}
        output.close();
      } catch(Exception e){
         JOptionPane.showMessageDialog(null, "Error al recuperar: " + e);
      }
      }
       private void Pdf2(Blob archivo) throws FileNotFoundException{
          
      try{ 
          String pathname ="/home/edgar/pdf_re2.pdf";
         File file = new File(pathname);
        FileOutputStream output = new FileOutputStream(file);
       
        InputStream inStream = archivo.getBinaryStream();
        int length = -1;
        int size = (int) archivo.length();
        byte[] buffer = new byte[size];
        while ((length = inStream.read(buffer)) != -1) {
        output.write(buffer, 0, length);}
        output.close();
      } catch(Exception e){
         JOptionPane.showMessageDialog(null, "Error al recuperar: " + e);
      }
       }
}
