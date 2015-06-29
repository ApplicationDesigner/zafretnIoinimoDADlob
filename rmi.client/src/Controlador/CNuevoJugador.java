/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import InterfacesVentana.ILogin;
import InterfacesVentana.INuevoJugador;
import Ventanas.VLogin;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class CNuevoJugador extends Controlador {
    private INuevoJugador inj;
    
    public CNuevoJugador(INuevoJugador inj)  throws RemoteException {
        this.inj = inj;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
            if(e.getActionCommand().equals("NuevoJugador")) {
                
                System.out.println("Creando nuevo jugador...");
                ILogin vlogin = new VLogin();
                Controlador c = null;
                try {
                    c = new CLogin(vlogin);
                } catch (RemoteException ex) {
                    Logger.getLogger(CNuevoJugador.class.getName()).log(Level.SEVERE, null, ex);
                }
                vlogin.setControlador(c);
            }
    }

    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
