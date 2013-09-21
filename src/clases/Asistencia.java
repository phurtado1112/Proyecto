package clases;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import util.Conecta;
import clases.Calendario;
import clases.Estudiantes;
import javax.swing.JInternalFrame;


/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:51 PM
 */
public class Asistencia {

	private String asistencia;
	private int idasistencia;
        Conecta cnx = new Conecta();
        Calendario Ca = new Calendario();
        Estudiantes Es = new Estudiantes();
        PreparedStatement ps;
        Connection conn;

	public Asistencia(){

	}

    public Asistencia(String asistencia, int idasistencia) {
        this.asistencia = asistencia;
        this.idasistencia = idasistencia;
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
        
        

       
        
        public void GuardarAsistencia(String s1, int idCal, String idEst) throws Exception
        {
            
            try {
                Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
               conn=DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");

               String query = "Insert Into Asistencia(asistencia, idcalendario, idestudiante) Values(?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, s1);
                ps.setInt(2, idCal);
                ps.setString(3,idEst );
                ps.executeUpdate();
               
               
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar Asistencia Clase: " + e.getMessage());
            } finally {
                ps.close();
                conn.close();
            }
    }

        
        
    

}