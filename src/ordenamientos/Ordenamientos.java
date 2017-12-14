/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamientos;

import java.util.Arrays;

/**
 *
 * @author JJSC
 */
public class Ordenamientos {

    /**
     * Intercambiar datos en un arreglo segun la posicion
     *
     * @param a listarreglo
     * @param pos1 Posicion inicial
     * @param pos2 Posicioin final
     */
    private static void cambio(int[] a, int pos1, int pos2) {
        int t;
        t = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = t;
    }

    /**
     * Metodo intercambio
     *
     * @param lista
     */
    public static void intercambio(int lista[]) {
        //Usamos un bucle anidado
        for (int i = 0; i < (lista.length - 1); i++) {
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[i] > lista[j]) {
                    //Intercambiamos valores
                    cambio(lista, i, j);

                }
            }
        }
    }

    /**
     * Metodo quicksort
     *
     * @param lista
     * @param izq limite inferior
     * @param der limite superior
     */
    public static void quicksort(int lista[], int izq, int der) {
        int i = izq;
        int j = der;
        int pivote = lista[(i + j) / 2];
        do {
            while (lista[i] < pivote) {
                i++;
            }
            while (lista[j] > pivote) {
                j--;
            }
            if (i <= j) {
                cambio(lista, i, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            quicksort(lista, izq, j);
        }
        if (i < der) {
            quicksort(lista, i, der);
        }
    }

    /**
     * Metodo burbuja
     *
     * @param lista
     */
    public static void burbuja(int lista[]) {
        int cuentaCambios = 0;
        //Usamos un bucle anidado, saldra cuando este ordenado el array
        for (boolean ordenado = false; !ordenado;) {
            for (int i = 0; i < lista.length - 1; i++) {
                if (lista[i] > lista[i + 1]) {
                    //Intercambiamos valores
                    cambio(lista, i, i+1);
                    //indicamos que hay un cambio
                    cuentaCambios++;
                }
            }
            //Si no hay intercambios, es que esta ordenado.
            if (cuentaCambios == 0) {
                ordenado = true;
            }
            //Inicializamos la variable para que empiece a contar de nuevo
            cuentaCambios = 0;
        }
    }

    /**
     * Metodo seleccion.
     *
     * @param lista el datosFuente que sera ordenado.
     */
    public static void seleccion(int[] lista) {
        //iteramos sobre los elementos 
        for (int i = 0; i < lista.length - 1; i++) {
            int min = i;

            //buscamos el menor número
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[j] < lista[min]) {
                    min = j;    //encontramos el menor número
                }
            }
            if (i != min) {
                //permutamos los valores
                cambio(lista, i, min);
            }
        }
    }

    /**
     * Metodo shell
     *
     * @param lista
     */
    public static void shell(int[] lista) {
        int d, i, sw;
        int n = lista.length;
        d = n;
        do {
            d = d / 2;
            do {
                sw = 0;
                i = -1;
                do {
                    i++;
                    if (lista[i] > lista[i + d]) {
                        cambio(lista, i, i + d);
                        sw = 1;
                    }
                } while (i + d != n - 1);
            } while (sw != 0);
        } while (d != 1);
    }

    /**
     * Metodo mezcla 
     * @param lista
     * @param izq
     * @param der 
     */
    public static void mezcla(int lista[], int izq, int der) {
        if (izq < der) {
            int m = (izq + der) / 2;
            mezcla(lista, izq, m);
            mezcla(lista, m + 1, der);
            merge(lista, izq, m, der);
        }
    }

    /**
     * Clase real de ordenamiento merge
     * 
     * @param lista
     * @param izq Valor inicial
     * @param m Valor medio
     * @param der Valor final
     */
    private static void merge(int lista[], int izq, int m, int der) {
        int i, j, k;
        int[] arr = new int[lista.length]; //array auxiliar
        for (i = izq; i <= der; i++) //copia ambas mitades en el array auxiliar
        {
            arr[i] = lista[i];
        }

        i = izq;
        j = m + 1;
        k = izq;
        while (i <= m && j <= der) //copia el siguiente elemento más grande
        {
            if (arr[i] <= arr[j]) {
                lista[k++] = arr[i++];
            } else {
                lista[k++] = arr[j++];
            }
        }
        while (i <= m) //copia los elementos que quedan de la
        {
            lista[k++] = arr[i++]; //primera mitad (si los hay)
        }
    }

    /**
     * Generar numeros aleatorios
     *
     * @param cuantos Cantidad de numeros a generar
     * @return
     */
    public static int[] generarNumeros(int cuantos) {
        int[] resultado = new int[cuantos];
        int m = 1;
        int n = cuantos * 2;

        for (int i = 0; i < cuantos; i++) {
            resultado[i] = (int) Math.floor(Math.random() * (n - m + 1) + m);
        }
        return resultado;
    }

    public static double diffTiempo(double start) {
        return (System.nanoTime() - start) / 1000000.0;
    }

    /**
     * Copiar un arreglo en otro
     *
     * @param fuente
     * @param destino (debe estar declarado y reservado con el espacio adecuado)
     */
    public static void copiarArreglo(int[] fuente, int[] destino) {
        System.arraycopy(fuente, 0, destino, 0, fuente.length);
    }

    
    public static void main(String[] args) {
        int cantidad = 20000;
        double start;

        int[] datosOrdenar = new int[cantidad];
        int[] datosFuente = generarNumeros(cantidad);

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        burbuja(datosOrdenar);
        System.out.println(String.format("Burbuja %f [msec]", diffTiempo(start)));

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        intercambio(datosOrdenar);
        System.out.println(String.format("Intercambio %f [msec]", diffTiempo(start)));

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        seleccion(datosOrdenar);
        System.out.println(String.format("Seleccion %f [msec]", diffTiempo(start)));
        
        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        mezcla(datosOrdenar, 0, datosOrdenar.length - 1);
        System.out.println(String.format("Mezcla %f [msec]", diffTiempo(start)));

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        shell(datosOrdenar);
        System.out.println(String.format("Shell %f [msec]", diffTiempo(start)));

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        quicksort(datosOrdenar, 0, datosOrdenar.length - 1);
        System.out.println(String.format("Quicksort %f [msec]", diffTiempo(start)));

        copiarArreglo(datosFuente, datosOrdenar);
        start = System.nanoTime();
        Arrays.sort(datosOrdenar);
        System.out.println(String.format("Arrays.sort %f [msec]", diffTiempo(start)));

        /*        
        for (int i = 0; i < datosFuente.length; i++) {
             System.out.println(datosOrdenar[i]);
        }
        */
        
    }

}
