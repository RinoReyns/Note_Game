
package nutki;

/**
 *
 * @author Rino
 */
public class Stats {
      
    /**
     *
     */
    public static int przeprowadzone;

    /**
     *
     */
    public static int stracone;

    /**
     *
     */
    public static int level;

    /**
     *
     */
   public  void reset(){ 
       przeprowadzone=0;
     stracone=0;
     level=1;
    
   } 
    
    /**
     *
     */
    public void zpunkty()
   {    przeprowadzone=0;
     stracone=0;    
   }
   
    /**
     *
     */
    public void plevel()
   {   level++;   
   
   }
}
   
    

