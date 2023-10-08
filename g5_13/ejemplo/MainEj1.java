package es.udc.sistemasinteligentes.g5_13.ejemplo;

import es.udc.sistemasinteligentes.g5_13.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Collections;

public class MainEj1 {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);                                            //Define el estado inicial del problema
        EstrategiaBusqueda buscador = new Estrategia4();                                                                //Define estrategia4

        System.out.println("ESTRATEGIA 4:\n");
        ArrayList<Nodo> nodoList = buscador.reconstruye_sol(buscador.estadoMeta(buscador.soluciona(aspiradora)));       //Arraylist donde se almacena la lista de nodos hasta la solucion que posteriormente se imprime
        Collections.reverse(nodoList);
        System.out.print("\n(");
        for (Nodo nodo : nodoList) {
            System.out.print(" "+nodo.estado+" ");
        }
        System.out.print(")");

        System.out.println("\n\n-----------------------------------------------------------\n");
        System.out.println("ESTRATEGIA BUSQUEDA GRAFO:\n");
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        buscador2.soluciona(aspiradora);                                                                                //Llamada a soluciona de EstrategiaBusquedaGrafo, que encuentra la solucion del problema e imprime una lista con los nodos hasta la solucion
    }
}
