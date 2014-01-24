package GUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import util.Conecta;

/**
 *
 * @author Villarreal
 */
public class CambioContrasena extends javax.swing.JInternalFrame {
    Conecta cnx=new Conecta();
    DefaultTableModel model;
    Statement stm;
    ResultSet rs;
   
    public CambioContrasena() {
        initComponents();
           llenarTabla();
           setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);        
           //JOptionPane.showMessageDialog(null,"Por favor seleccionar usuario");          
    }

    public String ContrasenaVieja(){        
          String cAnterior = null;
          cnx.Conecta();
        try{
            String SQL= "select contrasena from docente";
            stm=cnx.conn.createStatement();
            rs=stm.executeQuery(SQL);
            cAnterior = rs.getString("contrasena");
            rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener contraseña vieja "+ex.getMessage());
        }
            cnx.Desconecta();
            return cAnterior; 
    }
    
    private boolean Validar(){
             boolean val;
             String cAnteriorPassText=pswContraActual.getText();
             String cAnterior=ContrasenaVieja();
      
         if(!cAnteriorPassText.equals(cAnterior)){ //Compara la contraseña vieja con la actual
            JOptionPane.showMessageDialog(this, "La contraseña de usuario no es correcta");
            val = false;
          
         //Compara si las contraseñas nueva con la de confirmacion son iguales
        }else if(pswContraNueva.getText().equals(pswConfirmar.getPassword().toString())){
        JOptionPane.showMessageDialog(this, "Las contraseñas nuevas no coinciden");
            val = false;
        }else{
            val=true;
        }
            return val;
        }
    
    private void llenarTabla(){ //Metodo de llenar tabla
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Usuario"};
            String SQL="Select * from docente";
            model=new DefaultTableModel(null,titulos);
            PreparedStatement ps=cnx.conn.prepareStatement(SQL);
            rs=ps.executeQuery();
            String [] fila=new String [3];
            while (rs.next()){
                fila[0]=rs.getString("iddocente");
                fila[1]=rs.getString("usuario");
                fila[2]=rs.getString("contrasena");
          
                model.addRow(fila);
             tblCambio.setModel(model);
            }   
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error Llenar Tabla Cambio Contraseña" + e.getMessage());
        }
        cnx.Desconecta();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCambio = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pswContraActual = new javax.swing.JPasswordField();
        pswContraNueva = new javax.swing.JPasswordField();
        pswConfirmar = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setTitle("Cambio Contraseña");
        setVisible(true);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblCambio.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCambioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCambio);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambio Contraseña"));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Contraseña Nueva");

        txtNombre.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Contraseña Actual");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Confirmar Contraseña");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pswConfirmar)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addComponent(pswContraNueva))
                    .addComponent(pswContraActual, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswContraActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pswContraNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(btnActualizar)
                .addGap(61, 61, 61)
                .addComponent(btnCancelar))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnCancelar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       if (Validar()==true){        
        int i = JOptionPane.showConfirmDialog(null, "Desea Actualizar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
        cnx.Conecta();
        try{
          String SQL="update docente set contrasena=?"
          + " where iddocente=?";
          int fila= tblCambio.getSelectedRow();
          String dato=(String) tblCambio.getValueAt(fila,0);
          PreparedStatement ps=cnx.conn.prepareStatement(SQL);
          ps.setString(1,pswContraNueva.getText());
          ps.setString(2,dato);

          int n=ps.executeUpdate();
          if (n>0){
              JOptionPane.showMessageDialog(null,"Contraseña actualizada correctamente");
          }
          }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
          }
        }
        cnx.Desconecta();
        llenarTabla();
     }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            this.doDefaultCloseAction();}
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblCambioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCambioMouseClicked
       if (evt.getButton()==1){
            int fila=tblCambio.getSelectedRow();
            cnx.Conecta();
            try{

                String SQL="Select * from docente where iddocente = "+tblCambio.getValueAt(fila,0);
                stm=cnx.conn.createStatement();
                rs=stm.executeQuery(SQL);

                rs.next();
                txtNombre.setText(rs.getString("usuario"));
                //pswContrasena.setText(rs.getString("contrasena"));
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error Docente Mouse Cliked " +e.getMessage());
            }
            cnx.Desconecta();
        }
    }//GEN-LAST:event_tblCambioMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField pswConfirmar;
    private javax.swing.JPasswordField pswContraActual;
    private javax.swing.JPasswordField pswContraNueva;
    private javax.swing.JTable tblCambio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
