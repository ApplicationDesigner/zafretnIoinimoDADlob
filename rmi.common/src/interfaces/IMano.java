/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Dominio.Carta;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public interface IMano {
    
    public abstract void agregarCarta(Carta unaCarta);
    public abstract void agregarCarta(Carta unaCarta, int posicion);
    public abstract ArrayList<Carta> getColCartas();
    public abstract IJugador getUnJugador();
    public abstract void setUnJugador(IJugador unJugador);
    public abstract void mostrarMano();
    
}
