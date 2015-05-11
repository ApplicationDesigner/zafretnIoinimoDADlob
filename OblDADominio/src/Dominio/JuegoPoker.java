/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJuego;
import Interfaz.IPartida;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Sebastian
 */
public class JuegoPoker extends Observable implements IJuego {
    
    
    private static JuegoPoker instance = null;
    private static ArrayList<IPartida> colPartidas;
    
    private static String nombre;
    private static int numero;
    private static int minimoJugadores;   
    
    
    private JuegoPoker() {
        JuegoPoker.colPartidas      = new ArrayList<>();
        JuegoPoker.nombre           = "JuegoPoker";        
        JuegoPoker.minimoJugadores  = 4;
    }
    
    public static JuegoPoker getInstance() {
        if(JuegoPoker.instance == null) {
            JuegoPoker.instance = new JuegoPoker();
        }        
        return JuegoPoker.instance;
    }    

    
    @Override
    public ArrayList<IPartida> getColPartidas() {
        return colPartidas;
    }

    @Override
    public void setColPartidas(ArrayList<IPartida> colPartidas) {
        JuegoPoker.colPartidas = colPartidas;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        JuegoPoker.nombre = nombre;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void setNumero(int numero) {
        JuegoPoker.numero = numero;
    }

    @Override
    public int getMinimoJugadores() {
        return minimoJugadores;
    }

    @Override
    public void setMinimoJugadores(int minimoJugadores) {
        JuegoPoker.minimoJugadores = minimoJugadores;
    }
    
    @Override
    public void agregarPartida(IPartida p) {
        JuegoPoker.colPartidas.add(p);        
    }
    
    @Override
    public String toString() {
        return ("Nombre: " + JuegoPoker.nombre + " Numero: " + JuegoPoker.numero);
    }

}
