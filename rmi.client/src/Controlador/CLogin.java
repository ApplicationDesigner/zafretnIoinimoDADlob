/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import InterfacesVentana.ILogin;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import interfaces.IJugador;
import interfaces.ICasino;
import java.rmi.RemoteException;
import objetos.Casino;
/**
 *
 * @author Sebastian
 */
public class CLogin extends Controlador {

    private final ILogin ilogin;

    
    public CLogin(ILogin ilogin) throws RemoteException {
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
