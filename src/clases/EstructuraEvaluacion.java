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
 * @created 21-abr-2013 09:45:01 PM
 */
public class EstructuraEvaluacion {

    private int idestructuraevaluacion;
    private String nombreE;
    private double valor;
    private int idevaluacion;
    private int idasignatura;
    Conecta cnx = new Conecta();
    Statement stm;
    ResultSet rs;

    public EstructuraEvaluacion(){

    }

    public EstructuraEvaluacion(int idestructuraevaluacion, String nombreE, double valor, int idevaluacion, int idasignatura) {
        this.idestructuraevaluacion = idestructuraevaluacion;
        this.nombreE = nombreE;
        this.valor = valor;
        this.idevaluacion = idevaluacion;
        this.idasignatura = idasignatura;
    }        

    public String getnombreE(){
            return nombreE;
    }

    public double getvalor(){
            return valor;
    }

    public void setnombreE(String nombre){
            nombreE = nombre;
    }

    public void setvalor(double Valo){
            valor = Valo;
    }

    public int getIdestructuraevaluacion() {
        return idestructuraevaluacion;
    }

    public void setIdestructuraevaluacion(int idestructuraevaluacion) {
        this.idestructuraevaluacion = idestructuraevaluacion;
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
    
    public void actualizarEstructuraEvaluacion(){
        cnx.Conecta();
            try{
                String SQL ="update estructuraevaluacion set nombreE='"+getnombreE()+"',"
                        + " valor='"+getvalor()+"', idevaluacion='"+getIdevaluacion()+"',"
                + "idasignatura='"+getIdasignatura()+"' where idestructuraevaluacion='"+getIdestructuraevaluacion()+"'";
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);  
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar Estructura Evaluación: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void eliminarEstructuraEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "delete from estructuraevaluacion where idestructuraevaluacion= " + getIdestructuraevaluacion();
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if(n>0){                
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Eliminar Estructura Evaluación: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void guardarEstructuraEvaluacion(){
        cnx.Conecta();
            try {
                String SQL = "insert into estructuraevaluacion(nombreE,valor,idevaluacion,idasignatura) "
                + "values('"+getnombreE()+"','"+getvalor()+"','"+getIdevaluacion()+"','"+getIdasignatura()+"')";
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Estructura Evaluación: " + e.getMessage());
            } finally {               
                cnx.Desconecta();
            }
    }
    
    public int consultaIdEE(String nombre){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idestructuraevaluacion from estructuraevaluacion "
                    + "where nombreE = "+"\""+nombre+"\"";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("idestructuraevaluacion");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Estructura Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaEstructuraEvaluacion(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select nombreE from estructuraevaluacion where ididestructuraevaluacion="+id;
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("nombreE");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Estructura Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaEstructuraEvaluacion(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreE from estructuraevaluacion";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("nombreE"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Estructura Evaluacion: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}