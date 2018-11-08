/*
 */
package model;

/**
 *
 * @author boane
 */
public class State {
    public static final int MISSIONARIES=3;
    public static final int CANNIBALS=3;
    private int misioneDER;
    private int cannibalDER;
    private int misioneIZQ;
    private int cannibalIZQ;
    public State(boolean  inicial) {
        if(inicial){
            misioneDER=MISSIONARIES;
            cannibalDER=CANNIBALS;
        }
    }

    public State() {
    }
    public boolean mover(int misioneros, int canibales , char sentido){
        if(validar( misioneros,  canibales , sentido)){
            switch (sentido){
                case 'i':
                    misioneDER-=misioneros;
                    cannibalDER-=canibales;
                    
                    misioneIZQ+=misioneros;
                    cannibalIZQ+=canibales;
                    break;
                case 'd':
                    misioneDER+=misioneros;
                    cannibalDER+=canibales;
                    
                    misioneIZQ-=misioneros;
                    cannibalIZQ-=canibales;
                    break;
            }
        return true;
        }
        
        return false;
    }
    private boolean validar(int misioneros, int canibales , char sentido){
        if(misioneros+canibales<=2){
            switch(sentido){
            case  'i':
                if(misioneDER>=misioneros&&
                    cannibalDER>=canibales    ){                    
                    return true;
                }
                break;
            case 'd' :
                if(misioneIZQ>=misioneros&&
                   cannibalIZQ>=canibales    ){
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public int getCannibalIZQ() {
        return cannibalIZQ;
    }

    public int getMisioneIZQ() {
        return misioneIZQ;
    }
    /**
     * imprime estado log---
     */
    public void ImprimeEstado(){
        System.out.println("Estado"
                + "\n Derecha "
                +" \t misiioneros "+ misioneDER
                +"  \t Canibales"+ cannibalDER
                
                +"\n Izquierda"
                +" \t misiioneros "+ misioneIZQ
                + "  \t Canibales"+ cannibalIZQ
                
                
                
        );
    }        
}
