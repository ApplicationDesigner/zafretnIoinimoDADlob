/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import InterfazCommon.IJuego;
import InterfazCommon.IPartida;

/**
 *
 * @author Sebastian
 */
public abstract class AbstractFactory {
    public abstract IJuego getIJuego(String tipoJuego);
    public abstract IPartida getIPartida(String tipoPartida);
}
