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
    private int idem;

    public Tree() {
        currentState = new State(true);
        root = new NodeTree<>(new State(true));
        estados = new ArrayList<>();
        idem = 1;
        generarArbol(root);
    }

    private void generarArbol(NodeTree<State> state){
            if(state!=null){
                generarArbol(state);
            }
        
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
