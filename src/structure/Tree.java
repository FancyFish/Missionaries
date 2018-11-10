/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.util.ArrayList;
import model.State;

/**
 *
 * @author boane
 */
public class Tree {

    private NodeTree<State> root;
    private State currentState;
    private ArrayList<State> estados;
    private ArrayList<State> estadosDerivados;
    private int idem;

    public Tree() {
        currentState = new State(true);
        root = new NodeTree<>(new State(true));
        estados = new ArrayList<>();
        estadosDerivados= new ArrayList<>();
        idem = 1;
        generarArbol(root);
    }

    private void generarArbol(NodeTree<State> state){
            if(state!=null ){
                if(!esEstadoMortal(state.getData())){
                    estadosDerivados= generaHijos(state.getData(), (idem==1?'d':'i'));
                    idem*=-1;
                    for (State estadoDerivado : estadosDerivados) {
                        if(!estados.contains(estadoDerivado)){
                            generarArbol(state.AddChildren(estadoDerivado));
                            estados.add(estadoDerivado);
                        }
                        if(estadoDerivado.esFinal()){
                            break;
                        }
                        
                    }
                    
                    
                }
            } 
    }
    public ArrayList<State> generaHijos(State s, char sentido){
        ArrayList<State>  hijos = new ArrayList<>();
        State nuevoEstado=new State();
        for(int i=0; i<=2; i++){
            int j =0;
            while(i+j<=2){
                if(i+j>0){
                    System.out.println("mover misioneros :"+ i +" canibales :"+j);
                    if(s.validar(i, j, sentido)){
                        nuevoEstado=new State(s.getMisioneDER(), s.getCannibalDER(), 
                                              s.getMisioneIZQ(), s.getCannibalIZQ());
                        nuevoEstado.mover(i, j, sentido);
                        hijos.add(nuevoEstado);
                    }
                }
                j++;
              
            }
        }
        return hijos;
    }
    
    public boolean esEstadoMortal(State estado){
        if((estado.getCannibalIZQ()>estado.getMisioneIZQ() 
                && estado.getMisioneIZQ()>0) ||(estado.getCannibalDER()>estado.getMisioneDER()&&estado.getMisioneDER()>0)){
                return true;
        }
        return false;
    }

    /**
     * *
     * Verifica si el estado se tiene en cuenta como final del algoritmo
     *
     * @param estado
     * @return
     */
    public boolean esResultado(State estado) {
        if (estado.getCannibalIZQ() == 0 && estado.getMisioneIZQ() == 0) {
            return true;
        }
        return false;
    }
}
