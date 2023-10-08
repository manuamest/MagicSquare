package es.udc.sistemasinteligentes.g5_13;

import es.udc.sistemasinteligentes.g5_13.Ejercicio2A.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.g5_13.Ejercicio2B.Heuristica;
import es.udc.sistemasinteligentes.g5_13.ejemplo.Nodo;

import java.util.ArrayList;

public interface EstrategiaBusquedaInformada {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @param h Heurística que asigna a un estado un valor de utilidad
     * @return Estado meta obtenido
     */
    public abstract Estado soluciona(ProblemaCuadradoMagico p, Heuristica h) throws Exception;

    public abstract ArrayList<Nodo> reconstruye_sol(Nodo n);

    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p);
}
