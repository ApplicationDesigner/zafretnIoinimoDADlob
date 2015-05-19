package Interfaz;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sebastian
 */
public interface ICasino {    
    
    public abstract boolean validarLogin(String nick, String pass);
    public abstract IJuego agregarJuego(String tipoJuego);
    public abstract IPartida agregarPartida(IJuego j, String tipoPartida);
    public abstract IJugador buscarJugador(String nick);
    public abstract ArrayList<IPartida> getColPartidas();
    
    @Override
    public abstract String toString();
    
            
}
