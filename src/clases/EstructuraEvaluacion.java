package clases;

/**
 * @author Pablo Hurtado
 * @version 1.0
 * @created 21-abr-2013 09:45:01 PM
 */
public class EstructuraEvaluacion {

    private String nombreE;
    private double valor;
    public Asignatura m_Asignatura;
    public Evaluaciones m_Evaluaciones;

    public EstructuraEvaluacion(){

    }

    public EstructuraEvaluacion(String nombreE, double valor, Asignatura m_Asignatura, Evaluaciones m_Evaluaciones) {
        this.nombreE = nombreE;
        this.valor = valor;
        this.m_Asignatura = m_Asignatura;
        this.m_Evaluaciones = m_Evaluaciones;
    }        

    public String getnombreE(){
            return nombreE;
    }

    public double getvalor(){
            return valor;
    }

    public void setnombreE(String nombre){
            nombreE = nombre;
    }

    public void setvalor(double Valo){
            valor = Valo;
    }
}