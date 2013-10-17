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
 * @created 27-mar-2013 10:44:57 PM
 */
public class Docente {

    private int iddocente;
    private String apellido;
    private String nombre;
    private String password;
    private String usuario;
    Conecta cnx = new Conecta();
    PreparedStatement ps;
    ResultSet rs;
    
    public Docente(){

    }

    public Docente(int iddocente, String apellido, String nombre, String password, String usuario) {
        this.iddocente = iddocente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.password = password;
        this.usuario = usuario;  
    }

    public int getIdDocente() {
        return iddocente;
    }

    public void setIdDocente(int iddocente) {
        this.iddocente = iddocente;
    }
        
    public String getApellido(){
            return apellido;
    }

    public String getNombre(){
            return nombre;
    }

    public String getPassword(){
            return password;
    }

    public String getUsuario(){
            return usuario;
    }

    public void setApellido(String apellid){
            apellido = apellid;
    }

    public void setNombre(String nombr){
            nombre = nombr;
    }

    public void setPassword(String pwd){
            password = pwd;
    }

    public void setUsuario(String usuari){
            usuario = usuari;
    }

    public void actualizarDocente(){
        cnx.Conecta();
        try{
            String SQL ="update docente set nombre=?, apellido=?,"
                    + " usuario=?, contrasena=? where iddocente=?";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            ps.setString(4, password);
            ps.setInt(5, iddocente);
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
    
    public void eliminarDocente(){
        cnx.Conecta();
        try {
            String SQL = "delete from docente where iddocente= ?";
            
            ps = cnx.conn.prepareStatement(SQL); 
            ps.setInt(1, iddocente);
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
    
    public void guardarDocente(){
        cnx.Conecta();
        try {
            String SQL = "insert into docente(nombre, apellido, usuario, contrasena) "
            + "values(?,?,?,?)";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, usuario);
            ps.setString(4, password);
            int n = ps.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    public int consultaIdDo(String Docent){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select iddocente from docente where nombre = "+"\""+Docent+"\"";
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("iddocente");
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Docente: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaDocente(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select nombre from docentea_view where iddocente="+id;
            
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("nombre");                
            }
            
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Docente: " + e.getMessage());
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
            rs = ps.executeQuery();            
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