package es.udc.sistemasinteligentes.g5_13.Ejercicio2A;

import es.udc.sistemasinteligentes.g5_13.Accion;
import es.udc.sistemasinteligentes.g5_13.Estado;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.ArrayList;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class estadoCuadradoMagico extends Estado {
        public Integer[][] matrizCuadradoMagico;                                                                        //Matriz que representa el estado del CuadradoMagico

        public estadoCuadradoMagico(Integer[][] matrizCuadradoMagico){                                                  //Constructor del CuadradoMagico
            this.matrizCuadradoMagico = matrizCuadradoMagico;
        }

        @Override
        public String toString() {                                                                                      //Funcion encargada de transformar la matriz en un String, para hacer posible su impresion
            StringBuilder matriz = new StringBuilder("");
            int dimension = matrizCuadradoMagico.length;
            matriz.append("\n");
            for (int i = 0; i < dimension; i++) {
                matriz.append("[");
                for (int j = 0; j < dimension; j++) {
                    matriz.append(" ");
                    if (!(matrizCuadradoMagico[i][j]-9>0))
                        matriz.append(" ");
                    matriz.append(matrizCuadradoMagico[i][j]);                                                          //Recorre la matriz y con un String builder añade cada elemento
                }
                matriz.append(" ]\n");
            }
            return matriz.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static class AccionCuadradoMagico extends Accion {
        int acc;                                                                                                        //Variable encargada de representar la accion mediante un numero

        public AccionCuadradoMagico(int acc) {
            this.acc = acc;                                                                                             //Contructor para definir las acciones
        }


        @Override
        public String toString() {
            return String.valueOf(acc);                                                                                 //Funcion para representar el valor de una accion mediante un entero
        }

        @Override
        public boolean esAplicable(Estado es) {                                                                         //Funcion para comprobar si una accion es aplicable a un estado
            int dimension = ((estadoCuadradoMagico)es).matrizCuadradoMagico.length;
            for (int i = 0; i < dimension ; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (((estadoCuadradoMagico) es).matrizCuadradoMagico[i][j] == acc) {                                //Recorre la matriz comprobando si la accion(el entero) ya esta en la matriz
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            estadoCuadradoMagico S = (estadoCuadradoMagico) es;
            int dimension = S.matrizCuadradoMagico.length;
            Integer[][] matriz = new Integer[S.matrizCuadradoMagico.length][S.matrizCuadradoMagico.length];             //Creamos una nueva matriz con las proporciones de la matriz estado
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    matriz[i][j] = S.matrizCuadradoMagico[i][j];                                                        //Hacemos un HardCopy de la matriz estado en la nueva
                }
            }
            estadoCuadradoMagico nuevo = new estadoCuadradoMagico(matriz);                                              //Creamos un nuevo estado que usa la matriz nueva como representacion

            for (int i = 0; i < dimension ; i++)
                for (int j = 0; j < dimension; j++) {
                    if (nuevo.matrizCuadradoMagico[i][j] == 0) {
                        nuevo.matrizCuadradoMagico[i][j] = acc;                                                         //Aplicamos la accion sobre el nuevo estado y luego lo devolvemos
                        return nuevo;
                    }
                }
            return nuevo;
        }
    }
    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico (estadoCuadradoMagico estadoCuadradoMagico) {
        super(estadoCuadradoMagico);
        //Inicializamos la lista de acciones
    }

    @Override
    public Accion[] acciones(Estado es) {
        ArrayList<Accion> accions = new ArrayList<>();                                                                  //Crea un arraylist de acciones
        int dimension = ((estadoCuadradoMagico)es).matrizCuadradoMagico.length;
        for (int i = 1; i <= dimension*dimension; i++) {
            Accion c = new AccionCuadradoMagico(i);
            if (c.esAplicable(es))
                accions.add(c);                                                                                         //Añade al arraylist dimension*dimension acciones
        }
        listaAcciones = accions.toArray(new Accion[0]);                                                                 //Pasamos el arraylist a un array de acciones
        return listaAcciones;
    }

    @Override
    public boolean esMeta(Estado es) {
        Integer[][] matriz = ((ProblemaCuadradoMagico.estadoCuadradoMagico)es).matrizCuadradoMagico;
        int dimension = matriz.length;
        int result = dimension*(dimension*dimension+1)/2;
        int diagonalizq = 0,diagonalder = 0, fila = 0, columna = 0;                                                     //Variables para calcular la suma de las diagonales, filas y columnas
        for (int i = 0 ; i < dimension ; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j)                                                                                             //Condicion para verificar que estamos en la diagonal izquierda
                    diagonalizq += matriz[i][j];                                                                        //Suma el valor de la matriz en la variable para la diagonal izquierda
                if (i + j == dimension-1)                                                                               //Condicion para verificar que estamos en la diagonal derecha
                    diagonalder += matriz[i][j];                                                                        //Suma el valor de la matriz en la variable para la diagonla derecha
                fila += matriz[i][j];                                                                                   //Suma en cada iteracion el valor de la fila a la variable dedicada a estas
                columna += matriz[j][i];                                                                                //Suma en cada iteracion el valor de la columna a la variable dedicada a estas
            }
            if (fila != result || columna != result)                                                                    //Comprueba si la fila y la columna actuales suman los valores correctos
                return false;
            fila = 0;                                                                                                   //Resetea la variable donde se almacena la suma de cada fila
            columna = 0;                                                                                                //Resetea la variable donde se almacena la suma de cada columna
        }
        return diagonalizq == result && diagonalder == result;                                                          //Comprueba si la sumas de las diagonales corresponden al resultado correcto
        //Devuelve la meta
    }
}