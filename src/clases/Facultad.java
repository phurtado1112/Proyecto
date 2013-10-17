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
 * @created 27-mar-2013 10:45:03 PM
 */
public class Facultad {
    private String nombreF;
    private int iduniversidad;
    private int idfacultad;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;

    public Facultad(){

    }

    public Facultad(String nombreF, int iduniversidad, int idfacultad) {
        this.nombreF = nombreF;
        this.iduniversidad = iduniversidad;
        this.idfacultad = idfacultad;
    }

    public String getnombreF(){
            return nombreF;
    }

    public void setnombreF(String nombrF){
            nombreF = nombrF;
    }

    public int getIduniversidad() {
        return iduniversidad;
    }

    public void setIduniversidad(int iduniversidad) {
        this.iduniversidad = iduniversidad;
    }

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public void ActualizarFacultad(){
    cnx.Conecta();
        try{
            String SQL ="update facultad set nombreF=?, iduniversidad=?"
            + "where idfacultad=?";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombreF);
            ps.setInt(2, iduniversidad);
            ps.setInt(3, idfacultad);
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
    
    public void EliminarFacultad(){
        cnx.Conecta();
        try {
            String SQL = "delete from facultad where idfacultad= ?";

            ps = cnx.conn.prepareStatement(SQL);
            ps.setInt(1, idfacultad);
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
    
    public void GuardarFacultad(){
        cnx.Conecta();
        try {
            String SQL = "insert into facultad(nombreF,iduniversidad) values(?,?)";

            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombreF);
            ps.setInt(2, iduniversidad);
            int n = ps.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Guardar Facultad: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    public int consultaId(String Facult){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idfacultad from facultad where nombreF = "+"\""+Facult+"\"";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("idfacultad");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Facultad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaFacultad(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select nombreF from facultad where idfacultad="+id;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("nombreF");
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Facultad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaFacultad(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreF from facultad";
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                ls.add(rs.getString("nombreF"));
            }            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaFacultad: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }
}