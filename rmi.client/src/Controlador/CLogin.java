/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import InterfacesVentana.ILogin;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import InterfazCommon.IJugador;
import InterfazCommon.ICasino;
import java.rmi.RemoteException;
import InterfacesVentana.IIngresarAPartida;
import InterfazCommon.IJuego;
import InterfazCommon.IMensaje;
import InterfazCommon.IPartida;
import Ventanas.VPpalJugador;
import java.io.Serializable;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class CLogin extends Controlador {

    private final ILogin ilogin;
    private ICasino observable;
    private IJuego juegoPoker;
    private String nickIngresado;

    public CLogin(ILogin ilogin) throws RemoteException {
        this.ilogin = ilogin;
        this.nickIngresado = "";
        this.conectar();
    }

    public boolean conectar() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            this.observable = (ICasino) Naming.lookup("CasinoServer");
            this.observable.Add(this);
//            System.out.println("Me conecte...");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;

        }
        return true;
    }

    public boolean conectarJuegoPoker() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            this.juegoPoker = (IJuego) Naming.lookup("JuegoPokerServer");
            this.juegoPoker.Add(this);
//            System.out.println("Me conecte al juego...");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;

        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ICasino instanceCasino = observable;

        if (e.getActionCommand().equals("LoginAceptar")) {
            System.out.println("NickName: " + ilogin.getNickName() + " Pass: " + ilogin.getPass());

            try {
                if (instanceCasino.validarLogin(ilogin.getNickName(), ilogin.getPass())) {
                    IJugador j = instanceCasino.buscarJugador(ilogin.getNickName());

                    if (j != null) {
                        System.out.println("Logueando a ventana principal del jugador...");

                        ArrayList<IPartida> partidasDisponibles = this.observable.getColPartidasDisponibles();
//                        ArrayList<IPartida> partidasDisponibles = new ArrayList<>();
                        //Muestro solo las partidas que no estan completas
//                        for (IPartida p : this.observable.getColPartidas()) {
//                            if (p.getColManos().size() < Configuraciones.Constantes.getCantMinimoJugadoresPorMesa()) {
//                                partidasDisponibles.add(p);
//                            }
//                        }
                        //En caso de que esten todas las partidas ocupadas, creo una nueva y la agrego como disponible
//                        if (partidasDisponibles.size() < 1) {
//
//                            if (this.conectarJuegoPoker()) {
//                                IPartida nuevaPartida = null;
//                                try {
//                                    nuevaPartida = this.observable.agregarPartida(this.juegoPoker, "POKER");
//                                } catch (RemoteException ex) {
//                                    System.err.println(ex.getMessage());
//                                }
//                                this.juegoPoker.agregarPartida(nuevaPartida);
//                                partidasDisponibles.add(nuevaPartida);
//                            } else {
//                                System.out.println("No me pude conectar al JuegoPoker");
//                            }
//
//                        }

                        if (partidasDisponibles != null) {
                            IIngresarAPartida iip = null;
                            try {
//                                System.out.println("..................");
//                                System.out.println("Jugador = " + j.getNickName());
                                iip = new VPpalJugador(j, partidasDisponibles);
//                                System.out.println("Jugador = " + iip.getJugador().getNickName());
                                Controlador c = null;
                                c = new CPpalJugador(iip);
                                iip.setControlador(c);
                            } catch (RemoteException ex) {
                                System.err.println(ex.getMessage());
                                Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                } else {
                    ilogin.setLblMensaje("Usuario y/o password incorrectos");
                }
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    public void Update(Serializable obj) throws RemoteException {

        IMensaje msj = (IMensaje) obj;

        if (msj.getAccion().equals("LOGINOK")) {
            IJugador aux = (IJugador) msj.getValor();

            if (aux.getNickName() == this.nickIngresado) {
                System.out.println("Login ok...");
                //TODO abrir nueva ventana
            }

        }

    }
}
