/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import clases.Actividad;
import clases.ActividadDet;
import clases.Asignatura;
import clases.Calendario;
//import clases.EstructuraEvaluacion;
//import clases.Estudiantes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import clases.Notas;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import util.Globales;
import util.Valida;

/**
 *
 * @author Pablo
 */
public class NotasIF extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    DefaultComboBoxModel modeloCombo;
//    EstructuraEvaluacion Estev = new EstructuraEvaluacion();
    Notas n = new Notas();
    Calendario c = new Calendario();
    //Evaluaciones E = new Evaluaciones();
    Actividad ac = new Actividad();
    ActividadDet ad = new ActividadDet();
//    Estudiantes e = new Estudiantes();
    Asignatura a = new Asignatura();
    Conecta cnx = new Conecta();
    ResultSet rs;
    Statement stm;
    Valida va=new Valida();
    Calendario Ca = new Calendario();
    //int id = 1;

    public NotasIF() {
        initComponents();
        cnx.Conecta();        
        limpiar();        
        BotonesInicio();       
        LlenarTablaIngreso();        
        llenarTxtAsignatura();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);        
    }
    
    private void limpiar(){
        cbxFecha.removeAllItems();
        cbxActividad.removeAllItems();
        cbxActividadDet.removeAllItems();        
    }
      
    private void Habilitar(){        
        cbxFecha.setEnabled(true);
        llenarCBFecha();
        
        cbxActividad.setEnabled(true);
        //llenarCbxAct();
        
        cbxActividadDet.setEnabled(true);
        //llenarCbxActDet();
                
        TblNotas.setEnabled(true);
        
    }
    
    private void Deshabilitar() {        
        cbxFecha.setEnabled(false);
        cbxActividad.setEnabled(false);
        cbxActividadDet.setEnabled(false);        
        LlenarTablaIngreso();
        TblNotas.setEnabled(false);
    }
    
    private void BotonesInicio(){      
        jcbIngresar.setEnabled(true);
        jcbIngresar.setSelected(false);
        btnGuardar.setEnabled(false);
        jcbModificar.setEnabled(true);
        jcbModificar.setSelected(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(false);                        
    }
    
     private void BotonesIngresar(){
        btnGuardar.setEnabled(true);
        jcbModificar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);        
    }
     
     private void BotonesModificar(){
        jcbIngresar.setEnabled(false);    
        btnModificar.setEnabled(true);        
        btnCancelar.setEnabled(true);
    }
   
//    private void BotonesClick(){
//        btnGuardar.setEnabled(false);
//        btnCancelar.setEnabled(true);
//    }
    
    private void LlenarTablaIngreso() {
       int[] anchos = {30, 100, 100};
       
       cnx.Conecta();
        try{           
            String [] titulos ={"ID","Nombre","Nota"};
            
            String SQL = "Select * from estudiantea_view where idasignatura=" + Globales.id;
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[2];
            while(rs.next()){
                fila[0] = rs.getString("idestudiante");
                fila[1] = rs.getString("nombre");                
                model.addRow(fila);
            }
            TblNotas.setModel(model);
            
            for(int i = 0; i < TblNotas.getColumnCount(); i++) {
                TblNotas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }            
            //Centra los datos en las celdas
//         DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
//            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
//            TblNotas.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
//            TblNotas.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
//            TblNotas.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
//            TblNotas.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);            
    } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void LlenarTablaModificar() {
       int[] anchos = {30, 100, 100};       
       cnx.Conecta();
        try{                         
              String [] titulos ={"ID","Nombre","Notas"};              
              String SQL = "Select * from notas_view where idcalendario = " + Ca.ConsultarIDCal(cbxFecha.getSelectedItem().toString()) + 
                      " and idactividaddet = "+ad.consultaId(cbxActividadDet.getSelectedItem().toString()) +
                      " and idasignatura="+Globales.id;
              
              model = new DefaultTableModel(null, titulos);
              stm = cnx.conn.createStatement();
              rs = stm.executeQuery(SQL);
              String [] fila = new String[3];
              while(rs.next())
              {
                  fila[0] = rs.getString("idnotas");
                  fila[1] = rs.getString("nombre");
                  fila[2] = rs.getString("nota");            
                  model.addRow(fila);
              }
              TblNotas.setModel(model);

              for(int i = 0; i < TblNotas.getColumnCount(); i++) {
                    TblNotas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }

              //Centra los datos en las celdas
//              DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
//              centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
//              tblAsistencia.getColumnModel().getColumn(0).setCellRenderer(centraCelda);            
//              tblAsistencia.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
//              tblAsistencia.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
//              tblAsistencia.getColumnModel().getColumn(1).setHeaderRenderer(centraCelda);
//              tblAsistencia.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);              
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Notas para edición: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
//    private void busquedaModificar(String nomb) {
//       int[] anchos = {30, 100, 100};
//       
//       cnx.Conecta();
//        try{           
//            String [] titulos ={"ID","Nombre","Nota"};
//            
//            String SQL = "Select * from notas_view where nombre like '%" + nomb + "%' and "
//                    + "fecha = '" + cbxFecha.getSelectedItem() + "' and "
//                    + "nombreEs = " + "'" + cbxActividadDet.getSelectedItem() + "'";
//            
//            model = new DefaultTableModel(null, titulos);
//            stm = cnx.conn.createStatement();
//            rs = stm.executeQuery(SQL);
//            String [] fila = new String[3];
//            while(rs.next()){
//                fila[0] = rs.getString("idnotas");
//                fila[1] = rs.getString("nombre");
//                fila[2] = rs.getString("nota");             
//                model.addRow(fila);
//            }
//            TblNotas.setModel(model);
//            
//            for(int i = 0; i < TblNotas.getColumnCount(); i++) {
//                TblNotas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//            }
//            
//            //Centra los datos en las celdas
////            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
////            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
////            TblNotas.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
////            TblNotas.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
////            TblNotas.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
////            TblNotas.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);                
//        } catch(SQLException ex){
//            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + ex.getMessage());
//        } finally {
//            cnx.Desconecta();
//        }
//    }
    
    private void llenarCBFecha() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select distinct fecha from notacombobox_view";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("fecha"));
            }
            rs.close();
            cbxFecha.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBFecha: " + ex.getMessage());
        }
        //LlenarTablaIngreso();
        cbxFecha.setSelectedIndex(-1);
        cnx.Desconecta();
    }    
    
    private void llenarCbxAct() {
        cnx.Conecta();
        try {
                modeloCombo = new DefaultComboBoxModel();            
                String SQL = "select distinct actividad from notacombobox_view where fecha = '"+cbxFecha.getSelectedItem().toString() +"'";
                stm = cnx.conn.createStatement();            
                rs = stm.executeQuery(SQL);
                while (rs.next()) {
                    modeloCombo.addElement(rs.getObject("actividad"));
                }
                rs.close();
                cbxActividad.setModel(modeloCombo);                                                    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCbxAct: " + ex.getMessage());
        }
        //LlenarTabla();
        cbxActividad.setSelectedIndex(-1);
        cnx.Desconecta();
    }
    
     private void llenarCbxActDet() {
        cnx.Conecta();
        try {
                modeloCombo = new DefaultComboBoxModel();            
                String SQL = "select actividaddet from notacombobox_view where actividad = '" + cbxActividad.getSelectedItem().toString() +"' and fecha = '" 
                        + cbxFecha.getSelectedItem().toString() +"'";
                stm = cnx.conn.createStatement();            
                rs = stm.executeQuery(SQL);
                while (rs.next()) {
                    modeloCombo.addElement(rs.getObject("actividaddet"));
                }
                rs.close();
                cbxActividadDet.setModel(modeloCombo);
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCbxActDet: " + ex.getMessage());
        }
        //LlenarTabla();
        cbxActividadDet.setSelectedIndex(-1);
        cnx.Desconecta();
    }
                       
//    private void setJTexFieldChanged(JTextField txt){
//        DocumentListener documentListener = new DocumentListener() {
//
//            @Override
//            public void changedUpdate(DocumentEvent documentEvent) {
//                printIt(documentEvent);
//            }
//            @Override
//            public void insertUpdate(DocumentEvent documentEvent) {
//                printIt(documentEvent);
//            }
//            @Override
//            public void removeUpdate(DocumentEvent documentEvent) {
//                printIt(documentEvent);
//            }
//        };
//        txt.getDocument().addDocumentListener(documentListener);
//    }
//
//    private void printIt(DocumentEvent documentEvent) {
//        DocumentEvent.EventType type = documentEvent.getType();
//        if (type.equals(DocumentEvent.EventType.CHANGE))
//        {
//            txtbuscador();
//        }
//        else if (type.equals(DocumentEvent.EventType.INSERT))
//        {
//            txtbuscador();
//        }
//        else if (type.equals(DocumentEvent.EventType.REMOVE))
//        {
//            txtbuscador();
//        }
//    }
    
//    private void txtbuscador() {        
//        LlenarTablaSegunTxt();
//    }
         
    private void llenarTxtAsignatura() {
        cnx.Conecta();
         try {             
            String SQL = "select nombreA from asignatura where idasignatura = " + Globales.id;
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                txtAsignatura.setText(rs.getString("nombreA"));
            }
            rs.close();            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarTXTAsignatura: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
         }
    }
    
    private void llenarTxtValor() {
        cnx.Conecta();
         try {
             TblNotas.setVisible(true);
            String SQL = "select valor from notacombobox_view where actividaddet= '" + cbxActividadDet.getSelectedItem().toString() +
                    "' and actividad= '" +cbxActividad.getSelectedItem().toString() +"' and fecha= '" +cbxFecha.getSelectedItem().toString() +"'";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                txtValor.setText(rs.getString("valor"));
            }
            rs.close();            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarTXTValor: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
         }
    }
            
    private boolean valida()
    {
        boolean val=true;        
        if (cbxFecha.getSelectedIndex()==-1 || cbxActividad.getSelectedIndex()==-1 || cbxActividadDet.getSelectedIndex()==-1)
        {
            JOptionPane.showMessageDialog(null, "Las listas desplegables no pueden estar vacíos");
            val=false;            
        }        
        else
        {
                Double nota;
                TblNotas.getCellEditor().stopCellEditing();   //Detiene la edicion del JTable
                DefaultTableModel modelo = (DefaultTableModel)TblNotas.getModel();
                int filas = modelo.getRowCount();                
                for (int i = 0; i < filas; i++) 
                {
                    try
                    {
                        nota=Double.parseDouble(modelo.getValueAt(i,2).toString());
                        if (nota>Double.parseDouble(txtValor.getText()))
                        {
                            JOptionPane.showMessageDialog(null,"La nota ingresada no puede ser mayor que la actividad");
                            val=false;
                            break;                
                        }

                        if (nota<0)
                        {
                            JOptionPane.showMessageDialog(null,"La nota no puede ser negativa");
                            val=false;
                            break;                            
                        }
                    }catch (HeadlessException | NumberFormatException e){
                        JOptionPane.showMessageDialog(null,"No se permiten letras "+e.getMessage());
                        val=false;
                        break;
                    }                                                                                                         
                }
        }              
        return val;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARnInG: Do nOT modify this code. The content of this method is always
 regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxActividadDet = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbxFecha = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbxActividad = new javax.swing.JComboBox();
        txtAsignatura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblNotas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jcbIngresar = new javax.swing.JCheckBox();
        jcbModificar = new javax.swing.JCheckBox();
        btnModificar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro de Notas");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Notas"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Detalle de Actividad");

        cbxActividadDet.setEnabled(false);
        cbxActividadDet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxActividadDetItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Fecha");

        cbxFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFecha.setEnabled(false);
        cbxFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFechaItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Actividad");

        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxActividad.setEnabled(false);
        cbxActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxActividadItemStateChanged(evt);
            }
        });

        txtAsignatura.setEditable(false);
        txtAsignatura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAsignatura.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtAsignatura.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Asignatura");

        jLabel6.setText("Valor");

        txtValor.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4))
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxActividadDet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxActividad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(288, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxActividadDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        TblNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblNotas.setEnabled(false);
        jScrollPane1.setViewportView(TblNotas);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jcbIngresar.setText("Ingresar");
        jcbIngresar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbIngresarItemStateChanged(evt);
            }
        });

        jcbModificar.setText("Modificar");
        jcbModificar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbModificarItemStateChanged(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbIngresar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(jcbModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar)
                    .addComponent(jcbIngresar)
                    .addComponent(jcbModificar)
                    .addComponent(btnModificar))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                limpiar();
                Deshabilitar();
                LlenarTablaIngreso();
                BotonesInicio();                
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (valida()==false)
        { }
        else if(n.validarRegistros(Ca.ConsultarIDCal(cbxFecha.getSelectedItem().toString()),
         ad.consultaId(cbxActividadDet.getSelectedItem().toString()))>0)        
        {            
            JOptionPane.showMessageDialog(null, "Ya existe un registro de estas Actividades \n "+
                        "Si desea cambiar el valor utilice la función Modificar.");
        }
        else 
        {            
            int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION)
            {
                DefaultTableModel modelo = (DefaultTableModel)TblNotas.getModel();
                int filas = modelo.getRowCount();            
                try 
                {
                    for (int f = 0; f < filas; f++) 
                    {                    
                        n.setIdcalendario(c.ConsultarIDCal(cbxFecha.getSelectedItem().toString()));
                        n.setIdactividaddet(ad.consultaId(cbxActividadDet.getSelectedItem().toString()));
                        n.setIdestudiante(Integer.parseInt(modelo.getValueAt(f, 0).toString()));                                                
                        n.setValor(Double.parseDouble(modelo.getValueAt(f, 2).toString())); //Ponerlo a 2 cifras
                        n.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                        n.GuardarNotas();                        
                    }
                    JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
                    limpiar();
                    Deshabilitar();
                    BotonesInicio();
                } catch(HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error guardar Notas: " + ex.getMessage());
                }
            }            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cbxFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFechaItemStateChanged
        if (cbxFecha.getSelectedIndex()==-1)
        { }  
        else 
        {
            llenarCbxAct();            
        }
        
          
//        if(evt.getStateChange() == ItemEvent.SELECTED) {             
//            LlenarTablaSegunTxt();         
//        }
    }//GEN-LAST:event_cbxFechaItemStateChanged

    private void cbxActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadItemStateChanged
        if (cbxActividad.getSelectedIndex()==-1)
        { 
            cbxActividadDet.removeAllItems(); 
        }
        else
        {
            llenarCbxActDet();            
        }        
        
//        if ((cbxActividad.getSelectedIndex()==-1) || jcbIngresar.isSelected()==true)
//        {  }
//        else
//        {
//            LlenarTablaModificar();            
//        }
//         if(evt.getStateChange() == ItemEvent.SELECTED) {
//           llenarCbxActDet();          
//        }
    }//GEN-LAST:event_cbxActividadItemStateChanged

    private void jcbModificarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbModificarItemStateChanged
        if (jcbModificar.isSelected())
        {
            Habilitar();
            BotonesModificar();
            TblNotas.setVisible(false);            
        }
        else
        {
            limpiar();
            Deshabilitar();
            BotonesInicio();
            LlenarTablaIngreso();
            TblNotas.setVisible(true);
        }
    }//GEN-LAST:event_jcbModificarItemStateChanged

    private void jcbIngresarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbIngresarItemStateChanged
        if (jcbIngresar.isSelected())
        {
            Habilitar();
            BotonesIngresar();
        }
        else
        {
            limpiar();
            Deshabilitar();
            BotonesInicio();            
        }
    }//GEN-LAST:event_jcbIngresarItemStateChanged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (valida()==false)
        {  }
        else
        {
            int i = JOptionPane.showConfirmDialog(null, "Desea Modificar?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                DefaultTableModel modelo = (DefaultTableModel)TblNotas.getModel();
                int filas = modelo.getRowCount();       
                try {
                    for (int f = 0; f < filas; f++) {                        
                        n.setIdnota(Integer.parseInt(modelo.getValueAt(f, 0).toString()));
                        n.setIdcalendario(c.ConsultarIDCal(cbxFecha.getSelectedItem().toString()));
                        n.setIdactividaddet(ad.consultaId(cbxActividadDet.getSelectedItem().toString()));                                                                       
                        n.setValor(Double.parseDouble(modelo.getValueAt(f, 2).toString())); //Ponerlo a 2 cifras                        
                        n.ModificarNotas();                    
                    }
                JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
                limpiar();
                Deshabilitar();
                BotonesInicio();
                } catch(HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error guardar Asistencia: " + ex.getMessage());
                }
            }
        }
        
            
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxActividadDetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadDetItemStateChanged
        
        if (cbxActividadDet.getSelectedIndex()==-1)
        { 
            txtValor.setText("");
        } else if(jcbIngresar.isSelected()==true)             
        {
            llenarTxtValor();    
        } else if (jcbModificar.isSelected()==true)
        {
            llenarTxtValor();
            LlenarTablaModificar();
        }
        
        
    }//GEN-LAST:event_cbxActividadDetItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblNotas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxActividad;
    private javax.swing.JComboBox cbxActividadDet;
    private javax.swing.JComboBox cbxFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbIngresar;
    private javax.swing.JCheckBox jcbModificar;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
