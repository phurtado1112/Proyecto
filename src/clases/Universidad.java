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
 * @created 27-mar-2013 10:45:09 PM
 */
public class Universidad {

    private String nombreU;
    public Facultad m_Facultad;
    Conecta cnx = new Conecta();
    Statement stm;

    public Universidad(){

    }

    public Universidad(String nombreU, Facultad m_Facultad) {
        this.nombreU = nombreU;
        this.m_Facultad = m_Facultad;
    }

    public String getnombreU(){
            return nombreU;
    }

    public void setnombreU(String nombrU){
            nombreU = nombrU;
    }
        
    public int consultaIdU(String Univer){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select iduniversidad from universidad where nombreU = "+"\""+Univer+"\"";
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("iduniversidad");
            }
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
            String SQL = "Select nombreU from universidad where iduniversidad="+id;
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
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
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);            
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