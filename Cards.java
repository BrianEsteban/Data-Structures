package Cards;

import java.io.*;
import java.util.*;

/**
 * Throwing Cards Away consiste en tomar el mazo de una baraja ordenada de 1 a n, y la secuencia que sigue es: quitar el primer elemento,
 * luego quitar el segundo pero encolarlo inmediatamente a la lista, y así sucesivamente hasta que hayan menos de dos cartas. Lo que 
 * imprime es las cartas eliminadas antecedidas de la frase: "Discarded cards:" y también la carta que queda antecedida de la frase:
 * "Remaining cards:"
 * @author Brian Esteban Barreto Cardozo
 */
public class Cards {
    Node head = null;
    
    /**
     * Si la cabeza está vacía es por que la cola está vacía
     * @return verdadero si está vacía, falso si tiene al menos un elemente
     */
    public boolean isEmpty()
    {
        return head == null;
    }
    
    
    /**
     * Desencola el elemento de la cabeza, de manera que temp es la nueva cabeza para no perder información, así:
     * se guarda la informacion de temp en una variable de tipo String info.
     * @return la información guardada en info, o sea el elemento desencolado.
     */
    public int dequeue()
    {
        Node temp = head;
        head = head.next;
        int info = temp.value;
        temp = null;
        System.gc();
        return info;
    }
     
    /**
     * Si la cola está vacia el nuevo nodo será la cabeza, de lo contrario, recorre hasta que el apuntador de un nodo sea null y ahí se agrega.
     * @param newNode 
     */
    public void enqueue(Node newNode) 
    {
        if (isEmpty()) 
            head = newNode;
        else
        {
            Node temp = head;
            while(temp.next != null)
                temp = temp.next;
            
            temp.next = newNode;     
        }    
    }
    
    /**
     * Cada vez que pasa por un nodo va aumentando el contador
     * @return la dimension de la cola
     */
    public int size()
    {
        int size = 0;
        Node temp = head;
        while(temp != null)
        {
            temp = temp.next;
            size ++;
        }   
        return size;
    }
    
    /**
     * Se Trabaja con los métodos de las colas, pero primero se realiza un ciclo con el limite n_cards, que es el número de cartas 
     * que tiene el mazo y se encolan los números enteros hasta ese n, luego se procede a desencolar la primera carta e imprimirla,
     * la segunda se desencola y se vuelve a encolar, así hasta que queda una sola carta y que se imprime de ultimas.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException 
    {
        Cards card = new Cards();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);
        int n_cards = sc.nextInt();
        
        while(n_cards != 0)
        {
            for(int i = 1; i <= n_cards; i++) 
                card.enqueue(new Node(i));
            
            bw.write("Discarded cards: ");
            while(card.size() >= 2)
            {
                bw.write(card.dequeue() + ", ");
                int deq_enq = card.dequeue();
                Node enq = new Node(deq_enq);
                card.enqueue(enq);
            }
            bw.write("\n" + "Remaining cards: " + card.dequeue() + "\n");
            bw.flush();
            n_cards = sc.nextInt();
        }           
    }
}
/*
//Entradas: 7 19 10 6 0.
7 
Rspuesta: 
Discarded cards: 1, 3, 5, 7, 4, 2, 
Remaining cards: 6

19
Respuesta:
Discarded cards: 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 4, 8, 12, 16, 2, 10, 18, 14, 
Remaining cards: 6

10
Respuesta:
Discarded cards: 1, 3, 5, 7, 9, 2, 6, 10, 8, 
Remaining cards: 4

6
Respuesta
Discarded cards: 1, 3, 5, 2, 6, 
Remaining cards: 4

0
fin
*/