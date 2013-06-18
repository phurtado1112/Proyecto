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
 * @created 27-mar-2013 10:45:00 PM
 */
public class Estudiantes {

	private String apellidos;
	private int carne;
	private int celular;
	private String email;
	private String nombre;
	public Asistencia m_Asistencia;
	public Notas m_Notas;
	public Asignatura m_Asignatura;
        Conecta cnx = new Conecta();
        Statement stm;

	public Estudiantes(){

	}

    public Estudiantes(String apellidos, int carne, int celular, String email, String nombre, Asistencia m_Asistencia, Notas m_Notas, Asignatura m_Asignatura) {
        this.apellidos = apellidos;
        this.carne = carne;
        this.celular = celular;
        this.email = email;
        this.nombre = nombre;
        this.m_Asistencia = m_Asistencia;
        this.m_Notas = m_Notas;
        this.m_Asignatura = m_Asignatura;
    }
        
	public String getapellidos(){
		return apellidos;
	}

	public int getcarne(){
		return carne;
	}

	public int getcelular(){
		return celular;
	}

	public String getemail(){
		return email;
	}

	public String getnombre(){
		return nombre;
	}

	public void setapellidos(String apellid){
		apellidos = apellid;
	}

	public void setcarne(int carn){
		carne = carn;
	}

	public void setcelular(int celula){
		celular = celula;
	}

	public void setemail(String correo){
		email = correo;
	}

	public void setnombre(String nombr){
		nombre = nombr;
	}
        
        @Override
        public String toString(){
            return this.nombre+" "+this.apellidos;
        }
        
        public int consultaId(String Nombr, String Apell){
        int id = 0;
        cnx.Conecta();
        try{
            String SQL = "Select idestudiante from estudiante where nombreE = "+"\""+Nombr+"\""
                    + "and apellidoE="+"\""+Apell+"\"";
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);            
            while(rs.next()){
                id = rs.getInt("idestudiante");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta ID Estudiante: " + e.getMessage());
        } finally {
        cnx.Desconecta();       
        }
        return id;
        }
        
        public String[] consultaEstudiante(int id){
        
        String [] fila = new String[2];
        cnx.Conecta();
        try{
            String SQL = "Select nombreE, apellidoE from estudiante where idestudiante="+id;
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while(rs.next()){
                fila[0] = rs.getString("nombreE");
                fila[1] = rs.getString("apellidoE");
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consulta Nombre Estidiante: " + e.getMessage());
        } finally {
        cnx.Desconecta();
        }
        return fila;
        }
        
        public ArrayList<String> listaEstudiante(){
        cnx.Conecta();
        ArrayList<String> ls = new ArrayList<>();
        try{
            String SQL = "Select nombreE, apellidoE from estudiante";
            stm = cnx.conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);            
            while(rs.next()){
                ls.add(rs.getString("nombreE"));
                ls.add(rs.getString("apellidoE"));
            }
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error consultaUniversidad: " + e.getMessage());
        } finally {
        cnx.Desconecta();
        }
        return ls;                                  
    } 

}