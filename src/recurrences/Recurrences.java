package recurrences;

import java.io.*;

/**
 * El ejercicio consiste en trabajar ciertas operaciones con metodos recursivos que en varios casos resulta siendo 
 * la opcion mas eficaz comparandola con la manera iterativa. Todos las funciones a continuacion tienen recursion.
 * @author Brian Esteban Barreto Cardozo
 */
public class Recurrences {
    
    /**
     * Teniendo a x como parametro entero, retorna el factorial de ese numero.
     * @param x
     * @return el factorial de x
     */
    public int factorial(int x)
    {
        if(x==1 || x==0)
            return 1;
        else
            return x * factorial(x-1);
    }
    
    /**
     * Metodo con el que se obtiene el numero final de la serie fibonacci del numero dado. Si se toma el cero, su serie fibonacci
     * es cero, si es uno su serie fibonacci es uno, si es dos el ultimo digito de su serie fibonacci es tambien uno.
     * @param n
     * @return el ultimo numero de la serie fibonacci de n
     */
    public int fibonacci(int n)
    {
        if(n==0)
            return 0;
        else if (n==1) 
            return 1;
        else
            return fibonacci(n-1) + fibonacci(n-2); 
    }
    
    /**
     * La operacion que se realiza es sumar 'a' tantas veces como diga 'b', manera como tambien interpretar una multiplicacion.
     * @param a
     * @param b
     * @return la multiplicacion entre a y b
     */
    public int multiplicar(int a, int b)
    {
        if (a < 0) 
            return multiplicar(-a, -b);
        else if (a == 0) 
            return 0;
        else 
            return multiplicar(a-1, b) + b;
    }
    
    /**
     * Realiza el proceso logico del triangulo de pascal retornando un numero segun su n y k, si k=0 retorna 1 por ser la primera
     * columna o si n=k por tratarse de la diagonal principal. En otro caso hace la recursion.
     * @param n
     * @param k
     * @return el numero correspondiente siguiendo las instrucciones del triangulo de pascal
     */
    public static int pascal(int n, int k)
    {
        if (k==0 || k==n) 
            return 1;
        else
            return pascal(n-1, k-1) + pascal(n-1, k);
    }
    
    /**
     * usando el metodo pascal se obtiene el triangulo con 10 filas y 10 columnas porque asi es definido en los ciclos de i y j.
     * @return el triangulo de pascal (10 filas, 10 columnas)
     */
    public String print_pascal()
    {
        String pascal_matrix = "";
            for(int i = 0; i < 10; i++)
            {
                for(int j = 0; j <= i; j++)
                    pascal_matrix += pascal(i, j) + "\t";	
                    pascal_matrix += "\n";
            }	
        return pascal_matrix;
    }
    
    /**
     * 
     * @param s 
     */
    public void printInv(String s)
    {
        if (s.length() == 1) 
            System.out.print(s);
        else{
            printInv(s.substring(1));
            System.out.print(s.charAt(0));
        }
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return 
     */
    public int mcd_euclides(int a, int b)
    {
	return 0;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter( System.out ));
	Recurrences recurrencias = new Recurrences();
     
        bw.write(recurrencias.factorial(15) + "\n");
        bw.write(recurrencias.fibonacci(25) + "\n");
        bw.write(recurrencias.multiplicar(654, 97) + "\n");
        bw.write(recurrencias.print_pascal() + "\n");
        
        bw.flush();
        bw.close();        
    }
    
}
