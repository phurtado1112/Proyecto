package clases;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:07 PM
 */
public class TipoActividad {
    private String actividad;
    private int idtipoactividad;
    Conecta cnx = new Conecta();
    Statement stm;
    ResultSet rs;

    public TipoActividad(){

    }

    public TipoActividad(String Activida, int idtipoactividad) {
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
    
    public void ActualizarTipoActividad(){
        cnx.Conecta();
            try{
                String SQL ="update tipoactividad set actividad=?"
                + "where idtipoactividad=?";                
                stm = cnx.conn.createStatement();

                int n = stm.executeUpdate(SQL);
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }            
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar Tipo Actividad: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void EliminarTipoActividad(){
        cnx.Conecta();
                try {
                    String SQL = "delete from tipoactividad where idtipoactividad= " + getIdtipoactividad();
                    stm = cnx.conn.createStatement();            
                    int n = stm.executeUpdate(SQL);
                    if(n>0){                
                        JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                    }
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null, "Error Eliminar Tipo Actividad: " + e.getMessage());            
                } finally {
                    cnx.Desconecta();
                }
    }
    
    public void GuardarTipoActividad(){
        cnx.Conecta();
            try {
                String SQL = "insert into tipoactividad(actividad) values('"+getActividad()+"')";
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Tipo Actividad: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public int consultaIdTA(String Tipoact){
    int id = 0;
    cnx.Conecta();
        try{
            String SQL = "Select idtipoactividad from tipoactividad where actividad = "+"\""+Tipoact+"\"";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("idtipoactividad");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Id TipoActividad: " + e.getMessage());
        }
    cnx.Desconecta();       
    return id;
    }
    
    public String consultaTipoAct(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select actividad from tipoactividad where idtipoactividad="+id;
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("actividad");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaDescrip Actividad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaTipoActividad(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select actividad from tipoactividad";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("actividad"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaTipoActividad TipoActividad: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}