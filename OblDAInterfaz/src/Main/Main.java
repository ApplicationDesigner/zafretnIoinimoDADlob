/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.Controlador;
import Controlador.CNuevoJugador;
import Dominio.Casino;
import Interfaces.INuevoJugador;
import Interfaz.ICasino;

import Ventanas.VNuevoJugador;

/**
 *
 * @author Sebastian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        
        ICasino instanceCasino = Casino.getInstance();
        
        
        
        INuevoJugador vnj = new VNuevoJugador();
        Controlador c     = new CNuevoJugador(vnj);
        vnj.setControlador(c);            
                
    }
    
}
