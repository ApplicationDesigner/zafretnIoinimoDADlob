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

            
            int opcion = iip.getPartidaSeleccionada();
            if (opcion != 0) {               
                this.conectarJuegoPoker();               
                IPartida ip = null;
                try {
                    ip = this.juegoPoker.buscarPartida(opcion);

                    if (ip != null) {
                        String partidaID = "Partida" + Integer.toString(ip.getNumero());
                        this.conectarPartida(partidaID);
                        //Al iniciar saldo = saldo inicial            
                        this.iip.getJugador().setSaldo(this.iip.getJugador().getSaldoInicial());
                        ITableroPoker itp = new VTableroPoker(this.iip.getJugador(), ip);
                       // itp.setPartida(ip);
                       
                        Controlador c = null;
                        c = new CTableroPoker(itp);
                        ip.Add(c);
//                        System.out.println("jugador" + this.iip.getJugador().getNickName());
                        this.juegoPoker.ingresarJugadorAPartida(ip.getNumero(), this.iip.getJugador());
                        itp.setControlador(c);
                    } else {
                        System.out.println("partida es null.");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(CPpalJugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean conectarPartida(String partidaID) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            //this.observable = (IPartida) Naming.lookup("PARTIDA1");
            this.observable = (IPartida) Naming.lookup(partidaID);
            System.out.println("Partida " + partidaID + " conectada....");
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
//            System.out.println("Me conecte al juego...");
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
