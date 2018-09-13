
import BD.Areas;
import BD.DataPerson;
import BD.Departamentos;
import BD.DocImg;
import BD.Documentos;
import BD.Empleados;
import BD.Puestos;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edgar
 */
public class AddEmpleado extends javax.swing.JInternalFrame {
DefaultComboBoxModel modelocombo1;
DefaultComboBoxModel modelocombo2;
DefaultComboBoxModel modelocombo3;
Areas areas;
Departamentos depart;
Puestos puesto;
Empleados empleado;
DataPerson datosP;
DocImg docimg;
Documentos doc;
String idsDepart="";
String idsAreas="";
String idsPuesto="";
String[] d,d2;
File fileImg=null;
File[] files=null;
int tipo2;    
/**
     * Creates new form AddEmpleado
     */
    public AddEmpleado(String id,int tipo ) {
        initComponents();
         inicializar();
        FileALL();  
         tipo2=tipo;      
        if(tipo==1){
            btnActualizarEmpleado.setVisible(true);
             btnCrearEmpleado.setVisible(false);
             btnEliminarEmpleado.setVisible(true);
        }
       if(tipo==0){
         btnActualizarEmpleado.setVisible(false);
             btnCrearEmpleado.setVisible(true);
             btnEliminarEmpleado.setVisible(false);
         panelGeneral.setEnabledAt(1,false); 
         panelGeneral.setEnabledAt(2,true); 
       }
        
      
        
        TotalDepart();
        leer(tipo,id);
        
    }
    private void inicializar(){
    modelocombo1 = new DefaultComboBoxModel();
        modelocombo2 = new DefaultComboBoxModel();
        modelocombo3 = new DefaultComboBoxModel();
        areas= new Areas();
        depart= new Departamentos();
        puesto=new Puestos();
        empleado=new Empleados();
        datosP=new DataPerson();
        docimg=new DocImg();
        doc=new Documentos();
    }
    private void leer(int tip,String id){
       if(tip==1){
           int[] ed;
       ed=empleado.ReadEmpleado(labelID, txtNombre, txtApeP, txtApeM, comboContrato, checkStatus,labelImg,id);
       comboDepart.setSelectedIndex(leerDap(ed[0])); 
       comboArea.setSelectedIndex(leerdAp(ed[1],ed[0]));
       comboPuesto.setSelectedIndex(leerdaP(ed[2],ed[0],ed[1]));
       
       datosP.ReadEmpleado(txtDomi, txtLugarNac, dateFecha, txtEdad, txtCorreo, txtNSS, txtRFC, txtCURP, txtTel, comboEstado, id);
         docimg.ReadIMG(labelActa, labelApenales, labelDomicilio, labelCestudios, fileImg, labelCurp, fileImg,labelIne, id);
       }
     
    }
     private int leerDap(int id){
        int contador=-1;
        ArrayList<Integer> a=depart.ReadID();
     
        for(Integer nombre: a){
            contador++;
           if(nombre==id){return contador;}          
        }
       return 0;
    }
     private int leerdAp(int id,int depart){
        int contador=-1;
        ArrayList<Integer> a=areas.ReadID(depart);
     
        for(Integer nombre: a){
            contador++;
           if(nombre==id){return contador;}          
        }
       return 0;
    }
     private int leerdaP(int id,int depart, int area){
        int contador=-1;
        ArrayList<Integer> a=puesto.ReadID(depart,area);
     
        for(Integer nombre: a){
            contador++;
           if(nombre==id){return contador;}          
        }
       return 0;
    }
     private void limpiar(JComboBox combo) {
        while (combo.getItemCount() > 0) {
            ((DefaultComboBoxModel) combo.getModel()).removeAllElements();
        }
    }
    private void FileALL(){
       fileImg=new File("./src/img/usuario.png");
        files=new File[9];
        files[0]=new File("./src/img/acta.png");
        files[1]=new File("./src/img/acta.png");
        files[2]=new File("./src/img/acta.png");
        files[3]=new File("./src/img/acta.png");
        files[4]=new File("./src/img/acta.png");
        files[5]=new File("./src/img/acta.png");
        files[6]=new File("./src/img/acta.png");
        files[7]=new File("./src/img/acta.png");
        files[8]=new File("./src/img/acta.png");
    }
    private void TotalDepart() {
        
        limpiar(comboDepart);
        DefaultComboBoxModel modelocombo1 = (DefaultComboBoxModel) comboDepart.getModel();
        
        idsDepart=depart.ReadDepart(modelocombo1);
        
         d=idsDepart.split(",");
         areasDepart(d[0]);
         
    }
   
    
    private void areasDepart(String id){
          limpiar(comboArea);
        DefaultComboBoxModel modelocombo2 = (DefaultComboBoxModel) comboArea.getModel();
        idsAreas=areas.ReadArea(modelocombo2, id);
        d=idsAreas.split(",");
        puestoDepart(d[0]);
    }
    private void puestoDepart(String id){
          limpiar(comboPuesto);
        DefaultComboBoxModel modelocombo3 = (DefaultComboBoxModel) comboPuesto.getModel();
        puesto.ReadPuesto(modelocombo3, id);
        
    }
     public File cargarimg(JLabel label){
  JFileChooser archivo= new JFileChooser();
        FileNameExtensionFilter filtro = 
         new FileNameExtensionFilter("Formatos de Archivo JPEG(*.JPG;*.JPEG)","jpg","jpeg","png");
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        int ventana=archivo.showOpenDialog(null);
        File file=null;
     if(ventana==JFileChooser.APPROVE_OPTION){
        
          file=archivo.getSelectedFile();
        
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          return file;
        }else{
         file=new File("./src/img/x.png");
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          
     }
     return file=new File("./src/img/x.png");
  }
     public File cargarimg(JLabel label,JCheckBox check){
  JFileChooser archivo= new JFileChooser();
        FileNameExtensionFilter filtro = 
         new FileNameExtensionFilter("Formatos de Archivo JPEG(*.JPG;*.JPEG)","jpg","jpeg","png");
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        int ventana=archivo.showOpenDialog(null);
        File file=null;
     if(ventana==JFileChooser.APPROVE_OPTION){
        
          file=archivo.getSelectedFile();
        
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          check.setSelected(true);
          return file;
        }else{
         file=new File("./src/img/x.png");
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          check.setSelected(false);
     }
     return file=new File("./src/img/x.png");
  }
     public File cargarDoc(JCheckBox check,JLabel label){
      JFileChooser archivo= new JFileChooser();
        FileNameExtensionFilter filtro = 
         new FileNameExtensionFilter("Formatos de Archivo Documentos","docx","pdf","jpg","jpeg","png");
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        int ventana=archivo.showOpenDialog(null);
        File file=null;
     if(ventana==JFileChooser.APPROVE_OPTION){
        
          file=archivo.getSelectedFile();
          File file2=new File("./src/img/pdf.png");
          Image foto= getToolkit().getImage(file2.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          check.setSelected(true);
          return file;
        }else{
         file=new File("./src/img/x.png");
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          check.setSelected(false);
     }
     return file;
  }
   
     private String estadoDoc(JCheckBox check1){
         String estado=(check1.isSelected()==true)?"Entregado":"Faltante";
     return estado;
     }
     
     private void AbrirImg(){
         new MostrarImg().setVisible(true);
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JTabbedPane();
        panelEmpleado = new javax.swing.JPanel();
        comboDepart = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        comboArea = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboPuesto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelImg = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtApeP = new javax.swing.JTextField();
        txtApeM = new javax.swing.JTextField();
        comboContrato = new javax.swing.JComboBox<>();
        checkStatus = new javax.swing.JCheckBox();
        btnCrearEmpleado = new javax.swing.JButton();
        btnActualizarEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        panelPersonales = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtDomi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLugarNac = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dateFecha = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNSS = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCURP = new javax.swing.JTextField();
        btnDataPerson = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        panelDocumentos = new javax.swing.JPanel();
        btnGuardarImg = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panelCEstudios = new javax.swing.JPanel();
        labelCestudios = new javax.swing.JLabel();
        btnEstudios = new javax.swing.JButton();
        checkEstudios = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        labelCurp = new javax.swing.JLabel();
        btnCurp = new javax.swing.JButton();
        checkCURP = new javax.swing.JCheckBox();
        panelActa = new javax.swing.JPanel();
        labelActa = new javax.swing.JLabel();
        checkActa = new javax.swing.JCheckBox();
        btnActa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelIne = new javax.swing.JLabel();
        btnCIne = new javax.swing.JButton();
        checkINE = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        labelContrato = new javax.swing.JLabel();
        btnContrato = new javax.swing.JButton();
        checkContrato = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        labelDomicilio = new javax.swing.JLabel();
        btnDomicilio = new javax.swing.JButton();
        checkDomi = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        labelApenales = new javax.swing.JLabel();
        btnApenales = new javax.swing.JButton();
        checkAntP = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        labelCV = new javax.swing.JLabel();
        btnCV = new javax.swing.JButton();
        checkCV = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();

        setClosable(true);

        comboDepart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDepartItemStateChanged(evt);
            }
        });
        comboDepart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboDepartMouseClicked(evt);
            }
        });
        comboDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDepartActionPerformed(evt);
            }
        });

        jLabel1.setText("Departamento");

        comboArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboAreaItemStateChanged(evt);
            }
        });
        comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAreaActionPerformed(evt);
            }
        });

        jLabel2.setText("Area");

        comboPuesto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPuestoItemStateChanged(evt);
            }
        });

        jLabel3.setText("Puesto");

        jLabel4.setText("Tipo de contrato");

        labelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N
        labelImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelImgMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel6.setText("Nombre ");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel7.setText("Apellido Paterno");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel8.setText("Apellido Materno");

        txtApeP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApePKeyReleased(evt);
            }
        });

        txtApeM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApeMKeyReleased(evt);
            }
        });

        comboContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Temporal", "Confianza" }));

        checkStatus.setText("Activo");

        btnCrearEmpleado.setText("Crear Empleado");
        btnCrearEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCrearEmpleadoMouseClicked(evt);
            }
        });

        btnActualizarEmpleado.setText("Actualizar");
        btnActualizarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpleadoActionPerformed(evt);
            }
        });

        btnEliminarEmpleado.setText("Eliminar");

        javax.swing.GroupLayout panelEmpleadoLayout = new javax.swing.GroupLayout(panelEmpleado);
        panelEmpleado.setLayout(panelEmpleadoLayout);
        panelEmpleadoLayout.setHorizontalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEmpleadoLayout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(386, 386, 386)
                                            .addComponent(jLabel7))
                                        .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addGap(231, 231, 231)
                                                    .addComponent(jLabel2)
                                                    .addGap(227, 227, 227)
                                                    .addComponent(jLabel3)
                                                    .addGap(62, 62, 62))
                                                .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                                    .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(61, 61, 61)
                                                    .addComponent(comboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE))
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(308, 308, 308)))
                        .addComponent(labelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(comboDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleadoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCrearEmpleado)
                .addGap(98, 98, 98)
                .addComponent(btnActualizarEmpleado)
                .addGap(299, 299, 299)
                .addComponent(btnEliminarEmpleado)
                .addGap(257, 257, 257))
            .addComponent(jSeparator1)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(comboContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(checkStatus)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelEmpleadoLayout.setVerticalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGap(41, 41, 41))
                    .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelImg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(comboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearEmpleado)
                    .addComponent(btnActualizarEmpleado)
                    .addComponent(btnEliminarEmpleado))
                .addGap(24, 24, 24))
        );

        panelGeneral.addTab("Empleado", panelEmpleado);

        jLabel5.setText("Domicilio");

        txtDomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDomiActionPerformed(evt);
            }
        });

        jLabel9.setText("Lugar de nacimiento");

        jLabel10.setText("Fecha de nacimiento");

        jLabel11.setText("Telefono");

        jLabel12.setText("Correo");

        jLabel13.setText("Estado Civil");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero", "Casado", "Divorciado", "Union Li bre" }));

        jLabel14.setText("RFC");

        jLabel15.setText("NSS");

        jLabel16.setText("CURP");

        btnDataPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnDataPerson.setText("Guardar");
        btnDataPerson.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDataPersonMouseClicked(evt);
            }
        });

        jButton1.setText("Actualizar");

        jLabel18.setText("Edad");

        javax.swing.GroupLayout panelPersonalesLayout = new javax.swing.GroupLayout(panelPersonales);
        panelPersonales.setLayout(panelPersonalesLayout);
        panelPersonalesLayout.setHorizontalGroup(
            panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPersonalesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNSS, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel16))
                            .addComponent(jLabel14))
                        .addContainerGap(324, Short.MAX_VALUE))
                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel11))
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDomi, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtLugarNac, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(jLabel18))
                                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnDataPerson)
                                .addGap(63, 63, 63)
                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelPersonalesLayout.setVerticalGroup(
            panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPersonalesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtDomi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel18)))
                .addGap(18, 18, 18)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtLugarNac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPersonalesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPersonalesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDataPerson)
                            .addComponent(jButton1))
                        .addGap(70, 70, 70))))
        );

        panelGeneral.addTab("Personales", panelPersonales);

        btnGuardarImg.setText("Guardar");
        btnGuardarImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarImgMouseClicked(evt);
            }
        });

        jButton3.setText("jButton3");

        panelCEstudios.setBackground(java.awt.Color.lightGray);

        labelCestudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnEstudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnEstudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstudiosMouseClicked(evt);
            }
        });

        checkEstudios.setText("C. Estudios");

        javax.swing.GroupLayout panelCEstudiosLayout = new javax.swing.GroupLayout(panelCEstudios);
        panelCEstudios.setLayout(panelCEstudiosLayout);
        panelCEstudiosLayout.setHorizontalGroup(
            panelCEstudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCEstudiosLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(btnEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkEstudios)
                .addContainerGap())
            .addGroup(panelCEstudiosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelCestudios, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCEstudiosLayout.setVerticalGroup(
            panelCEstudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCEstudiosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCestudios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCEstudiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCEstudiosLayout.createSequentialGroup()
                        .addComponent(btnEstudios)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCEstudiosLayout.createSequentialGroup()
                        .addComponent(checkEstudios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
        );

        jPanel1.setBackground(java.awt.Color.lightGray);

        labelCurp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnCurp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCurp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCurpMouseClicked(evt);
            }
        });

        checkCURP.setText("CURP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkCURP))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelCurp)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCurp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCurp)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        panelActa.setBackground(java.awt.Color.lightGray);

        labelActa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelActa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelActaMouseClicked(evt);
            }
        });

        checkActa.setText("Acta de Nacimiento");

        btnActa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnActa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelActaLayout = new javax.swing.GroupLayout(panelActa);
        panelActa.setLayout(panelActaLayout);
        panelActaLayout.setHorizontalGroup(
            panelActaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnActa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelActaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelActa)
                    .addComponent(checkActa))
                .addGap(21, 21, 21))
        );
        panelActaLayout.setVerticalGroup(
            panelActaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelActa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(panelActaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkActa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActa))
                .addContainerGap())
        );

        jPanel3.setBackground(java.awt.Color.lightGray);

        labelIne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelIne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIneMouseClicked(evt);
            }
        });

        btnCIne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCIne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCIneMouseClicked(evt);
            }
        });

        checkINE.setText("INE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelIne, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnCIne, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(checkINE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCIne)
                    .addComponent(checkINE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(java.awt.Color.lightGray);

        labelContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelContratoMouseClicked(evt);
            }
        });

        btnContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContratoMouseClicked(evt);
            }
        });

        checkContrato.setText("Contrato");
        checkContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkContrato)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(labelContrato)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelContrato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContrato)
                    .addComponent(checkContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(java.awt.Color.lightGray);

        labelDomicilio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnDomicilio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnDomicilio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDomicilioMouseClicked(evt);
            }
        });

        checkDomi.setText("C. Domicilio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkDomi)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDomicilio)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDomicilio)
                    .addComponent(checkDomi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(java.awt.Color.lightGray);

        labelApenales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnApenales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnApenales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApenalesMouseClicked(evt);
            }
        });

        checkAntP.setText("Ante. Penales");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addComponent(btnApenales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkAntP))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelApenales)))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelApenales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnApenales, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkAntP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(java.awt.Color.lightGray);

        labelCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCVMouseClicked(evt);
            }
        });

        btnCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCVMouseClicked(evt);
            }
        });

        checkCV.setText("CV");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnCV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkCV))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(labelCV)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCV)
                    .addComponent(checkCV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDocumentosLayout = new javax.swing.GroupLayout(panelDocumentos);
        panelDocumentos.setLayout(panelDocumentosLayout);
        panelDocumentosLayout.setHorizontalGroup(
            panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDocumentosLayout.createSequentialGroup()
                .addGap(338, 338, 338)
                .addComponent(btnGuardarImg)
                .addGap(315, 315, 315)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDocumentosLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(138, 138, 138)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelActa, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCEstudios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );
        panelDocumentosLayout.setVerticalGroup(
            panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDocumentosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelActa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDocumentosLayout.createSequentialGroup()
                        .addComponent(btnGuardarImg)
                        .addContainerGap())))
        );

        panelGeneral.addTab("Documentos", panelDocumentos);

        labelID.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelID.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboDepartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDepartItemStateChanged
        int index=comboDepart.getSelectedIndex();
        d=idsDepart.split(",");

        areasDepart(d[index]);
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartItemStateChanged

    private void comboDepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboDepartMouseClicked
        //String s=comboDepart.get        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartMouseClicked

    private void comboDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartActionPerformed

    private void comboAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboAreaItemStateChanged
        int index=comboArea.getSelectedIndex();
        d2=idsAreas.split(",");
        if(index!=-1){
            puestoDepart(d2[index]);
        }
    }//GEN-LAST:event_comboAreaItemStateChanged

    private void comboAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAreaActionPerformed

    private void comboPuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPuestoItemStateChanged

    }//GEN-LAST:event_comboPuestoItemStateChanged

    private void labelImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImgMouseClicked
        fileImg=cargarimg(labelImg);
        // TODO add your handling code here:
    }//GEN-LAST:event_labelImgMouseClicked

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        txtNombre.setText(txtNombre.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApePKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePKeyReleased
        txtApeP.setText(txtApeP.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtApePKeyReleased

    private void txtApeMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMKeyReleased
        txtApeM.setText(txtApeM.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeMKeyReleased

    private void btnCrearEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearEmpleadoMouseClicked
        int[] id= new int[3];
        id[0]=depart.idNombre((String) comboDepart.getSelectedItem());
        id[1]=areas.idNombre((String) comboArea.getSelectedItem());
        id[2]=puesto.idNombre((String) comboPuesto.getSelectedItem());
        String status=(checkStatus.isSelected())?"A":"I";
        empleado.InsertEmpleado(fileImg,txtNombre.getText(),txtApeP.getText(),txtApeM.getText(),
            id[0],id[1],id[2],
            comboContrato.getSelectedItem().toString(),status);
        labelID.setText(empleado.ultimoidEmpleado()+"");
        panelGeneral.setEnabledAt(1,true);
        panelGeneral.setEnabledAt(2,true);
        btnActualizarEmpleado.setVisible(true);
        btnEliminarEmpleado.setVisible(true);
        this.btnCrearEmpleado.setVisible(false);

    }//GEN-LAST:event_btnCrearEmpleadoMouseClicked

    private void txtDomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDomiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDomiActionPerformed

    private void btnDataPersonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataPersonMouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String Fecha = sdf.format(dateFecha.getDate());

        System.out.println(Fecha);
        datosP.InsertDatos(labelID.getText(),txtDomi.getText(),txtLugarNac.getText()
            ,Fecha,Integer.parseInt(txtEdad.getText()),txtCorreo.getText(),
            txtNSS.getText(),txtRFC.getText(),txtCURP.getText(),txtTel.getText(),
            comboEstado.getSelectedItem().toString());
    }//GEN-LAST:event_btnDataPersonMouseClicked

    private void labelIneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelIneMouseClicked

    private void labelActaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelActaMouseClicked
        AbrirImg();
    }//GEN-LAST:event_labelActaMouseClicked

    private void checkContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkContratoActionPerformed

    private void btnCVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCVMouseClicked

        files[6]=cargarDoc(checkCV,labelCV);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnCVMouseClicked

    private void btnActaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActaMouseClicked
        files[0]=cargarimg(labelActa,checkActa);
    }//GEN-LAST:event_btnActaMouseClicked

    private void btnContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContratoMouseClicked
        files[4]=cargarDoc(checkContrato,labelContrato);
    }//GEN-LAST:event_btnContratoMouseClicked

    private void btnEstudiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstudiosMouseClicked
        files[3]=cargarimg(labelCestudios,checkEstudios);
    }//GEN-LAST:event_btnEstudiosMouseClicked

    private void btnCurpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCurpMouseClicked
        files[5]=cargarimg(labelCurp,checkCURP);
    }//GEN-LAST:event_btnCurpMouseClicked

    private void btnCIneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCIneMouseClicked
        files[7]=cargarimg(labelIne,checkINE);
    }//GEN-LAST:event_btnCIneMouseClicked

    private void btnApenalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApenalesMouseClicked
        files[1]=cargarimg(labelApenales,checkAntP);
    }//GEN-LAST:event_btnApenalesMouseClicked

    private void btnDomicilioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDomicilioMouseClicked
        files[2]=cargarimg(labelDomicilio,checkDomi);
    }//GEN-LAST:event_btnDomicilioMouseClicked

    private void btnGuardarImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarImgMouseClicked
        docimg.InsertImg(labelID.getText(),files[0],files[1] ,files[2],files[3],
            files[4],files[5],files[6],files[7]);
        /*doc.InsertDoc(labelID.getText(),estadoDoc(checkActa),estadoDoc(checkAntP),
            estadoDoc(checkDomi),estadoDoc(checkEstudios),estadoDoc(checkContrato),
            estadoDoc(checkCURP),estadoDoc(checkCV),estadoDoc(checkINE));*/
        JOptionPane.showMessageDialog(null, "Documentos guardados con exito!");
    }//GEN-LAST:event_btnGuardarImgMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           
            // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void labelCVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCVMouseClicked
       if(tipo2==1){
           String pathname ="/home/edgar/pdf_re.pdf";
       
        try 
        {
            Runtime.getRuntime().exec("evince " +pathname);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "linux No  pude abrir evince");
            System.out.println("err al ejecutar evince (pdfs()):" + e);
        } 
       }// TODO add your handling code here:
    }//GEN-LAST:event_labelCVMouseClicked

    private void labelContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelContratoMouseClicked
    if(tipo2==1){         String pathname ="/home/edgar/pdf_re2.pdf";
        try 
        {
            Runtime.getRuntime().exec("evince " +pathname);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "linux No  pude abrir evince");
            System.out.println("err al ejecutar evince (pdfs()):" + e);
        } 
       }       // TODO add your handling code here:
    }//GEN-LAST:event_labelContratoMouseClicked

    private void btnActualizarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpleadoActionPerformed
        int[] id= new int[3];
        id[0]=depart.idNombre((String) comboDepart.getSelectedItem());
        id[1]=areas.idNombre((String) comboArea.getSelectedItem());
        id[2]=puesto.idNombre((String) comboPuesto.getSelectedItem());
        String status=(checkStatus.isSelected())?"A":"I";
        empleado.UpdateMPS(txtNombre.getText(),txtApeP.getText(),txtApeM.getText(), id[0], id[1],id[2],
                comboContrato.getSelectedItem().toString(),status, fileImg,labelID.getText());
    }//GEN-LAST:event_btnActualizarEmpleadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActa;
    private javax.swing.JButton btnActualizarEmpleado;
    private javax.swing.JButton btnApenales;
    private javax.swing.JButton btnCIne;
    private javax.swing.JButton btnCV;
    private javax.swing.JButton btnContrato;
    private javax.swing.JButton btnCrearEmpleado;
    private javax.swing.JButton btnCurp;
    private javax.swing.JButton btnDataPerson;
    private javax.swing.JButton btnDomicilio;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnEstudios;
    private javax.swing.JButton btnGuardarImg;
    private javax.swing.JCheckBox checkActa;
    private javax.swing.JCheckBox checkAntP;
    private javax.swing.JCheckBox checkCURP;
    private javax.swing.JCheckBox checkCV;
    private javax.swing.JCheckBox checkContrato;
    private javax.swing.JCheckBox checkDomi;
    private javax.swing.JCheckBox checkEstudios;
    private javax.swing.JCheckBox checkINE;
    private javax.swing.JCheckBox checkStatus;
    private javax.swing.JComboBox<String> comboArea;
    private javax.swing.JComboBox<String> comboContrato;
    private javax.swing.JComboBox<String> comboDepart;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboPuesto;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelActa;
    private javax.swing.JLabel labelApenales;
    private javax.swing.JLabel labelCV;
    private javax.swing.JLabel labelCestudios;
    private javax.swing.JLabel labelContrato;
    private javax.swing.JLabel labelCurp;
    private javax.swing.JLabel labelDomicilio;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelImg;
    private javax.swing.JLabel labelIne;
    private javax.swing.JPanel panelActa;
    private javax.swing.JPanel panelCEstudios;
    private javax.swing.JPanel panelDocumentos;
    private javax.swing.JPanel panelEmpleado;
    private javax.swing.JTabbedPane panelGeneral;
    private javax.swing.JPanel panelPersonales;
    private javax.swing.JTextField txtApeM;
    private javax.swing.JTextField txtApeP;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDomi;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtLugarNac;
    private javax.swing.JTextField txtNSS;
    public javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
