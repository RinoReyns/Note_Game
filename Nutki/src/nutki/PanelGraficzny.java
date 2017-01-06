///////// przeprowadzone po ostatnim levelu szfankują

package nutki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * @author Rino 
 */
public class PanelGraficzny extends JPanel  {
   
    /**
     *
     */
    public static int p=1;
    private static int przeprowadzone;
    private static int stracone;

    /**
     *
     */
    public int licznik_opoznienia_nutek=0;    

    /**
     *
     */
    public int sszerokosc; /// pola    

    /**
     *
     */
    public int swysokosc; //// pola

    /**
     *
     */
    public int wpasek= 70;  ///// wysokość paska

    /**
     *
     */
    public static Font menu;    

    /**
     *
     */
    public static Font staty;
    private static Stats [] Statystyki;

    /**
     *
     */
    public static int poziom=0;     ///////////////// czy działa w rysuj i innych miejscach użytku
    private  static Poruszanie [] ruch;

    /**
     *
     */
    public static int [] elementy= new int[Elementy.maxob];
    private static int srodekx;
  private static int srodeky;

    /**
     *
     * @param szerokosc
     * @param wysokosc
     * @param srodekx
     * @param srodeky
     */
    public PanelGraficzny(int szerokosc, int wysokosc, int srodekx, int srodeky){
      
        PanelGraficzny.srodekx=srodekx;
        PanelGraficzny.srodeky=srodeky;
        sszerokosc=szerokosc;
        swysokosc=wysokosc;
        resetGry();
        addMouseListener(new myszka()); 

        addMouseListener(new MouseAdapter(){
            @Override
              public void mouseClicked(MouseEvent me){
       
              
              //////////////// Guzik Menu
              if(me.getX()>(sszerokosc-150) && me.getY()<(70) && me.getX()<(sszerokosc-50)){    
                 Elementy.pause=!Elementy.pause; 
                  repaint();
                }
              
                ////////////////// Koniec gry
                 if(me.getX()<(250)  && me.getY()<(70) ){
                      if(Elementy.pause==true){
                          zamknij();
                  }
              }
                            //////Nowa gra
                if(me.getX()>560 && me.getX()<790 && me.getY()<(70)){
                    if(Elementy.pause==true){
                      poziom=0;
                      Elementy.pause=false;
                      resetGry();               
                  }
               repaint();
                }
                
////////////////////////////////////// sprawdzanie trafienia////////////////////////////////////////////////////////////////
               
                for(int i=0;i<ruch.length;i++){
                  if(me.getY()>(wpasek)){
                      if(ruch[i].zawierapunkty(me.getX(), me.getY())==true){
                          if(!ruch[i].hit[elementy[i]]){
                                ruch[i].Hit();
                                ruch[i].k=1;
                          }}}}
                 
                for(int i=0;i<ruch.length;i++)
                {  if(ruch[i].hit[elementy[i]]==true)
                    {   ruch[i].Hit();
                        }}}
            });
    }//koniec Panelu
    
   
    
    @Override
    protected void paintComponent(Graphics grafa){
        Graphics2D g=(Graphics2D)grafa;
        
          g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g.drawImage(Elementy.tlo[poziom], 0, 0, null);
          g.setColor(Color.white);
       
          if(Elementy.koniec==false && poziom<3){
          for (int i=0;i<p;i++){   
          ruch[i].ruszam();
          g.drawImage(ruch[i].icon[elementy[i]],ruch[i].nowX[elementy[i]],ruch[i].nowY[elementy[i]], null);
         
 
          if(Poruszanie.punkt==(-1)){
          Statystyki[poziom].stracone++;
          Poruszanie.punkt=0;
          elementy[i]=los();
          ruch[i].element=elementy[i];
            }
         
          if(Poruszanie.punkt==(1)){
          Statystyki[poziom].przeprowadzone++;
          Poruszanie.punkt=0;
          elementy[i]=los();
          ruch[i].element=elementy[i];
          }
        }
          }
        
        g.setColor(Color.red);
        g.setFont(menu);
        g.drawString("Menu",(sszerokosc-150),40);
         
       
        if(Elementy.pause==false ) 
        {
        g.setFont(menu);
        g.drawString("Poziom: ",450,40);
        g.drawString(""+Statystyki[poziom].level,610,40);
        
        g.setFont(staty);
        
        g.drawString("Uratowane nutki: ",10,30);
        g.drawString(""+Statystyki[poziom].przeprowadzone,285,30);
        
        g.drawString("Utopione nutki: ",10,75);
        g.drawString(""+Statystyki[poziom].stracone,260,75);
        }
        
        else{                                       ////////// wyświetlenie po wciśnieciu guzika menu
         g.setFont(menu);
         g.drawString("Koniec Gry!! ",10,40);
       // g.drawString("Inne",380,40);           
         g.drawString("Nowa Gra ",580,40);
        }
        rysuj();
        
        
    }
 
    private static void resetGry()
    
    { 
        if(poziom==0){
             menu=new Font("Jokerman",Font.BOLD,36);
             staty=new Font("Jokerman",Font.BOLD,28);
             Statystyki = new Stats[4];     
             ruch =new Poruszanie [Elementy.maxob];
     for(int i=0;i<Elementy.ileleveli+1;i++)
        {   Statystyki[i]= new Stats();
            Statystyki[i].reset();
            Statystyki[i].level=i+1;
        }
     przeprowadzone=0;
     stracone=0;
    }
    
    for(int i=0; i<Elementy.maxob;i++){
            elementy[i]=los();
            ruch[i]=new Poruszanie(0,i,Elementy.Nutki,elementy[i] );
    }
    }
    
    
    class myszka implements MouseListener
    {
    
    @Override
    public void mousePressed(MouseEvent e) {
       Okienko.licznik_czasu(3);
    }
 
    @Override
    public void mouseReleased(MouseEvent e) {
      Okienko.licznik_czasu(4);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    }

    /**
     *
     */
    public void rysuj()
{        
        if(p<4+poziom){ 
        if(licznik_opoznienia_nutek==10)
        { p++;  
          licznik_opoznienia_nutek=0;  
        }  
        else{
        licznik_opoznienia_nutek++;
        }
        }
        repaint();
        
        
     /////////////////// prawdzenie czy wymagana ilość nutek utopiona, albo przeprowadzonac
        if(Statystyki[poziom].przeprowadzone==15 && poziom==2)
        {
             przeprowadzone=przeprowadzone+Statystyki[poziom].przeprowadzone;
               stracone=stracone+Statystyki[poziom].stracone;
               okienpolevel(poziom);
            poziom++;
        }
        
        if(Statystyki[poziom].przeprowadzone==15)
           { 
               przeprowadzone=przeprowadzone+Statystyki[poziom].przeprowadzone;
               stracone=stracone+Statystyki[poziom].stracone;
               okienpolevel(poziom);
               poziom++; 
               resetGry();

           }
        
        if(Statystyki[poziom].stracone==5 )    
        {przeprowadzone=przeprowadzone+Statystyki[poziom].przeprowadzone;
               stracone=stracone+Statystyki[poziom].stracone;
               poziom=3;
               okienpolevel(poziom);
              
        }
        
        
       try {
            Thread.sleep(80);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanelGraficzny.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    
    /**
     *
     * @return
     */
    public static  int los(){
     Random y1 = new Random(); 
            int los=y1.nextInt(5);
        return los;
    }

    /**
     *
     */
    public static void zamknij()
         
 {
 Okienko.licznik_czasu(1);
                try {
                        DoPliku zapis= new DoPliku(przeprowadzone,stracone,0) ;
                         } catch (IOException ex) {
                            Logger.getLogger(PanelGraficzny.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      System.exit(0);
 
 }

    /**
     *
     */
    public static void zamknij1()
         
 {
        
                try {
                        DoPliku zapis= new DoPliku(przeprowadzone,stracone,1) ;
                         } catch (IOException ex) {
                            Logger.getLogger(PanelGraficzny.class.getName()).log(Level.SEVERE, null, ex);
                          }
                       
                Elementy.blowTime=0;
                Elementy.medTime=0;
                p=1;
                poziom=0;
                Elementy.pause=false;
                resetGry(); 
                      
                
                
                
 }

    /**
     *
     * @param poziom
     */
    public static void okienpolevel(int poziom)
         
 {
     if(poziom==2){
         
       OkienkoKoniec ok=new OkienkoKoniec (); 
       ok.setSize(360,320 ); 
       ok.setLocation(500,srodeky); 
       ok.setVisible(true);
     ok.setAlwaysOnTop(true);
     ok.setResizable(false); 
     }
    if((poziom==0) || (poziom==1)){
     OkienkoLeveli ok=new OkienkoLeveli (poziom); 
      ok.setSize(360,320 ); 
     ok.setLocation(500,srodeky); 
     ok.setVisible(true);
     ok.setAlwaysOnTop(true);
     ok.setResizable(false); 
     
     
     }
     if(poziom==3){
 OkienkoPrzegrana ok=new OkienkoPrzegrana (); 
    
      ok.setSize(360,320 ); 
     ok.setLocation(500,srodeky); 
     ok.setVisible(true);
     ok.setAlwaysOnTop(true);
     ok.setResizable(false); 
     }
 }

}



