/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import clases.Actividad;
import clases.Asignatura;
import clases.EstructuraEvaluacion;
import clases.ActividadDet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import util.Globales;
import util.Valida;

/**
 *
 * @author PabloAntonio
 */
public class EstructuraEvaluacionIF extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    DefaultComboBoxModel<String> modeloComboAc;
    DefaultComboBoxModel<String> modeloComboAcDet;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    ResultSet rs;
    Statement stm;
    Actividad ac = new Actividad();
    ActividadDet ad = new ActividadDet();
    Asignatura a = new Asignatura();
    EstructuraEvaluacion ee = new EstructuraEvaluacion();
    Double notaActualizar;

    /**
     * Creates new form EstructuraEvaluacionIF
     */
    public EstructuraEvaluacionIF() {
        initComponents();
        cnx.Conecta();
        Deshabilitar();
        BotonesInicio();
        LlenarTabla();
        llenarTXT();
        llenarCBAc();
        llenarCBAcDet();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void limpiar() {
        cbxActividadDet.removeAllItems();
        cbxActividad.removeAllItems();
        txtValor.setText("");
    }

    private void Deshabilitar() {
        cbxActividadDet.setEnabled(false);
        cbxActividad.setEnabled(false);
        txtValor.setEnabled(false);
    }

    private void Habilitar() {
        cbxActividad.setEnabled(true);
        txtValor.setEnabled(true);
        va.SoloNumerosNota(txtValor);
        va.SeleccionarTodo(txtValor);
        cbxActividadDet.setEnabled(true);
        cbxActividad.requestFocus();
    }

    private void BotonesInicio() {
        btnNuevo.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    private void BotonesNuevo() {
        btnNuevo.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    private void BotonesClick() {
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    private void LlenarTabla() {
        cnx.Conecta();
        try {
            String[] titulos = {"ID", "Actividad", "Detalle Actividad", "Valor"};
            String SQL = "Select * from estructuraevaluacion_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String[] fila = new String[4];
            while (rs.next()) {
                fila[0] = rs.getString("idestructuraevaluacion");
                fila[1] = rs.getString("actividad");
                fila[2] = rs.getString("actividaddet");
                fila[3] = rs.getString("valor");
                model.addRow(fila);
            }
            tblEstructuraEvaluacion.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Estructura Evaluación: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    private DefaultComboBoxModel llenarCBAc() {
        cnx.Conecta();
        try {
            modeloComboAc = new DefaultComboBoxModel<String>();
            String SQL = "select actividad from actividad";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboAc.addElement(rs.getString("actividad"));
            }
            cbxActividad.setModel(modeloComboAc);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBAc: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboAc;
    }

    private DefaultComboBoxModel llenarCBAcDet() {
        cnx.Conecta();
        try {
            modeloComboAcDet = new DefaultComboBoxModel<String>();
            String SQL = "select actividaddet from actividaddet where idactividad = " + ac.consultaIdAct((String) cbxActividad.getSelectedItem());
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboAcDet.addElement(rs.getString("actividaddet"));
            }
            cbxActividadDet.setModel(modeloComboAcDet);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBAc: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboAcDet;
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

    private boolean validar() {
        boolean val = true;
        String valor = txtValor.getText().trim(); //Hace referencia al txtValor

        if (cbxActividad.getSelectedIndex() == -1 || cbxActividadDet.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Las listas desplegables no contienen valores");
            val = false;
        } else if (valor.length() == 0) { //Valida la nota
            JOptionPane.showMessageDialog(this, "El campo de texto Valor está vacío,por favor llenarlo");
            val = false;
        } else if (valor.startsWith(".") && valor.endsWith(".")) {  //Valida si solamente se puso 1 punto
            JOptionPane.showMessageDialog(this, "Formato para nota no valido");
            val = false;
        } else if (Double.parseDouble(valor) > 100) { //Valida que la nota no sea mayor que 100
            JOptionPane.showMessageDialog(this, "El valor máximo para el campo de texto Valor es 100");
            val = false;
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
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxActividadDet = new javax.swing.JComboBox<String>();
        cbxActividad = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstructuraEvaluacion = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estructura de Evaluación"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Valor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Actividad");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Detalle Actividad");

        cbxActividadDet.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxActividad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxActividadItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Asignatura");

        txtAsignatura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAsignatura.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtAsignatura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxActividadDet, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxActividadDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblEstructuraEvaluacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEstructuraEvaluacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstructuraEvaluacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstructuraEvaluacion);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
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
                .addGap(0, 24, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        BotonesNuevo();
        llenarCBAc();
        cbxActividad.setSelectedIndex(-1);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (ee.validarRegistros(ac.consultaIdAct(cbxActividad.getSelectedItem().toString()), ad.consultaId(cbxActividadDet.getSelectedItem().toString())) > 0
                && notaActualizar == Math.rint(Double.parseDouble(txtValor.getText().trim()) * 100) / 100) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro con:\n"
                    + "Actividad: " + cbxActividad.getSelectedItem().toString() + "\n"
                    + "Detalle:   " + cbxActividadDet.getSelectedItem().toString());
        } else if (validar() == true) {
            int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                int fila = tblEstructuraEvaluacion.getSelectedRow();
                ee.setIdactividad(ac.consultaIdAct(cbxActividad.getSelectedItem().toString()));
                ee.setIdactividaddet(ad.consultaId(cbxActividadDet.getSelectedItem().toString()));
                ee.setValor(Double.parseDouble(txtValor.getText().trim()));
                ee.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                ee.setIdestructuraevaluacion(Integer.parseInt(tblEstructuraEvaluacion.getValueAt(fila, 0).toString()));
                ee.actualizarEstructuraEvaluacion();
            }
            LlenarTabla();
            limpiar();
            Deshabilitar();
            BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?", "Confirmar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (i == JOptionPane.OK_OPTION) {
            int fila = tblEstructuraEvaluacion.getSelectedRow();
            ee.setIdestructuraevaluacion(Integer.parseInt(tblEstructuraEvaluacion.getValueAt(fila, 0).toString()));
            ee.eliminarEstructuraEvaluacion();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (ee.validarRegistros(ac.consultaIdAct(cbxActividad.getSelectedItem().toString()), ad.consultaId(cbxActividadDet.getSelectedItem().toString())) > 0) {
            JOptionPane.showMessageDialog(null, "Ya existe un registro con:\n"
                    + "Actividad: " + cbxActividad.getSelectedItem().toString() + "\n"
                    + "Detalle:   " + cbxActividadDet.getSelectedItem().toString());
        } else if (validar() == true) {

            int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?", "Confirmar",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (i == JOptionPane.OK_OPTION) {
                ee.setIdactividad(ac.consultaIdAct(cbxActividad.getSelectedItem().toString()));
                ee.setIdactividaddet(ad.consultaId(cbxActividadDet.getSelectedItem().toString()));
                ee.setValor(Double.parseDouble(txtValor.getText().trim()));
                ee.setIdasignatura(a.consultaIdA(txtAsignatura.getText()));
                ee.guardarEstructuraEvaluacion();
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
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?", "Confirmar",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        if (i == JOptionPane.OK_OPTION) {
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblEstructuraEvaluacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstructuraEvaluacionMouseClicked
        if (evt.getButton() == 1) {
            int fila = tblEstructuraEvaluacion.getSelectedRow();
            Habilitar();
            llenarCBAcDet();
            llenarCBAc();
            BotonesClick();
            cnx.Conecta();
            try {
                String SQL = "Select * from estructuraevaluacion_view where idestructuraevaluacion = " + tblEstructuraEvaluacion.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);

                rs.next();
                txtValor.setText(rs.getString("valor"));
                cbxActividadDet.setSelectedItem(rs.getString("actividaddet"));
                cbxActividad.setSelectedItem(rs.getString("actividad"));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblEstructuraEvaluacionMouseClicked

    private void cbxActividadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActividadItemStateChanged
        if (cbxActividad.getSelectedItem() == "") {

        } else {
            llenarCBAcDet();
            cbxActividadDet.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_cbxActividadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxActividad;
    private javax.swing.JComboBox<String> cbxActividadDet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstructuraEvaluacion;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
