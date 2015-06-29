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
import Interfaz.IJugador;
import Interfaz.ICasino;
import java.rmi.RemoteException;
import Dominio.Casino;
import Dominio.JuegoPoker;
import InterfacesVentana.IIngresarAPartida;
import Interfaz.IJuego;
import Interfaz.IMensaje;
import Interfaz.IPartida;
import Ventanas.VPpalJugador;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Sebastian
 */
public class CLogin extends Controlador {

    private final ILogin ilogin;
    private ICasino observable;
    private String nickIngresado;

    
    public CLogin(ILogin ilogin) throws RemoteException {
        this.ilogin = ilogin;
        this.nickIngresado = "";
    }
    
    public  boolean conectar(){
         if(System.getSecurityManager()==null){
                System.setSecurityManager(new RMISecurityManager());
         }
        try {
            observable = (ICasino) Naming.lookup("CasinoServer");
            observable.Add(this);
        }catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ICasino instanceCasino = Casino.getInstance();

        if (e.getActionCommand().equals("LoginAceptar")) {
            System.out.println("NickName: " + ilogin.getNickName() + " Pass: " + ilogin.getPass());

            this.nickIngresado = ilogin.getNickName();
            this.observable.validarLogin(ilogin.getNickName(), ilogin.getPass());
            
//            if (instanceCasino.validarLogin(ilogin.getNickName(), ilogin.getPass())) {
//                IJugador j = instanceCasino.buscarJugador(ilogin.getNickName());
//
//                if (j != null) {
//                    System.out.println("Logueando a ventana principal del jugador...");
//                    
//                    ArrayList<IPartida> partidasDisponibles = new ArrayList<>();
//                    //Muestro solo las partidas que no estan completas
//                    for(IPartida p : Casino.getInstance().getColPartidas()){
//                        if(p.getColManos().size() < Configuraciones.Constantes.getCantMinimoJugadoresPorMesa()){
//                            partidasDisponibles.add(p);
//                        }
//                    }
//                    //En caso de que esten todas las partidas ocupadas, creo una nueva y la agrego como disponible
//                    if(partidasDisponibles.size() < 1){
//                        IJuego unJuegoPoker = JuegoPoker.getInstance();
//                        IPartida nuevaPartida = Casino.getInstance().agregarPartida(unJuegoPoker, "POKER");
//                        unJuegoPoker.agregarPartida(nuevaPartida);
//                        partidasDisponibles.add(nuevaPartida);
//                    }
//                    
//                    
//                    if(partidasDisponibles != null){
//                        IIngresarAPartida iip = new VPpalJugador(j, partidasDisponibles);
//                        Controlador c = null;
//                        try {
//                            c = new CPpalJugador(iip);
//                        } catch (RemoteException ex) {
//                            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        iip.setControlador(c);
//                    }
//                }
//            } else {
//                ilogin.setLblMensaje("Usuario y/o password incorrectos");
//            }

        }
    }
            
    
    public void Update(Serializable obj) throws RemoteException {
        
        IMensaje msj = (IMensaje)obj;
        
        if(msj.getAccion().equals("LOGINOK")) {
            IJugador aux = (IJugador) msj.getValor();
            
            if (aux.getNickName() == this.nickIngresado) {
                System.out.println("Login ok...");
                //TODO abrir nueva ventana
            }
            
        }
        
        
        
        
    }
}
