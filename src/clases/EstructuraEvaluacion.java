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
 * @created 21-abr-2013 09:45:01 PM
 */
public class EstructuraEvaluacion {

    private int idactividad;
    private int idactividaddet;
    private int idasignatura;
    private int idestructuraevaluacion;
    private double valor;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public EstructuraEvaluacion(){

    }

    public EstructuraEvaluacion(int idactividad, int idactividaddet, int idasignatura, int idestructuraevaluacion, double valor) {
        this.idactividad = idactividad;
        this.idactividaddet = idactividaddet;
        this.idasignatura = idasignatura;
        this.idestructuraevaluacion = idestructuraevaluacion;
        this.valor = valor;
    }

    public int getIdestructuraevaluacion() {
        return idestructuraevaluacion;
    }

    public void setIdestructuraevaluacion(int idestructuraevaluacion) {
        this.idestructuraevaluacion = idestructuraevaluacion;
    }

    public int getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    public int getIdactividaddet() {
        return idactividaddet;
    }

    public void setIdactividaddet(int idactividaddet) {
        this.idactividaddet = idactividaddet;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = Math.rint(valor*100)/100;
    }
    
    public void actualizarEstructuraEvaluacion(){
        cnx.Conecta();
            try{
                String SQL ="update estructuraevaluacion set idactividad=?, idactividaddet=?,"
                + "valor=?, idasignatura=? where idestructuraevaluacion=?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, idactividad);
                ps.setInt(2, idactividaddet);
                ps.setDouble(3, valor);
                ps.setInt(4, idasignatura);
                ps.setInt(5, idestructuraevaluacion);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Estructura de Evaluacion: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarEstructuraEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "delete from estructuraevaluacion where idestructuraevaluacion = ?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, idestructuraevaluacion);

                int n = ps.executeUpdate();                
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error eliminando Estructura de Evaluacion: " + e.getMessage());
            } finally {                
                cnx.Desconecta();        
            }
    }
    
    public void guardarEstructuraEvaluacion(){
        cnx.Conecta();
            try {                
                String SQL ="insert into estructuraevaluacion (idactividad, idactividaddet,"
                + "valor, idasignatura) values(?,?,?,?)";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, idactividad);
                ps.setInt(2, idactividaddet);
                ps.setDouble(3, valor);
                ps.setInt(4, idasignatura);

                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Estructura de Evaluacion: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public int consultaIdEstEva(String EstrucEva){
    int id = 0;
    cnx.Conecta();
    try{
        String SQL = "Select idestructuraevaluacion from estructuraevaluacion where actividaddet = "+"\""+EstrucEva+"\"";
        ps = cnx.conn.prepareStatement(SQL);
        
        rs = ps.executeQuery();            
        while(rs.next()){
            id = rs.getInt("idestructuraevaluacion");
        }
    } catch(SQLException | HeadlessException e){
        JOptionPane.showMessageDialog(null, "Error consulta ID Estructura de Evaluacion: " + e.getMessage());
    }
    cnx.Desconecta();       
    return id;
    }
    
    public String consultaEstrucEva(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select actividaddet from estructuraevaluacion where idestructuraevaluacion="+id;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("actividaddet");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Detalle de Actividad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaEstrucEva(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select actividaddet from estructuraevaluacion";
            ps = cnx.conn.prepareStatement(SQL);
            
            rs = ps.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("actividaddet"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaEstructuraEvaluacion: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
    
//    public int ObtenerIDEstruvturaevaluacion(String Estevaluacion){
//        
//            cnx.Conecta();
//            int fe=0;
//            
//            try{
//                String SQL="select idestructuraevaluacion from estructuraevaluacion "
//                        + "where idactividaddet=?";
//                ps = cnx.conn.prepareStatement(SQL);
//                ps.setInt(1, idestructuraevaluacion);
//                rs = ps.executeQuery();                
//                rs.next();                
//                fe = rs.getInt("idestructuraevaluacion");
//                
//                
//
//                cnx.conn.close();
//                        
//            
//            }catch(SQLException e){javax.swing.JOptionPane.showMessageDialog(null, "Error obtenerIDEstructuraevaluacion: "
//                    + e.getMessage());}
//                
//            
//            return fe;
//            }    
}