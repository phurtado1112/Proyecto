package GUI;

import clases.Carrera;
import clases.Facultad;
import clases.Universidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import util.Valida;

/**
 *
 * @author PabloAntonio
 */
public class CarreraIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    DefaultComboBoxModel modeloComboF;
    DefaultComboBoxModel modeloComboU;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    Statement stm;
    ResultSet rs;
    Facultad f = new Facultad();
    Universidad u = new Universidad();
    Carrera c = new Carrera();

    /**
     * Creates new form EscuelaIF
     */
    
    public CarreraIF() {
        initComponents();
        cbxUniversidad.setModel(new DefaultComboBoxModel(new String[] {}));
        cbxFacultad.setModel(new DefaultComboBoxModel(new String[] {}));
        cnx.Conecta();
        Deshabilitar();
        LlenarTabla();
        cbxUniversidad.setModel(llenarCBUni());
        cbxUniversidad.setSelectedIndex(-1);
        cbxFacultad.setSelectedIndex(-1);
        BotonesInicio();        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void limpiar(){
        txtCarrera.setText("");
        cbxUniversidad.setSelectedItem(-1);
        cbxFacultad.setSelectedItem(-1);
    }
    
    private void Deshabilitar() {
        txtCarrera.setEnabled(false);
        cbxUniversidad.setEnabled(false);
        cbxFacultad.setEnabled(false);
    }
    
    public void Habilitar(){
        txtCarrera.setEnabled(true);
        va.SoloLetras(txtCarrera);
        va.SeleccionarTodo(txtCarrera);
        txtCarrera.requestFocus();
        cbxUniversidad.setEnabled(true);
        cbxFacultad.setEnabled(true);
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
    
    private DefaultComboBoxModel llenarCBUni() {
        cnx.Conecta();
        try {            
            modeloComboU = new DefaultComboBoxModel();            
            String SQL = "select nombreU from universidad";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboU.addElement(rs.getObject("nombreU"));
            }
            cbxUniversidad.setModel(modeloComboU);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Universidad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboU;
    }
    
    private DefaultComboBoxModel llenarCBFac() {        
        cnx.Conecta();
        try {            
            modeloComboF = new DefaultComboBoxModel();            
            String SQL = "select nombreF from facultad where iduniversidad = " + u.consultaIdU((String)cbxUniversidad.getSelectedItem());
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboF.addElement(rs.getObject("nombreF"));
            }
            cbxFacultad.setModel(modeloComboF);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Facultad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboF;
    }        
    
    private void LlenarTabla() {
        int[] anchos = {30, 150, 160, 180};
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Carrera","Facultad","Universidad"};
            String SQL = "Select * from carrera_compl_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[4];
            while(rs.next()){
                fila[0] = rs.getString("idcarrera");
                fila[1] = rs.getString("nombreC");
                fila[2] = rs.getString("nombreF");
                fila[3] = rs.getString("nombreU");
                model.addRow(fila);
            }
            tblCarrera.setModel(model);
            for(int i = 0; i < tblCarrera.getColumnCount(); i++) {
                tblCarrera.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Carrera: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
	boolean val;
        if(txtCarrera.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Carrera está vacío,por favor llenarlo");
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxUniversidad = new javax.swing.JComboBox();
        cbxFacultad = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarrera = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("Catálogo Carrera");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Carrera"));

        jLabel1.setText("Carrera");

        jLabel2.setText("Universidad");

        cbxUniversidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUniversidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxUniversidadItemStateChanged(evt);
            }
        });

        cbxFacultad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Facultad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblCarrera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCarreraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCarrera);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        llenarCBUni();
        llenarCBFac();        
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                int fila = tblCarrera.getSelectedRow();
                c.setnombreC(this.txtCarrera.getText().trim());
                c.setIdfacultad(f.consultaId(this.cbxFacultad.getSelectedItem().toString()));
                c.setIdcarrera(Integer.parseInt(this.tblCarrera.getValueAt(fila, 0).toString()));
                c.actualizarCarrera();
            }
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblCarrera.getSelectedRow();
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            c.setIdcarrera(Integer.parseInt(tblCarrera.getValueAt(fila, 0).toString()));
            c.eliminarCarrera();
        }
        LlenarTabla();
        limpiar();
        Deshabilitar();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            c.setnombreC(txtCarrera.getText().trim());
            c.setIdfacultad(f.consultaId(this.cbxFacultad.getSelectedItem().toString()));
            c.guardarCarrera();
        }
        LlenarTabla();
        limpiar();
        Deshabilitar();
        BotonesInicio();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCarreraMouseClicked
        if (evt.getButton()==1){
            int fila = tblCarrera.getSelectedRow();
            Habilitar();
            llenarCBFac();
            llenarCBUni();
            BotonesClick();
            cnx.Conecta();
            try{                
                String SQL = "select * from carrera_compl_view where idcarrera = " + tblCarrera.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);
                
                rs.next();
                this.txtCarrera.setText(rs.getString("nombreC"));
                this.cbxUniversidad.setSelectedItem(rs.getString("nombreU"));
                this.cbxFacultad.setSelectedItem(rs.getString("nombreF"));      
            } catch(SQLException e){                
                JOptionPane.showMessageDialog(null, "Error Carrera Mouse Cliked: " + e.getMessage());
            }finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblCarreraMouseClicked

    private void cbxUniversidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxUniversidadItemStateChanged
        this.cbxFacultad.setModel(llenarCBFac());
    }//GEN-LAST:event_cbxUniversidadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxFacultad;
    private javax.swing.JComboBox cbxUniversidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCarrera;
    private javax.swing.JTextField txtCarrera;
    // End of variables declaration//GEN-END:variables
}
