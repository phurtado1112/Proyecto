/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Globales;

/**
 *
 * @author PabloAntonio
 */
public class RepEstudiantesIF extends javax.swing.JInternalFrame {
    public Connection conn;
    JasperReport reporte;
    JasperPrint jasperprint;
    JasperViewer visor;
    /**
     * Creates new form repEstudianteIF
     */
    public RepEstudiantesIF() {
        initComponents();
    }
public void reporteEstudianteDetalle() {       
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
            conn=DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");
            
            Map<String,Object> parametros = new HashMap<String,Object>();
            parametros.put("idAsig", Globales.id);
            
            JOptionPane.showMessageDialog(null, "El valor de idasig: " + parametros);
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile("src/reportes/repEstudianteDetalle.jasper"); 
            jasperprint = JasperFillManager.fillReport(reporte, parametros, conn);
            visor = new JasperViewer(jasperprint,false);
            visor.setTitle("Estudiante - CNAE");
            visor.setVisible(true);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en Reporte EstudianteDetalle: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepEstudiantesIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
    
    public void reporteEstudiante() {       
        try {
            Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
            conn=DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");
            
            Map<String,Object> parametros = new HashMap<String,Object>();
            parametros.put("idAsig", Globales.id);
            JOptionPane.showMessageDialog(null, "El valor de idasig: " + parametros);
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile("src/reportes/repEstudiante.jasper"); 
            jasperprint = JasperFillManager.fillReport(reporte, parametros, conn);
            visor = new JasperViewer(jasperprint,false);
            visor.setTitle("Estudiante - CNAE");
            visor.setVisible(true);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en Reporte EstudianteLista: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepEstudiantesIF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rbDetalle = new javax.swing.JRadioButton();
        rbListado = new javax.swing.JRadioButton();

        setTitle("Reporte de Estudiante"); // NOI18N

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estudiantes"));

        buttonGroup1.add(rbDetalle);
        rbDetalle.setText("Detalle");
        rbDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDetalleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbListado);
        rbListado.setText("Listado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbListado)
                .addGap(59, 59, 59)
                .addComponent(rbDetalle)
                .addGap(112, 112, 112))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbListado)
                    .addComponent(rbDetalle))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btnEjecutar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir del Informe?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        
        if(rbDetalle.isSelected()==true){
            this.reporteEstudianteDetalle();
            
        } else if (rbListado.isSelected()==true){
            this.reporteEstudiante();
       }
    
       
}//GEN-LAST:event_btnEjecutarActionPerformed

    private void rbDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbDetalleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDetalle;
    private javax.swing.JRadioButton rbListado;
    // End of variables declaration//GEN-END:variables
}
