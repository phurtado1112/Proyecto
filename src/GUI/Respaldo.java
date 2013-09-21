
package GUI;

//JFileChooser
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.FileNotFoundException;
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
public class Respaldo extends javax.swing.JInternalFrame {
String path="";//creamos una variable global para guardar el path
  
    public Respaldo() {
        initComponents();
    }
    
    private void open() throws IOException {
        
        //Empezamos implementando la clase JFileChooser para abrir archivos
        JFileChooser JFC = new JFileChooser();
        
        //Filtro que muestra solo los archivos con extension *.sqlite
        JFC.setFileFilter(new FileNameExtensionFilter("todos los archivos *.sqlite", ".sqlite","SQLITE"));
        
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
                    jTextRastreo.setText(null); //Limpiamos el textArea antes de sobreescribir
                    while((linea=BR.readLine())!=null){  //Cuando termina el texto del archivo?
                    jTextRastreo.append(linea+"\n");  //Utilizamos append para escribir en el textArea
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBaseDeDatos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextRastreo = new javax.swing.JTextArea();

        setVisible(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nota:");

        jLabel3.setText("La ruta de respaldo tambien la puede guardar en su USB");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Respaldo"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Respaldo de Datos");

        btnBaseDeDatos.setText("Rastrear Base de datos");
        btnBaseDeDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaseDeDatosActionPerformed(evt);
            }
        });

        jTextRastreo.setColumns(20);
        jTextRastreo.setRows(5);
        jScrollPane1.setViewportView(jTextRastreo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(btnBaseDeDatos)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBaseDeDatos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3)
                        .addGap(43, 43, 43)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(btnSave))
                    .addComponent(btnSalir))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBaseDeDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaseDeDatosActionPerformed
        try {
        open();
    
     } catch (IOException ex) {
        Logger.getLogger(Respaldo.class.getName()).log(Level.SEVERE, null, "Error IOException"+ ex);
    }

    }//GEN-LAST:event_btnBaseDeDatosActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
String texto = jTextRastreo.getText(); //Variable para comparacion
        
       //Compara si en el JTextRespaldo hay texto sino muestrtra un mensaje en pantalla
       if (texto.matches("[[ ]*[\n]*[\t]]*")) {
           JOptionPane.showMessageDialog(null,"No hay archivo para guardar!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            JFileChooser fileChooser = new JFileChooser();
            //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.EDU", "edu","EDU"));//filtro para ver solo archivos .edu
            int seleccion = fileChooser.showSaveDialog(null);
            try{
                if (seleccion == JFileChooser.APPROVE_OPTION){  //Comprueba si ha presionado el boton de aceptar
                    File JFC = fileChooser.getSelectedFile();
                    String PATH = JFC.getAbsolutePath();       //Obtenemos el path del archivo a guardar
                    PrintWriter printwriter = new PrintWriter(JFC);
                    printwriter.print(jTextRastreo.getText());   //Escribe en el archivo todo lo que se encuentre en el JTextArea
                    printwriter.close();              //Por ultimo, cierra el archivo
                    
                    //Comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                    if(!(PATH.endsWith(".sqlite"))){
                        File temp = new File(PATH+".sqlite");
                        JFC.renameTo(temp);//renombramos el archivo
                    }
                    
                    JOptionPane.showMessageDialog(null,"Respaldo exitoso!", "¡AVISO!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception e){//por alguna excepcion salta un mensaje de error
                JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Error" + e, JOptionPane.ERROR_MESSAGE);
            }
        }
       
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaseDeDatos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextRastreo;
    // End of variables declaration//GEN-END:variables
}
