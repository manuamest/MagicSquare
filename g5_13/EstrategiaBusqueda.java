package es.udc.sistemasinteligentes.g5_13;

import es.udc.sistemasinteligentes.g5_13.ejemplo.Nodo;

import java.util.ArrayList;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    public abstract ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception;

    public Nodo estadoMeta(ArrayList<Nodo> nodos);

    public ArrayList<Nodo> reconstruye_sol(Nodo n);

    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p);

}
