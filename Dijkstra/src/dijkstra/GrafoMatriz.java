/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author Sebastian
 */
public class GrafoMatriz {

    int size;
    int cantNodos;
    Arco[][] matrizAdyacencia;
    boolean[] nodosUsados;

    public GrafoMatriz() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }

    public Arco[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public void setMatrizAdyacencia(Arco[][] matrizAdyacencia) {
        this.matrizAdyacencia = matrizAdyacencia;
    }

    public boolean[] getNodosUsados() {
        return nodosUsados;
    }

    public void setNodosUsados(boolean[] nodosUsados) {
        this.nodosUsados = nodosUsados;
    }

    public int caminoMasCorto(int o, int d) {

        int[] costo = new int[this.cantNodos + 1];

        for (int i = 1; i <= this.cantNodos; i++) {

            if (i != o) {
                if (o.esAdyacente(i)) {
                    costo[i] = this.matrizAdyacencia[i][o].kms;
                } else {
                    costo[i] = Integer.MAX_VALUE;
                }
            }
           

        }

        int[] camino = new int[this.cantNodos + 1];

        boolean[] visitado = new boolean[this.cantNodos + 1];

        for (int i = 1; i <= this.cantNodos; i++) {

            int u = distanciaMasCorta(costo, visitado);

            visitado[u] = true;

            for (int j = 1; j <= this.cantNodos; j++) {

                if (this.sonAdyacentes(u, j) && !visitado[j]) {

                    if (this.matrizAdyacencia[u][j].kms + costo[u] < costo[j]) {

                        costo[j] = this.matrizAdyacencia[u][j].kms + costo[u];

                        camino[j] = u;

                    }

                }

            }

        }

//        imprimirCaminoEnDisplay(
//        ...); 

 return costo[d];

    }

//Postcondición: Devuelve el indice del nodo de costo menor y no visitado 
    int distanciaMasCorta(int[] costo, boolean[] visitado) {

        int i = 0;
        int j = 0;

        while (visitado[i]) {
            i++;
        }

        int IndiceMin = i;

        boolean parar = false;

        for (j = i + 1; j <= visitado.length; j++) {

            if (costo[j] < costo[IndiceMin]) {
                IndiceMin = j;
            }

        }

        return IndiceMin;

    }

    private boolean sonAdyacentes(int u, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
