/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dominio.JuegoPoker;
import InterfazCommon.IJuego;
import InterfazCommon.IPartida;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class JuegoFactory extends AbstractFactory{

    @Override
    public IJuego getIJuego(String tipoJuego) {
        
        if(tipoJuego.equals("POKER")) {
            
            IJuego j = null;
            try {
                j = JuegoPoker.getInstance();
            } catch (RemoteException ex) {
                Logger.getLogger(JuegoFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return j;
        }
        
        return null;
    }

    @Override
    public IPartida getIPartida(String tipoPartida) {
        return null;
    }


    
}
