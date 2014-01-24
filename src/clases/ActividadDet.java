package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:02 PM
 */
public class ActividadDet {
    
    private int idactividaddet;
    private String actividaddet;
    private int idactividad;
    ResultSet rs;
    PreparedStatement ps;
    Conecta cnx = new Conecta();    

    public ActividadDet(){

    }

    public ActividadDet(int idevaluacion,String evaluacion, int idasignatura) {
        this.idactividaddet = idevaluacion;
        this.actividaddet = evaluacion;
        this.idactividad = idasignatura;
    }

    public String getActividadDet(){
            return actividaddet;
    }

    public void setActividadDet(String actividaddet){
            this.actividaddet = actividaddet;
    }

    public int getIdActividadDet() {
        return idactividaddet;
    }

    public void setIdActividadDet(int idactividaddet) {
        this.idactividaddet = idactividaddet;
    }

    public int getIdActividad() {
        return idactividad;
    }

    public void setIdActividad(int idactividad) {
        this.idactividad = idactividad;
    }
    
    public void actualizarActividadDet(){
        cnx.Conecta();
            try{
                String SQL ="update actividaddet set actividaddet=?, idactividad=?"
                + "where idactividaddet=?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setString(1, actividaddet);
                ps.setInt(2, idactividad);
                ps.setInt(3, idactividaddet);                       
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Detalle de Actividad: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarActividadDet(){
        cnx.Conecta();
            try {
                String SQL = "delete from actividaddet where idactividaddet = ?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, idactividaddet);

                int n = ps.executeUpdate();                
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error eliminando Detalle de Actividad: " + e.getMessage());
            } finally {                
                cnx.Desconecta();        
            }
    }
    
    public void guardarEvaluacionDet(){
        cnx.Conecta();
            try {
                String SQL = "insert into actividaddet(actividaddet,idactividad) "
                + "values(?,?)";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setString(1, actividaddet);
                ps.setInt(2, idactividad);

                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Detalle Actividad: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public int consultaId(String ActivDet){
    int id = 0;
    cnx.Conecta();
    try{
        String SQL = "Select idactividaddet from actividaddet where actividaddet = "+"\""+ActivDet+"\"";
        ps = cnx.conn.prepareStatement(SQL);
        
        rs = ps.executeQuery();            
        while(rs.next()){
            id = rs.getInt("idactividaddet");
        }
    } catch(SQLException | HeadlessException e){
        JOptionPane.showMessageDialog(null, "Error consulta ID Detalle Actividad: " + e.getMessage());
    }
    cnx.Desconecta();       
    return id;
    }
    
    public String consultaActividadDet(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select actividaddet from actividaddet where idactividaddet="+id;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("actividaddet");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Detalle de Actividad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaActividadDet(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select actividaddet from actividaddet";
            ps = cnx.conn.prepareStatement(SQL);
            
            rs = ps.executeQuery();            
            while(rs.next()){
                ls.add(rs.getString("actividaddet"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaActividad Detalle: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
    
    public int validarRegistro(int idactividad, String actividaddet) {
        int cantidad = 0;
        cnx.Conecta();
        try {
            String SQL = "select count(*) from actividaddet where idactividad="+idactividad+" and actividaddet like '"+actividaddet+"'";
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            cantidad = rs.getInt("count(*)");
            ps.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Validación de Registro a Clase: " + e.getMessage());
        }
        cnx.Desconecta();
        return cantidad;
    }
}