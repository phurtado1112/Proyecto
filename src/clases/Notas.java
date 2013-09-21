package clases;

import cnae.Cnae;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:05 PM
 */
public class Notas {

	private double valor;
	public Evaluaciones m_Evaluaciones;
        util.Conecta cnx = new util.Conecta();
        Calendario Ca = new Calendario();
        Estudiantes Es = new Estudiantes();
        Cnae C = new Cnae();
        PreparedStatement ps;
        Connection conn;

	public Notas(){

	}

        public Notas(double valor, Evaluaciones m_Evaluaciones) {
            this.valor = valor;
            this.m_Evaluaciones = m_Evaluaciones;
        }
        
	public double getvalor(){
		return valor;
	}

	public void setvalor(double valo){
		valor = valo;
	}

        
        public void GuardarNotas(String s1, int idEstEv, String idEst,int idCal) throws Exception
        {
            
            try {
                Class.forName("org.sqlite.JDBC"); //driver a utilizar                       
               conn=DriverManager.getConnection("jdbc:sqlite:cnae.sqlite");

               String query = "Insert Into notas(nota, idestructuraevaluacion, idestudiante,idcalendario) Values(?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, s1);
                ps.setInt(2, idEstEv);
                ps.setString(3,idEst );
                ps.setInt(4, idCal);
                ps.executeUpdate();
               
               
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar notas Clase: " + e.getMessage());
            } finally {
                ps.close();
                conn.close();
            }
    }
        
        
        
}

