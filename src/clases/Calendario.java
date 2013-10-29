package clases;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:53 PM
 */
public class Calendario {

        private int idcalendario;
	private String fecha;
        private int idactividaddet;
        private int idasignatura;
        Conecta cnx = new Conecta();
        PreparedStatement ps;       
        ResultSet rs;
	

	public Calendario(){

	}

        public Calendario(int idcalen, String fecha, int idactdet, int idasigna) {
            this.idcalendario = idcalen;
            this.fecha = fecha;
            this.idactividaddet = idactdet;
            this.idasignatura = idasigna;
        }

        public int getIdcalendario() {
           return idcalendario;
      }

        public void setIdcalendario(int idcalen) {
           this.idcalendario = idcalen;
         }
        
	public String getfecha(){
		return fecha;
	}

	public void setfecha(String fech){
		fecha = fech;
	}   

    public int getIdactividadDet() {
        return idactividaddet;
    }

    public void setIdactividadDet(int idactividaddet) {
        this.idactividaddet = idactividaddet;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }
    
    public void actualizarCalendario(){
        cnx.Conecta();
            try{
                String SQL ="update calendario set fecha=?,idactividaddet=?,"
                        + "idasignatura=? where idcalendario=?";
                ps = cnx.conn.prepareStatement(SQL);                
                ps.setString(1, fecha);
                ps.setInt(2, idactividaddet);
                ps.setInt(3, idasignatura);
                ps.setInt(4, idcalendario);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");                
                }
            }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Actualizar: " + e.getMessage());
            } finally {
                cnx.Desconecta();
            }
    }
    
    public void eliminarCalendario(){
        cnx.Conecta();
            try {
                String SQL = "delete from calendario where idcalendario= ?";
                ps = cnx.conn.prepareStatement(SQL);
                ps.setInt(1, idcalendario);
                int n = ps.executeUpdate();
                if(n>0){
                    JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Eliminar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
    
    public void guardarCalendario(){
        cnx.Conecta();
            try {
                String SQL = "insert into calendario(fecha,idactividaddet,idasignatura)"
                + "values(?,?,?)";
                ps = cnx.conn.prepareStatement(SQL);
                ps.setString(1, fecha);
                ps.setInt(2, idactividaddet);
                ps.setInt(3, idasignatura);
                int n = ps.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Datos guardados correctamente");                    
                }
            } catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error Guardar: " + e.getMessage());
            } finally {                
                cnx.Desconecta();
            }
    }
        
    public int ConsultarIDCal(String fecha){        
        cnx.Conecta();
        int id=0;            
        try{
            String SQL = "select idcalendario from Calendario where fecha='"+ fecha +"' ";
            ps = cnx.conn.prepareStatement(SQL);
            rs = ps.executeQuery();                
            rs.next();                
            id = rs.getInt("idcalendario");                
            ps.close();                                                    
        }catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Error obtenerIDCalendario: "+ e.getMessage());
        } finally{
            cnx.Desconecta();
        }                            
        return id;
    }  
}
        
        
