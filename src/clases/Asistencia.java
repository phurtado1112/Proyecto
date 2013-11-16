package clases;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import util.Conecta;


/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:51 PM
 */
public class Asistencia {

    private int idasistencia;	
    private String asistencia;	
    private int idcalendario;
    private int idestudiante;
    private int idasignatura;
    Conecta cnx = new Conecta();
    Calendario Ca = new Calendario();
    Estudiantes Es = new Estudiantes();
    PreparedStatement ps;
        
    public Asistencia(){

    }

    public Asistencia(int idasistencia, String asistencia, int idcalendario, int idestudiante, int idasignatura) {
        this.idasistencia = idasistencia;
        this.asistencia = asistencia;
        this.idcalendario = idcalendario;
        this.idestudiante = idestudiante;
        this.idasignatura = idasignatura;        
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public int getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(int idasistencia) {
        this.idasistencia = idasistencia;
    }

    public int getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(int idcalendario) {
        this.idcalendario = idcalendario;
    }

    public int getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }
            
    public void GuardarAsistencia(){
        cnx.Conecta();
        try {
            String SQL = "insert into asistencia(asistencia, idcalendario, idestudiante, idasignatura) Values(?,?,?,?)";
            
            ps = cnx.conn.prepareStatement(SQL);
            ps.setString(1, asistencia);
            ps.setInt(2, idcalendario);
            ps.setInt(3, idestudiante);
            ps.setInt(4, idasignatura);
            ps.executeUpdate();        
            ps.close();
        } catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error Guardar Asistencia a Clase: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }    
}