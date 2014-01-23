package clases;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:05 PM
 */
public class Notas {

    private int idnota;
    private double valor;
    private int idcalendario;
    private int idactividaddet;
    private int idestudiante;
    private int idasignatura;
    util.Conecta cnx = new util.Conecta();
    Calendario Ca = new Calendario();
    Estudiantes Es = new Estudiantes();
    PreparedStatement ps;
    Statement stm;
    ResultSet rs;

    public Notas() {

    }

    public Notas(int idnota, double valor, int idcalendario, int idactividaddet, int idestudiante, int idasignatura) {
        this.idnota = idnota;
        this.valor = valor;
        this.idcalendario = idcalendario;
        this.idactividaddet = idactividaddet;
        this.idestudiante = idestudiante;
        this.idasignatura = idasignatura;
    }

    public int getIdnota() {
        return idnota;
    }

    public void setIdnota(int idnota) {
        this.idnota = idnota;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = Math.rint(valor*100)/100;
    }

    public int getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(int idcalendario) {
        this.idcalendario = idcalendario;
    }

    public int getIdactividaddet() {
        return idactividaddet;
    }

    public void setIdactividaddet(int idactividaddet) {
        this.idactividaddet = idactividaddet;
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

    public void GuardarNotas() {
        cnx.Conecta();
        try {
            String SQL = "insert into notas(nota, idcalendario, idactividaddet, idestudiante, idasignatura) Values(?,?,?,?,?)";

            ps = cnx.conn.prepareStatement(SQL);
            ps.setDouble(1, valor);
            ps.setInt(2, idcalendario);
            ps.setInt(3, idactividaddet);
            ps.setInt(4, idestudiante);
            ps.setInt(5, idasignatura);
            ps.executeUpdate();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Guardar notas Clase: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    public void ModificarNotas() {
        cnx.Conecta();
        try {
            String SQL = "update notas set nota= ? where idnotas= ?";
            ps = cnx.conn.prepareStatement(SQL);
            ps = cnx.conn.prepareStatement(SQL);
            ps.setDouble(1, valor);
            ps.setInt(2, idnota);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Modificar notas Clase: " + e.getMessage());
        } finally {
            cnx.Desconecta();
        }
    }

    public int validarRegistros(int idcalend, int idactdet) {
        int cantidad = 0;
        cnx.Conecta();
        try {
            String SQL = "select count(*) from notas where idcalendario= " + idcalend + " and idactividaddet = " + idactdet;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            cantidad = rs.getInt("count(*)");
            ps.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Validación de Registros a Clase: " + e.getMessage());
        }
        cnx.Desconecta();
        return cantidad;
    }

    public int validarFecha(int idcalendario, int idactividaddet, int idasignatura) {
        int cantidad = 0;
        cnx.Conecta();
        try {
            String SQL
                    = "select count(*) from Notas where idcalendario= " + idcalendario + " and idactividaddet=" + idactividaddet + " and idasignatura=" + idasignatura;
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            cantidad = rs.getInt("count(*)");
            ps.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Validación de Fecha a Clase: " + e.getMessage());
        }
        cnx.Desconecta();
        return cantidad;
    }
}
