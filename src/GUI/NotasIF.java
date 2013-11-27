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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import util.Globales;

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
    //int id = 1;

    public NotasIF() {
        initComponents();
        cnx.Conecta();
        llenarTXT();
        limpiar();
        llenarCbxAct();
        llenarCbxActDet();        
        llenarCBFecha();
        BotonesInicio();       
        LlenarTabla();
        setJTexFieldChanged(TxtBuscar);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void limpiar(){
        cbxActividadDet.setSelectedItem(-1);
    }
    
    private void Deshabilitar() {        
        cbxActividadDet.setEnabled(false);
    }
    
    private void Habilitar(){
        cbxActividadDet.setEnabled(true);
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
            String [] titulos ={"ID","Nombre","Nota"};
            
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
            TblNotas.setModel(model);
            
            for(int i = 0; i < TblNotas.getColumnCount(); i++) {
                TblNotas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }            
            //Centra los datos en las celdas
         DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            TblNotas.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);
    } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void LlenarTablaSegunTxt() {
       int[] anchos = {30, 100, 100};
       
       cnx.Conecta();
        try{           
            String [] titulos ={"ID","Nombre","Nota"};
            
            String SQL = "Select * from notas_view where nombre like '%" + TxtBuscar.getText() + "%' and "
                    + "fecha = '" + cbxFecha.getSelectedItem() + "' and "
                    + "nombreEs = " + "'" + cbxActividadDet.getSelectedItem() + "'";
            
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[3];
            while(rs.next()){
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
            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            TblNotas.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            TblNotas.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);                
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void llenarCbxAct() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select actividad from actividad";
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
        cnx.Desconecta();
    }
    
     private void llenarCbxActDet() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select actividaddet from actividaddet where idactividad = " + ac.consultaIdAct(cbxActividad.getSelectedItem().toString());
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
        LlenarTabla();
        cnx.Desconecta();
    }
     
     private void llenarCBFecha() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select fecha from calendario";
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
        LlenarTabla();
        cnx.Desconecta();
    }
                  
    private void setJTexFieldChanged(JTextField txt){
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
    
    private void txtbuscador() {        
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
     * WARnInG: Do nOT modify this code. The content of this method is always
 regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxActividadDet = new javax.swing.JComboBox();
        TxtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxFecha = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbxActividad = new javax.swing.JComboBox();
        txtAsignatura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblNotas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnIntroducir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

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

        cbxActividadDet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxActividadDetItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Buscar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Fecha");

        cbxFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFechaItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Actividad");

        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxActividadDet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxActividad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(26, Short.MAX_VALUE))
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
        ));
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

        btnIntroducir.setText("Nota");
        btnIntroducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntroducirActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnIntroducir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(27, 27, 27)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnIntroducir)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                limpiar();
                Deshabilitar();
                LlenarTabla();
                BotonesInicio();
                TblNotas.setEnabled(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            DefaultTableModel modelo = (DefaultTableModel)TblNotas.getModel();
            int filas = modelo.getRowCount();       
            try {
                for (int f = 0; f < filas; f++) {
                    n.setValor(Double.parseDouble(modelo.getValueAt(i, 2).toString()));
                    n.setIdcalendario(c.ConsultarIDCal(cbxFecha.getSelectedItem().toString()));
                    n.setIdactividaddet(ad.consultaId(cbxActividadDet.getSelectedItem().toString()));
                    n.setIdestudiante(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                    n.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                    n.GuardarNotas();                    
                }
            JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
            } catch(HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Error guardar Asistencia: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnIntroducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntroducirActionPerformed
        TblNotas.setEnabled(true);
        Habilitar();
        LlenarTabla();

    }//GEN-LAST:event_btnIntroducirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void cbxActividadDetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadDetItemStateChanged
         if(evt.getStateChange() == ItemEvent.SELECTED) {
            LlenarTablaSegunTxt();
        }
    }//GEN-LAST:event_cbxActividadDetItemStateChanged

    private void cbxFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFechaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {             
            LlenarTablaSegunTxt();         
        }
    }//GEN-LAST:event_cbxFechaItemStateChanged

    private void cbxActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadItemStateChanged
         if(evt.getStateChange() == ItemEvent.SELECTED) {
           llenarCbxActDet();          
        }
    }//GEN-LAST:event_cbxActividadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblNotas;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIntroducir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxActividad;
    private javax.swing.JComboBox cbxActividadDet;
    private javax.swing.JComboBox cbxFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}
