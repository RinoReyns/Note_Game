
package nutki;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Rino
 */
public class Elementy {
     
    /**
     *
     */
    public final static long ileleveli=3; // zaczynamy od pierwszego
    
    /**
     *
     */
    public static Image [] tlo;    
    
    /**
     *
     */
    public static Image kursor;
   
    /**
     *
     */
    public static Image[] Nutki;
    
    ////////////////////// związane z pauza i czasem zmienne

    /**
     *
     */
       public static long GAME_TIME=Long.MAX_VALUE;
   
    /**
     *
     */
    public static long startTime;
    
    /**
     *
     */
    public static long endTime;
    
    /**
     *
     */
    public static long medTime=0;
     
    /**
     *
     */
    public static long startLevel;      
    
    /**
     *
     */
    public static double blowStart;
    
    /**
     *
     */
    public static double blowStop;
    
    /**
     *
     */
    public static double blowTime=0;
    
    /**
     *
     */
    public static boolean pause=false;

    /**
     *
     */
    public static boolean koniec=false;
////////////////////////// koniec pauzy i czasu
    
    /**
     *
     */
    public static int aktP=0;   ////// aktualny poziom
   
    /**
     *
     */
    public static int maxob=6; //// max ilość obietków
    
    /**
     *
     */
    public static String nazwa;
   
    /**
     *
     */
    public static void ładujobrazki() {
        
        tlo= new Image[4];
        tlo[0] = new ImageIcon("image\\rzeka1.jpg").getImage();
        tlo[1] =new ImageIcon("image\\rzeka2.jpg").getImage();
        tlo[2] =new ImageIcon("image\\rzeka3.jpg").getImage();
        tlo[3] =new ImageIcon("image\\rzeka1.jpg").getImage();
        kursor=new ImageIcon("image\\cel2.png").getImage();
         
         
        Nutki= new Image[6];
        Nutki[0]=new ImageIcon("image\\1.png").getImage();
        Nutki[1]=new ImageIcon("image\\4.png").getImage();
        Nutki[2]=new ImageIcon("image\\5.png").getImage();
        Nutki[3]=new ImageIcon("image\\8.png").getImage();
        Nutki[4]=new ImageIcon("image\\16.png").getImage();
        Nutki[5]=new ImageIcon("image\\32.png").getImage();
    }


}
