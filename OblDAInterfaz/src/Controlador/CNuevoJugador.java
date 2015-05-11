/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.ILogin;
import Interfaces.INuevoJugador;
import Ventanas.VLogin;
import java.awt.event.ActionEvent;

/**
 *
 * @author Sebastian
 */
public class CNuevoJugador extends Controlador {
    private INuevoJugador inj;
    
    public CNuevoJugador(INuevoJugador inj) {
        this.inj = inj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
            if(e.getActionCommand().equals("NuevoJugador")) {
                
                System.out.println("Creando nuevo jugador...");
                ILogin vlogin = new VLogin();
                Controlador c = new CLogin(vlogin);
                vlogin.setControlador(c);
            }
    }
    
}
