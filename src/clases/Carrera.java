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
 * @created 27-mar-2013 10:44:55 PM
 */
public class Carrera {

    private int idcarrera;
    private String nombreC;
    private int idfacultad;
    Conecta cnx = new Conecta();
    PreparedStatement ps;    
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
    
    public void actualizarCarrera(){
        cnx.Conecta();
            try{
                String SQL ="update carrera set nombreC=?, idfacultad=?"
                + "where idcarrera=?";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombreC);
                ps.setInt(2, idfacultad);
                ps.setInt(3, idcarrera);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
                
                ps.close();
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void eliminarCarrera(){
        cnx.Conecta();
                try {
                    String SQL = "delete from carrera where idcarrera= ?";
                    
                    ps = cnx.conn.prepareStatement(SQL);
                    ps.setInt(1, idcarrera);
                    int n = ps.executeUpdate();
                    if(n>0){                
                        JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                    }
                    
                    ps.close();
                } catch(SQLException | HeadlessException e){
                    JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());            
                } finally {
                    cnx.Desconecta();
                }
    }
    
    public void guardarCarrera(){
        cnx.Conecta();
            try {
                String SQL = "insert into carrera(nombreC,idfacultad) values(?,?)";
                
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, nombreC);
                ps.setInt(2, idfacultad);
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
                }
                
                ps.close();
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
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("idcarrera");
            }
            
            ps.close();
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
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("nombreC");
            }
            
            ps.close();
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
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("nombreC"));
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaCarrera: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}