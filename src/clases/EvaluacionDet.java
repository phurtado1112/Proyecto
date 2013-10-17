package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:02 PM
 */
public class EvaluacionDet {
    
    private int idevaluaciondet;
    private String evaluaciondet;
    private int idevaluacion;
    ResultSet rs;
    PreparedStatement ps;
    Conecta cnx = new Conecta();
    //Statement stm;

    public EvaluacionDet(){

    }

    public EvaluacionDet(int idevaluacion,String evaluacion, int idasignatura) {
        this.idevaluaciondet = idevaluacion;
        this.evaluaciondet = evaluacion;
        this.idevaluacion = idasignatura;
    }

    public String getEvaluacionDet(){
            return evaluaciondet;
    }

    public void setEvaluacionDet(String evalua){
            evaluaciondet = evalua;
    }

    public int getIdEvaluacionDet() {
        return idevaluaciondet;
    }

    public void setIdEvaluacionDet(int idevaluacion) {
        this.idevaluaciondet = idevaluacion;
    }

    public int getIdEvaluacion() {
        return idevaluacion;
    }

    public void setIdEvaluacion(int idasignatura) {
        this.idevaluacion = idasignatura;
    }
    
    public void actualizarEvaluacionDet(){
        cnx.Conecta();
            try{
                String SQL ="update evaluaciondet set evaluaciondet=?, idevaluacion=?"
                + "where idevaluaciondet=?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setString(1, evaluaciondet);
                ps.setInt(2, idevaluacion);
                ps.setInt(3, idevaluaciondet);
                       

                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Detalle de Evaluación: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarEvaluacionDet(){
        cnx.Conecta();
            try {
                String SQL = "delete from evaluaciondet where idevaluaciondet = ?";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, idevaluaciondet);

                int n = ps.executeUpdate();                
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error eliminando Detalle de Evaluación: " + e.getMessage());
            } finally {                
                cnx.Desconecta();        
            }
    }
    
    public void guardarEvaluacionDet(){
        cnx.Conecta();
            try {
                String SQL = "insert into evaluaciondet(evaluaciondet,idevaluacion) "
                + "values(?,?)";
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setString(1, evaluaciondet);
                ps.setInt(2, idevaluacion);

                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Detalle Evaluación: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public int consultaId(String Evalua){
    int id = 0;
    cnx.Conecta();
    try{
        String SQL = "Select idevaluaciondet from evaluaciondet where evaluaciondet = "+"\""+Evalua+"\"";
        ps = cnx.conn.prepareStatement(SQL);
        
        rs = ps.executeQuery(SQL);            
        while(rs.next()){
            id = rs.getInt("idevaluaciondet");
        }
    } catch(SQLException | HeadlessException e){
        JOptionPane.showMessageDialog(null, "Error consulta ID Evaluacion: " + e.getMessage());
    }
    cnx.Desconecta();       
    return id;
    }
    
    public String consultaEvaluacion(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select evaluaciondet from evaluaciondet where idevaluaciondet="+id;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("evaluaciondet");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Detalle de Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaEvaluacion(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select evaluaciondet from evaluaciondet";
            ps = cnx.conn.prepareStatement(SQL);
            
            rs = ps.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("evaluaciondet"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaEvaluacion Detalle: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}