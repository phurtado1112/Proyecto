package clases;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.Date;
import util.Conecta;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:53 PM
 */
public class Calendario {

        private int idfecha;
	private Date fecha;
        Conecta cnx = new Conecta();
        PreparedStatement ps;
        //Statement stm;
        ResultSet rs;
	

	public Calendario(){

	}

        public Calendario(int IDfecha, Date fecha) {
            this.idfecha = IDfecha;
            this.fecha = fecha;            
        }

        public int getIdfecha() {
           return idfecha;
      }

        public void setIdfecha(int idfecha) {
           this.idfecha = idfecha;
         }
        
	public Date getfecha(){
		return fecha;
	}

	public void setfecha(Date fech){
		fecha = fech;
	}   
        
        public int ObtenerIDCalendario(String fecha){        
            cnx.Conecta();
            int fe=0;            
            try{
                String SQL = "select idcalendario from Calendario where fecha='"+ fecha +"' ";
                ps = cnx.conn.prepareStatement(SQL);
                rs = ps.executeQuery();                
                rs.next();                
                fe = rs.getInt("idcalendario");                
                
                ps.close();                                                    
            }catch(Exception e){
                javax.swing.JOptionPane.showMessageDialog(null, "Error obtenerIDCalendario: "+ e.getMessage());
            } finally{
                cnx.Desconecta();
            }                            
            return fe;
        }  
}
        
        
