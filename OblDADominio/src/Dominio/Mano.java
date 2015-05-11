        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Mano {
    
    public final static int cantCartas = 5;
    private final ArrayList<Carta> colCartas;
    private Jugador unJugador;
    
    public Mano() {
        colCartas       = new ArrayList<>(cantCartas);
        this.unJugador  = null;
    }
    
    public Mano(Jugador unJugador) {
        this.colCartas = null;
        this.unJugador = unJugador;
    }
    
    public void agregarCarta(Carta unaCarta) {
        this.colCartas.add(unaCarta);
    }

    public ArrayList<Carta> getColCartas() {
        return colCartas;
    }

    public Jugador getUnJugador() {
        return unJugador;
    }

    public void setUnJugador(Jugador unJugador) {
        this.unJugador = unJugador;
    }
    
    
    
    public void mostrarMano() {       
           
        for (Carta unaCarta : this.colCartas) {
            System.out.println(unaCarta.toString());
        }   
    }
}
