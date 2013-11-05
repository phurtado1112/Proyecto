/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import clases.Asignatura;
import clases.Calendario;
import clases.EstructuraEvaluacion;
import clases.Evaluaciones;
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
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Pablo
 */
public class NotasIF extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    DefaultComboBoxModel modeloCombo;
    EstructuraEvaluacion Estev = new EstructuraEvaluacion();
    Asignatura a = new Asignatura();
    Notas N = new Notas();
    Calendario C = new Calendario();
    Evaluaciones E = new Evaluaciones();
    Conecta cnx = new Conecta();
    ResultSet rs;
    Statement stm;
    int id = 1;
    /**
     * Creates new form Evaluación
     */
    public NotasIF() {
        initComponents();
        cnx.Conecta();
        limpiar();
        llenarCBEstEva();
        llenarCBEva();
        llenarCBFecha();
        BotonesInicio();       
        LlenarTabla();
        setJTexFieldChanged(TxtBuscar);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    
    private void limpiar(){
        CbxEstrucEvaluacion.setSelectedItem(-1);
    }
    
    private void Deshabilitar() {        
        CbxEstrucEvaluacion.setEnabled(false);
    }
    
    private void Habilitar(){
        CbxEstrucEvaluacion.setEnabled(true);
    }
    
    private void BotonesInicio(){
        btnGuardar.setEnabled(true);
        btnIntroducir.setEnabled(true);       
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesModificar(){
        btnGuardar.setEnabled(false);
        btnIntroducir.setEnabled(false);      
        btnCancelar.setEnabled(true);
    }
    
    private void BotonesClick(){
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(true);
    }
    
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

    } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + e.getMessage());
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
                    + "fecha = '" + CbxFecha.getSelectedItem() + "' and "
                    + "nombreEs = " + "'" + CbxEstrucEvaluacion.getSelectedItem() + "'";
            
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
             
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla notas: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private void llenarCBEva() {
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
            CbxEvaluacion.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBEva: " + ex.getMessage());
        }
        LlenarTabla();
        cnx.Desconecta();
    }
    
     private void llenarCBEstEva() {
        cnx.Conecta();
        try {            
            modeloCombo = new DefaultComboBoxModel();            
            String SQL = "select nombreEs from estructuraevaluacion where idevaluacion = " + E.ObtenerIDevaluacion(CbxEvaluacion.getSelectedItem().toString());
            stm = cnx.conn.createStatement();            
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombreEs"));
            }
            rs.close();
            CbxEstrucEvaluacion.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB: " + ex.getMessage());
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
            CbxFecha.setModel(modeloCombo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCBFecha: " + ex.getMessage());
        }
        LlenarTabla();
        cnx.Desconecta();
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
        CbxEstrucEvaluacion = new javax.swing.JComboBox();
        TxtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbxFecha = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        CbxEvaluacion = new javax.swing.JComboBox();
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
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Evaluación"));

        jLabel1.setText("Evaluación");

        CbxEstrucEvaluacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxEstrucEvaluacionItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar");

        jLabel3.setText("Fecha");

        CbxFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CbxFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxFechaItemStateChanged(evt);
            }
        });

        jLabel4.setText("Estructura Evaluuacion");

        CbxEvaluacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CbxEvaluacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxEvaluacionItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(CbxEvaluacion, javax.swing.GroupLayout.Alignment.LEADING, 0, 196, Short.MAX_VALUE)
                        .addComponent(CbxFecha, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(CbxEstrucEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CbxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CbxEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CbxEstrucEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
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

        btnIntroducir.setText("Introducir");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnGuardar)
                .addGap(30, 30, 30)
                .addComponent(btnIntroducir)
                .addGap(127, 127, 127)
                .addComponent(btnCancelar)
                .addGap(27, 27, 27)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
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
       
        try
        {
            for (int f = 0; f < filas; f++) {
                
            N.GuardarNotas(modelo.getValueAt(f, 2).toString(),
                    Estev.consultaIdEstEva(CbxEstrucEvaluacion.getSelectedItem().toString()) ,
                    modelo.getValueAt(f, 0).toString(), C.ConsultarIDCal(CbxFecha.getSelectedItem().toString()));
                    
            }
          JOptionPane.showMessageDialog(null, "Datos Guardados Exitosamente");
        }
            
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error guardar Asistencia: " + e.getMessage());
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

    private void CbxEstrucEvaluacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxEstrucEvaluacionItemStateChanged

        
         if(evt.getStateChange() == ItemEvent.SELECTED)
        {
           
            LlenarTablaSegunTxt();

        }
        
    }//GEN-LAST:event_CbxEstrucEvaluacionItemStateChanged

    private void CbxFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxFechaItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
             
            LlenarTablaSegunTxt();
          
        }
    }//GEN-LAST:event_CbxFechaItemStateChanged

    private void CbxEvaluacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxEvaluacionItemStateChanged
         if(evt.getStateChange() == ItemEvent.SELECTED)
        {
           llenarCBEstEva();
          
        }
    }//GEN-LAST:event_CbxEvaluacionItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CbxEstrucEvaluacion;
    private javax.swing.JComboBox CbxEvaluacion;
    private javax.swing.JComboBox CbxFecha;
    private javax.swing.JTable TblNotas;
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIntroducir;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
