package clases;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:53 PM
 */
public class Calendario {

    private Date fecha;
    private int idcalendario;
    private int idtipoactividad;
    private int idasignatura;
    Conecta cnx = new Conecta();
    Statement stm;
    ResultSet rs;
    

    public Calendario(){

    }

    public Calendario(Date fecha, int idcalendario, int idtipoactividad, int idasignatura) {
        this.fecha = fecha;
        this.idcalendario = idcalendario;
        this.idtipoactividad = idtipoactividad;
        this.idasignatura = idasignatura;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setfecha(Date fecha){
            this.fecha = fecha;
    }

    public int getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(int idcalendario) {
        this.idcalendario = idcalendario;
    }

    public int getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(int idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    public void ActualizarCalendario(){
        cnx.Conecta();
            try{
                String SQL ="update calendario set fecha=?,idtipoactividad=?, idasignatura=?"
                + "where idcalendario=?";
                //int fila = tblCalendario.getSelectedRow();
//                String dato = (String)tblCalendario.getValueAt(fila, 0);
//                PreparedStatement ps = cnx.conn.prepareStatement(SQL);
//                ps.setString(1, ((JTextField)jdcFecha.getDateEditor().getUiComponent()).getText().trim());
//                ps.setInt(2, ta.consultaIdTA(cbxTipoActividad.getSelectedItem().toString()));
//                ps.setInt(3, a.consultaIdA(txtAsignatura.getText().toString()));
//                ps.setString(4, dato);
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void EliminarCalendario(){
        
    }
    
    public void GuardarCalendario(){
        
    }
}