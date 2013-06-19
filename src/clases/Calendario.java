package clases;
import java.util.Date;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:53 PM
 */
public class Calendario {

    private Date fecha;
    private int idcalendario;
    private int idtipoactividad;
    private int idasignatura;

    public Calendario(){

    }

    public Calendario(Date fecha, int idcalendario, int idtipoactividad, int idasignatura) {
        this.fecha = fecha;
        this.idcalendario = idcalendario;
        this.idtipoactividad = idtipoactividad;
        this.idasignatura = idasignatura;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setfecha(Date fecha){
            this.fecha = fecha;
    }

    public int getIdcalendario() {
        return idcalendario;
    }

    public void setIdcalendario(int idcalendario) {
        this.idcalendario = idcalendario;
    }

    public int getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(int idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public int getIdasignatura() {
        return idasignatura;
    }

    public void setIdasignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }

    public void ActualizaCalendario(){
        
    }
    
    public void EliminaCalendario(){
        
    }
    
    public void GuardaCalendario(){
        
    }
}