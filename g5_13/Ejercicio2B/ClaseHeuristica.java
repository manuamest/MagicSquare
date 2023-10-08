package es.udc.sistemasinteligentes.g5_13.Ejercicio2B;

import es.udc.sistemasinteligentes.g5_13.Ejercicio2A.ProblemaCuadradoMagico;
import es.udc.sistemasinteligentes.g5_13.Estado;

public class ClaseHeuristica extends Heuristica {

    @Override
    public float evalua(Estado es) {
        Integer[][] matriz = ((ProblemaCuadradoMagico.estadoCuadradoMagico)es).matrizCuadradoMagico;
        int dimension = matriz.length;
        int result = dimension*(dimension*dimension+1)/2;                                                               //Variable para guardar el resultado correspondiente a la suma de una fila/columna/diagonal
        int diagonalizq = 0,diagonalder = 0, fila = 0, columna = 0;
        float pT = 0, pP =(float) 1/(2+dimension*2);                                                                    //Variable para guardar la puntuacion total y la puntuacion parcial
        for (int i = 0 ; i < dimension ; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j)
                    diagonalizq += matriz[i][j];
                if (i + j == dimension-1)
                    diagonalder += matriz[i][j];
                fila += matriz[i][j];
                columna += matriz[j][i];
            }
            if (fila == result)
                pT += pP;                                                                                               //Si la fila esta bien, aumenta la puntuacion
            if (columna == result)
                pT += pP;                                                                                               //Si la colummna esta bien, aumenta la puntuacion
            fila = 0;
            columna = 0;
        }
        if (diagonalizq == result)
            pT += pP;                                                                                                   //Si la diagonal izquierda esta bien, aumenta la puntuacion
        if (diagonalder == result)
            pT += pP;                                                                                                   //Si la diagonal izquierda esta bien, aumenta la puntuacion

        return pT;
        //Devuelve la meta
    }

    /*
    @Override
    public float evalua(Estado es) {
            Integer[][] matriz = ((ProblemaCuadradoMagico.estadoCuadradoMagico)es).matrizCuadradoMagico;
            int dimension = matriz.length;
            int result = dimension*(dimension*dimension+1)/2;
            int diagonalizq = 0,diagonalder = 0, fila = 0, columna = 0,
                    cnt = 0, filacont = 0, columcont = 0, diagonalicnt = 0, diagonaldcnt = 0, cnt2;
            float puntuacionTotal = 1, puntuacionParcial =(float) 1/(2+dimension*2+(dimension*dimension));
            for (int i = 0 ; i < dimension ; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (matriz[j][i] == 0) {
                        columcont++;
                    }
                    if (matriz[i][j] == 0) {
                        filacont++;
                        cnt++;
                    }
                    if (i == j) {
                        diagonalizq += matriz[i][j];
                        if (matriz[i][j] == 0)
                            diagonalicnt++;
                    }
                    if (i + j == dimension-1)
                        diagonalder += matriz[i][j];
                        if (matriz[i][j] == 0)
                            diagonaldcnt++;
                    fila += matriz[i][j];
                    columna += matriz[j][i];
                }
                if (fila == result)
                    puntuacionTotal -= puntuacionParcial;
                if (fila < result && filacont != 0)
                    puntuacionTotal -= puntuacionParcial;
                if (columna == result)
                    puntuacionTotal -= puntuacionParcial;
                if (columna == result && columcont != 0)
                    puntuacionTotal -= puntuacionParcial;
                fila = 0;
                filacont = 0;
                columna = 0;
                columcont = 0;
            }
            if (diagonalder == result)
                puntuacionTotal -= puntuacionParcial;
            if (diagonalder < result && diagonaldcnt != 0)
                puntuacionTotal -= puntuacionParcial;
            if (diagonalizq == result)
                puntuacionTotal -= puntuacionParcial;
            if (diagonalizq < result && diagonalicnt != 0)
                puntuacionTotal -= puntuacionParcial;
            if (cnt != 0) {
                puntuacionTotal -= puntuacionParcial;
            }
            cnt2 = dimension-cnt;
            while (cnt2 != 0) {
                puntuacionTotal -= puntuacionParcial;
                cnt2--;
            }
            return puntuacionTotal;
    }*/
}
