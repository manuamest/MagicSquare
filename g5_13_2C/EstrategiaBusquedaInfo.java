package es.udc.sistemasinteligentes.g5_13;

import java.util.ArrayList;
import java.util.Collections;

public class EstrategiaBusquedaInfo implements EstrategiaBusquedaInformada {
    public EstrategiaBusquedaInfo(){

    }
    int nodoCreados = 0;
    int nodoExpandidos = 0;
    @Override
    public Estado soluciona(ProblemaCuadradoMagico p, Heuristica h) throws Exception{
            float coste = h.evalua(p.getEstadoInicial());                                                               //Establece el coste de la heuristica del estado inicial
            Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null, coste);
            nodoCreados++;
            ArrayList<Estado> explorados = new ArrayList<>();
            ArrayList<Nodo> frontera = new ArrayList<>();
            frontera.add(nodoActual);
            int i = 1;
            System.out.println(nodoActual.estado);

            while (!frontera.isEmpty()) {                                                                               //Mientras la frontera tenga elementos se busca solucion
                frontera.sort(Nodo::compareTo);                                                                         //Ordena la frontera usanado la funcion compareTo que compara nodo a nodo su coste
                Collections.reverse(frontera);                                                                          //La frontera se ordena inversamente, revirtiendola obtenemos el orden deseado
                nodoActual = frontera.remove(0);                                                                  //Elimina el primer elemento de la frontera
                Estado posibleMeta = nodoActual.estado;
                if (p.esMeta(posibleMeta)) {                                                                            //Comprueba si el estado del nodoActual es meta
                    System.out.println((i++) + " - FIN - " + posibleMeta);
                    System.out.println("nodoCreados = " + nodoCreados);
                    System.out.println("nodoExpandidos = " + nodoExpandidos);
                    return nodoActual.estado;
                } else {
                    explorados.add(nodoActual.estado);
                    ArrayList<Nodo> H = sucesores(nodoActual, p);                                                       //Crea una Arraylist de nodos compuesta por los sucesores del nodoActual
                    nodoExpandidos++;
                    for (int j = 0; j < H.size() ; j++) {                                                               //Recorre la lista hijo a hijo
                        Estado S = H.get(j).estado;
                        float c = h.evalua(S);                                                                          //Calcula el coste del nodo hijo que se esta evaluando
                        float cn = c + nodoActual.costeF;                                                               //Suma el coste del nodo hijo al coste total del camino

                        Nodo nodoHijo = new Nodo(nodoActual, S, H.get(j).accion, cn);                                   //Crea un nodoHijo con el coste del camino
                        nodoCreados++;
                        if (!explorados.contains(S)) {                                                                  //Comprueba si el estado del nodo hijo esta en la lista de explorados
                            if (!frontera.isEmpty()) {                                                                  //Comprueba si quedan elementos en la frontera
                                for (int l = 0; l < frontera.size(); l++) {
                                    if ((frontera.get(l).estado == S)) {                                                //Busca el estado actual en la frontera
                                        if (c < nodoHijo.costeF) {                                                      //Comprueba si el coste del camino del estado encontrado es mayor que el del actual
                                            for (int k = 0; k < frontera.size(); k++) {
                                                if (frontera.get(k).estado == S && frontera.get(k).costeF > nodoHijo.costeF) {
                                                    frontera.remove(k);                                                 //Sustituye el nodo con el menor coste de camino en la lista frontera
                                                    frontera.add(nodoHijo);
                                                    System.out.println((i++) + " " + S);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!frontera.contains(nodoHijo)) {                                                         //En caso de que el nodoHijo no este en la frontera lo añade y lo printea
                                frontera.add(nodoHijo);
                                System.out.println((i++) + " " + S);
                            }
                        }
                    }
                }
            }
            return nodoActual.estado;
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
            ArrayList<Nodo> listaSucesores = new ArrayList<>();                                                         //Creamos una lista para almacenar los sucesores
            Estado res;
            Accion[] la = p.acciones(n.estado);                                                                         //Almacenamos las acciones en un array de acciones
            for (int i = 0; i < la.length; i++) {
                res = p.result(n.estado, la[i]);                                                                        //Calculamos el resultado de aplicar la accion i al estado
                Nodo sucesor = new Nodo(n, res, la[i], 1);
                nodoCreados++;
                listaSucesores.add(sucesor);                                                                            //Almacenamos el nuevo nodo en la lista de sucesores
            }
            return listaSucesores;
        }
    }
