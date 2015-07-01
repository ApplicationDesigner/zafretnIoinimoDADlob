/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesVentana;

import InterfazCommon.IJugador;

/**
 *
 * @author Sebastian
 */
public interface IIngresarAPartida extends IVentana {
    public abstract void setJugador(IJugador j);
    public abstract IJugador getJugador();
    public abstract int getPartidaSeleccionada();
    
}
