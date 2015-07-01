        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DominioCommon;

import Configuraciones.Constantes;
import InterfazCommon.IJugador;
import InterfazCommon.IMano;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Mano implements IMano {

    private final ArrayList<Carta> colCartas;
    private IJugador unJugador;

    public Mano() {
        colCartas = new ArrayList<>(Constantes.getCantCartasEnMano());
    }

    public Mano(IJugador unJugador) {
        this.colCartas = null;
        this.unJugador = unJugador;
    }

    @Override
    public void agregarCarta(Carta unaCarta) {
        this.colCartas.add(unaCarta);
    }

    @Override
    public ArrayList<Carta> getColCartas() {
        return colCartas;
    }

    @Override
    public IJugador getUnJugador() {
        return unJugador;
    }

    @Override
    public void setUnJugador(IJugador unJugador) {
        this.unJugador = unJugador;
    }

    public void mostrarMano() {

        for (Carta unaCarta : this.colCartas) {
            System.out.println(unaCarta.toString());
        }
    }

    @Override
    public void agregarCarta(Carta unaCarta, int posicion) {
        this.colCartas.add(posicion, unaCarta);
    }

}
