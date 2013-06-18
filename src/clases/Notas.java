package clases;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 27-mar-2013 10:45:05 PM
 */
public class Notas {

	private double valor;
	public Evaluaciones m_Evaluaciones;

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

}