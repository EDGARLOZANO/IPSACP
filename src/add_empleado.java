
import BD.Areas;
import BD.DataPerson;
import BD.Departamentos;
import BD.DocImg;
import BD.Documentos;
import BD.Empleados;
import BD.Puestos;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author edgar
 */
public class add_empleado extends javax.swing.JFrame {
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
    /**
     * Creates new form add_empleado
     */
    public add_empleado() {
        initComponents();
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
        FileALL();        
        
         panelGeneral.setEnabledAt(1,false); 
        panelGeneral.setEnabledAt(2,false); 
           btnActualizarEmpleado.setVisible(false);
        btnEliminarEmpleado.setVisible(false);
        TotalDepart();
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
          
        }else{
         file=new File("./src/img/x.png");
          Image foto= getToolkit().getImage(file.toString());
          foto= foto.getScaledInstance(130,130,Image.SCALE_DEFAULT);
          label.setIcon(new ImageIcon(foto));
          check.setSelected(false);
     }
     return file=new File("./src/img/x.png");
  }
   
     private String estadoDoc(JCheckBox check1){
         String estado=(check1.isSelected()==true)?"Entregado":"Faltante";
     return estado;
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
        labelIne = new javax.swing.JLabel();
        labelActa = new javax.swing.JLabel();
        checkINE = new javax.swing.JCheckBox();
        checkActa = new javax.swing.JCheckBox();
        labelCestudios = new javax.swing.JLabel();
        checkCURP = new javax.swing.JCheckBox();
        labelCurp = new javax.swing.JLabel();
        checkEstudios = new javax.swing.JCheckBox();
        labelApenales = new javax.swing.JLabel();
        checkContrato = new javax.swing.JCheckBox();
        labelContrato = new javax.swing.JLabel();
        btnCV = new javax.swing.JButton();
        btnActa = new javax.swing.JButton();
        btnContrato = new javax.swing.JButton();
        btnEstudios = new javax.swing.JButton();
        btnCurp = new javax.swing.JButton();
        btnCIne = new javax.swing.JButton();
        checkAntP = new javax.swing.JCheckBox();
        labelCV = new javax.swing.JLabel();
        btnApenales = new javax.swing.JButton();
        checkCV = new javax.swing.JCheckBox();
        labelDomicilio = new javax.swing.JLabel();
        btnDomicilio = new javax.swing.JButton();
        checkDomi = new javax.swing.JCheckBox();
        btnGuardarImg = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel6.setText("Nombre ");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        jLabel7.setText("Apellido Paterno");

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
                            .addComponent(jLabel7)
                            .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(comboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleadoLayout.createSequentialGroup()
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleadoLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel4)
                                .addGap(120, 120, 120))
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(btnCrearEmpleado)
                                .addGap(68, 68, 68)))
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarEmpleado))
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(checkStatus))
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(btnEliminarEmpleado)))
                        .addGap(39, 39, 39)))
                .addGap(168, 168, 168))
        );
        panelEmpleadoLayout.setVerticalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtApeP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtApeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(comboContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkStatus))
                        .addGap(53, 53, 53)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearEmpleado)
                            .addComponent(btnActualizarEmpleado)
                            .addComponent(btnEliminarEmpleado))
                        .addGap(24, 24, 24))
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(labelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(panelPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPersonalesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel16))
                            .addComponent(jLabel14))
                        .addContainerGap(42, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
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

        labelIne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelIne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelIneMouseClicked(evt);
            }
        });

        labelActa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N
        labelActa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelActaMouseClicked(evt);
            }
        });

        checkINE.setText("INE");

        checkActa.setText("Acta de Nacimiento");

        labelCestudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        checkCURP.setText("CURP");

        labelCurp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        checkEstudios.setText("C. Estudios");

        labelApenales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        checkContrato.setText("Contrato");
        checkContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkContratoActionPerformed(evt);
            }
        });

        labelContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCVMouseClicked(evt);
            }
        });

        btnActa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnActa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActaMouseClicked(evt);
            }
        });

        btnContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnContrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContratoMouseClicked(evt);
            }
        });

        btnEstudios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnEstudios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstudiosMouseClicked(evt);
            }
        });

        btnCurp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCurp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCurpMouseClicked(evt);
            }
        });

        btnCIne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnCIne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCIneMouseClicked(evt);
            }
        });

        checkAntP.setText("Ante. Penales");

        labelCV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnApenales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnApenales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApenalesMouseClicked(evt);
            }
        });

        checkCV.setText("CV");

        labelDomicilio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acta.png"))); // NOI18N

        btnDomicilio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargar.png"))); // NOI18N
        btnDomicilio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDomicilioMouseClicked(evt);
            }
        });

        checkDomi.setText("C. Domicilio");

        btnGuardarImg.setText("Guardar");
        btnGuardarImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarImgMouseClicked(evt);
            }
        });

        jButton3.setText("jButton3");

        javax.swing.GroupLayout panelDocumentosLayout = new javax.swing.GroupLayout(panelDocumentos);
        panelDocumentos.setLayout(panelDocumentosLayout);
        panelDocumentosLayout.setHorizontalGroup(
            panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDocumentosLayout.createSequentialGroup()
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDocumentosLayout.createSequentialGroup()
                                .addComponent(labelIne)
                                .addGap(64, 64, 64)
                                .addComponent(labelActa)
                                .addGap(54, 54, 54))
                            .addGroup(panelDocumentosLayout.createSequentialGroup()
                                .addComponent(btnCIne, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkINE)
                                .addGap(40, 40, 40)
                                .addComponent(btnActa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkActa)
                                .addGap(37, 37, 37))))
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(labelCV)
                        .addGap(27, 27, 27)
                        .addComponent(labelApenales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addComponent(btnEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkEstudios)
                        .addGap(284, 284, 284))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDocumentosLayout.createSequentialGroup()
                        .addComponent(labelCestudios)
                        .addGap(51, 51, 51)
                        .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelContrato)
                            .addComponent(labelCurp)
                            .addGroup(panelDocumentosLayout.createSequentialGroup()
                                .addComponent(btnCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(checkCURP))
                            .addGroup(panelDocumentosLayout.createSequentialGroup()
                                .addComponent(btnContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkContrato)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelDocumentosLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(btnGuardarImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(164, 164, 164))
            .addGroup(panelDocumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkCV)
                .addGap(80, 80, 80)
                .addComponent(btnApenales, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkAntP)
                .addGap(52, 52, 52)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDomicilio)
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addComponent(btnDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkDomi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDocumentosLayout.setVerticalGroup(
            panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDocumentosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelIne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCestudios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCurp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelActa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDocumentosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(checkActa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkEstudios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelDocumentosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnActa)
                                    .addComponent(btnEstudios)
                                    .addComponent(checkCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCIne)
                                    .addComponent(checkINE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDocumentosLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnCurp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelApenales, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDomicilio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelContrato, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkCV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApenales, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkAntP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkDomi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContrato, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkContrato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDomicilio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(52, 52, 52)
                .addGroup(panelDocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarImg)
                    .addComponent(jButton3))
                .addGap(27, 27, 27))
        );

        panelGeneral.addTab("Documentos", panelDocumentos);

        jLabel17.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel17.setText("ID-");

        labelID.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        labelID.setText("---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 730, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelGeneral)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboDepartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboDepartMouseClicked
       //String s=comboDepart.get        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartMouseClicked

    private void comboDepartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDepartItemStateChanged
          int index=comboDepart.getSelectedIndex();
          d=idsDepart.split(",");
      
          areasDepart(d[index]);
          // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartItemStateChanged

    private void comboPuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPuestoItemStateChanged
        
    }//GEN-LAST:event_comboPuestoItemStateChanged

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

    private void comboDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDepartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartActionPerformed
 
    private void labelIneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelIneMouseClicked
                // TODO add your handling code here:
    }//GEN-LAST:event_labelIneMouseClicked

    private void labelActaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelActaMouseClicked
     // files[0]=cargarimg(labelActa);        // TODO add your handling code here:
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

    private void labelImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelImgMouseClicked
        fileImg=cargarimg(labelImg); 
              // TODO add your handling code here:
    }//GEN-LAST:event_labelImgMouseClicked

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

    private void btnGuardarImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarImgMouseClicked
        docimg.InsertImg(labelID.getText(),files[0],files[1] ,files[2],files[3],
                files[4],files[5],files[6],files[7]);
        doc.InsertDoc(labelID.getText(),estadoDoc(checkActa),estadoDoc(checkAntP),
                estadoDoc(checkDomi),estadoDoc(checkEstudios),estadoDoc(checkContrato),
                estadoDoc(checkCURP),estadoDoc(checkCV),estadoDoc(checkINE));
      JOptionPane.showMessageDialog(null, "Documentos guardados con exito!");
    }//GEN-LAST:event_btnGuardarImgMouseClicked

    private void txtApePKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePKeyReleased
           txtApeP.setText(txtApeP.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtApePKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
           txtNombre.setText(txtNombre.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApeMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMKeyReleased
        txtApeM.setText(txtApeM.getText().toUpperCase());        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeMKeyReleased

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
            java.util.logging.Logger.getLogger(add_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(add_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(add_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(add_empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new add_empleado().setVisible(true);
            }
        });
    }

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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
