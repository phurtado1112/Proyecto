package clases;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:59 PM
 */
public class Escuela {

	private String nombreE;
	public Carrera m_Carrera;

	public Escuela(){

	}

        public Escuela(String nombreE, Carrera m_Carrera) {
            this.nombreE = nombreE;
            this.m_Carrera = m_Carrera;
        }
       
	public String getnombreE(){
		return nombreE;
	}

	public void setnombreE(String nombrE){
		nombreE = nombrE;
	}

}