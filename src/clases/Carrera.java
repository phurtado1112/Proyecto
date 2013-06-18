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
 * @created 27-mar-2013 10:44:55 PM
 */
public class Carrera {

    private int idcarrera;
    private String nombreC;
    private int idfacultad;
    Conecta cnx = new Conecta();
    Statement stm;
    ResultSet rs;

    public Carrera(){

    }

    public Carrera(int idcarrera, String nombreC, int idfacultad) {
        this.idcarrera = idcarrera;
        this.nombreC = nombreC;
        this.idfacultad = idfacultad;
        
    }   

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getnombreC(){
            return nombreC;
    }

    public void setnombreC(String nombrC){
            nombreC = nombrC;
    }

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }       
    
    public void ActualizarCarrera(){
        cnx.Conecta();
            try{
                String SQL ="update carrera set nombreC=?, idfacultad=?"
                + "where idcarrera=?";                
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
    
    public void EliminarCarrera(){
        cnx.Conecta();
                try {
                    String SQL = "delete from carrera where idcarrera= " + getIdcarrera();
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
    
    public void GuardarCarrera(){
        cnx.Conecta();
            try {
                String SQL = "insert into carrera(nombreC,idfacultad) values('"+getnombreC()+"','"+getIdfacultad()+"')";
                stm = cnx.conn.createStatement();
                int n = stm.executeUpdate(SQL);
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Carrera: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }

     public int consultaIdCa(String Carrer){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idcarrera from carrera where nombreC = "+"\""+Carrer+"\"";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("idcarrera");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Carrera: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaCarrera(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select nombreC from carrera where idcarrera="+id;
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("nombreC");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Carrera: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaCarrera(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreC from carrera";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("nombreC"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaCarrera: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}