package util;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PabloAntonio
 */
public class Valida {
    public void SoloLetras(JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(((int)c>=00 && (int)c<=31) || //32 es para la barra espaciadora
                   ((int)c>=33 && (int)c<=64) || //65-90 es para A-Z (sin Ñ, ni vocales con dieresis o tilde)   
                   ((int)c>=91 && (int)c<=96) || //97-122 es para a-z (sin ñ, ni vocales con dieresis o tilde)
                   ((int)c>=123 && (int)c<=192)||
                   ((int)c>=194 && (int)c<=195)|| //193 es para Á
                   ((int)c>=197 && (int)c<=200)|| //196 es para Ä
                   ((int)c==202)               || //201 es para É
                   ((int)c==204)               || //203 es para Ë
                   ((int)c==206)               || //205 es para Í
                   ((int)c==208)               || //207 es para Ï
                   ((int)c==210)               || //209 es para Ñ
                   ((int)c>=212 && (int)c<=213)|| //211 es para Ó
                   ((int)c>=215 && (int)c<=217)|| //214 es para Ö
                   ((int)c==219)               || //218 es para Ú
                   ((int)c>=221 && (int)c<=224)|| //220 es para Ü
                   ((int)c>=226 && (int)c<=227)|| //225 es para á
                   ((int)c>=229 && (int)c<=232)|| //228 es para ä
                   ((int)c==234)               || //233 es para é
                   ((int)c==236)               || //235 es para ë
                   ((int)c==238)               || //237 es para í
                   ((int)c==240)               || //239 es ï
                   ((int)c==242)               || //241 es para ñ
                   ((int)c>=244 && (int)c<=245)|| //243 es para ó
                   ((int)c>=247 && (int)c<=249)|| //246 es para ö
                   ((int)c==251)               || //250 es para ú
                   ((int)c>=253))     {             //252 es para ü                     
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasNumeros(JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(((int)c>=00 && (int)c<=31) ||  //32 es para la barra espaciadora
                        ((int)c>=33 && (int)c<=44) || //45 es para -
                        ((int)c>=46 && (int)c<=47) || //48-57 es para numeros
                        ((int)c>=58 && (int)c<=64) || //65-90 es para A-Z (sin Ñ, ni vocales con dieresis o tilde)
                        ((int)c>=91 && (int)c<=96) || //97-122 es para a-z(sin ñ, ni vocales con dieresis o tilde)
                        ((int)c>=123 && (int)c<=208) || //209 es para Ñ
                        ((int)c>=210 && (int)c<=240) || //241 es para ñ
                        ((int)c>=242)){                    
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasSinEspacios(JTextField jt){ //Para las siglas que no permiten espacios
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(((int)c>=00 && (int)c<=64) || //65-90 es para A-Z (sin Ñ, ni vocales con dieresis o tilde)
                   ((int)c>=91 && (int)c<=96) || //97-122 es para a-z (sin ñ, ni vocales con dieresis o tilde)
                   ((int)c>=97 && (int)c<=122)|| //***Para que no permita minusculas.
                   ((int)c>=123 && (int)c<=192)||
                   ((int)c>=194 && (int)c<=195)|| //193 es para Á
                   ((int)c>=197 && (int)c<=200)|| //196 es para Ä
                   ((int)c==202)               || //201 es para É
                   ((int)c==204)               || //203 es para Ë
                   ((int)c==206)               || //205 es para Í
                   ((int)c==208)               || //207 es para Ï
                   ((int)c==210)               || //209 es para Ñ
                   ((int)c==209)               || //**209 para la Ñ
                   ((int)c>=212 && (int)c<=213)|| //211 es para Ó
                   ((int)c>=215 && (int)c<=217)|| //214 es para Ö
                   ((int)c==219)               || //218 es para Ú
                   ((int)c>=221 && (int)c<=224)|| //220 es para Ü
                   ((int)c>=226 && (int)c<=227)|| //225 es para á
                   ((int)c>=229 && (int)c<=232)|| //228 es para ä
                   ((int)c==234)               || //233 es para é
                   ((int)c==236)               || //235 es para ë
                   ((int)c==238)               || //237 es para í
                   ((int)c==240)               || //239 es ï
                   ((int)c==242)               || //241 es para ñ
                   ((int)c>=244 && (int)c<=245)|| //243 es para ó
                   ((int)c>=247 && (int)c<=249)|| //246 es para ö
                   ((int)c==251)               || //250 es para ú
                   ((int)c>=253)                  //252 es para ü                                        
                   ){               
                    e.consume();
                }                
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void SoloNumeros(JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(
                   ((int)c>=00 && (int)c<=47) || //Solo agarra los numeros. (Omite la barra espaciadora)
                   ((int)c>=58)
                        
                   //PREGUNTAR en el caso de ESTRUCTURA EVALUACION como se hace con las notas que llevan decimal.
                   //este metodo no pemite punto para decimales.
                   ){                    
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }
    }
                    
    public void SeleccionarTodo(final JTextField jt){  
            try{
                jt.addFocusListener(new FocusAdapter() {                        
                @Override
                public void focusGained(FocusEvent e){
                    jt.selectAll();
                }                
            });
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Error en la validación " + e);
            }               
        }
    
//    public void email(JTextField jt){
//        try{
//            jt.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e){
//                char c = e.getKeyChar();
//                if(Character.isSpaceChar(c)){                    
//                    e.consume();
//                }
//            }
//        });
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }               
//    }
    
    //"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)"
//    public void esEmail(String jt) {
//   
//        String input = jt;
//        // comprueba que no empieze por punto o @
//        Pattern p = Pattern.compile("^.|^@");
//        Matcher m = p.matcher(input);
//        try{            
//            if (m.find()){                
//                JOptionPane.showMessageDialog(null,"Las direcciones email no empiezan por punto o @");
//            }
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//
//        // comprueba que no empieze por www.
//        p = Pattern.compile("^www.");
//        m = p.matcher(input);
//        try {
//            if (m.find())
//                JOptionPane.showMessageDialog(null,"Los emails no empiezan por www");
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }        
//
//        // comprueba que contenga @
//        p = Pattern.compile("@");
//        m = p.matcher(input);
//        try {
//            if (!m.find())
//                JOptionPane.showMessageDialog(null,"La cadena no tiene arroba");         
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//        
//        // comprueba que no contenga caracteres prohibidos	
//        p = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
//        m = p.matcher(input);
//        try {
//            StringBuffer sb = new StringBuffer();
//            boolean resultado = m.find();
//            boolean caracteresIlegales = false;
//
//            while(resultado) {
//                caracteresIlegales = true;
//                m.appendReplacement(sb, "");
//                resultado = m.find();
//            }
//            
//            m.appendTail(sb);
//
//            //input = sb.toString();
//
//            if (caracteresIlegales) {
//                JOptionPane.showMessageDialog(null, "La cadena contiene caracteres ilegales");
//        }
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }
//        
//        // Añade el ultimo segmento de la entrada a la cadena
//        
//   }
}
