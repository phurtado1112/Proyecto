package clases;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:44:57 PM
 */
public class Docente {

	private String apellido;
	private String nombre;
	private String password;
	private String usuario;
	public Asistencia m_Asistencia;
	public Notas m_Notas;
	public Asignatura m_Asignatura;

	public Docente(){

	}

        public Docente(String apellido, String nombre, String password, String usuario, Asistencia m_Asistencia, Notas m_Notas, Asignatura m_Asignatura) {
            this.apellido = apellido;
            this.nombre = nombre;
            this.password = password;
            this.usuario = usuario;
            this.m_Asistencia = m_Asistencia;
            this.m_Notas = m_Notas;
            this.m_Asignatura = m_Asignatura;
        }
        
	public String getapellido(){
		return apellido;
	}

	public String getnombre(){
		return nombre;
	}

	public String getpassword(){
		return password;
	}

	public String getusuario(){
		return usuario;
	}

	public void setapellido(String apellid){
		apellido = apellid;
	}

	public void setnombre(String nombr){
		nombre = nombr;
	}

	public void setpassword(String pwd){
		password = pwd;
	}

	public void setusuario(String usuari){
		usuario = usuari;
	}

}