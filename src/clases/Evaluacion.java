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
 * @created 27-mar-2013 10:45:07 PM
 */
public class Evaluacion {
    private String actividad;
    private int idtipoactividad;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;    

    public Evaluacion(){

    }

    public Evaluacion(String Activida, int idtipoactividad) {
        this.actividad = Activida;
        this.idtipoactividad = idtipoactividad;            
    }

    public String getActividad(){
            return actividad;
    }

    public void setActividad(String activid){
            actividad = activid;
    }

    public int getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(int idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }        
    
    public void ActualizarEvaluacion(){
        cnx.Conecta();
            try{
                String SQL ="update evaluacion set actividad=?"
                + "where idtipoactividad=?";
                
                ps = cnx.conn.prepareStatement(SQL);               
                ps.setString(1, actividad);
                ps.setInt(2, idtipoactividad);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
                ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar Evaluación: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void EliminarEvaluacion(){
        cnx.Conecta();
                try {
                    String SQL = "delete from evaluacion where idtipoactividad=?";
                    
                    ps = cnx.conn.prepareStatement(SQL);     
                    ps.setInt(1, idtipoactividad);                    
                    int n = ps.executeUpdate();
                    if(n>0){                
                        JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                    }
                    ps.close();
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null, "Error Eliminar evaluacion: " + e.getMessage());            
                } finally {
                    cnx.Desconecta();
                }
    }
    
    public void GuardarEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "insert into evaluacion(actividad) values(?)";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, actividad);                
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
                ps.close();
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar evaluacion: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public int consultaIdE(String Evalua){
    int id = 0;
    cnx.Conecta();
        try{
            String SQL = "Select idtipoactividad from evaluacion where actividad = "+"\""+Evalua+"\"";
            
            ps = cnx.conn.prepareStatement(SQL);
//            ps.setString(1, actividad);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idtipoactividad");
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Id evaluacion: " + e.getMessage());
        }
    cnx.Desconecta();       
    return id;
    }
    
    public String consultaEvaluacion(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select actividad from evaluacion where idtipoactividad=" + id;
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("actividad");
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaDescrip Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaEvaluacion(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select actividad from evaluacion";
            
            ps = cnx.conn.prepareStatement(SQL);            
            rs = ps.executeQuery();
            while(rs.next()){
                ls.add(rs.getString("actividad"));
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaEvaluacion: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}