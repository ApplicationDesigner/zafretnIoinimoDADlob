/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Dominio.Carta;
import Interfaz.IJugador;
import Interfaz.IMano;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public interface ITableroPoker extends IVentana {
    public abstract IJugador getJugador();
    public abstract void mostrarMano(IMano mano);
}
