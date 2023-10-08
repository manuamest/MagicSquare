package es.udc.sistemasinteligentes.g5_13.ejemplo;

import es.udc.sistemasinteligentes.g5_13.Accion;
import es.udc.sistemasinteligentes.g5_13.Estado;
import es.udc.sistemasinteligentes.g5_13.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g5_13.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

        public EstrategiaBusquedaGrafo() {
        }

        @Override
        public ArrayList<Nodo> soluciona(ProblemaBusqueda p) throws Exception{                                          //Metodo soluciona encargado de devolver un arraylist con la solucion del problema que se le pasa por entrada
            Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null, 1);                         //Nodo inicial con el estado del que parte el problema
            ArrayList<Estado> explorados = new ArrayList<>();                                                           //Lista de estados explorados
            ArrayList<Nodo> nodosExplorados = new ArrayList<>();                                                        //Lista de nodos explorados
            ArrayList<Nodo> frontera = new ArrayList<>();                                                               //Lista de nodos compuesta por los nodos frontera
            frontera.add(nodoActual);                                                                                   //Añade ell nodo inical a la frontera
            int i = 1;

            System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.estado);

            while (!frontera.isEmpty()){                                                                                //Mientras la frontera tenga elementos sigue buscando
                Nodo N = frontera.get(0);
                frontera.remove(0);                                                                               //Elimina el primer elemento de la frontrera despues de guardarlo
                Estado posibleMeta = N.estado;
                if (posibleMeta != nodoActual.estado)
                    System.out.println((i++) + " - Estado actual cambiado a " + N.estado);

                if (p.esMeta(posibleMeta)){                                                                             //Comprueba si el estado actual es meta
                    System.out.println((i++) + " - FIN - " + posibleMeta);
                    explorados.add(N.estado);
                    nodosExplorados.add(N);
                    ArrayList<Nodo> listSol = reconstruye_sol(N);
                    Collections.reverse(listSol);
                    System.out.println("");
                    System.out.print("(");
                    for (Nodo nodo : listSol) System.out.print(" "+nodo.estado+" ");
                    System.out.print(")");
                    return nodosExplorados;                                                                             //Finaliza imprimiendo la lista de nodos hasta la solucion en caso de encontrar la solucion
                } else {
                    System.out.println((i++) + " - " + posibleMeta + " no es meta");
                    if (!explorados.contains(N.estado)) {                                                               //Comprueba si la lista de nodos explorados contiene el estado que se acaba de comprobar
                        System.out.println((i++) + " - " + N.estado + " NO explorado");
                        explorados.add(N.estado);                                                                       //Añade el ultimo nodo que se comprobo a la lista de explorados
                        nodosExplorados.add(N);
                    }
                    ArrayList<Nodo> H = sucesores(N, p);                                                                //Crea una lista H con los sucesores del nodo actual
                        for (Nodo nodo : H) {
                            if (!frontera.contains(nodo) && !explorados.contains(nodo.estado)) {
                                frontera.add(nodo);                                                                     //En caso de que el nodo actual no este en la frontera y su estado no haya sido explorado, este se añade a la frontera
                            }
                    }
                }
            }
            throw new Exception("No se ha podido encontrar una solución");
        }

        @Override
        public Nodo estadoMeta(ArrayList<Nodo> nodos){                                                                  //Funcion para calcular el estado meta de la lista
            int i = nodos.size();
            return nodos.get(i-1);
        }

        @Override
        public ArrayList<Nodo> reconstruye_sol(Nodo n){
            ArrayList<Nodo> nodoSol = new ArrayList<>();                                                                //Creamos un array con los nodos desde el nodo meta al inicial
            Nodo nodoActual = n;
            nodoSol.add(nodoActual);
            while (nodoActual.nPadre!=null){                                                                            //Saltamos al nodo anterior a través del padre
                nodoSol.add(nodoActual.nPadre);
                nodoActual = nodoActual.nPadre;
            }
            return nodoSol;
        }

        @Override
        public ArrayList<Nodo> sucesores(Nodo n, ProblemaBusqueda p){
            ArrayList<Nodo> listaSucesores = new ArrayList<>();                                                         //Crea una lista de nodos en la que introducira los sucesores del nodo
            Estado res;
            Accion accion = n.accion;
            for (int i = 0; i < p.acciones(n.estado).length; i++) {                                                     //Bucle para cada accion
                res = p.result(n.estado, p.acciones(n.estado)[i]);                                                      //Calcula el resultado de aplicarle una accion a un estado
                Nodo sucesor = new Nodo(n, res, accion, 1);                                                       //Crea un nodo sucesor
                listaSucesores.add(sucesor);                                                                            //Añade el nodo creado a la lista de sucesores
            }
            return listaSucesores;
        }
}