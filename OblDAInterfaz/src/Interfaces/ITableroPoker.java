/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Interfaz.IJugador;
import Interfaz.IMano;
import Interfaz.IPartida;
import java.util.ArrayList;


/**
 *
 * @author Sebastian
 */
public interface ITableroPoker extends IVentana {
    public abstract IJugador getJugador();
    public abstract IPartida getPartida();
    public abstract void setPartida(IPartida partida);    
    public abstract void mostrarMano(IMano mano);
    public abstract void mostarSaldoJugador(String saldoJugador);
    public abstract float getMontoApostado();
    public abstract void mostrarPozo(String montoPozo);
    public abstract ArrayList<String> getBotonesDeCartasSeleccionadas();
    public abstract void habilitarBotonPedirCartas(boolean estado);
    public abstract void habilitarBotonApostar(boolean estado);
    public abstract void habilitarBotonPagar(boolean estado);
    public abstract void habilitarBotonRetirarme(boolean estado);
    public abstract void escribirLog(String log);
        

    
}
