
package nutki;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author Rino
 */
public class Poruszanie {
   
      private int xg;
      private int yg;

    /**
     *
     */
    public int[] nowX;

    /**
     *
     */
    public int [] nowY;

    /**
     *
     */
    public final  Image[] icon; //// tablica obrazów 
      private final  int waga;           /////// poruszanie prędkość 
      private  int [] szer;
      private  int  [] wys;
      private  int cszer;          ///// global
      private  int cwys;           ///// global

    /**
     *
     */
    public int element;        //// który element (jaka nutka) wybierana z tej tablicy 

    /**
     *
     */
    public int k=0;

    /**
     *
     */
    public boolean [] hit;//////// tablica hitów
      private boolean traf=false;

    /**
     *
     */
    public static int punkt=0;
      
    /**
     *
     * @param x
     * @param nutka
     * @param obraz
     * @param element
     */
    public Poruszanie(int x,int nutka, Image[] obraz, int element){
        
        this.element=element;
        this.xg=x;           //////// początkowy x          
        waga=nutka+1;//// szybkość poruszania zależna od tablicy
        init();
        icon = new Image[6]; 
        System.arraycopy(obraz, 0, icon, 0, 6);
        for(int i=0;i<6;i++){ 
        szer[i]=((int)(icon[i].getWidth(null)));
         wys[i]=(int)(icon[i].getHeight(null));
        
        }
    }
    
    /**
     *
     */
    public void Hit(){
          hit[element]=!hit[element];
          }
    
    private  int  movex(){ 
       return xg=waga+xg+2;}
 
 
     private  int  movey(){ 
            return yg=waga+yg;}

     
     private int  movey2(){ 
            return yg=(int)((yg+waga)*0.95);}
   
    /**
     *
     */
    public void ruszam(){
               
         if(k==0){
                nowX[element]=movex();
                nowY[element]=movey();           
        }
        else{ 
             k++;
                nowX[element]=movex();
                nowY[element]=movey2(); 
    
                if(k==10)
                    k=0;             
         }
     
         if (nowY[element]>cwys-30){
     
          xg=0;
          yg=yrandom();
          punkt=-1;
     
        }
     
        if (nowX[element]>cszer){
        
          xg=0;
          yg=yrandom();
          punkt=1;
        if (nowY[element]>cwys-100)
        {
          punkt=-1;
        }
     } 
 }
 
 ///////////// sprawdzanie czy wciśnięte miejsce celowanika zawiera się w obrazku

    /**
     *
     * @param x
     * @param y
     * @return
     */
      public boolean zawierapunkty(int x, int y){
     
             traf=false;
             if(x>=(int)((nowX[element]-0.3*(szer[element]))) && x<((int)(nowX[element]+0.70*(szer[element])))){   
                 if(y>=(nowY[element]+(wys[element]/2)-10) && y<(wys[element]+nowY[element]-50) && (element==1 )){
                 traf=true; 
                 }    
                 if(y>=(nowY[element]+(wys[element]/2)-10) && y<(wys[element]+nowY[element]-60) && ( element==2)){
                 traf=true; 
                 } 
                 ////////////////////// dla elementu 0
                if(y>=(int)(nowY[element]-wys[element]/2) && y<(int)((nowY[element]+wys[element]/2)) && (element==0) ){
                 traf=true;
                    }
                ///////////////// dla elementów 3,4,5
                if(y>=(int)(nowY[element]+(wys[element]/2)+10) && y<(int)((nowY[element]+ wys[element]-10))&&(element==3 || element==4  || element==5)){
                 traf=true;
                    }
                    }
              return traf;

}

private void init()
        
{
    
        cszer=1024;
        cwys=700;
       
        yg=yrandom();                      ///////// poczatkowy y
        nowX=new int[6];
        nowY=new int[6];
        szer=new int[6];
        wys=new int[6];
         hit =new boolean[6];
         for(int i=0;i<6;i++)
         {hit[i]=false;
         nowX[i]=xg;
         nowY[i]=yg;
         }

      }
        private int yrandom()
        {
        Random y1 = new Random(); 
        return y1.nextInt(400)+100;
        }

}