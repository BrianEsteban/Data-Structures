package Sorts;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Metodos de ordenamiento tradicionales que trabajan sobre arreglos con numeros aleatorios, tambien hay metodos de busqueda
 * que son usados luego de ordenar un arreglo.
 * @author Brian Esteban Barreto
 */
public class Sorts {
    
   /**
    * bubleSort es un metodo de ordenamiento tradicional. Compara elementos y realiza un cambio(swap) si se cumple la condicion 
    * de que sea mayor el que se encuentra en la posicion i al que se encuentra en la posicion i+1. 
    * @param unsorted
    * @return arreglo ordenado
    */
    public int[] bubleSort(int[] unsorted)
    {
        boolean swap = false;
        int temp;     
        do
        {
            swap = false;
            for (int i = 0; i < (unsorted.length - 1); i++) {
                if (unsorted[i] > unsorted[i+1]) {
                    temp = unsorted[i];
                    unsorted[i] = unsorted[i+1];
                    unsorted[i+1] = temp;
                    swap=true;
                }
            }
        }while(swap);
        return null;
    }
    
    /**
     * countingSort crea un arreglo auxiliar con un tamaño del indice mayor del arreglo unsorted, de manera que cada posicion 
     * del nuevo arreglo coincida con cada indice de unsorted para llevar un conteo de las veces que es repetido un numero
     * @param unsorted
     */
    
     public void countingSort(int[] unsorted)
     {     
         int max = Integer.MIN_VALUE; //MIN_VALUE nos arroja el menor valor de los enteros.
         int index = 0;
         
         for (int i = 0; i < unsorted.length; i++) 
         {
             if (unsorted[i] > max) 
                 max = unsorted[i];
         }
         int[] aux = new int[max + 1];
         for (int i = 0; i < unsorted.length; i++) 
         {
            aux[ unsorted[i] ] += 1;
         }
         for (int i = 0; i < aux.length; i++){
             for (int j = 0; j < aux[i]; j++) 
             {
                unsorted[index] = i; 
                index += 1;
             }
         }
    }
    /**
     * insertionSort compara posiciones del arreglo en intervalos cada vez mas grandes.
     * @param unsorted
     */
    
    public void insertionSort(int[] unsorted)
    {
        int temp, indexHole;
        
        for (int i = 1; i < unsorted.length; i++) {
            temp = unsorted[i];
            indexHole = i;
            
            while(indexHole > 0 && unsorted[indexHole - 1] > temp)
            {
                unsorted[indexHole] = unsorted[indexHole -1];   //iguala por un momento las dos posicicones
                indexHole -= 1;                                 
            }
            unsorted[indexHole] = temp;                         //Realiza el cambio
        }
    }
    
    /**
     * mergeSort recursivo y que tambien llama a merge es un metodo que divide el arreglo en dos y estos a su vez van dividiendo
     * en dos hasta llegar a arreglos de tamaño 1. Luego compara devolviendose por el camino por el cual se hizo la division.
     * @param unsorted
     * @return arreglo ordenado
     */
    
    public int[] mergeSort(int[] unsorted)   //paso base, paso recursivo (Recursivas)
    { 
        int size;
        if (unsorted.length > 1) 
        {
            size = unsorted.length / 2;
            int[] left_array = new int[size];
            int[] right_array = new int[unsorted.length - size]; //por si el arreglo es impar
            
            for (int i = 0; i < size; i++) 
                left_array[i] = unsorted[i];
            
            for (int i = size; i < unsorted.length; i++) 
                right_array[i - size] = unsorted[i];
            
            left_array = mergeSort(left_array);
            right_array = mergeSort(right_array);
            
            return merge(left_array, right_array);
        }
        return unsorted;
    }
    
    public int[] merge(int[]A, int[]B)
    {
        int l = A.length + B.length; 
        int[] C = new int [l];
        int indexA = 0, indexB = 0, indexC = 0;
        
        while(indexA < A.length && indexB < B.length)
        {
            if (A[indexA] < B[indexB]) {
                C[indexC] = A[indexA];
                indexA += 1;
            }
            else
            {
                C[indexC] = B[indexB];
                indexB += 1;
            }
            indexC += 1;
        }
        
        while(indexA < A.length)
        {
            C[indexC] = A[indexA];
            indexA += 1;
            indexC += 1;
        }
        
        while(indexB < B.length)
        {
            C[indexC] = B[indexB];
            indexB += 1;
            indexC += 1;
        }
        
        return C;
    }
    
   /**
     * Sabiendo que Random es una funcion que arroja numeros al azar, para el caso entre 0 y 9, y los va guardando en cada 
     * posicion del arreglo temp que tendra tamaño length
     * @param length
     * @return arreglo con numeros aleatorios de tamaño length 
     */
    
    public int[] bigArray(int length)
    {
        Random rd = new Random();
        int[] temp = new int[length];
        
        for (int i = 0; i < length; i++) {
            temp[i] = rd.nextInt(10);
        }
        return temp;
    }

    /**
     * Funcion que imprime el arreglo
     * @param array
     * @throws IOException 
     */
    
    public void printArray(int[] array) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < array.length; i++) {
            bw.write(array[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
    
    /**
     * linealSearch, metodo de busqueda, compara cada posicion del arreglo mediante un bucle for(pues conocemos la dimesion)
     * hasta encontrar a x
     * @param sorted
     * @param x
     * @return posicion en la cual fue encontrado el numero x
     */
    
    public int linealSearch(int[] sorted, int x)
    {
        int index = -1;
        
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] == x) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    /**
     * binarySearch no recursivo, va descartando la mitad del arreglo preguntando si x es mayor o menor a middlePoint.
     * Esto lo sigue haciendo el ciclo hasta hallar x
     * @param sorted
     * @param x
     * @return posicion del arreglo en donde se encuentra x, si no está -1.
     */
    
    public int binarySearch(int sorted[], int x)
    {
        int lowerBound = 0;
        int upperBound = sorted.length - 1;
        int index = -1;
        
        while(lowerBound < upperBound)
        {
            int middlePoint = (lowerBound + upperBound) / 2;
            if (x == sorted[middlePoint]) {
                index = middlePoint;
                break;
            }
            else
            {
                if (x < middlePoint) 
                    upperBound = middlePoint - 1;
                else
                    lowerBound = middlePoint + 1;
            }
        }
        
        
        if ((lowerBound == upperBound) && sorted[lowerBound] == x) 
            index = lowerBound;
        
        return index;
    }
    
    /**
     * binary search recursivo tiene dos parametros adicionales que son los limites del arreglo y son necesarios para tener 
     * el paso de la finalizacion de la funcion recursiva. 
     * @param sorted
     * @param x
     * @param lB
     * @param uB
     * @return 
     */
    
    public int binarySearch_2(int[] sorted, int x, int lB, int uB)
    {
        int middlePoint = (lB + uB)/2;
        if (lB == uB) {
            if(sorted[middlePoint] == x)
                return middlePoint;  
            else
                return -1;
        }
        else
        {
            if (sorted[middlePoint] == x) 
                return middlePoint;
            else
            {
                if (x < sorted[middlePoint]) 
                    return binarySearch_2(sorted, x, lB, middlePoint);
                else
                    return binarySearch_2(sorted, x, middlePoint +1, uB);
            }
        }
    }
    
    /**
     * Metodo de busqueda con mejoras a partir de binary search
     * @param sorted
     * @param x
     * @return posicion del arreglo en donde se encuentra x
     */
    
    public int interpolationSearch(int[] sorted, int x)
    {
        int limiteInf = 0;
        int limiteSup = sorted.length - 1;
        int index = -1;
        
        while(limiteInf < limiteSup)
        {
            int puntoMedio = limiteInf + ((limiteSup - limiteInf) / sorted[limiteSup]-sorted[limiteInf]) * (x - sorted[limiteInf]);
            if (x == sorted[puntoMedio]) {
                index = puntoMedio;
                break;
            }
            else
            {
                if (x < sorted[puntoMedio]) 
                    limiteInf = puntoMedio-1;
                else
                    limiteSup = puntoMedio+1;
            }
        }
        
        if ((limiteInf == limiteSup) && (sorted[limiteInf] == x))
            index = limiteInf;
         
        return index;
    }
    
    /**
     * Prueba de los metodos de ordenamiento y metodos de busqueda
     * @param args
     * @throws IOException 
     */
           
    public static void main(String[] args) throws IOException {
        //
        Sorts sorts = new Sorts();
        long totalSum = 0;
        long startTime = System.currentTimeMillis();
        
        int[] a = sorts.bigArray(10);
        int[] b = sorts.bigArray(10);
        
        sorts.printArray(a);
        sorts.bubleSort(a);
        sorts.printArray(a);
        
        totalSum+= (System.currentTimeMillis()-startTime);
        System.out.println("Tiempo que demora bubleSort: " + totalSum);
        
        long totalSum2 = 0;
        long startTime2 = System.currentTimeMillis();
        
        
        sorts.printArray(b);
        sorts.countingSort(b);
        sorts.printArray(b);
        
        totalSum += (System.currentTimeMillis()-startTime2);
        System.out.println(sorts.binarySearch(b, 9));
        //System.out.println(sorts.binarySearch_2(b, 5, 0, b.length));
        //System.out.println(sorts.interpolationSearch(b, 5));
        //System.out.println(sorts.linealSearch(b, 5));
        
        System.out.println(" tiempo que demora countingSort y binarySearch: " + totalSum2);
    }
    
}
