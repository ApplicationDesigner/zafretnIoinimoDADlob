/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Casino;
import Dominio.JuegoPoker;
import Interfaces.IIngresarAPartida;
import Interfaces.ILogin;
import Interfaz.ICasino;
import Interfaz.IJuego;
import Interfaz.IJugador;
import Interfaz.IPartida;
import Ventanas.VPpalJugador;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Sebastian
 */
public class CLogin extends Controlador {

    private final ILogin ilogin;

    public CLogin(ILogin ilogin) {
        this.ilogin = ilogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ICasino instanceCasino = Casino.getInstance();

        if (e.getActionCommand().equals("LoginAceptar")) {
            System.out.println("NickName: " + ilogin.getNickName() + " Pass: " + ilogin.getPass());

            if (instanceCasino.validarLogin(ilogin.getNickName(), ilogin.getPass())) {
                IJugador j = instanceCasino.buscarJugador(ilogin.getNickName());

                if (j != null) {
                    System.out.println("Logueando a ventana principal del jugador...");
                    
                    ArrayList<IPartida> partidasDisponibles = new ArrayList<>();
                    //Muestro solo las partidas que no estan completas
                    for(IPartida p : Casino.getInstance().getColPartidas()){
                        if(p.getColManos().size() < Configuraciones.Constantes.getCantMinimoJugadoresPorMesa()){
                            partidasDisponibles.add(p);
                        }
                    }
                    //En caso de que esten todas las partidas ocupadas, creo una nueva y la agrego como disponible
                    if(partidasDisponibles.size() < 1){
                        IJuego unJuegoPoker = JuegoPoker.getInstance();
                        IPartida nuevaPartida = Casino.getInstance().agregarPartida(unJuegoPoker, "POKER");
                        unJuegoPoker.agregarPartida(nuevaPartida);
                        partidasDisponibles.add(nuevaPartida);
                    }
                    
                    
                    if(partidasDisponibles != null){
                        IIngresarAPartida iip = new VPpalJugador(j, partidasDisponibles);
                        Controlador c = new CPpalJugador(iip);
                        iip.setControlador(c);
                    }
                }
            } else {
                ilogin.setLblMensaje("Usuario y/o password incorrectos");
            }

        }
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
