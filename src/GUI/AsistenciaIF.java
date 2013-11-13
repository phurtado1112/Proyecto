package GUI;

import clases.Asignatura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.Conecta;
import java.awt.event.ItemEvent;
import clases.Calendario;
import clases.Asistencia;
import clases.Estudiantes;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import util.Globales;
/**
 *
 * @author Pablo
 */
public class AsistenciaIF extends javax.swing.JInternalFrame {
    
    DefaultTableModel model;
    DefaultComboBoxModel modeloCombo;
    Asignatura a = new Asignatura();
    Calendario Ca = new Calendario();
    Asistencia asis = new Asistencia();
    Estudiantes E = new Estudiantes();
    Conecta cnx = new Conecta();
    ResultSet rs;
    Statement stm;
//    int id = 1;
    
  
    //private Boolean[] editables= {false,false,true};
    /**
     * Creates new form Asistencia
     */
    public AsistenciaIF() {
        initComponents();
        cnx.Conecta();
        limpiar();
        BotonesInicio();
        LlenarTabla();
        setJTexFieldChanged(TxtBuscar);
       //CbxFecha.setModel(llenarCB());
        llenarCB();
        llenarTXT();
       
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
     
    public void Asistencia(JTable Tabla, TableColumn columna ){
    
        String Asis[] = {"Presente","Ausente"};
        JComboBox Combo = new JComboBox(Asis);
        columna.setCellEditor(new DefaultCellEditor(Combo));
 
    }

    private void limpiar(){
        CbxFecha.setSelectedItem(-1);
    }
    
    private void Deshabilitar() {        
        CbxFecha.setEnabled(false);
    }
    
    private void Habilitar(){
        CbxFecha.setEnabled(true);
    }
    
    private void BotonesInicio(){
        btnGuardar.setEnabled(true);
        btnIntroducir.setEnabled(true);       
        btnCancelar.setEnabled(true);
    }
    
//    private void BotonesModificar(){
//        btnGuardar.setEnabled(false);
//        btnIntroducir.setEnabled(false);      
//        btnCancelar.setEnabled(true);
//    }
//    
//    private void BotonesClick(){
//        btnGuardar.setEnabled(false);
//        btnCancelar.setEnabled(true);
//    }

    private void LlenarTabla() {
       int[] anchos = {30, 100, 100};
       
       cnx.Conecta();
        try{           
            String [] titulos ={"ID","Nombre","Asistencia"};
            
            String SQL = "Select * from estudiantea_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[2];
            while(rs.next()){
                fila[0] = rs.getString("idestudiante");
                fila[1] = rs.getString("nombre");             
                model.addRow(fila);
            }
            TblAsistencia.setModel(model);
            
            for(int i = 0; i < TblAsistencia.getColumnCount(); i++) {
                TblAsistencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            
            //Centra los datos en las celdas
         DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            TblAsistencia.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);
             Asistencia(TblAsistencia, TblAsistencia.getColumnModel().getColumn(2));   
             
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Asistencia: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
        
    private void LlenarTablaSegunTxt() {
       int[] anchos = {30, 100, 100};
       
       cnx.Conecta();
        try{
           String fecha=CbxFecha.getSelectedItem().toString();
           JOptionPane.showMessageDialog(null, "la fecha es " + fecha);
            String [] titulos ={"ID","Nombre","Asistencia"};
            
            String SQL = "Select * from asistencia_view where nombre like '%" + TxtBuscar.getText() +
                    "%' and fecha = '" + CbxFecha.getSelectedItem().toString() + "'";
            
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[3];
            while(rs.next()){
                fila[0] = rs.getString("idasistencia");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("asistencia");
             

                model.addRow(fila);
            }
            TblAsistencia.setModel(model);
            
            for(int i = 0; i < TblAsistencia.getColumnCount(); i++) {
                TblAsistencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            
            //Centra los datos en las celdas
         DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            TblAsistencia.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            TblAsistencia.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);
             Asistencia(TblAsistencia, TblAsistencia.getColumnModel().getColumn(2));   
             
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Asistencia para edición: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
        
    private DefaultComboBoxModel llenarCB() {        
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();
            String SQL = "select fecha from calendario";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getString("fecha"));
            }
            CbxFecha.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        //LlenarTabla();            
        return modeloCombo;
    }
       
    private void setJTexFieldChanged(JTextField txt)
    {
        DocumentListener documentListener = new DocumentListener() {

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            printIt(documentEvent);
        }
        };
        txt.getDocument().addDocumentListener(documentListener);

    }

    private void printIt(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();

        if (type.equals(DocumentEvent.EventType.CHANGE))
        {
            txtbuscador();
        }
        else if (type.equals(DocumentEvent.EventType.INSERT))
        {
            txtbuscador();
        }
        else if (type.equals(DocumentEvent.EventType.REMOVE))
        {
            txtbuscador();
        }
    }
    
    private void txtbuscador()
    {
        
        LlenarTablaSegunTxt();
    }
    
    private void llenarTXT() {
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
            JOptionPane.showMessageDialog(null, "Error LlenarTXT: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
         }
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CbxFecha = new javax.swing.JComboBox();
        TxtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        btnIntroducir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblAsistencia = new javax.swing.JTable();

        setTitle("Lista de Asistencia");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Asistencia"));

        jLabel1.setText("Fecha");

        CbxFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CbxFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxFechaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar");

        jLabel3.setText("Asignatura");

        txtAsignatura.setEditable(false);
        txtAsignatura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        btnIntroducir.setText("Asistencia");
        btnIntroducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntroducirActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        TblAsistencia.setModel(new javax.swing.table.DefaultTableModel(
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
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblAsistencia.setColumnSelectionAllowed(true);
        TblAsistencia.setEnabled(false);
        TblAsistencia.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TblAsistencia);
        TblAsistencia.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (TblAsistencia.getColumnModel().getColumnCount() > 0) {
            TblAsistencia.getColumnModel().getColumn(0).setResizable(false);
            TblAsistencia.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnIntroducir)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(155, 155, 155)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnIntroducir)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar))
                .addGap(36, 36, 36))
        );

        getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIntroducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntroducirActionPerformed
       TblAsistencia.setEnabled(true);
         Habilitar();
        LlenarTabla();

    }//GEN-LAST:event_btnIntroducirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       DefaultTableModel modelo = (DefaultTableModel)TblAsistencia.getModel();
       int filas = modelo.getRowCount();
       
        try
        {
            for (int i = 0; i < filas; i++) {
                   int ax = a.consultaIdA(txtAsignatura.getText());
              asis.setAsistencia(modelo.getValueAt(i, 2).toString()); 
               asis.setIdcalendario(Ca.ConsultarIDCal(CbxFecha.getSelectedItem().toString()));
               asis.setIdestudiante(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
               asis.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
               JOptionPane.showMessageDialog(null, "Id Asignatur tiene el valor de " + ax );
               asis.GuardarAsistencia();                           
            }
          JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
        }            
        catch(HeadlessException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Error guardar Asistencia: " + e.getMessage());
        }        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                limpiar();
                Deshabilitar();
                LlenarTabla();
                BotonesInicio();
                TblAsistencia.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void CbxFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxFechaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            LlenarTablaSegunTxt();
            TblAsistencia.setEnabled(false);
        }
    }//GEN-LAST:event_CbxFechaItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CbxFecha;
    private javax.swing.JTable TblAsistencia;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIntroducir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}