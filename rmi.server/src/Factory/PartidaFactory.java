/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Dominio.PartidaPoker;
import Interfaz.IJuego;
import Interfaz.IPartida;

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
            return new PartidaPoker();
        }
        
        return null;
    }


}
