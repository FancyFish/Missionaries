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
    private ArrayList<State> estadosHijo;
    private int idem;
    ArrayList<State> caminoo;

    public Tree() {

        root = new NodeTree<>(new State(true));
        caminoo = new ArrayList<>();

        State estadoTemporal = new State(0, 1, 3, 2);
        NodeTree<State> nodeTemp;
        nodeTemp=generarArbol( root, 1);
        
        System.out.println("------------------------------");
        System.out.println("------------------------------");
        System.out.println("Pasos Para resolver el algoritmo");
        imprimirpasos(nodeTemp);
        int pasos = 0;

        

    }
    private void imprimirpasos(NodeTree<State> resu){
        ArrayList<State> pasos= new ArrayList<>();
        
        while(resu!=null){
            pasos.add(0, resu.getData());
            resu=resu.getParent();
        }
        int step=1;
        State pasoPrev=null;
        byte sentido=1;
        for (State paso : pasos) {
            if(pasoPrev!=null){
                System.out.println(interprete(pasoPrev, paso, (sentido==1?'d':'i')));
                System.out.println("paso #"+step);
            paso.ImprimeEstado();
            step++;
            }
            pasoPrev=paso;
            
        }
    }
    /**
     * Convierte una transicion entre estados a palabras
     * @param inicial
     * @param fin
     * @param sentido 
     */
    private String interprete(State inicial, State fin, char sentido){
        if(inicial!=null && fin!=null){
            int misionerosMovidos, canibalMovidos;
            misionerosMovidos=inicial.getMisioneDER()-fin.getMisioneDER();
            canibalMovidos=inicial.getCannibalDER()-fin.getCannibalDER();
            if(misionerosMovidos==0&&canibalMovidos==0){
                return "no se efectua ninguna accion";
            } else if(misionerosMovidos>0|| canibalMovidos>0){
                
            }  else if(misionerosMovidos>0|| canibalMovidos>0){
            }     
            switch(sentido){
            case 'd':
                
                break;
            case 'i':
                break;
            }
        }
    }
    
/**
 * Genera el arbol de posibilidades para buscar la solucion. es un algoritmo recursivo 
 * (que se llama a si mismo), buscando primero en profundidad
 * @param camino
 * @param state
 * @param idem me permite cambiar el sentido en que se mueve
 * @param nivel
 * @return el nodo en que se encontro la respuesta, y que tiene conectados los
 * nodos que pertenecen a los pasos ue se realizaron
 */
    private NodeTree<State> generarArbol( NodeTree<State> state, int idem) {
        
        if (state != null && state.getData() != null) {
            if (!esEstadoMortal(state.getData())) {
                // SE CAMBIA EL SENTIDO DESDE EL QUE SE MUEVEN MISIONEROS O CANIBALES
                idem *= -1;
                // se generan  los estados que pueden derivarse del estado a evaluar, sin embargo estos estados hijo todavia no se utilizan
                estadosHijo = generaHijos(state.getData(), (idem == 1 ? 'd' : 'i'));
                
                for (State hijo : estadosHijo) {
                    
                    if (hijo.esFinal()) {
                        // SI ENCONTRO LA SOLUCION
                        NodeTree<State> temporal=state.AddChildren(hijo);
                        return temporal;
                    } else {
                        if (!yaExiste(hijo, state)) {
                            NodeTree<State> temporal = state.AddChildren(hijo);
                            //AQUI ES DONDE SE EMPLEA LA RECURSIVIDAD
                            NodeTree<State> res=generarArbol( temporal, idem);
                            if(res!=null){
                                return  res;
                            }
                        } else {
                            //aca permite detectar si ese paso se habia realizado antes
                            System.out.println("loop!");
                        }
                    }
                }

            } 

        }
        
        return null;
    }

    /**
     * Permite verificar si un estado ya se ha encontrado(generado) en la rama
     * en la que se ubica esto para evitar loops infinitos
     *
     * @param estado
     * @param rama
     * @return
     */
    public boolean yaExiste(State estado, NodeTree<State> rama) {
        NodeTree<State> current = rama;
        int countex = 0;
        while (current != null) {
            //si el estado ya existe en el nodo a revisar
            if (current.getData().compareTo(estado) == 0) {
                countex++;
            }
            current = current.getParent();

        }
        return (countex > 1);

    }

    public ArrayList<State> generaHijos(State s, char sentido) {
        ArrayList<State> hijos = new ArrayList<>();
        State nuevoEstado = new State();
        for (int i = 0; i <= 2; i++) {
            int j = 0;
            while (i + j <= 2) {
                if (i + j > 0) {
                    if (s.validar(i, j, sentido)) {
                        nuevoEstado = new State(s.getMisioneDER(), s.getCannibalDER(),
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
/**
 * Permite verificar si el estado a evaluar conduce a la muerte de alguno de los
 * misionesos
 * @param estado: estado a evaluar
 * @return true si alguno de los misioneros se muere
 */
    public boolean esEstadoMortal(State estado) {
        if ((estado.getCannibalIZQ() > estado.getMisioneIZQ()
                && estado.getMisioneIZQ() > 0) || (estado.getCannibalDER() > estado.getMisioneDER() && estado.getMisioneDER() > 0)) {
            return true;
        }
        return false;
    }

    /**
     * *
     * Verifica si el estado se tiene en cuenta como final del algoritmo
     * osea, si se pasaron todos los misioneros y canibales al otro lado del rio
     *
     * @param estado
     * @return
     */
    public boolean esResultado(State estado) {
        return estado.getCannibalIZQ() == 0 && estado.getMisioneIZQ() == 0;
    }
}
