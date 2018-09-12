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
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author edgar
 */
public class DataPerson {
     Connection cn;
    
     public DataPerson(){
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
    
        public   void ReadEmpleado(JTextField txtdomi,JTextField txtLugarNac,JDateChooser fecha,
            JTextField txtEdad,JTextField txtCorreo,JTextField txtnss,JTextField txtRFC,
            JTextField txtCurp,JTextField txtTelefono,JComboBox estado,
          String idEmpleado) {
          
        try {
            String sql = "SELECT * FROM data_person WHERE id_empleado='"+idEmpleado+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            //System.out.println(sql);
             while (rs.next()) {
                
                    txtdomi.setText(rs.getString (2));
                    txtLugarNac.setText(rs.getString (3));
                    java.util.Date fechaParseada= new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString (4));
                    fecha.setDate(fechaParseada);
                    txtEdad.setText(rs.getString (5));
                    txtCorreo.setText(rs.getString (6));
                     txtnss.setText(rs.getString (7));
                    txtRFC.setText(rs.getString (8));
                     txtCurp.setText(rs.getString (9));
                    txtTelefono.setText(rs.getString (10));
                    estado.setSelectedIndex(1);
                   }
            cmd.close();
           
        } catch (Exception ex) {System.out.println(ex);}
    
      }
}
