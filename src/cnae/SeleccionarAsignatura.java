
package cnae;

import clases.Asignatura;
import clases.Carrera;
import clases.Facultad;
import clases.Universidad;
import util.Conecta;
import util.Globales;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class SeleccionarAsignatura extends JFrame {

    DefaultTableModel modelo;
    DefaultComboBoxModel<String> modelocbxU;
    DefaultComboBoxModel<String> modelocbxF;
    DefaultComboBoxModel<String> modelocbxC;
    Conecta cnx = new Conecta();
    Asignatura a = new Asignatura();
    Carrera c = new Carrera();
    Facultad f = new Facultad();
    Universidad u = new Universidad();
    Statement st;
    ResultSet rs;
    
    public SeleccionarAsignatura() {
        initComponents();
        this.setLocationRelativeTo(null);             
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        if (this.verificarAsignatura()>0){
            llenarTabla();
            ocultaFormulario();
        } else {
//            cbxUniversidad.setModel(new DefaultComboBoxModel<String>(new String[] {}));
//            cbxFacultad.setModel(new DefaultComboBoxModel<String>(new String[] {}));
//            cbxCarrera.setModel(new DefaultComboBoxModel<String>(new String[] {}));   
            muestraFormulario();
            llenarCBUni();
            llenarCBFac();
            llenarCBUni();
        }
    }
    
    private void ocultaFormulario(){
        cbxCarrera.setVisible(false);
        cbxFacultad.setVisible(false);
        cbxUniversidad.setVisible(false);
        txtAsignatura.setVisible(false);
        txtAnio.setVisible(false);
        txtGrupo.setVisible(false);
        txtPeriodo.setVisible(false);            
        lblCarrera.setVisible(false);
        lblFacultad.setVisible(false);
        lblUniversidad.setVisible(false);
        lblAsignatura.setVisible(false);
        lblAnio.setVisible(false);
        lblGrupo.setVisible(false);
        lblPeriodo.setVisible(false);
        pnlAsignatura.setVisible(false);
        btnGuardarAsignatura.setVisible(false);
        tblAsignatura.setEnabled(true); 
    }
    
    private void muestraFormulario(){
        tblAsignatura.setVisible(false);
        jScrollPane1.setVisible(false);
        pnlAsignatura.setVisible(true);        
        cbxUniversidad.setVisible(true);    
        cbxUniversidad.setModel(llenarCBUni());
        cbxFacultad.setVisible(true);
        cbxFacultad.setModel(llenarCBFac());
        cbxCarrera.setVisible(true);
        cbxCarrera.setModel(llenarCBCar());
        txtAsignatura.setVisible(true);
        txtAnio.setVisible(true);
        txtGrupo.setVisible(true);
        txtPeriodo.setVisible(true);
        lblCarrera.setVisible(true);
        lblFacultad.setVisible(true);
        lblUniversidad.setVisible(true);
        lblAsignatura.setVisible(true);
        lblAnio.setVisible(true);
        lblGrupo.setVisible(true);
        lblPeriodo.setVisible(true);
        btnGuardarAsignatura.setVisible(true);
        btnAceptar.setVisible(false);
    }
    
    private int verificarAsignatura(){
        int i=0;
        cnx.Conecta();
        try{
            String SQL = "select idasignatura from asignatura";
            st = cnx.conn.createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()){
                i = rs.getInt("idasignatura");
            }           
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Universidad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return i;
    }

    private DefaultComboBoxModel<String> llenarCBUni() {
        cnx.Conecta();
        try {            
            modelocbxU = new DefaultComboBoxModel<String>();            
            String SQL = "select nombreU from universidad";
            st = cnx.conn.createStatement();            
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                modelocbxU.addElement(rs.getString("nombreU"));
            }
            cbxUniversidad.setModel(modelocbxU);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Universidad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modelocbxU;
    }
    
    private DefaultComboBoxModel<String> llenarCBFac() {
        cnx.Conecta();
        try {            
            modelocbxF = new DefaultComboBoxModel<String>();            
            String SQL = "select nombreF from facultad where iduniversidad = " + u.consultaIdU((String)cbxUniversidad.getSelectedItem());
            st = cnx.conn.createStatement();            
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                modelocbxF.addElement(rs.getString("nombreF"));
            }
            cbxFacultad.setModel(modelocbxF);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Facultad: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modelocbxF;
    }
    
    private DefaultComboBoxModel<String> llenarCBCar() {
        cnx.Conecta();
        try {            
            modelocbxC = new DefaultComboBoxModel<String>();            
            String SQL = "select nombreC from carrera where idfacultad = " + f.consultaId((String)cbxFacultad.getSelectedItem());
            st = cnx.conn.createStatement();            
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                modelocbxC.addElement(rs.getString("nombreC"));
            }
            cbxCarrera.setModel(modelocbxC);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error LlenarCB Carrera: " + ex.getMessage());
        } finally {
            cnx.Desconecta();
        }
        return modelocbxC;
    }   
    
    private void llenarTabla() {
        cnx.Conecta();
        try{
            String [] titulos ={"ID","Asignatura","Cód. Grupo", "Período", 
                "Año", "Carrera", "Facultad", "Universidad"};
            int[] anchos = {30, 160, 80, 90, 40, 160, 180, 190};            
            String SQL = "Select * from asignatura_compl_view";
            modelo = new DefaultTableModel(null, titulos);
            st = cnx.conn.createStatement();
            rs = st.executeQuery(SQL);
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
                modelo.addRow(fila);
            }
            tblAsignatura.setModel(modelo);
            for(int i = 0; i < tblAsignatura.getColumnCount(); i++) {
                tblAsignatura.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            tblAsignatura.changeSelection(0, 1, false, false);
        } catch(SQLException e){
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
            } else if(txtGrupo.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Código de Grupo está vacío,por favor llenarlo");
            val = false;
            } else if(txtAnio.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Año está vacío,por favor llenarlo");
            val = false;
            } else if(txtAnio.getText().trim().length()!=4){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Año debe tener 4 Digitos");
            val = false;
            } else if(txtPeriodo.getText().trim().length()==0){ //Valida campo Apellido
            JOptionPane.showMessageDialog(this, "El campo de texto Período está vacío,por favor llenarlo");
            val = false;
        } else {
            val=true;
        }       
        return val;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAsignatura = new javax.swing.JPanel();
        lblCarrera = new javax.swing.JLabel();
        lblUniversidad = new javax.swing.JLabel();
        lblFacultad = new javax.swing.JLabel();
        cbxCarrera = new javax.swing.JComboBox();
        cbxUniversidad = new javax.swing.JComboBox();
        cbxFacultad = new javax.swing.JComboBox();
        lblAsignatura = new javax.swing.JLabel();
        txtAsignatura = new javax.swing.JTextField();
        lblAnio = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        lblGrupo = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lblPeriodo = new javax.swing.JLabel();
        txtPeriodo = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnGuardarAsignatura = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsignatura = new javax.swing.JTable();

        pnlAsignatura.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignatura"));

        lblCarrera.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCarrera.setText("Carrera: ");

        lblUniversidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUniversidad.setText("Universidad: ");

        lblFacultad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFacultad.setText("Facultad: ");

        cbxUniversidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUniversidadActionPerformed(evt);
            }
        });

        cbxFacultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFacultadActionPerformed(evt);
            }
        });

        lblAsignatura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsignatura.setText("Asignatura");

        lblAnio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAnio.setText("Año");

        lblGrupo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGrupo.setText("Grupo");

        lblPeriodo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPeriodo.setText("Período");

        javax.swing.GroupLayout pnlAsignaturaLayout = new javax.swing.GroupLayout(pnlAsignatura);
        pnlAsignatura.setLayout(pnlAsignaturaLayout);
        pnlAsignaturaLayout.setHorizontalGroup(
            pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignaturaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCarrera)
                    .addComponent(lblUniversidad)
                    .addComponent(lblFacultad)
                    .addComponent(lblAsignatura))
                .addGap(18, 18, 18)
                .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbxUniversidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxFacultad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAsignaturaLayout.createSequentialGroup()
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGrupo)
                            .addComponent(lblAnio))
                        .addGap(25, 25, 25)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtAnio)))
                    .addGroup(pnlAsignaturaLayout.createSequentialGroup()
                        .addComponent(lblPeriodo)
                        .addGap(18, 18, 18)
                        .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAsignaturaLayout.setVerticalGroup(
            pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignaturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAsignaturaLayout.createSequentialGroup()
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUniversidad)
                            .addComponent(cbxUniversidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFacultad)
                            .addComponent(cbxFacultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCarrera)
                            .addComponent(cbxCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAsignatura)
                            .addComponent(txtAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAsignaturaLayout.createSequentialGroup()
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnio)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGrupo)
                            .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlAsignaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeriodo)
                            .addComponent(txtPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnGuardarAsignatura.setText("Guardar");
        btnGuardarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAsignaturaActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblAsignatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Asignatura", "Codigo de Grupo", "Periodo", "Año"
            }
        ));
        jScrollPane1.setViewportView(tblAsignatura);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardarAsignatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(401, 401, 401)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAsignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnGuardarAsignatura)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cbxUniversidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUniversidadActionPerformed
        this.cbxFacultad.setModel(llenarCBFac());
}//GEN-LAST:event_cbxUniversidadActionPerformed

private void cbxFacultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFacultadActionPerformed
        this.cbxCarrera.setModel(llenarCBCar());
}//GEN-LAST:event_cbxFacultadActionPerformed


private void btnGuardarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAsignaturaActionPerformed
    if (validar()==true){
        int i = JOptionPane.showConfirmDialog(null, "Desea Guardar?","Confirmar",
            JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
            if(i==JOptionPane.OK_OPTION){
                a.setnombreA(txtAsignatura.getText().trim());
                a.setgrupo(this.txtGrupo.getText().trim());
                a.setanio(Integer.parseInt(this.txtAnio.getText().trim()));
                a.setperiodo(this.txtPeriodo.getText().trim());
                a.setIdcarrera(c.consultaIdCa(this.cbxCarrera.getSelectedItem().toString().trim()));
                a.guardaAsignatura();
                new SeleccionarAsignatura().setVisible(true);
            }
        }
}//GEN-LAST:event_btnGuardarAsignaturaActionPerformed

private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
    Globales.id = Integer.parseInt(this.tblAsignatura.getValueAt(this.tblAsignatura.getSelectedRow(), 0).toString());    
    this.setVisible(false);
    new Cnae().setVisible(true);
}//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int i = JOptionPane.showConfirmDialog(null, "Desea Salir?","Confirmar",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
        if(i==JOptionPane.OK_OPTION){
            System.exit(0);}
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardarAsignatura;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> cbxFacultad;
    private javax.swing.JComboBox<String> cbxUniversidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblFacultad;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblUniversidad;
    private javax.swing.JPanel pnlAsignatura;
    private javax.swing.JTable tblAsignatura;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtPeriodo;
    // End of variables declaration//GEN-END:variables

}
