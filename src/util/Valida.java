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
                if(
                   ((int)c==32) || //32 es para la barra espaciadora      
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c=='Á') ||
                    (c=='Ä') ||
                    (c=='É') ||
                    (c=='Ë') ||
                    (c=='Í') ||
                    (c=='Ï') ||
                    (c=='Ó') ||
                    (c=='Ö') ||
                    (c=='Ú') ||
                    (c=='Ü') ||
                    (c>='a' && c<='z') ||
                    (c=='ñ')||
                    (c=='á') ||
                    (c=='ä') ||
                    (c=='é') ||
                    (c=='ë') ||
                    (c=='í') ||
                    (c=='ï') ||
                    (c=='ó') ||
                    (c=='ö') ||
                    (c=='ú') ||
                    (c=='ü')                  
                  )                                    
                {}
                else {
                    e.consume();
                }
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasSinEspacios(final JTextField jt){ //Para las siglas en Universidad
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(                    
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ') ||
                    (c=='-')
                   ){
                    e.consume();
                    String mayuscula=String.valueOf(c).toUpperCase();                                                           
                    jt.setText(jt.getText().concat(mayuscula));                    
                } 
                else {
                        e.consume();
                }                
            }
        });
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en la validación " + e);
        }               
    }
    
    public void LetrasNumeros(final JTextField jt){
        try{
            jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();          
                if (((int)c==32)|| // es par la barra espaciadora
                    ((int)c=='-')|| 
                    (c>='0' && c<='9') ||
                    (c>='A' && c<='Z') ||
                    (c=='Ñ')           ||
                    (c>='a' && c<='z') ||
                    (c=='ñ')
                   ){
                    e.consume();
                    String mayuscula=String.valueOf(c).toUpperCase();                                                           
                    jt.setText(jt.getText().concat(mayuscula));                    
                }
                else {
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
                if ((c>='0' && c<='9') ||
                     c=='.'
                    ){} 
                else{
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
