        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJugador;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Mano {
    
    public final static int cantCartas = 5;
    private final ArrayList<Carta> colCartas;
    private IJugador unJugador;
    
    public Mano() {
        colCartas       = new ArrayList<>(cantCartas);
        this.unJugador  = null;
    }
    
    public Mano(IJugador unJugador) {
        this.colCartas = null;
        this.unJugador = unJugador;
    }
    
    public void agregarCarta(Carta unaCarta) {
        this.colCartas.add(unaCarta);
    }

    public ArrayList<Carta> getColCartas() {
        return colCartas;
    }

    public IJugador getUnJugador() {
        return unJugador;
    }

    public void setUnJugador(IJugador unJugador) {
        this.unJugador = unJugador;
    }
    
    
    
    public void mostrarMano() {       
           
        for (Carta unaCarta : this.colCartas) {
            System.out.println(unaCarta.toString());
        }   
    }
}
