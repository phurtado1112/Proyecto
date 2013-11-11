/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;
import clases.Universidad;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
//import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import util.Valida;

/**
 *
 * @author PabloAntonio
 */
public class UniversidadIF extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    Conecta cnx = new Conecta();
    Valida va = new Valida();
    Universidad u = new Universidad();
    //Statement stm;
    ResultSet rs;
//    private FileInputStream fis;
//    private int LongitudBit;
    PreparedStatement ps;
//    Universidad univer = new Universidad();

    /**
     * Creates new form UniversidadIF
     */
    public UniversidadIF() {
        initComponents();
        cnx.Conecta();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jLabel3.setVisible(false);
        BtnBuscarLogo.setVisible(false);
        LblLogo.setVisible(false);
        jPanel2.setVisible(false);
    }

    public void limpiar(){
        txtUniversidad.setText("");
        txtSiglas.setText("");
        LblLogo.setIcon(null);
    }
    
    private void Deshabilitar() {
        txtUniversidad.setEnabled(false);
        txtSiglas.setEnabled(false);
        BtnBuscarLogo.setEnabled(false);
    }
    
    public void Habilitar(){
        txtUniversidad.setEnabled(true);
        va.SoloLetras(txtUniversidad); 
        va.SeleccionarTodo(txtUniversidad);
        txtSiglas.setEnabled(true);
        va.LetrasSinEspacios(txtSiglas);
        va.SeleccionarTodo(txtSiglas);
        txtUniversidad.requestFocus();
        BtnBuscarLogo.setEnabled(true);
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
        int[] anchos = {20, 250, 80};
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Universidad","Siglas"};
            String SQL = "Select * from universidad";            
            
            model = new DefaultTableModel(null, titulos);
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            String [] fila = new String[3];
            while(rs.next()){
                fila[0] = rs.getString("iduniversidad");
                fila[1] = rs.getString("nombreU");
                fila[2] = rs.getString("siglas");
                model.addRow(fila);
            }           
            tblUniversidad.setModel(model);
            for(int i = 0; i < tblUniversidad.getColumnCount(); i++) {
                tblUniversidad.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            
            DefaultTableCellRenderer centraCelda = new DefaultTableCellRenderer();
            centraCelda.setHorizontalAlignment(SwingConstants.CENTER);
            tblUniversidad.getColumnModel().getColumn(0).setHeaderRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(2).setHeaderRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(0).setCellRenderer(centraCelda);
            tblUniversidad.getColumnModel().getColumn(2).setCellRenderer(centraCelda);
        } catch(SQLException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error LlenarTabla Universidad: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }
    
    private boolean validar(){
        boolean val;
        if(txtUniversidad.getText().trim().length()==0){ //Valida campo Nombre
            JOptionPane.showMessageDialog(this, "El campo de texto Universidad está vacío,por favor llenarlo");
            val = false;
        } else if(txtSiglas.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Siglas está vacío,por favor llenarlo");
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
        txtUniversidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSiglas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtnBuscarLogo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        LblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUniversidad = new javax.swing.JTable();
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
        setTitle("Catálogo de Universidad");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Universidad"));

        jLabel1.setText("Universidad");

        jLabel2.setText("Siglas");

        jLabel3.setText("Escoger Logotipo:");

        BtnBuscarLogo.setText("Buscar");
        BtnBuscarLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarLogoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 0, 0)));

        LblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblLogoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(BtnBuscarLogo)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(BtnBuscarLogo)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblUniversidad.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUniversidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUniversidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUniversidad);
        if (tblUniversidad.getColumnModel().getColumnCount() > 0) {
            tblUniversidad.getColumnModel().getColumn(0).setResizable(false);
            tblUniversidad.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblUniversidad.getColumnModel().getColumn(1).setResizable(false);
            tblUniversidad.getColumnModel().getColumn(1).setPreferredWidth(320);
            tblUniversidad.getColumnModel().getColumn(2).setResizable(false);
            tblUniversidad.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnActualizar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Habilitar();
        limpiar();
        BotonesNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validar()==true){        
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){                
                int fila = tblUniversidad.getSelectedRow();
                u.setnombreU(txtUniversidad.getText().trim());
                u.setSiglas(txtSiglas.getText().trim());
                //u.setLogo(pLogo.get...);
                u.setIduniversidad(Integer.parseInt(tblUniversidad.getValueAt(fila, 0).toString())); 
                u.actualizarUniversidad();
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
            int fila = tblUniversidad.getSelectedRow();
            u.setIduniversidad(Integer.parseInt(tblUniversidad.getValueAt(fila, 0).toString()));
            u.eliminarUniversidad();
        }
        limpiar();
        Deshabilitar();
        LlenarTabla();
        BotonesInicio();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
                u.setnombreU(txtUniversidad.getText().trim());
                u.setSiglas(txtSiglas.getText().trim());
//                u.setLogo((Blob)(FileInputStream)LblLogo.getIcon());
                u.guardarUniversidad();
                //u.guardarUniversidad(txtUniversidad.getText().trim(),txtSiglas.getText().trim(),this.fis,this.LongitudBit);
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
    
    private void tblUniversidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUniversidadMouseClicked
        if (evt.getButton()==1){
            int fila = tblUniversidad.getSelectedRow();
            cnx.Conecta();
            try{
                Habilitar();                               
                String SQL = "Select * from universidad where iduniversidad = " + tblUniversidad.getValueAt(fila, 0);
                ps = cnx.conn.prepareStatement(SQL);
                rs = ps.executeQuery();
                while (rs.next()){
                txtUniversidad.setText(rs.getString("nombreU"));
                txtSiglas.setText(rs.getString("siglas"));
                }                
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error Universidad Mouse Cliked: " + e.getMessage());
            } finally {
                BotonesClick();
                cnx.Desconecta();
            }
        }
    }//GEN-LAST:event_tblUniversidadMouseClicked

    private void LblLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblLogoMouseClicked
//    JFileChooser buscar = new JFileChooser();
//        buscar.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int estado = buscar.showOpenDialog(null);
//        if (estado == JFileChooser.APPROVE_OPTION){
//            
//            try{
//                fis = new FileInputStream(buscar.getSelectedFile());
//                this.LongitudBit = (int)buscar.getSelectedFile().length();
//                
//                Image icono = ImageIO.read(buscar.getSelectedFile()).getScaledInstance(
//                              LblLogo.getWidth(), LblLogo.getHeight(), Image.SCALE_DEFAULT);
//                LblLogo.setIcon(new ImageIcon(icono));
//                LblLogo.updateUI();
//                        
//                
//            }catch(FileNotFoundException ex){
//                
//                ex.printStackTrace();
//                
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
        
        
    }//GEN-LAST:event_LblLogoMouseClicked

    private void BtnBuscarLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarLogoActionPerformed
//        JFileChooser buscar = new JFileChooser();
//        buscar.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int estado = buscar.showOpenDialog(null);
//        if (estado == JFileChooser.APPROVE_OPTION){            
//            try{
//                fis = new FileInputStream(buscar.getSelectedFile());
//                this.LongitudBit = (int)buscar.getSelectedFile().length();
//                
//                Image icono = ImageIO.read(buscar.getSelectedFile()).getScaledInstance(
//                              LblLogo.getWidth(), LblLogo.getHeight(), Image.SCALE_DEFAULT);
//                LblLogo.setIcon(new ImageIcon(icono));
//                LblLogo.updateUI();                                        
//            }catch(FileNotFoundException ex){                
//                ex.printStackTrace();                
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }//GEN-LAST:event_BtnBuscarLogoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarLogo;
    private javax.swing.JLabel LblLogo;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUniversidad;
    private javax.swing.JTextField txtSiglas;
    private javax.swing.JTextField txtUniversidad;
    // End of variables declaration//GEN-END:variables
}
