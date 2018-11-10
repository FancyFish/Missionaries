/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.util.ArrayList;

/**
 *
 * @author boane
 */
public class NodeTree<T> {
    private T data;
    private ArrayList<NodeTree> chldren;

    public NodeTree(T data ) {
        this.data=data;
        this.chldren=new ArrayList<>();
    }

    public T getData() {
        return data;
    }
    
    public NodeTree<T> AddChildren(T data){
       NodeTree<T> nuevoNodo= new NodeTree(data);
       this.chldren.add(new NodeTree(data));
       return nuevoNodo;
                
    }
    public void AddChildren(NodeTree node){
        this.chldren.add(node);
    }   
    
}
