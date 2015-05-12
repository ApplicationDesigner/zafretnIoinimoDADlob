package Interfaz;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sebastian
 */
public interface IJuego {    
    
    public abstract  ArrayList<IPartida> getColPartidas();
    public abstract void setColPartidas(ArrayList<IPartida> colPartidas);
    public abstract String getNombre();
    public abstract void setNombre(String nombre);
    public abstract int getNumero();
    public abstract void setNumero(int numero);
    public abstract int getMinimoJugadores();
    public abstract void setMinimoJugadores(int minimoJugadores);
    public abstract IPartida buscarPartida(int numero);
    public abstract void agregarPartida(IPartida p);
    public abstract void ingresarJugadorAPartida(int nroPartida, IJugador j);
    
    
    @Override
    public abstract String toString();
}
