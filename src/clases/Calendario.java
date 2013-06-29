package clases;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:53 PM
 */
public class Calendario {

    private String fecha;
    private int idcalendario;
    private int idtipoactividad;
    private int idasignatura;
    Conecta cnx = new Conecta();
    Statement stm;
    ResultSet rs;
    

    public Calendario(){

    }

    public Calendario(String fecha, int idcalendario, int idtipoactividad, int idasignatura) {
        this.fecha = fecha;
        this.idcalendario = idcalendario;
        this.idtipoactividad = idtipoactividad;
        this.idasignatura = idasignatura;
    }

    public String getFecha() {
            return fecha;
    }
    
    public void setFecha(String fecha){
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

    public void actualizarCalendario(){
        cnx.Conecta();
            try{
                String SQL ="update calendario set fecha=?,idtipoactividad=?, idasignatura=?"
                + "where idcalendario=?";
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
    
    public void eliminarCalendario(){
        cnx.Conecta();
            try {
                String SQL = "delete from calendario where idcalendario= " + getIdcalendario();
                stm = cnx.conn.createStatement();            
                int n = stm.executeUpdate(SQL);
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void guardarCalendario(){
        cnx.Conecta();
            try {
                String SQL = "insert into calendario(fecha,idtipoactividad,idasignatura)"
                + "values('"+getFecha()+"','"+getIdtipoactividad()+"','"+getIdasignatura()+"')";
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                    
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
}