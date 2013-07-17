package GUI;

import clases.Asignatura;
import clases.TipoActividad;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import util.Conecta;

/**
 *
 * @author Pablo
 */
public class AsistenciaIF extends javax.swing.JInternalFrame {
    
    DefaultTableModel model;
    DefaultComboBoxModel modeloCombo;
    TipoActividad ta = new TipoActividad();
    Asignatura a = new Asignatura();
    Conecta cnx = new Conecta();
    ResultSet rs;
    Statement stm;
    int id = 1;
    
    /**
     * Creates new form Asistencia
     */
    public AsistenciaIF() {
        initComponents();
        cnx.Conecta();
        limpiar();
        llenarTXT();
        BotonesInicio();
        LlenarTabla();
        llenarCB();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void limpiar(){
        cbxFecha.removeAllItems();        
    }
    
    private void Deshabilitar() {        
        cbxFecha.setEnabled(false);
    }
    
    private void Habilitar(){
        cbxFecha.setEnabled(true);
        cbxFecha.requestFocus();
    }
    
    private void BotonesInicio(){
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);        
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesModificar(){
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnEliminar.setEnabled(true);        
        btnCancelar.setEnabled(true);
    }
    
    private void LlenarTabla() {        
        cnx.Conecta();
        try{
            int[] anchos = {25, 250, 25};
            String [] titulos ={"No","Estudiante","Asistencia"};
            String SQL = "Select * from estudianteA_view";            
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            Object [] Colum = new Object[3];
            int No = 0;
            while(rs.next()){
                No++;
                String sNo = String.valueOf(No);
                Colum[0] = sNo;
                Colum[1] = rs.getString("nombre");
                //Colum[2] = cbxAsistencia();
                model.addRow(Colum);                            
            }            
            tblAsistencia.setModel(model);            
            for(int i = 0; i < tblAsistencia.getColumnCount(); i++) {
                tblAsistencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);                
            }
            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            tblAsistencia.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            tblAsistencia.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
            tblAsistencia.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            cbxAsis(tblAsistencia.getColumnModel().getColumn(2));
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error Llenar Tabla Asistencia: " + e.getMessage());
        }
    }
    
    private void llenarCB() {
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
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
        }
        LlenarTabla();
        cnx.Desconecta();
    }
    
    private void llenarTXT() {
        cnx.Conecta();
         try {             
            String SQL = "select nombreA from asignatura where idasignatura = " + id;
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
    
    private static void cbxAsis(TableColumn columna){
        String Asis[] = {"Presente","Ausente"};
        JComboBox Combo = new JComboBox(Asis);
        columna.setCellEditor(new DefaultCellEditor(Combo));
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
        cbxFecha = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsistencia = new javax.swing.JTable();

        setTitle("Lista de Asistencia");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de Asistencia"));

        jLabel1.setText("Fecha");

        cbxFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Asignatura");

        txtAsignatura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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

        tblAsistencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
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
        jScrollPane1.setViewportView(tblAsistencia);
        tblAsistencia.getColumnModel().getColumn(0).setResizable(false);
        tblAsistencia.getColumnModel().getColumn(1).setResizable(false);
        tblAsistencia.getColumnModel().getColumn(2).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(14, 14, 14)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar))
                .addGap(128, 128, 128))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        Habilitar();
//        limpiar();
//        llenarCB();
        //BotonesNuevo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cnx.Conecta();
        try{
            String SQL ="update asistencia set fecha=?,idtipoactividad=?, idasignatura=?"
            + "where idcalendario=?";
            int fila = tblAsistencia.getSelectedRow();
            String dato = (String)tblAsistencia.getValueAt(fila, 0);
            PreparedStatement ps = cnx.conn.prepareStatement(SQL);
            ps.setInt(1, a.consultaIdA(cbxFecha.getSelectedItem().toString()));
            ps.setString(4, dato);

            int n = ps.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
            }
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Actualizar: " + e.getMessage());
        }
        LlenarTabla();
        BotonesInicio();
        cnx.Desconecta();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblAsistencia.getSelectedRow();
        cnx.Conecta();
        try {
            String SQL = "delete from calendario where idcalendario= " + tblAsistencia.getValueAt(fila, 0);
            stm = cnx.conn.createStatement();
            int n = stm.executeUpdate(SQL);
            if(n>0){
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        cnx.Desconecta();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        cnx.Conecta();
        try {
            String SQL = "insert into calendario(fecha,idtipoactividad,idasignatura)"
            + "values(?,?,?)";
            PreparedStatement ps = cnx.conn.prepareStatement(SQL);
            //ps.setString(1, ((JTextField)jdcFecha.getDateEditor().getUiComponent()).getText());
            ps.setInt(2, ta.consultaIdTA(cbxFecha.getSelectedItem().toString()));
            //ps.setInt(3, a.consultaIdTA(txtAsignatura.getText()));
            int n = ps.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
                LlenarTabla();
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Guardar: " + e.getMessage());
        }
        BotonesInicio();
        cnx.Desconecta();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAsistencia;
    private javax.swing.JTextField txtAsignatura;
    // End of variables declaration//GEN-END:variables
}

