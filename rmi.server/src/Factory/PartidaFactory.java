/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dominio.PartidaPoker;
import InterfazCommon.IJuego;
import InterfazCommon.IPartida;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class PartidaFactory extends AbstractFactory {

    @Override
    public IJuego getIJuego(String tipoJuego) {
        return null;
    }

    @Override
    public IPartida getIPartida(String tipoPartida) {
        
        if(tipoPartida.equals("POKER")){
            try {
                return (IPartida) new PartidaPoker();
            } catch (RemoteException ex) {
                Logger.getLogger(PartidaFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }


}
