/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missionaries;

import model.State;

/**
 *
 * @author boane
 */
public class Missionaries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        State s= new State(true);
        System.out.println(s.mover(1, 1, 'i'));
        s.ImprimeEstado();
        System.out.println(s.mover(1, 0, 'i'));
        s.ImprimeEstado();
        System.out.println(s.mover(1, 0, 'i'));
        s.ImprimeEstado();
    }
    
}
