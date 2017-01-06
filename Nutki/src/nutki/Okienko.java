
package nutki;


import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author Rino
 */
public class Okienko extends JFrame {
    
    /**
     *
     * @param szerokosc
     * @param wysokosc
     * @param srodekx
     * @param srodeky
     */
    public Okienko(int szerokosc, int wysokosc, int srodekx, int srodeky) {
        super(); 
        
        
        Elementy.ładujobrazki();
        
        setSize(szerokosc,wysokosc ); 
        setLocation(srodekx,0); 
       // setUndecorated(true);
        setResizable(false); 
        setTitle("Uciekające Nutki");
      muzyczka(new File("sound\\rino.wav"));
        licznik_czasu(0);
        init(szerokosc,wysokosc, srodekx, srodeky);
        setVisible(true); //pokaż okno
     
   
        
    } //koniec Okienko
    
  
    private void init(int szerokosc, int wysokosc, int srodekx, int srodeky)
            
    { 
        setLayout(new GridLayout(1,1)); 
        Toolkit takietam = Toolkit.getDefaultToolkit();
        Cursor tarcza = takietam.createCustomCursor( Elementy.kursor, new Point(0,0), "Kursor");
        setCursor(tarcza);
        add(new PanelGraficzny(szerokosc, wysokosc, srodekx, srodeky));
             
    }
  
    /**
     *
     * @param zdarzenie
     */
    static public void licznik_czasu (int zdarzenie)
    {    
         
       if (zdarzenie==0)
       {
       Elementy.startTime=System.currentTimeMillis();
       }  
     
      
       if(zdarzenie==1)
       {
       Elementy.endTime=System.currentTimeMillis();
       Elementy.medTime= Elementy.medTime+(Elementy.endTime-Elementy.startTime)/1000L;
       Elementy.koniec=true;
       }
       
       if (zdarzenie==3)
       {
       Elementy.blowStart=System.currentTimeMillis();
       }  
     
      
       if(zdarzenie==4)
       {
       Elementy.blowStop=System.currentTimeMillis();
       Elementy.blowTime=Elementy.blowTime+(Elementy.blowStop-Elementy.blowStart)/1000L;
       
       }  
    }

  private synchronized void muzyczka(final File f) {
        new Thread(new Runnable() {
          @Override
          public void run() {
           
              try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                Clip clip = AudioSystem.getClip();
                clip.open(inputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                Thread.sleep(10000); // looping as long as this thread is alive        
                  
      
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
              System.err.println(e.getMessage());
            }
          }
        }).start();
    
    }   /// muzyczka koniec
  
}//koniec Okienka