/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obldadominio;

import Dominio.Casino;
import Interfaz.IJuego;
import Interfaz.IJugador;
import Interfaz.IPartida;
/**
 *
 * @author Sebastian
 */
public class OblDADominio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Casino casinoInstancia = Casino.getInstance();
        
        System.out.println(casinoInstancia.toString());
        
        IJuego j1;
        j1 = casinoInstancia.agregarJuego("POKER");        
        System.out.println(j1.toString());
        
        IPartida p1;
        p1 = casinoInstancia.agregarPartida(j1, "POKER");        
        System.out.println(p1.toString());
        
        j1.agregarPartida(p1);        
        
        IPartida p2;
        p2 = casinoInstancia.agregarPartida(j1, "POKER");        
        System.out.println(p2.toString());
        
        j1.agregarPartida(p2);  
        
        IJugador jug1 = casinoInstancia.buscarJugador("nickJugador1");
        IJugador jug2 = casinoInstancia.buscarJugador("nickJugador2");
        IJugador jug3 = casinoInstancia.buscarJugador("nickJugador3");
        IJugador jug4 = casinoInstancia.buscarJugador("nickJugador4");
        
        j1.ingresarJugadorAPartida(1, jug1);
        j1.ingresarJugadorAPartida(1, jug2);
        j1.ingresarJugadorAPartida(1, jug3);
        j1.ingresarJugadorAPartida(1, jug4);
        
        p1.iniciarReparticion();
        
        
                
        
    }
    
}
