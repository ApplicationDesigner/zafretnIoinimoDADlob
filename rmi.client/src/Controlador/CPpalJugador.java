/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import DominioCommon.JuegoPoker;
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
    private IPartida observable;


    public CPpalJugador(IIngresarAPartida iip)  throws RemoteException {
        this.iip = iip;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("IIngresarAPartida")) {

            int opcion = iip.getPartidaSeleccionada();
            if (opcion != 0) {
                IJuego ij = JuegoPoker.getInstance();
                IPartida ip = ij.buscarPartida(opcion);
                
                if(ip != null){
                    
                    try {
                        String partidaID = "Partida" + Integer.toString(ip.getNumero());
                        
                        this.conectar(partidaID);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CPpalJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    //Al iniciar saldo = saldo inicial            
                    this.iip.getJugador().setSaldo(this.iip.getJugador().getSaldoInicial());

                    ITableroPoker itp = new VTableroPoker(this.iip.getJugador());
                    itp.setPartida(ip);
                    Controlador c = null;
                    try {
                        c = new CTableroPoker(itp);
                    } catch (RemoteException ex) {
                        Logger.getLogger(CPpalJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    itp.setControlador(c);

                    try {
                        //TODO cambiar por el nuevo metodo
                        // ip.agregarObserver((Observer) c);

                        ij.ingresarJugadorAPartida(ip.getNumero(), this.iip.getJugador());
                    } catch (RemoteException ex) {
                        Logger.getLogger(CPpalJugador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    public boolean conectar(String partidaID) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            this.observable = (IPartida) Naming.lookup(partidaID);
            this.observable.Add(this);
            System.out.println("Me conecte...");
        } catch (Exception ex) {
            System.out.println("No me conecte...");
            return false;

        }
        return true;
    }
    
    
    public void update(Observable o, Object o1
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
