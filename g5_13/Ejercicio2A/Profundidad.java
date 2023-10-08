package es.udc.sistemasinteligentes.g5_13.Ejercicio2A;

import es.udc.sistemasinteligentes.g5_13.Estado;
import es.udc.sistemasinteligentes.g5_13.ejemplo.Nodo;
import es.udc.sistemasinteligentes.g5_13.Accion;
import es.udc.sistemasinteligentes.g5_13.EstrategiaBusquedaCuadradoMagico;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Stack;//MAS CORTA Y MEJOR

public class Profundidad implements EstrategiaBusquedaCuadradoMagico {

    public Profundidad(){

    }
    int nodosCreados = 0;
    int nodosExpandidos = 0;
    @Override
    public ArrayList<Nodo> busqueda_grafo(ProblemaCuadradoMagico p) throws Exception{
        Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null, 1);                             //Creamos un nodo nuevo con el estado inicial
        nodosCreados++;
        ArrayList<Estado> explorados = new ArrayList<>();                                                               //Lista de estados explorados
        Stack<Nodo> frontera = new Stack<>();                                                                           //Pila frontera
        frontera.push(nodoActual);                                                                                      //Inserta un elemento al final de la estructura
        int i = 1;
        System.out.println(nodoActual.estado);

        while (!frontera.isEmpty()) {                                                                                   //Mientras la frontera no este vacia
            nodoActual = frontera.pop();                                                                                //Extrae el elemento que este mas arriba de la pila
            Estado posibleMeta = nodoActual.estado;
            if (p.esMeta(posibleMeta)) {                                                                                //Comprueba si el estado actual es la meta
                System.out.println((i++) + " - FIN - " + posibleMeta);
                System.out.println("nodosCreados = " + nodosCreados);
                System.out.println("nodosExpandidos = " + nodosExpandidos);
                return reconstruye_sol(nodoActual);
            } else {
                explorados.add(nodoActual.estado);
                ArrayList<Nodo> H = sucesores(nodoActual, p);                                                           //Crea una lista con los sucesores del nodo actual
                nodosExpandidos++;
                for (int j = 0; j < H.size() ; j++) {
                    Estado S = H.get(j).estado;
                    System.out.println((i++) + " " + S);
                    Nodo nodoHijo = new Nodo(nodoActual, S, H.get(j).accion, 1);
                    nodosCreados++;

                    if (!frontera.contains(nodoHijo) && !explorados.contains(S)) {                                      //En caso de que el nodoHijo no este en la frontera y su estado no se haya explorado
                        frontera.push(nodoHijo);                                                                        //Inserta el nodoHijo al final de frontera
                    } else {
                        System.out.println("nodosCreados = " + nodosCreados);
                        System.out.println("nodosExpandidos = " + nodosExpandidos);
                        throw new Exception("No se ha podido encontrar una solución");
                    }
                }
            }                                                                    //Se define el nodo Padre del nodo actual
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
