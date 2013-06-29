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
 * @created 27-mar-2013 10:45:02 PM
 */
public class TipoEvaluacion {
    
    private int idevaluacion;
    private String evaluacion;
    private int idasignatura;
    Conecta cnx = new Conecta();
    Statement stm;

    public TipoEvaluacion(){

    }

    public TipoEvaluacion(int idevaluacion,String evaluacion, int idasignatura) {
        this.idevaluacion = idevaluacion;
        this.evaluacion = evaluacion;
        this.idasignatura = idasignatura;
    }

    public String getEvaluacion(){
            return evaluacion;
    }

    public void setEvaluacion(String evalua){
            evaluacion = evalua;
    }

    public int getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(int idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }
    
    public void actualizarTipoEvaluacion(){
        cnx.Conecta();
            try{
                String SQL ="update evaluacion set evaluacion='"+getEvaluacion()+"',"
                        + "idasignatura='"+getIdasignatura()+"'"
                + "where idevaluacion='"+getIdevaluacion()+"'";
                stm = cnx.conn.createStatement();

                int n = stm.executeUpdate(SQL);
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarTipoEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "delete from evaluacion where idevaluacion= " + getIdevaluacion();
                stm = cnx.conn.createStatement();

                int n = stm.executeUpdate(SQL);                
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {                
                cnx.Desconecta();        
            }
    }
    
    public void guardarTipoEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "insert into evaluacion(evaluacion,idasignatura) "
                + "values('"+getEvaluacion()+"','"+getIdasignatura()+"')";
                stm = cnx.conn.createStatement();

                int n = stm.executeUpdate(SQL);
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public int consultaId(String Asig){
    int id = 0;
    cnx.Conecta();
    try{
        String SQL = "Select idevaluacion from evaluacion where evaluacion = "+"\""+Asig+"\"";
        stm = cnx.conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);            
        while(rs.next()){
            id = rs.getInt("idevaluacion");
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
            String SQL = "Select evaluacion from evaluacion where idevaluacion="+id;
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("evaluacion");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaEvaluacion(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select evaluacion from evaluacion";
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("evaluacion"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaEvaluacion: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}