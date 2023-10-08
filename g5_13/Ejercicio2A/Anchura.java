package es.udc.sistemasinteligentes.g5_13.Ejercicio2A;

import es.udc.sistemasinteligentes.g5_13.Estado;
import es.udc.sistemasinteligentes.g5_13.ejemplo.Nodo;
import es.udc.sistemasinteligentes.g5_13.Accion;
import es.udc.sistemasinteligentes.g5_13.EstrategiaBusquedaCuadradoMagico;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.*;

public class Anchura implements EstrategiaBusquedaCuadradoMagico {

    public Anchura() {
    }
    int nodosCreados = 0;
    int nodosExpandidos = 0;

    @Override
    public ArrayList<Nodo> busqueda_grafo(ProblemaCuadradoMagico p) throws Exception{
        Estado S = p.getEstadoInicial();                                                                                //Se define el estado inicial del problema
        System.out.println("Estado inicial"+"\n"+S);
        Nodo nodoActual = new Nodo(null, S, null, 1);                                                //Se crea un nodo nuevo
        nodosCreados++;
        ArrayList<Estado> explorados = new ArrayList<>();                                                               //ArrayList para almacenar los estados explorados
        if (p.esMeta(S)) {                                                                                              //Comprueba si el estado inicial es meta
            System.out.println(S);
            explorados.add(S);
            return reconstruye_sol(nodoActual);                                                                         //Llamada a reconstruye sol para devolver la lista de nodos hasta la solucion
        }
        Queue<Nodo> frontera = new LinkedList<>();                                                                      //Cola frontera
        frontera.add(nodoActual);
        int i = 0;
        while (!frontera.isEmpty()) {                                                                                   //Mientras la frontera no este vacia ejecuta
            nodoActual = frontera.poll();                                                                               //Devuelve el elemento que esta en la cabeza de la cola
            S = nodoActual.estado;
            explorados.add(S);
            ArrayList<Nodo> H = sucesores(nodoActual, p);                                                               //Crea una lista con los nodos sucesores
            nodosExpandidos++;
            for (int j = 0; j < H.size(); j++) {                                                                        //Para cada sucesor
                Estado Shijo = H.get(j).estado;
                Nodo nodoHijo = new Nodo(nodoActual, Shijo, H.get(j).accion, 1);                                  //Crea un Nodo hijo
                nodosCreados++;
                if (p.esMeta(Shijo)) {                                                                                  //Comprueba si el estado actual es meta
                    System.out.println((i++) + " - FIN - " + Shijo);
                    System.out.println("nodosCreados = " + nodosCreados);
                    System.out.println("nodosExpandidos = " + nodosExpandidos);
                    return reconstruye_sol(nodoActual);
                } else {
                    System.out.println((i++) + " - " + Shijo + " no es meta");
                    if (!frontera.contains(nodoHijo) && !explorados.contains(Shijo))                                    //En caso de que el nodo hijo no este en la frontera y su estado no se haya explorado, se añade a la frontera
                        frontera.add(nodoHijo);
                    else {
                        System.out.println("nodosCreados = " + nodosCreados);
                        System.out.println("nodosExpandidos = " + nodosExpandidos);
                        throw new Exception("No se ha podido encontrar una solución");
                    }
                }
            }
        }
        return reconstruye_sol(nodoActual);
    }

    @Override
    public ArrayList<Nodo> reconstruye_sol(Nodo n){
        ArrayList<Nodo> nodoSol = new ArrayList<>();                                                                    //Creamos un array con los nodos desde el nodo meta al inicial
        Nodo nodoActual = n;
        nodosCreados++;
        nodoSol.add(nodoActual);
        while (nodoActual.nPadre!=null){                                                                                //Saltamos al nodo anterior a través del padre
            nodoSol.add(nodoActual.nPadre);
            nodoActual = nodoActual.nPadre;
        }
        return nodoSol;
    }

    @Override
    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p){
        ArrayList<Nodo> listaSucesores = new ArrayList<>();                                                             //Creamos una lista para almacenar los sucesores
        Estado res;
        Accion[] la = p.acciones(n.estado);                                                                             //Almacenamos las acciones en un array de acciones

        for (int i = 0; i < la.length; i++) {
            res = p.result(n.estado, la[i]);                                                                            //Calculamos el resultado de aplicar la accion i al estado
            Nodo sucesor = new Nodo(n, res, la[i], 1);
            nodosCreados++;
            listaSucesores.add(sucesor);                                                                                //Almacenamos el nuevo nodo en la lista de sucesores
        }
        return listaSucesores;
    }
}
