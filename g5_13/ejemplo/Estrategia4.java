package es.udc.sistemasinteligentes.g5_13.ejemplo;

import es.udc.sistemasinteligentes.g5_13.Accion;
import es.udc.sistemasinteligentes.g5_13.Estado;
import es.udc.sistemasinteligentes.g5_13.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception{                                              //Metodo soluciona encargado de devolver un arraylist con la solucion del problema que se le pasa por entrada
        ArrayList<Estado> explorados = new ArrayList<>();                                                               //Lista de estados explorados
        ArrayList<Nodo> nodosExplorados = new ArrayList<>();                                                            //Lista de nodos explorados
        Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null, 1);                             //Nodo inicial con el estado del que parte el problema
        nodosExplorados.add(nodoActual);                                                                                //Añadimos el nodo inicial a la lista de nodos explorados
        explorados.add(nodoActual.estado);                                                                              //Y su estado a la lista de explorados
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.estado);

        while (!p.esMeta(nodoActual.estado)){                                                                           //Mientras no encuentra una meta ejecuta el codigo
            System.out.println((i++) + " - " + nodoActual.estado + " no es meta");
            Accion[] accionesDisponibles = p.acciones(nodoActual.estado);                                               //Crea un array en el que mete las acciones disponibles para el estado actual
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {                                                                     //Para cada accion una iteracion del bucle
                Estado sc = p.result(nodoActual.estado, acc);                                                           //Establece el nuevo estado despues de aplicar la accion
                System.out.println((i++) + " - RESULT(" + nodoActual.estado + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {                                                                         //Comprueba que el estado tras aplicar la accion no se ha explorado anteriormente
                    Nodo nodoPadre = new Nodo(nodoActual.nPadre, nodoActual.estado, nodoActual.accion, 1);        //Se crea el nodo padre para el nodo actual
                    nodoActual.estado = sc;                                                                             //El estado tras aplicar la accion se convierte en el estado del nodo actual
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(nodoActual.estado);                                                                  //Se añade el estado y nodo actual a las listas explorados
                    nodosExplorados.add((nodoActual));                                                                  //Y el nodo a la lista de nodos explorados
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + nodoActual.estado);
                    nodoActual.nPadre = nodoPadre;                                                                      //Se define el nodo Padre del nodo actual
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");                             //En caso de no encontrar una solucion manda exception
        }
        System.out.println((i++) + " - FIN - " + nodoActual.estado);
        return nodosExplorados;
    }

    @Override
    public Nodo estadoMeta(ArrayList<Nodo> nodos){                                                                      //Funcion para calcular el estado meta de la lista
        int i = nodos.size();
        return nodos.get(i-1);                                                                                          //Le resta uno al tamaño de la lista para calcular el ultimo
    }

    @Override
    public ArrayList<Nodo> reconstruye_sol(Nodo n){
        ArrayList<Nodo> nodoSol = new ArrayList<>();                                                                    //Creamos un array con los nodos desde el nodo meta al inicial
        Nodo nodoActual = n;
        nodoSol.add(nodoActual);
        while (nodoActual.nPadre!=null){                                                                                //Saltamos al nodo anterior a través del padre
            nodoSol.add(nodoActual.nPadre);
            nodoActual = nodoActual.nPadre;
        }
        return nodoSol;
    }

    @Override
    public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p){
        return null;                                                                                                    //No se usa en esta implementacion
    }
}