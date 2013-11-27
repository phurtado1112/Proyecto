
package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Villarreal
 */
public class RecuperacionDeDatos extends javax.swing.JInternalFrame {
String path="";//creamos una variable global para guardar el path
 
   
    public RecuperacionDeDatos() {
        initComponents();
    }
    
     private void open() throws IOException {
        
        //Empezamos implementando la clase JFileChooser para abrir archivos
        JFileChooser JFC = new JFileChooser();
        
        //Filtro que muestra solo los archivos con extension *.sqlite
        JFC.setFileFilter(new FileNameExtensionFilter("todos los archivos *.sqlite", "sqlite","SQLITE"));
        
        //Comprueba si se ha presionado al boton aceptar
        int abrir = JFC.showDialog(null, "Abrir");
        if (abrir == JFileChooser.APPROVE_OPTION) {
            FileReader FR = null;
            BufferedReader BR = null;

            try {
                //Abro el fichero y creo un BufferedReader para hacer
                //una lectura comoda (tener el metodo readLine();)
                File archivo = JFC.getSelectedFile();//abre un archivo .lengf
                
                //Evitar abrir archivo con otra extension que no sea *.sqlite
                String PATH = JFC.getSelectedFile().getAbsolutePath();
                if(PATH.endsWith(".sqlite")||PATH.endsWith(".sqlite")){
                    
                    FR = new FileReader(archivo);
                    BR = new BufferedReader(FR);
                    
                //Leyendo el archivo
               String linea;//variable para leer linea por linea el archivo de entrada
               if(path.compareTo(archivo.getAbsolutePath())==0){
               JOptionPane.showMessageDialog(this, "Archivo Abierto","Error", JOptionPane.ERROR_MESSAGE);
               }else{
                    path = archivo.getAbsolutePath();
                    jTextArea1.setText(null); //Limpiamos el textArea antes de sobreescribir
                    while((linea=BR.readLine())!=null){  //Cuando termina el texto del archivo?
                    jTextArea1.append(linea+"\n");  //Utilizamos append para escribir en el textArea
                        }
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Archivo no soportado","Error", JOptionPane.ERROR_MESSAGE);
                    open();
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            
            //Cerramos el fichero, para asegurar que se cierra tanto
            // Si todo va bien si salta una excepcion
            } finally {
                try {
                    if(null!= FR){
                        FR.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnRescatar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnDB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("NOTA: ");

        btnRescatar.setText("Reestablecer");
        btnRescatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRescatarActionPerformed(evt);
            }
        });

        jLabel2.setText("La Base de Datos se guardará dentro del Proyecto");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnDB.setText("Buscar Base de datos");
        btnDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDBActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(btnDB)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnDB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRescatar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRescatar)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDBActionPerformed
        try {
        open();
    
     } catch (IOException ex) {
        Logger.getLogger(RecuperacionDeDatos.class.getName()).log(Level.SEVERE, null, "Error IOException"+ ex);
    }
    }//GEN-LAST:event_btnDBActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRescatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRescatarActionPerformed
       String texto = jTextArea1.getText();//variable para comparacion
        
       //Compara si en el JTextArea hay texto sino muestrtra un mensaje en pantalla
       if (texto.matches("[[ ]*[\n]*[\t]]*")) {
           JOptionPane.showMessageDialog(null,"No hay archivo para Respaldar!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            JFileChooser fileChooser = new JFileChooser("src\\BD\\");
            //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.EDU", "edu","EDU"));//filtro para ver solo archivos .edu
            int seleccion = fileChooser.showSaveDialog(null);
            try{
                if (seleccion == JFileChooser.APPROVE_OPTION){  //Comprueba si ha presionado el boton de aceptar
                    File JFC = fileChooser.getSelectedFile();
                    String PATH = JFC.getAbsolutePath();       //Obtenemos el path del archivo a guardar
                    PrintWriter printwriter = new PrintWriter(JFC);
                    printwriter.print(jTextArea1.getText());   //Escribe en el archivo todo lo que se encuentre en el JTextArea
                    printwriter.close();              //Por ultimo, cierra el archivo
                    
                    //Comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                    if(!(PATH.endsWith(".sqlite"))){
                        File temp = new File(PATH+".sqlite");
                        JFC.renameTo(temp);//renombramos el archivo
                    }
                    
                    JOptionPane.showMessageDialog(null,"Restauracion exitosa!", "¡AVISO!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception e){//por alguna excepcion salta un mensaje de error
                JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }           

    

    }//GEN-LAST:event_btnRescatarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDB;
    private javax.swing.JButton btnRescatar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
