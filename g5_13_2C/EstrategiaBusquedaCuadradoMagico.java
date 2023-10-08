package es.udc.sistemasinteligentes.g5_13;

import java.util.ArrayList;

public interface EstrategiaBusquedaCuadradoMagico {
    public abstract ArrayList<Nodo> busqueda_grafo (ProblemaCuadradoMagico p) throws Exception;

    public abstract ArrayList<Nodo> reconstruye_sol(Nodo n);

    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p);
}
