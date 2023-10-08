package es.udc.sistemasinteligentes.g5_13.Ejercicio2A;

import es.udc.sistemasinteligentes.g5_13.EstrategiaBusquedaCuadradoMagico;

public class MainEj2a {
    public static void main(String[] args) throws Exception {
        //Integer [][] matriz = {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        //Integer [][] matriz = {{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        //Integer [][] matriz = {{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 1, 0, 0}};
        Integer [][] matriz = {{2, 8, 15, 9}, {14, 12, 5, 3}, {11, 13, 0, 6}, {0, 1, 10, 0}};

        ProblemaCuadradoMagico.estadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.estadoCuadradoMagico(matriz);
        ProblemaCuadradoMagico p1 = new ProblemaCuadradoMagico(estadoInicial);
        ProblemaCuadradoMagico p2 = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusquedaCuadradoMagico resAnchura = new Anchura();                                                    //Problema a resolver por anchura
        EstrategiaBusquedaCuadradoMagico resProfundidad = new Profundidad();                                            //Problema a resolver por profundidad
        System.out.println("ANCHURA");resAnchura.busqueda_grafo(p1);
        System.out.println("------------------------------");
        System.out.println("PROFUNDIDAD");resProfundidad.busqueda_grafo(p2);
    }
}
