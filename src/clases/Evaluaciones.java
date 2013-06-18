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
public class Evaluaciones {
    
    private String evaluacion;
    Conecta cnx = new Conecta();
    Statement stm;

    public Evaluaciones(){

    }

    public Evaluaciones(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getevaluacion(){
            return evaluacion;
    }

    public void setevaluacion(String evalua){
            evaluacion = evalua;
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