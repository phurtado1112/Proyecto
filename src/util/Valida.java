package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
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
                if(((c=='¡') ||
                        (int)c>=00 && (int)c<=31) ||
                        ((int)c>=33 && (int)c<=64) ||
                        ((int)c>=91 && (int)c<=96) || 
                        ((int)c>=123 && (int)c<=128) ||
                        
                        ((int)c>=131 && (int)c<=143) || 
                        ((int)c>=145 && (int)c<=159) || 
                        ((int)c>=166 && (int)c<=180) || 
                        ((int)c>=182 && (int)c<=213) || 
                        ((int)c>=215 && (int)c<=223) || 
                        ((int)c>=225 && (int)c<=232) || 
                        ((int)c>=234 && (int)c<=255)){                    
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
                if(((int)c>=00 && (int)c<=47) || 
                        ((int)c>=58 && (int)c<=255)){                    
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }
    }
    
//    public void LetrasEspacios(JTextField jt){
//        try{
//            jt.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e){
//                char c = e.getKeyChar();
//                if(((int)c>=00 && (int)c<=31) || 
//                        ((int)c>=33 && (int)c<=64) ||
//                        ((int)c>=91 && (int)c<=96) || 
//                        ((int)c>=123 && (int)c<=128) || 
//                        ((int)c>=131 && (int)c<=143) || 
//                        ((int)c>=145 && (int)c<=159) || 
//                        ((int)c>=166 && (int)c<=180) || 
//                        ((int)c>=182 && (int)c<=213) || 
//                        ((int)c>=215 && (int)c<=223) || 
//                        ((int)c>=225 && (int)c<=232) || 
//                        ((int)c>=234 && (int)c<=255)){                    
//                    e.consume();
//                }
//            }
//        });
//        } catch (Exception e){
//            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
//        }               
//    }
    
    public void LetrasNumeros(JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(((int)c>=00 && (int)c<=47) || 
                        ((int)c>=58 && (int)c<=64) ||
                        ((int)c>=91 && (int)c<=96) || 
                        ((int)c>=123 && (int)c<=128) || 
                        ((int)c>=131 && (int)c<=143) || 
                        ((int)c>=145 && (int)c<=159) || 
                        ((int)c>=166 && (int)c<=180) || 
                        ((int)c>=182 && (int)c<=213) || 
                        ((int)c>=215 && (int)c<=223) || 
                        ((int)c>=225 && (int)c<=232) || 
                        ((int)c>=234 && (int)c<=255)){                    
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
                if(
                   ((int)c==45)||                       //Para permitir el "-"
                   ((int)c>=65 && (int)c<=90) ||        //Mayusculas de A-Z
                   ((int)c>=97 && (int)c<=122) ||       //Minusculas de a-z
                   ((int)c==209) ||                     //Para permitir ñ
                   ((int)c==241)                        //Para permitir Ñ                               
                   ){
                    //Saber como se hace para  que se haga mayuscula
                } else { //Si no esta dentro del rango de caracteres especificados en el if superior, no se ingresa.
                    e.consume();
                }                
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
