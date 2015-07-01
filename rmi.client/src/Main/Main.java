/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.CLogin;
import Controlador.Controlador;
import InterfacesVentana.ILogin;
//import Controlador.CNuevoJugador;
import InterfacesVentana.INuevoJugador;
import Ventanas.VLogin;
import InterfazCommon.ICasino;
import java.rmi.RemoteException;

//import Ventanas.VNuevoJugador;

/**
 *
 * @author Sebastian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {  
        
        //ICasino instanceCasino = Casino.getInstance();
        
        ILogin l = new VLogin();
        Controlador c = new CLogin(l);
        l.setControlador(c);         
                
    }
    
}
