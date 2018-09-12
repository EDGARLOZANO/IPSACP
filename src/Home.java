
import BD.Areas;
import BD.Departamentos;
import BD.Documentos;
import BD.Empleados;
import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edgar
 */
public class Home extends javax.swing.JFrame {
   
    DefaultTableModel modelo,modeloEmpleado,modeloDoc;
      Departamentos depart;
      Empleados empleados;
      Documentos doc;
    /**
     * Creates new form Home
     */
     
     
     
     
     
    public Home() {
        initComponents();
        depart= new Departamentos();
        empleados=new Empleados();
        doc=new Documentos();
          modelo = (DefaultTableModel) tablaDepart.getModel();
         
          tablanew();
           tablanew2();
           modeloDoc = (DefaultTableModel) tablaArchivo.getModel();
           modeloEmpleado = (DefaultTableModel) tablaEmpleado.getModel();
          
          
         ImgUS();
          RadioGroup();
        TotalDepart();
        TotalEmpleados();
         TotalDocumentos();
        TablaDimensiones();
        TablaDimensiones2();
    }
    
    private void RadioGroup(){
       radioGroup1.add(radioNom);
        radioGroup1.add(radioID);
        radioGroup1.add(radioDepart);
        radioNom.setSelected(true);
        radioGroup2.add(radioInactivos);
        radioGroup2.add(radioActivos);
        radioGroup2.add(radioTodos);
        radioActivos.setSelected(true);
    }
    private void ImgUS(){
       File fileImg=new File("./src/img/usuario.png");
       Image foto= getToolkit().getImage(fileImg.toString());
          foto= foto.getScaledInstance(50,50,Image.SCALE_DEFAULT);
          labelUS.setIcon(new ImageIcon(foto));
    }
    private void TablaDimensiones(){
    
      tablaEmpleado.setRowHeight(60);
      tablaEmpleado.getTableHeader().setFont(new Font("Ubuntu", 1,15));
      tablaEmpleado.getColumnModel().getColumn(6).setPreferredWidth(2);
      tablaEmpleado.getColumnModel().getColumn(0).setPreferredWidth(40);
      tablaEmpleado.getColumnModel().getColumn(1).setPreferredWidth(10);
      tablaEmpleado.getColumnModel().getColumn(2).setPreferredWidth(250);
       tablaEmpleado.getColumnModel().getColumn(4).setPreferredWidth(50);
      /*
      tablaProductos.getTableHeader().setFont(new Font("Ubuntu", 1,15));*/
    }
     private void TablaDimensiones2(){
    
      tablaArchivo.setRowHeight(50);
      tablaArchivo.getColumnModel().getColumn(2).setPreferredWidth(300);
      /*tablaEmpleado.getTableHeader().setFont(new Font("Ubuntu", 1,15));
      tablaEmpleado.getColumnModel().getColumn(6).setPreferredWidth(2);
      tablaEmpleado.getColumnModel().getColumn(0).setPreferredWidth(40);
      tablaEmpleado.getColumnModel().getColumn(1).setPreferredWidth(10);
      tablaEmpleado.getColumnModel().getColumn(2).setPreferredWidth(250);
       tablaEmpleado.getColumnModel().getColumn(4).setPreferredWidth(50);
      /*
      tablaProductos.getTableHeader().setFont(new Font("Ubuntu", 1,15));*/
    }
    
    private void TotalDepart() {
        limpiar(tablaDepart);
        DefaultTableModel modelo = (DefaultTableModel) tablaDepart.getModel();
        depart.ReadDepart(modelo);
         
    }
    private void TotalEmpleados(){
        limpiar(tablaEmpleado);
      // modeloEmpleado = (DefaultTableModel) tablaEmpleado.getModel();
        empleados.ReadEmpleado(modeloEmpleado);
    
    }
    private void TotalDocumentos(){
        limpiar(tablaArchivo);
        
        doc.ReadDocumentos(modeloDoc);
    
    }
      private void limpiar(JTable tabla) {
        while (tabla.getRowCount() > 0) {
            ((DefaultTableModel) tabla.getModel()).removeRow(0);
        }
    }

      private void tablanew(){
          DefaultTableModel modelo3=new DefaultTableModel(){
    public Class getColumnClass(int indiceColumna){
        Object stefany=getValueAt(0, indiceColumna);
        if(stefany == null){
            return Object.class;
        }else{
        return stefany.getClass();
        }
       }
        };
         
        modelo3.addColumn("No. Empleado",new Object[] {});
        modelo3.addColumn("Imagen",new Object[] {});
        modelo3.addColumn("Nombre",new Object[] {});
        modelo3.addColumn("Acta de Nac.",new Object[] {});
        modelo3.addColumn("Carta Ant. Pen.",new Object[] {});
        modelo3.addColumn("Comp. Domicilio",new Object[] {});
        modelo3.addColumn("Certi.  Estudios",new Object[] {});
        modelo3.addColumn("Contrato",new Object[] {});
        modelo3.addColumn("Curp",new Object[] {});
        modelo3.addColumn("CV",new Object[] {});
        modelo3.addColumn("INE",new Object[] {});
       
        this.tablaArchivo.setModel(modelo3);
      
      }
      
  private void tablanew2(){
          DefaultTableModel modelo3=new DefaultTableModel(){
    public Class getColumnClass(int indiceColumna){
        Object stefany=getValueAt(0, indiceColumna);
        if(stefany == null){
            return Object.class;
        }else{
        return stefany.getClass();
        }
       }
        };
         
        modelo3.addColumn("No. Empleado",new Object[] {});
        modelo3.addColumn("Imagen",new Object[] {});
        modelo3.addColumn("Nombre",new Object[] {});
        modelo3.addColumn("Departamentos",new Object[] {});
        modelo3.addColumn("Area.",new Object[] {});
        modelo3.addColumn("Puesto",new Object[] {});
        modelo3.addColumn("Status",new Object[] {});
       
       
        this.tablaEmpleado.setModel(modelo3);
      
      }
      
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroup1 = new javax.swing.ButtonGroup();
        radioGroup2 = new javax.swing.ButtonGroup();
        Documentos = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleado = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        radioID = new javax.swing.JRadioButton();
        radioNom = new javax.swing.JRadioButton();
        radioDepart = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        radioActivos = new javax.swing.JRadioButton();
        radioInactivos = new javax.swing.JRadioButton();
        radioTodos = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDepart = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        PanelDocumentos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaArchivo = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelUS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaEmpleado.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaEmpleado);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jButton1.setText("Nuevo");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        radioID.setText("No. Empleado");

        radioNom.setText("Nombre");

        radioDepart.setText("Departamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioDepart)
                    .addComponent(radioNom)
                    .addComponent(radioID))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioNom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioDepart)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        radioActivos.setText("Activos");

        radioInactivos.setText("Inactivos");

        radioTodos.setText("Todos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioActivos)
                    .addComponent(radioInactivos)
                    .addComponent(radioTodos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioActivos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioInactivos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioTodos)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(61, 61, 61))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGeneralLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton1)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        Documentos.addTab("Empleados", panelGeneral);

        tablaDepart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Deparpartmentos", "No de Areas"
            }
        ));
        jScrollPane2.setViewportView(tablaDepart);

        jButton2.setText("Nuevo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        Documentos.addTab("Departamentos", jPanel2);

        tablaArchivo.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tablaArchivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaArchivo);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDocumentosLayout = new javax.swing.GroupLayout(PanelDocumentos);
        PanelDocumentos.setLayout(PanelDocumentosLayout);
        PanelDocumentosLayout.setHorizontalGroup(
            PanelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
            .addGroup(PanelDocumentosLayout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelDocumentosLayout.setVerticalGroup(
            PanelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDocumentosLayout.createSequentialGroup()
                .addGroup(PanelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDocumentosLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        Documentos.addTab("Documentos", PanelDocumentos);

        jPanel3.setBackground(java.awt.Color.gray);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUS, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelUS, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Documentos)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Documentos, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        //new add_empleado().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiar(tablaEmpleado);
        txtBuscar.setText(txtBuscar.getText().toUpperCase());
        String parametro="";
        int tipo=0;
        if(radioID.isSelected()){ parametro="e.id_empleado";}
        if(radioNom.isSelected()){ parametro="e.nombre";}
        if(radioDepart.isSelected()){ parametro="d.departamento";}
        if(radioTodos.isSelected()){ tipo=1;}
        if(radioActivos.isSelected()){ tipo=2;}
        if(radioInactivos.isSelected()){ tipo=3;}
        empleados.busqueda(modeloEmpleado,txtBuscar.getText(), parametro,tipo);
    }//GEN-LAST:event_txtBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Documentos;
    private javax.swing.JPanel PanelDocumentos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelUS;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JRadioButton radioActivos;
    private javax.swing.JRadioButton radioDepart;
    private javax.swing.ButtonGroup radioGroup1;
    private javax.swing.ButtonGroup radioGroup2;
    private javax.swing.JRadioButton radioID;
    private javax.swing.JRadioButton radioInactivos;
    private javax.swing.JRadioButton radioNom;
    private javax.swing.JRadioButton radioTodos;
    private javax.swing.JTable tablaArchivo;
    private javax.swing.JTable tablaDepart;
    private javax.swing.JTable tablaEmpleado;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
