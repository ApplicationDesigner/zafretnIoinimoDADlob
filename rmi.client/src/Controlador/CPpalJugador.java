/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import InterfacesVentana.IIngresarAPartida;
import InterfacesVentana.ITableroPoker;
import InterfazCommon.IJuego;
import InterfazCommon.IPartida;
import Ventanas.VTableroPoker;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class CPpalJugador extends Controlador {

    private final IIngresarAPartida iip;
    private IJuego juegoPoker;
    private IPartida observable;

    public CPpalJugador(IIngresarAPartida iip) throws RemoteException {
        this.iip = iip;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("IIngresarAPartida")) {

            System.out.println("Click en boton ingresar a partida");
            int opcion = iip.getPartidaSeleccionada();
            if (opcion != 0) {
                 
                System.out.println("Opcion: " + opcion);
                //if (this.conectarJuegoPoker()) {
                this.conectarJuegoPoker();
                    
                    System.out.println("Me conecte al juegoPoker");
                    IPartida ip = null;
                    try {
                        ip = this.juegoPoker.buscarPartida(opcion);

                        if (ip != null) {

                            try {
                                String partidaID = "Partida" + Integer.toString(ip.getNumero());

                                System.out.println("Partida a conectar: " + partidaID);
                                this.conectar(partidaID);
                                System.out.println("Partida conectada....");

                                //Al iniciar saldo = saldo inicial            
                                this.iip.getJugador().setSaldo(this.iip.getJugador().getSaldoInicial());

                                ITableroPoker itp = new VTableroPoker(this.iip.getJugador());
                                itp.setPartida(ip);
                                Controlador c = null;
                                try {
                                    c = new CTableroPoker(itp);
                                } catch (RemoteException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                itp.setControlador(c);

                                try {
                                    ip.Add(c);    
                                    System.out.println("jugador" + this.iip.getJugador().getNickName());

                                    this.juegoPoker.ingresarJugadorAPartida(ip.getNumero(), this.iip.getJugador());
                                } catch (RemoteException ex) {
                                    System.out.println("Hubo un error al ingresar observador");
                                    System.err.println(ex.getMessage());
                                }

                            } catch (RemoteException ex) {
                                System.err.println(ex.getMessage());
                            }
                        } else {
                            System.out.println("partida es null.");
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(CPpalJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                } else {
//                    System.err.println("No me concete al juegoPoker");
//                }

            }
        }
    }

    public boolean conectar(String partidaID) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            this.observable = (IPartida) Naming.lookup("PARTIDA1");
           //this.observable.Add(this);
            System.out.println("Me conecte...");
        } catch (Exception ex) {
            System.out.println("Hubo un error en partida");
            System.err.println(ex.getMessage());
            System.out.println("No me conecte...");
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
            //this.juegoPoker.Add(this);
            System.out.println("Me conecte al juego...");
        } catch (Exception ex) {
            System.out.println("Error al conectar JuegoPoker");
            System.err.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public void update(Observable o, Object o1
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
