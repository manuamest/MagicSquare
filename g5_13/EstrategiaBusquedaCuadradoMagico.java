package es.udc.sistemasinteligentes.g5_13;

import es.udc.sistemasinteligentes.g5_13.Ejercicio2A.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.g5_13.ejemplo.Nodo;

import java.util.ArrayList;

public interface EstrategiaBusquedaCuadradoMagico {
    public abstract ArrayList<Nodo> busqueda_grafo (ProblemaCuadradoMagico p) throws Exception;

    public abstract ArrayList<Nodo> reconstruye_sol(Nodo n);

    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p);
}
