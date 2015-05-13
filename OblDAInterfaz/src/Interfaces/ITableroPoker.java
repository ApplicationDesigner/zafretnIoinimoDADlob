/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Interfaz.IJugador;
import Interfaz.IMano;
import Interfaz.IPartida;


/**
 *
 * @author Sebastian
 */
public interface ITableroPoker extends IVentana {
    public abstract IJugador getJugador();
    public abstract IPartida getPartida();
    public abstract void setPartida(IPartida partida);    
    public abstract void mostrarMano(IMano mano);
    public abstract void mostarSaldoJugador(IMano unaMano);
    public abstract float getMontoApostado();
    
}
