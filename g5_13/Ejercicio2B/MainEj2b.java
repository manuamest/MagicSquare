package es.udc.sistemasinteligentes.g5_13.Ejercicio2B;
import es.udc.sistemasinteligentes.g5_13.Ejercicio2A.ProblemaCuadradoMagico;

public class MainEj2b {
    public static void main(String[] args) throws Exception{
        //Integer [][] matriz = {{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        //Integer [][] matriz = {{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        Integer [][] matriz = {{2, 8, 15, 9}, {14, 12, 5, 3}, {11, 13, 0, 6}, {0, 1, 10, 0}};

        Heuristica h = new ClaseHeuristica();
        ProblemaCuadradoMagico.estadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.estadoCuadradoMagico(matriz);
        ProblemaCuadradoMagico p1 = new ProblemaCuadradoMagico(estadoInicial);
        System.out.println(estadoInicial);
        EstrategiaBusquedaInfo resEstrategiaBusquedaInformada = new EstrategiaBusquedaInfo();                           //Problema a resolver por EstrategiaBusquedaInformada
        System.out.println("ESTRATEGIA BUSQUEDA INFORMADA");resEstrategiaBusquedaInformada.soluciona(p1, h);
    }
}
