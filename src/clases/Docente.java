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
 * @created 27-mar-2013 10:44:57 PM
 */
public class Docente {

    private int iddocente;
    private String apellido;
    private String nombre;
    private String password;
    private String usuario;
    Conecta cnx = new Conecta();
    Statement stm;
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
            String SQL ="update docente set nombre='"+getNombre()+"', apellido='"+getApellido()+"',"
                    + " usuario='"+getUsuario()+"', contrasena='"+getPassword()+"'"
            + "where iddocente='"+getIdDocente()+"'";
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
    
    public void eliminarDocente(){
        cnx.Conecta();
        try {
            String SQL = "delete from docente where iddocente= " + getIdDocente();
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
    
    public void guardarDocente(){
        cnx.Conecta();
        try {
            String SQL = "insert into docente(nombre, apellido, usuario, contrasena) "
            + "values('"+getNombre()+"','"+getApellido()+"','"+getUsuario()+"','"+getPassword()+"')";
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

    public int consultaIdDo(String Docent){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select iddocente from docente where nombre = "+"\""+Docent+"\"";
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("iddocente");
            }
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
            stm = cnx.conn.createStatement();
            rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila = rs.getString("nombre");                
            }
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