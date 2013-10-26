package GUI;

import clases.Asignatura;
import clases.Calendario;
import clases.Actividad;
import clases.ActividadDet;
import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import util.Globales;

/**
 *
 * @author Pablo Hurtado
 */
public class CalendarioIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    DefaultComboBoxModel modeloComboAD;
    DefaultComboBoxModel modeloComboA;
    Actividad ac = new Actividad();
    ActividadDet ad = new ActividadDet();
    Asignatura a = new Asignatura();
    Calendario c = new Calendario();
    Conecta cnx = new Conecta();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
    ResultSet rs;
    Statement stm;
    String Asignatura;

    /**
     * Creates new form CalendarioIF
     */
    public CalendarioIF() {
        initComponents();
        cnx.Conecta();
        Deshabilitar();
        BotonesInicio();
        LlenarTabla();
        llenarTXT();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);        
    }
    
    private void limpiar(){
        jdcFecha.setCalendar(null);
        cbxActividadDet.removeAllItems();
        cbxActividad.removeAllItems();        
    }
    
    private void Deshabilitar() {
        jdcFecha.setEnabled(false);        
        cbxActividadDet.setEnabled(false);
        cbxActividad.setEnabled(false);        
    }
    
    private void Habilitar(){
        jdcFecha.setEnabled(true);
        ((JTextFieldDateEditor)jdcFecha.getDateEditor()).setEnabled(false);
        cbxActividadDet.setEnabled(true);
        cbxActividad.setEnabled(true);
        jdcFecha.requestFocus();
    }
    
    private void BotonesInicio(){
        btnNuevo.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
    private void BotonesNuevo(){
        btnNuevo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesClick(){
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    private void LlenarTabla() {
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Actividad","Detalle Actividad","Fecha"};
            String SQL = "Select * from calendario_view where idasignatura = " + Globales.id;
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[4];
            while(rs.next()){
                fila[0] = rs.getString("idcalendario");
                fila[1] = rs.getString("actividad");
                fila[2] = rs.getString("actividaddet");
                fila[3] = rs.getString("fecha");
                model.addRow(fila);
            }
            tblCalendario.setModel(model);
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Calendario: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private DefaultComboBoxModel llenarCBAct() {
        cnx.Conecta();
        try {            
            modeloComboA = new DefaultComboBoxModel();            
            String SQL = "select actividad from actividad";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboA.addElement(rs.getObject("actividad"));
            }
            cbxActividad.setModel(modeloComboA);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Actividad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboA;
    }
    
    private DefaultComboBoxModel llenarCBActDet() {
        cnx.Conecta();
        try {            
            modeloComboAD = new DefaultComboBoxModel();            
            String SQL = "select actividaddet from actividaddet where idactividad = " + ac.consultaIdAct((String)cbxActividad.getSelectedItem());
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboAD.addElement(rs.getObject("actividaddet"));
            }
            cbxActividadDet.setModel(modeloComboAD);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Actividad Detalle: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboAD;
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarTXT: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
         }
    }
    
     private boolean validar(){
	boolean val;
        if(jdcFecha.getCalendar()==null){ //Valida campo Fecha
            JOptionPane.showMessageDialog(this, "El campo de texto Fecha está vacío,por favor llenarlo");
            val = false;
            } 
        else 
        {
            val=true;
        }       
        return val;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    /**
     * regenerated by the Form Editor.
     */
// This method is called from within the constructor to initialize the form.WARNING: Do NOT modify this code. The content of this method is always
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxActividadDet = new javax.swing.JComboBox();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbxActividad = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCalendario = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setTitle("Catálogo de Calendario");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fechas"));

        jLabel1.setText("Fecha");

        jLabel3.setText("Detalle de Actividad");

        cbxActividadDet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jdcFecha.setDateFormatString("dd/MM/yyyy");
        jdcFecha.setName("txtFecha"); // NOI18N

        jLabel2.setText("Asignatura");

        txtAsignatura.setEnabled(false);

        jLabel4.setText("Actividad");

        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxActividadItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(60, 60, 60)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxActividadDet, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxActividadDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tblCalendario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Actividad", "Detalle Actividad", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCalendarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCalendario);
        if (tblCalendario.getColumnModel().getColumnCount() > 0) {
            tblCalendario.getColumnModel().getColumn(0).setResizable(false);
            tblCalendario.getColumnModel().getColumn(1).setResizable(false);
            tblCalendario.getColumnModel().getColumn(2).setResizable(false);
            tblCalendario.getColumnModel().getColumn(3).setResizable(false);
        }

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(12, 12, 12)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       if (validar()==true){            
            int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
                if(i==JOptionPane.OK_OPTION){
                int fila = tblCalendario.getSelectedRow();
                c.setfecha((String)sdf.format(jdcFecha.getDate()).trim());
                c.setIdactividadDet(ad.consultaId(cbxActividadDet.getSelectedItem().toString().trim()));
                c.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                c.setIdcalendario(Integer.parseInt(tblCalendario.getValueAt(fila, 0).toString()));
                c.actualizarCalendario();
            }
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
       }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        llenarCBAct();
        llenarCBActDet();
        llenarTXT();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
            if (validar()==true){
            int i = JOptionPane.showConfirmDialog(null, "Desea eliminar?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                int fila = tblCalendario.getSelectedRow();
                c.setIdcalendario(Integer.parseInt(tblCalendario.getValueAt(fila, 0).toString().trim()));
                c.eliminarCalendario();
            }
            limpiar();
            Deshabilitar();
            LlenarTabla();
            BotonesInicio();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){
            int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                c.setfecha((String)sdf.format(jdcFecha.getDate()).trim());
                c.setIdactividadDet(ad.consultaId(cbxActividadDet.getSelectedItem().toString().trim()));
                c.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                c.guardarCalendario();
            }
            limpiar();
            Deshabilitar();
            LlenarTabla();
            BotonesInicio();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblCalendarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCalendarioMouseClicked
        if (evt.getButton()==1){
            int fila = tblCalendario.getSelectedRow();
            Habilitar();
            llenarCBAct();
            llenarCBActDet();
            llenarTXT();
            BotonesClick();
            cnx.Conecta();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            try{                                               
                String SQL = "Select * from calendario where idcalendario = " + tblCalendario.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);
                
                rs.next();
                //jdcFecha.setDate(formatoFecha.parse(rs.getString("fecha")));
                jdcFecha.setDate(formatoFecha.parse(rs.getString("fecha")));
                cbxActividadDet.setSelectedItem(ad.consultaActividad(rs.getInt("idtipoactividad")));                
            } catch(SQLException | ParseException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblCalendarioMouseClicked

    private void cbxActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadItemStateChanged
        this.cbxActividadDet.setModel(llenarCBActDet());
    }//GEN-LAST:event_cbxActividadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxActividad;
    private javax.swing.JComboBox cbxActividadDet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable tblCalendario;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}

