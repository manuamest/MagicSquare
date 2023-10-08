package es.udc.sistemasinteligentes.g5_13;

public class Nodo extends Comparable<Nodo> {
    public Nodo nPadre;
    public Estado estado;
    public Accion accion;
    public float costeF;                                                                                                //Variable que contiene el coste de del camino seguid

    public Nodo(Nodo nPadre, Estado estado, Accion accion, float coste) {                                               //Contructor para meter los argumentos de cada nodo en el mismo
        this.nPadre = nPadre;
        this.estado = estado;
        this.accion = accion;
        this.costeF = coste;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Nodo o) {                                                                                      //Funcion para comparar los costes de dos nodos diferentes
        return Float.compare(this.costeF, o.costeF);
    }
}
