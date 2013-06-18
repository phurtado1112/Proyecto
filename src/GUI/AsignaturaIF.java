/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import clases.Asignatura;
import clases.Carrera;
import clases.Facultad;
import clases.Universidad;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
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
 * @author Pablo
 */
public class AsignaturaIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    DefaultComboBoxModel modeloComboU;
    DefaultComboBoxModel modeloComboF;
    DefaultComboBoxModel modeloComboC;
    Universidad u = new Universidad();
    Facultad f = new Facultad();
    Carrera c = new Carrera();
    Asignatura a = new Asignatura();            
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    Statement stm;
    ResultSet rs;

    /**
     * Creates new form CursoIF
     */
    public AsignaturaIF() {
        initComponents();
        cbxUniversidad.setModel(new DefaultComboBoxModel(new String[] {}));
        cbxFacultad.setModel(new DefaultComboBoxModel(new String[] {}));
        cbxCarrera.setModel(new DefaultComboBoxModel(new String[] {}));
        Deshabilitar();
        BotonesInicio();
        LlenarTabla();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")        
    
     private void limpiar(){
         txtAsignatura.setText("");
         txtCodigoGrupo.setText("");
         txtPeriodo.setText("");         
         txtAnio.setText("");
         cbxUniversidad.setSelectedItem(-1);
         cbxFacultad.setSelectedItem(-1);
         cbxCarrera.setSelectedItem(-1);
    }
    
    private void Deshabilitar() {
        txtAsignatura.setEnabled(false);
        txtCodigoGrupo.setEnabled(false);
        txtPeriodo.setEnabled(false);
        txtAnio.setEnabled(false);
        cbxUniversidad.setEnabled(false);
        cbxFacultad.setEnabled(false);
        cbxCarrera.setEnabled(false);
    }
    
    private void Habilitar(){
        txtAsignatura.setEnabled(true);
        va.LetrasEspacios(txtAsignatura);
        txtCodigoGrupo.setEnabled(true);
        va.LetrasNumeros(txtCodigoGrupo);
        txtPeriodo.setEnabled(true);
        va.LetrasNumeros(txtPeriodo);
        txtAnio.setEnabled(true);
        va.SoloNumeros(txtAnio);
        cbxUniversidad.setEnabled(true);
        cbxFacultad.setEnabled(true);
        cbxCarrera.setEnabled(true);
        txtAsignatura.requestFocus();
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
    
    private DefaultComboBoxModel llenarCBCar() {
        cnx.Conecta();
        try {            
            modeloComboC = new DefaultComboBoxModel();            
            String SQL = "select nombreC from carrera where idfacultad = " + f.consultaId((String)cbxFacultad.getSelectedItem());
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloComboC.addElement(rs.getObject("nombreC"));
            }
            cbxCarrera.setModel(modeloComboC);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Carrera: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modeloComboC;
    }   
    
    private void LlenarTabla() {
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Asignatura","Cód. Grupo", "Período", 
                "Año", "Carrera", "Facultad", "Universidad"};
            int[] anchos = {30, 160, 80, 90, 40, 160, 180, 190};            
            String SQL = "Select * from asignatura_compl_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[8];
            while(rs.next()){
                fila[0] = rs.getString("idasignatura");
                fila[1] = rs.getString("nombreA");                                
                fila[2] = rs.getString("grupo");
                fila[3] = rs.getString("periodo");
                fila[4] = rs.getString("anio");
                fila[5] = rs.getString("nombreC");
                fila[6] = rs.getString("nombreF");
                fila[7] = rs.getString("nombreU");
                model.addRow(fila);
            }
            tblAsignatura.setModel(model);
            for(int i = 0; i < tblAsignatura.getColumnCount(); i++) {
                tblAsignatura.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de llenarTabla: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
	boolean val;
        if(txtAsignatura.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Asignatura está vacío,por favor llenarlo");
            val = false;
            } else if(txtCodigoGrupo.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Código de Grupo está vacío,por favor llenarlo");
            val = false;
            } else if(txtAnio.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Año está vacío,por favor llenarlo");
            val = false;
            } else if(txtPeriodo.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Período está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoGrupo = new javax.swing.JTextField();
        txtAsignatura = new javax.swing.JTextField();
        txtPeriodo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        cbxCarrera = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxUniversidad = new javax.swing.JComboBox();
        cbxFacultad = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsignatura = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Catálogo Asignatura");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignatura"));

        jLabel1.setText("Asignatura");

        jLabel2.setText("Código de Grupo");

        jLabel3.setText("Carrera");

        jLabel7.setText("Período");

        jLabel9.setText("Año");

        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Universidad");

        jLabel5.setText("Facultad");

        cbxUniversidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxUniversidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxUniversidadItemStateChanged(evt);
            }
        });

        cbxFacultad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxFacultad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxFacultadItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigoGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(txtAsignatura)
                    .addComponent(cbxCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxUniversidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxFacultad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAnio, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(txtPeriodo))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbxUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblAsignatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAsignatura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableMouseClikedPerformed(evt);
            }
        });
        jScrollPane1.setViewportView(tblAsignatura);
        tblAsignatura.getColumnModel().getColumn(0).setResizable(false);
        tblAsignatura.getColumnModel().getColumn(1).setResizable(false);
        tblAsignatura.getColumnModel().getColumn(2).setResizable(false);
        tblAsignatura.getColumnModel().getColumn(3).setResizable(false);
        tblAsignatura.getColumnModel().getColumn(4).setResizable(false);

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
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(btnSalir)
                .addContainerGap(352, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizar, btnCancelar, btnEliminar, btnGuardar, btnNuevo, btnSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        llenarCBUni();
        llenarCBFac();
        llenarCBCar();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                int fila = tblAsignatura.getSelectedRow();
                a.setnombreA(this.txtAsignatura.getText().trim());
                a.setgrupo(this.txtCodigoGrupo.getText().trim());
                a.setanio(Integer.parseInt(this.txtAnio.getText().trim()));
                a.setperiodo(this.txtPeriodo.getText().trim());
                a.setIdcarrera(c.consultaIdCa(this.cbxCarrera.getSelectedItem().toString().trim()));
                a.setIdasignatura(Integer.parseInt(this.tblAsignatura.getValueAt(fila, 0).toString()));
            }
            LlenarTabla();
            limpiar();
            Deshabilitar();        
            BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            int fila = tblAsignatura.getSelectedRow();
            a.setIdasignatura(Integer.parseInt(tblAsignatura.getValueAt(fila, 0).toString()));
            a.EliminarAsignatura();
        }
        limpiar();
        LlenarTabla();
        Deshabilitar();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                a.setnombreA(txtAsignatura.getText().trim());
                a.setgrupo(this.txtCodigoGrupo.getText().trim());
                a.setanio(Integer.parseInt(this.txtAnio.getText().trim()));
                a.setperiodo(this.txtPeriodo.getText().trim());
                a.setIdcarrera(c.consultaIdCa(this.cbxCarrera.getSelectedItem().toString().trim()));
                c.GuardarCarrera();
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

    private void JTableMouseClikedPerformed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseClikedPerformed
        if (evt.getButton()==1){
            int fila = tblAsignatura.getSelectedRow();
            Habilitar();
            llenarCBUni();
            llenarCBFac();
            llenarCBCar();
            BotonesClick();
            cnx.Conecta();
            try{                                               
                String SQL = "Select * from asignatura_compl_view where idasignatura = " + tblAsignatura.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);
                
                rs.next();
                txtAsignatura.setText(rs.getString("nombreA"));
                txtCodigoGrupo.setText(rs.getString("grupo"));
                txtPeriodo.setText(rs.getString("periodo"));
                txtAnio.setText(rs.getString("anio"));
                cbxUniversidad.setSelectedItem(rs.getString("nombreU"));
                cbxFacultad.setSelectedItem(rs.getString("nombreF"));
                cbxCarrera.setSelectedItem(rs.getString("nombreC"));
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error Mouse Cliked: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_JTableMouseClikedPerformed

    private void cbxUniversidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxUniversidadItemStateChanged
        cbxFacultad.setModel(llenarCBFac());
    }//GEN-LAST:event_cbxUniversidadItemStateChanged

    private void cbxFacultadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxFacultadItemStateChanged
        cbxCarrera.setModel(llenarCBCar());
    }//GEN-LAST:event_cbxFacultadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxCarrera;
    private javax.swing.JComboBox cbxFacultad;
    private javax.swing.JComboBox cbxUniversidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JTable tblAsignatura;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtCodigoGrupo;
    private javax.swing.JTextField txtPeriodo;
    // End of variables declaration//GEN-END:variables
}
