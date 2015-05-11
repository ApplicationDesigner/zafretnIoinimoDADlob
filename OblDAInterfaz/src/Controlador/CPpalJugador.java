/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.IIngresarAPartida;
import Interfaces.ITableroPoker;
import Ventanas.VTableroPoker;
import java.awt.event.ActionEvent;

/**
 *
 * @author Sebastian
 */
public class CPpalJugador extends Controlador {
    private IIngresarAPartida iip;
    
    public CPpalJugador(IIngresarAPartida iip) {
        this.iip = iip;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
            if(e.getActionCommand().equals("IIngresarAPartida")) {
                
                System.out.println("Creando nueva partida...");
                ITableroPoker itp = new VTableroPoker();
                
                Controlador c = new CTableroPoker(itp);
                itp.setControlador(c);
            }
    }
}
