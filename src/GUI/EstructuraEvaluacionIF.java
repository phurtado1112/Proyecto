/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import clases.Asignatura;
import clases.EstructuraEvaluacion;
import clases.TipoEvaluacion;
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
    DefaultComboBoxModel modeloCombo,modeloCombo1;    
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    ResultSet rs;
    Statement stm;
    TipoEvaluacion ev = new TipoEvaluacion();
    Asignatura a = new Asignatura();
    EstructuraEvaluacion ee = new EstructuraEvaluacion();
    //int id = 1;    
    
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
        llenarCBTE();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    private void limpiar(){        
        txtNobreEvaluacion.setText("");
        txtValor.setText("");
        cbxTipoEvaluacion.removeAllItems();
    }
    
    private void Deshabilitar() {
        txtNobreEvaluacion.setEnabled(false);        
        txtValor.setEnabled(false);
        cbxTipoEvaluacion.setEnabled(false);
    }
    
    private void Habilitar(){
        txtNobreEvaluacion.setEnabled(true);
        va.SoloLetras(txtNobreEvaluacion);
        va.SeleccionarTodo(txtNobreEvaluacion);
        txtValor.setEnabled(true);
        va.SoloNumeros(txtValor);
        va.SeleccionarTodo(txtValor);
        cbxTipoEvaluacion.setEnabled(true);
        txtNobreEvaluacion.requestFocus();
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
            String [] titulos ={"ID","Evaluación","Valor","Tipo Evaluación"};
            String SQL = "Select * from estructuraevaluacion_view";
            model = new DefaultTableModel(null, titulos);
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            String [] fila = new String[4];
            while(rs.next()){
                fila[0] = rs.getString("idestructuraevaluacion");
                fila[1] = rs.getString("nombreE");
                fila[2] = rs.getString("valor");
                fila[3] = rs.getString("evaluacion");
                model.addRow(fila);
            }
            tblEstructuraEvaluacion.setModel(model);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Estructura Evaluación: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void llenarCBTE() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select evaluacion from evaluacion";
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("evaluacion"));
            }
            rs.close();
            cbxTipoEvaluacion.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBTE: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
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
    
    private boolean validar(){
	boolean val;
        int contador=0;   //Para que cuente cuantos puntos decimales escribio el usuario
        String valor=txtValor.getText().trim(); //Hace referencia al txtValor
        if(valor.length()>0)
        {            
            for(int i=0; i<=valor.length()-1; i++)
            {
                if(valor.charAt(i)=='.'){
                    contador+=1;                    
                }
            }            
        }
        if(txtNobreEvaluacion.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Evaluación está vacío,por favor llenarlo");
            val = false;
        } else if(valor.length()==0){ //Valida la nota
            JOptionPane.showMessageDialog(this, "El campo de texto Valor está vacío,por favor llenarlo");
            val = false;
        } else if(valor.startsWith(".")&& valor.endsWith(".")){  //Valida si solamente se puso 1 punto
            JOptionPane.showMessageDialog(this, "Formato para nota no valido");
            val = false;
        } else if(contador>1){ //Valida que no haya mas de 1 punto decimal
            JOptionPane.showMessageDialog(this, "Formato para nota no valido");
            val = false;
        }  else if(Integer.parseInt(valor)>100){ //Valida que la nota no sea mayor que 100
            JOptionPane.showMessageDialog(this, "El valor máximo para el campo de texto Valor es 100");
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
        txtNobreEvaluacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxTipoEvaluacion = new javax.swing.JComboBox();
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

        jLabel1.setText("Nombre Evaluación");

        jLabel2.setText("Valor");

        jLabel3.setText("Tipo Evaluación");

        jLabel4.setText("Asignatura");

        cbxTipoEvaluacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtAsignatura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNobreEvaluacion, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoEvaluacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAsignatura))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNobreEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbxTipoEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
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
                .addGap(0, 19, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        BotonesNuevo();
//        llenarCBA();
        llenarCBTE();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            int fila = tblEstructuraEvaluacion.getSelectedRow();
            ee.setnombreE(txtNobreEvaluacion.getText().trim());
            ee.setvalor(Double.parseDouble(txtValor.getText().trim()));
            //ee.setIdevaluacion(Integer.parseInt(cbxTipoEvaluacion.getSelectedItem().toString()));
            //ee.setIdasignatura(Integer.parseInt(txtAsignatura.getText().trim()));
            //ee.setIdestructuraevaluacion(Integer.parseInt(tblEstructuraEvaluacion.getValueAt(fila, 0).toString()));
            //ee.actualizarEstructuraEvaluacion();
        }
        LlenarTabla();
        llenarCBTE();
        limpiar();
        Deshabilitar();        
        BotonesInicio();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Eliminar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            int fila = tblEstructuraEvaluacion.getSelectedRow();
            //ee.setIdestructuraevaluacion(Integer.parseInt(tblEstructuraEvaluacion.getValueAt(fila, 0).toString()));
            //ee.eliminarEstructuraEvaluacion();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        llenarCBTE();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){            
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            ee.setnombreE(txtNobreEvaluacion.getText().trim());
            ee.setvalor(Double.parseDouble(txtValor.getText().trim()));
          // ee.setIdevaluacion(Integer.parseInt(cbxTipoEvaluacion.getSelectedItem().toString()));
           // ee.setIdasignatura(Integer.parseInt(txtAsignatura.getText().trim()));
            // ee.guardarEstructuraEvaluacion();
        }
        LlenarTabla();
        llenarCBTE();
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

    private void tblEstructuraEvaluacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstructuraEvaluacionMouseClicked
        if (evt.getButton()==1){
            int fila = tblEstructuraEvaluacion.getSelectedRow();
            Habilitar();
//            llenarCBA();
            llenarCBTE();
            BotonesClick();
            cnx.Conecta();            
            try{                                               
                String SQL = "Select * from estructuraevaluacion_view where idestructuraevaluacion = " + tblEstructuraEvaluacion.getValueAt(fila, 0);
                stm = cnx.conn.createStatement();
                rs = stm.executeQuery(SQL);
                
                rs.next();
                txtNobreEvaluacion.setText(rs.getString("nombreE"));
                txtValor.setText(rs.getString("valor"));
                cbxTipoEvaluacion.setSelectedItem(rs.getString("evaluacion"));
//                cbxAsignatura.setSelectedItem(rs.getString("nombreA"));
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblEstructuraEvaluacionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxTipoEvaluacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEstructuraEvaluacion;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtNobreEvaluacion;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
