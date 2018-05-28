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
public class Abb {
    Node rigth = null;
    Node left = null;

    public Node root;
          
    public void abb()
    {
        Node root = new Node();
    }
    
    public void insert(int value)
    {
        Node node = new Node(value);
        
        if (root == null) 
            root = node;
        else
        {
            Node temp = root;
            Node parent = null;
            boolean left = false;
            while(temp != null)
            {
                parent = temp;
                if (value < temp.value)
                {
                    temp = temp.left;
                    left = true;
                }
                else
                {
                    temp = temp.rigth;
                    left = false;
                }
            }
            temp = node;
            if (left) 
                parent.left = temp;
            else
                parent.rigth = temp;
            
        }   
    }
    
    public void print_inorder(Node node)
    {
        if (node != null) {
            print_inorder(node.left);
            System.out.println(node);
            print_inorder(node.rigth);
        }    
    }
    
    public void print_postorder(Node node)
    {
        if (node != null) {
            print_postorder(node.left);
            print_postorder(node.rigth);
            System.out.println(node);
        }    
    }
    
    public void print_preorder(Node node)
    {
        if (node != null) {
            System.out.println(node);
            print_preorder(node.left);
            print_preorder(node.rigth);
        }    
    }
    
    public int offspring(Node node)
    {
        return (node.left != null) ? (node.rigth != null ? 2 : 1) : (node.rigth != null ? 1 : 0);
    }
    
    public void delete(int value)
    {
        Node temp = root;
        Node parent = root;
        boolean left = false;
                
        while(temp != null)
        {
            if (temp.value == value) 
                break;
            else
            {
                parent = temp;
                
                if (value < temp.value)
                {
                   temp = temp.left; 
                   left = true;
                   //parent.left = temp;
                }
                else{
                   temp = temp.rigth;
                   left = false;
                }
            }
        }
        
        if (temp != null) 
        {
            int counter = this.offspring(temp);
            if (counter == 0) {
                if (left) 
                    parent.left = null;
                else
                    parent.rigth = null;
            }else
            {
                if (counter == 1) 
                {
                    if (temp.left != null) 
                    {
                        if (left) 
                            parent.left = temp.left;
                        else
                            parent.rigth = temp.rigth;
                    }
                    else
                    {
                        if (left) 
                           parent.left = temp.left;
                        else
                            parent.rigth = temp.rigth;
                    }
                }
                else
                {                                       //menor de los mayores desde el nodo a eliminar
                        Node less = temp.rigth;       //en el peor de los casos tomará este
                        Node less_parent = less;
                        
                        while(less.left != null)
                        {
                            less_parent = less;
                            less = less.left;
                        }
                        
                        if (!less_parent.equals(less)) {
                            if (less.rigth != null) 
                                less_parent.left = less.rigth;
                            
                            less.rigth = temp.rigth;
                        }
                            
                        less.left = temp.left;
                        
                        if (left) 
                            parent.left = less;
                        else
                            parent.rigth = less;    
                        
                        /*less = parent.left;
                        less = parent.rigth;    
                        rigth = parent.left;*/
                }     
            }
        }
    }
    
    public Node search(int value)
    {
        Node temp = root;
        Node parent = root;
        boolean left = false;
        
        while(temp != null)
        {
            if (temp.value == value) {
                break;
            }else
                parent = temp;
                
                if (value < temp.value){
                   temp = temp.left; 
                   left = true;
                   //parent.left = temp;
                }
                else{
                   temp = temp.rigth;
                   left = false;
                }
        }
        return temp;
    }
    
    public String pre_post(String inorder, String preorder)
    {
        Abb recovery = new Abb();
        recovery = recovery_subtree(inorder, preorder);
        recovery.print_preorder(recovery.root);
        
        return null;
    }
    
   
    public String pre_pos(String inorder, String preorder)
    {
        Abb recovery = new Abb();
        int [] indexes = new int[inorder.length()];
        
        for (int i = 0; i < inorder.length(); i++) 
            indexes[i] = preorder.indexOf( inorder.substring(i, i+1) );
        
        for(int i = 0; i<indexes.length; i++)
            System.out.print( indexes[i] + " ");
        
        /*
        for (int i = 0; i < indexes.length; i++) 
        {
            int menor = indexes[0];
            if (menor > indexes[i]) {
                menor = indexes[i];
            }
            String primera = inorder.substring(0,menor);
            String segunda = inorder.substring(menor, inorder.length());
            recursion(primera, segunda);
        }
        */
        return null;
    }


    public Abb recovery_subtree(String sub_inorder, String preorder)
    {
        System.out.println(sub_inorder);
        
        if (sub_inorder.length() == 0)
            return new Abb();
        else
        {
            Abb temp = new Abb();
            if (sub_inorder.length() == 1)
                temp.root = new Node(sub_inorder);
            else
            {
                int[] indexes = new int[sub_inorder.length()];
                
                for (int i = 0; i < sub_inorder.length(); i++) 
                    indexes[i] = preorder.indexOf( sub_inorder.substring(i, i+1));
                
                int less = Integer.MAX_VALUE, index = -1;
                for (int i = 0; i < indexes.length; i++) {
                    if (indexes[i] < less) {
                        index = i;
                        less = indexes[i];
                    }
                }
                //Abb temp = new Abb();
                temp.root = new Node( sub_inorder.substring(index, index + 1) );                         //Guardar en el nodo raiz el indice menor
                temp.root.left = recovery_subtree(sub_inorder.substring(0, index), preorder).root;       //sub arbol izquierdo entra a la recursividad
                temp.root.rigth = recovery_subtree(sub_inorder.substring(index + 1), preorder).root;     //sub arbol derecho entra a la recursividad
            }
        return temp;
        }
    }
    
    public String recursion(String primera,String segunda)
    {
        if (primera.length() < 1 || segunda.length() < 1 ) {
            return primera;
        }else
            return recursion(primera, segunda);    
    }
            
    
    
    public static void main(String[] args) 
    {
        Abb binaryTree = new Abb();
        binaryTree.insert(52);
        binaryTree.insert(18);
        binaryTree.insert(74);
        binaryTree.insert(60);
        binaryTree.insert(87);
        binaryTree.insert(83);
        binaryTree.insert(100);
        binaryTree.insert(85);
        System.out.println("Inorder:");
        binaryTree.print_inorder(binaryTree.root);
        System.out.println("Posorder:");
        binaryTree.print_postorder(binaryTree.root);
        System.out.println("Preorder:");
        binaryTree.print_preorder(binaryTree.root);
        /*
        binaryTree.delete(87);
        System.out.println("Inorder:");
        binaryTree.print_inorder(binaryTree.root);
        */
        System.out.println(binaryTree.pre_post("ABCDEFG","DBACEGF"));
    }
}
