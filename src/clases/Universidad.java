package clases;

import java.awt.HeadlessException;
import java.sql.Blob;
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
 * @created 27-mar-2013 10:45:09 PM
 */
public class Universidad {

    private int iduniversidad;
    private String nombreU;
    private String siglas;
    private Blob logo;
    Conecta cnx = new Conecta();
   // Statement stm;
    PreparedStatement ps;
    ResultSet rs;

    public Universidad(){

    }

    public Universidad(int iduniversidad, String nombreU, String siglas, Blob logo) {
        this.iduniversidad = iduniversidad;
        this.nombreU = nombreU;
        this.siglas = siglas;
        this.logo = logo;
    }

    public String getnombreU(){
            return nombreU;
    }

    public void setnombreU(String nombrU){
            nombreU = nombrU;
    }

    public int getIduniversidad() {
        return iduniversidad;
    }

    public void setIduniversidad(int iduniversidad) {
        this.iduniversidad = iduniversidad;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }
    
    public void actualizarUniversidad(){
        cnx.Conecta();
        try{
            String SQL ="update universidad set nombreU=?, siglas=?,"
                    + "logo=? where iduniversidad=?";
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);
            
            ps.setString(1, getnombreU());
            ps.setString(2, getSiglas());
            ps.setBlob(3, getLogo());
            ps.setInt(4, getIduniversidad());
            //ps.executeUpdate();

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
    
    public void eliminarUniversidad(){
        cnx.Conecta();
            try {
                String SQL = "delete from universidad where iduniversidad= ?";
                //stm = cnx.conn.createStatement();            
                ps = cnx.conn.prepareStatement(SQL);
                
                ps.setInt(1, getIduniversidad());
                
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
    
    public void guardarUniversidad(){
        cnx.Conecta();
        try{
            String SQL = "insert into universidad(nombreU,siglas,logo) "
                    + "values(?,?,?)";
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);
            
            ps.setString(1, getnombreU());
            ps.setString(2, getSiglas());
            ps.setBlob(3, getLogo());
           
            int n = ps.executeUpdate();
            if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {                
            cnx.Desconecta();
        }
    }
        
    public int consultaIdU(String Univer){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select iduniversidad from universidad where nombreU = "+"\""+Univer+"\"";
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, Univer);
            rs = ps.executeQuery();            
            while(rs.next()){
                id = rs.getInt("iduniversidad");
            }
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Universidad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return id;
    }
    
    public String consultaUniversidad(int id){
        String fila= "";
        cnx.Conecta();
        try{
            String SQL = "Select nombreU from universidad where iduniversidad=?";
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                fila = rs.getString("nombreU");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Universidad: " + e.getMessage());
        }
        cnx.Desconecta();       
        return fila;
    }
    
    public ArrayList<String> consultaUniversidad(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreU from universidad";
            //stm = cnx.conn.createStatement();
            ps = cnx.conn.prepareStatement(SQL);            
            rs = ps.executeQuery();            
            while(rs.next()){
                ls.add(rs.getString("nombreU"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaUniversidad: " + e.getMessage());
        }
        cnx.Desconecta();
        return ls;                                  
    }    
}