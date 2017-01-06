
package nutki;

import java.awt.Toolkit;  /// poczytać to Toolkit

/**
 *
 * @author Rino
 */
public class Nutki {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        
   		int eWidth = 1024;	// wymiary okna wyskakującego
   		int eHeight = 700;
   
   		int screenW= Toolkit.getDefaultToolkit().getScreenSize().width;
  		int screenH=Toolkit.getDefaultToolkit().getScreenSize().height;
   
   		int CenterX=(screenW-eWidth)/2 ;
   		int CenterY=(screenH-eHeight)/2;

                OkienkoWpisu ok=new OkienkoWpisu (eWidth,eHeight,CenterX,CenterY); 
                ok.setVisible(true);  
                ok.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
              
   
    }  
}