/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abb;

/**
 *
 * @author Estudiante
 */
public class Node {

    int value;
    String valor;
    Node left;
    Node rigth;

    public Node(){}
    
    public Node(int value) {
        this.value = value;
    }
    
    public Node(String valor){
        this.valor = valor;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    @Override
    public String toString() {
        return value + " ";
    }
    

    
}
