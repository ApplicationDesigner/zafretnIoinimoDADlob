/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dominio.JuegoPoker;
import Interfaz.IJuego;
import Interfaz.IPartida;

/**
 *
 * @author Sebastian
 */
public class JuegoFactory extends AbstractFactory{

    @Override
    public IJuego getIJuego(String tipoJuego) {
        
        if(tipoJuego.equals("POKER")) {
            return JuegoPoker.getInstance();
        }
        
        return null;
    }

    @Override
    public IPartida getIPartida(String tipoPartida) {
        return null;
    }


    
}
