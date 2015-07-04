/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazCommon;

import DominioCommon.Carta;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public interface IMano  extends Serializable {
    
    public abstract void agregarCarta(Carta unaCarta);
    public abstract void agregarCarta(Carta unaCarta, int posicion);
    public abstract ArrayList<Carta> getColCartas();
    public abstract IJugador getUnJugador();
    public abstract void setUnJugador(IJugador unJugador);
    public abstract void setColCartas(ArrayList<Carta> colCartas);
    public abstract void mostrarMano();
    
}
