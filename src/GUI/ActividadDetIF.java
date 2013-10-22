package GUI;

import clases.Actividad;
import clases.ActividadDet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import util.Valida;

/**
 *
 * @author Pablo
 */
public class ActividadDetIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    DefaultComboBoxModel modeloCombo;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    ActividadDet te = new ActividadDet();
    Actividad e = new Actividad();
    Statement stm;
    PreparedStatement ps;
    ResultSet rs;
    int id = 1;
    
    /**
     * Creates new form ActividadDetIF
     */
    public ActividadDetIF() {
        initComponents();
        limpiar();
        Deshabilitar();
        BotonesInicio();
        LlenarTabla();
//        llenarTXT();
    }
    
    private void limpiar(){
        txtDetActividad.setText("");
        cbxActividad.removeAllItems();
    }
    
    private void Deshabilitar() {
        txtDetActividad.setEnabled(false);
        cbxActividad.setEnabled(false);
    }
    
    private void Habilitar(){
        txtDetActividad.setEnabled(true);
        va.LetrasNumeros(txtDetActividad);
        va.SeleccionarTodo(txtDetActividad);
        cbxActividad.setEnabled(true);
        txtDetActividad.requestFocus();
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
    
    public final void llenarCB() {
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
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    //Llena con datos el JTable con un consulta
    private void LlenarTabla() {
        int[] anchos = {30, 300, 100};
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Detalle Evaluación","Evaluación"};
            String SQL = "Select * from evaluaciondet_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[3];
            while(rs.next()){
                fila[0] = rs.getString("idevaluaciondet");
                fila[1] = rs.getString("evaluaciondet");
                fila[2] = rs.getString("actividad");
                model.addRow(fila);
            }
            tblActividadDet.setModel(model);
            //Dimensiona el ancho de las columnas de la tabla
            tblActividadDet.setModel(model);
            for(int i = 0; i < tblActividadDet.getColumnCount(); i++) {
                tblActividadDet.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            //Centra los datos en las celdas
            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            tblActividadDet.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            tblActividadDet.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error Llenar Tabla Detalle de Evaluación: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
        boolean val;
        if(txtDetActividad.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Detalle de Evaluación está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActividadDet = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDetActividad = new javax.swing.JTextField();
        cbxActividad = new javax.swing.JComboBox();

        setTitle("Catálogo de Detalle de Actividad");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblActividadDet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Detalle de Actividad", "Actividad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblActividadDet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblActividadDetJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblActividadDet);
        tblActividadDet.getColumnModel().getColumn(0).setResizable(false);
        tblActividadDet.getColumnModel().getColumn(1).setResizable(false);
        tblActividadDet.getColumnModel().getColumn(2).setResizable(false);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Actividad"));

        jLabel1.setText("Actividad");

        jLabel2.setText("Detalle de Actividad");

        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDetActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(cbxActividad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDetActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        llenarCB();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){            
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){            
            te.setEvaluacionDet(txtDetActividad.getText().trim());
            te.setIdEvaluacion(e.consultaIdAct(this.cbxActividad.getSelectedItem().toString().trim()));
            te.guardarEvaluacionDet();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){            
        int i = JOptionPane.showConfirmDialog(null, "Desea Acutalizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            int fila = tblActividadDet.getSelectedRow();
            te.setEvaluacionDet(txtDetActividad.getText().trim());
            te.setIdEvaluacion(e.consultaIdAct(cbxActividad.getSelectedItem().toString().trim()));
            te.setIdEvaluacionDet(Integer.parseInt(tblActividadDet.getValueAt(fila, 0).toString()));
            te.actualizarActividadDet();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblActividadDetJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblActividadDetJTableMouseClicked
        if (evt.getButton()==1){
            int fila = tblActividadDet.getSelectedRow();
            Habilitar();
            llenarCB();
            BotonesClick();
            cnx.Conecta();
            try{                
                String SQL = "Select * from actividaddet where idevaluaciondet = " + tblActividadDet.getValueAt(fila, 0);
                ps = cnx.conn.prepareStatement(SQL);
                rs = ps.executeQuery();

                rs.next();
                txtDetActividad.setText(rs.getString("evaluaciondet"));
                cbxActividad.setSelectedItem(e.consultaActividad(rs.getInt("idevaluacion")));
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblActividadDetJTableMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
        int fila = tblActividadDet.getSelectedRow();
            te.setIdEvaluacionDet(Integer.parseInt(tblActividadDet.getValueAt(fila, 0).toString()));
            te.eliminarActividadDet();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxActividad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblActividadDet;
    private javax.swing.JTextField txtDetActividad;
    // End of variables declaration//GEN-END:variables
}
