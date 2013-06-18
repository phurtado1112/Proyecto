package clases;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:51 PM
 */
public class Asistencia {

	private boolean asistencia;
	public Calendario fecha;

	public Asistencia(){

	}

        public Asistencia(boolean asistencia, Calendario fecha) {
            this.asistencia = asistencia;
            this.fecha = fecha;
        }
                
	public boolean isasistencia(){
		return asistencia;
	}

	public void setasistencia(boolean asisten){
		asistencia = asisten;
	}

}