
package nutki;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rino
 */
public class DoPliku {
   
    /**
     *
     * @param przeprowadzone
     * @param stracone
     * @param gdzie
     * @throws IOException
     */
    public DoPliku(int przeprowadzone, int stracone, int gdzie) throws IOException 
    {
          
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date date = new Date();
                DecimalFormat df=new DecimalFormat("0.000");
       
                
                File sciezka=new File("statystyki.txt");
		FileWriter file=new FileWriter(sciezka, true);
		String content ="Gracz: "+ Elementy.nazwa+"\r\n"
                               +"Data i czas rozgrywki: " + dateFormat.format(date)+"\r\n"       
                                + "Całkowity czas gry:  " + Elementy.medTime + " s"+ "\r\n" 
                                    + "Całkowity czas dmuchu : " + df.format(Elementy.blowTime) + " s"+ "\r\n"            
                                         + "Ilość nutek przeprowadzonych: " + przeprowadzone + "\r\n"
                                            +"Ilość nutek utopionych: " + stracone + "\r\n"+"\r\n" 
                                               +"---------------------------------------------------------------------------------------------------------------"+"\r\n"+"\r\n";
                
                  try {
			if (!sciezka.exists()) {
				sciezka.createNewFile();
			}
                
                         file.write(content);
                         file.flush();
                         file.close();
                         if(gdzie==0){
                         Desktop.getDesktop().open(sciezka);   
                         }
		} catch (IOException e) {
                     System.out.println(e);
                    }
           }
        }
    
    
           

   
           
           
           
        
            
    
   
