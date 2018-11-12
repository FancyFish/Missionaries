/*
 */
package model;

/**
 * Clase estado, guarda los misioneros y canibales en cada lado del rio en un momento dado
 * @author boane
 */
public class State implements Comparable<State>{
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

    public State(int misioneDER, int cannibalDER, int misioneIZQ, int cannibalIZQ) {
        this.misioneDER = misioneDER;
        this.cannibalDER = cannibalDER;
        this.misioneIZQ = misioneIZQ;
        this.cannibalIZQ = cannibalIZQ;
    }
    
    public State() {
    }
    /**
     * permite realizar paso de canibales o misioneros a los lados del rio, dado determinado sentido
     * @param misioneros
     * @param canibales
     * @param sentido
     * @return 
     */
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
    /**
     * Permite verificar si es un movimiento valido de canibales y misioneros
     * de acuerdo a las reglas de la logica solo se pueden llevar 2 personas (capacidad del bote)
     * y deben existir al menos la cantidad de personas especificadas desde el lado donde se mueve
     * @param misioneros
     * @param canibales
     * @param sentido
     * @return 
     */
    public boolean validar(int misioneros, int canibales , char sentido){
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

    public int getCannibalDER() {
        return cannibalDER;
    }

    public int getMisioneDER() {
        return misioneDER;
    }

    public boolean esFinal() {
        if(misioneIZQ==MISSIONARIES&&
           cannibalIZQ==CANNIBALS     ){
            return true;
        }
        return false;
    }
    /**
     * Metodo compare to me permite decir si dos estados son iguales
     * @param o
     * @return 
     */
    @Override
    public int compareTo(State o) {
        if(o!=null){
            return (this.cannibalDER==o.getCannibalDER() &&
                         this.cannibalIZQ==o.getCannibalIZQ()&&
                         this.misioneDER==o.getMisioneDER() &&
                         this.misioneIZQ==o.getMisioneIZQ())?0:-1;
        }
        return -1;
    }

   
    
}
