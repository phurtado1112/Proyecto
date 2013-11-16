
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Villarreal
 */
public class Reestaurar extends javax.swing.JInternalFrame {
String origen=new String();  //  @jve:decl-index=0:
String destino=new String();  //  @jve:decl-index=0:
   

    public Reestaurar() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
public void copiarFichero(String orig,String desti)
	{
		File ArchivoOrigen = new File(orig);
		File ArchivoDestino = new File(desti);
		try {
                    OutputStream out;
                    try (InputStream in = new FileInputStream(ArchivoOrigen)) {
                        out = new FileOutputStream(ArchivoDestino+"/cnae.sqlite");
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        } }
		  out.close();
	           JOptionPane.showMessageDialog(null,"Reestaurado correctamente");
		} catch (IOException ioe){
                   JOptionPane.showMessageDialog(null,"Error al reestaurar el archivo");
		  ioe.printStackTrace();
		}
	}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBuscarArchivo = new javax.swing.JButton();
        btnRutaRespaldo = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        txtdestino = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reestablecer Datos");

        btnguardar.setText("Reestablecer");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reestablecer Base de Datos"));

        btnBuscarArchivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscarArchivo.setText("Buscar archivo");
        btnBuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArchivoActionPerformed(evt);
            }
        });

        btnRutaRespaldo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRutaRespaldo.setText("Reestablecer a");
        btnRutaRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaRespaldoActionPerformed(evt);
            }
        });

        txtdestino.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRutaRespaldo)
                        .addGap(18, 18, 18)
                        .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscarArchivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarArchivo)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRutaRespaldo)
                    .addComponent(txtdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btnguardar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
         this.copiarFichero(origen, destino);
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
         int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnRutaRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaRespaldoActionPerformed
        if(evt.getSource()==btnRutaRespaldo)
        {
            //En este caso indicamo la ruta donde estara se guardara el archivo
            JFileChooser fileChooser = new JFileChooser("..\\cnaes");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); /*****Directorios solamente***///
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                destino = fileChooser.getSelectedFile().toString();
                String nombre= fileChooser.getSelectedFile().getAbsolutePath().toString();
                txtdestino.setText(nombre);
            }
        }
    }//GEN-LAST:event_btnRutaRespaldoActionPerformed

    private void btnBuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArchivoActionPerformed
        if(evt.getSource()==btnBuscarArchivo)
        {
            // En este caso no le agregamos filtro, porque queremos que lo encuentre donde tiene el respaldo
            JFileChooser fileChooser = new JFileChooser("C:\\");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("sqlite", "sqlite"); /*****sqlite****/
            fileChooser.setFileFilter(filter);
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                origen = fileChooser.getSelectedFile().getAbsolutePath().toString();
                String nombre= fileChooser.getSelectedFile().getName().toString();
                txtbuscar.setText(nombre);
            }
        }
    }//GEN-LAST:event_btnBuscarArchivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarArchivo;
    private javax.swing.JButton btnRutaRespaldo;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtdestino;
    // End of variables declaration//GEN-END:variables
}
