/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJuego;
import Interfaz.IJugador;
import Interfaz.IPartida;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class JuegoPoker implements IJuego {
    
    
    private static JuegoPoker instance = null;
    private static ArrayList<IPartida> colPartidas;
    
    private static String nombre;
    private static int numero;    
    private static float ganancias;
    
    
    private JuegoPoker() {
        JuegoPoker.colPartidas      = new ArrayList<>();       
        JuegoPoker.nombre           = "JuegoPoker"; 
        JuegoPoker.ganancias        = 0;
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
    public float getGanancias() {
        return ganancias;
    }

    @Override
    public void setGanancias(float ganancias) {
        JuegoPoker.ganancias = ganancias;
    }
    
    @Override
    public void sumarGanancias(float monto) {        
        this.setGanancias(this.getGanancias() + monto);
        System.out.println("Las ganancias del JuegoPoker son: " + this.getGanancias());
    }
    
    
    @Override
    public void agregarPartida(IPartida p) {
        JuegoPoker.colPartidas.add(p);        
    }
    
    @Override
    public String toString() {
        return ("Nombre: " + JuegoPoker.nombre + " Numero: " + JuegoPoker.numero);
    }

    @Override
    public IPartida buscarPartida(int numero) {
        IPartida retorno = null;
        for(IPartida unaPartida : this.getColPartidas()){
            if(unaPartida.getNumero() == numero){
                retorno = unaPartida;
            }
        }
        return retorno;
    }

    @Override
    public void ingresarJugadorAPartida(int nroPartida, IJugador j) {
        IPartida unaPartida = this.buscarPartida(nroPartida);
        if(unaPartida != null){
            unaPartida.ingresarJugador(j);
        }
    }

}
