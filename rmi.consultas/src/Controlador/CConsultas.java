/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import InterfacesVentana.IConsultas;
import InterfazCommon.ICasino;
import InterfazCommon.IPartida;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class CConsultas extends Controlador {

    private final IConsultas iConsultas;
    private ICasino observable;

    public CConsultas(IConsultas iconsulta) throws RemoteException {
        this.iConsultas = iconsulta;
        this.conectar();
    }

    public boolean conectar() {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            this.observable = (ICasino) Naming.lookup("CasinoServer");
            //this.observable.Add(this);
            System.out.println("Me conecte...");
        } catch (Exception ex) {
            System.out.println("No me conecte...");
            return false;

        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ICasino instanceCasino = observable;

        if (e.getActionCommand().equals("VerGanancias")) {
            float ganancias = 0;
            try {
                ganancias = instanceCasino.getHistoricoGanancias();
            } catch (RemoteException ex) {
                Logger.getLogger(CConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
                iConsultas.setGanancias(ganancias);
                System.out.println("Ganancias: " + ganancias);
        }
        
        
        if(e.getActionCommand().equals("HistoricoPartidas")){
            iConsultas.limpiarCampos();
            try {
                ArrayList<String> listaPartidas = instanceCasino.getHistoricoPartidas();
                iConsultas.setHistoricoPartidas(listaPartidas);
            } catch (RemoteException ex) {
                Logger.getLogger(CConsultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
