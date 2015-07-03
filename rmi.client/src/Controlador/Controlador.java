/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;


import InterfacesVentana.IVentana;
import InterfazCommon.IMensaje;
import InterfazCommon.IObserver;
import InterfazCommon.IPartida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santiagoa
 */
public class Controlador extends UnicastRemoteObject implements ActionListener, IObserver{

    IVentana ventanaActual;
    private IPartida observable;
    
    public Controlador() throws RemoteException
    {
        
    }
    
    public void SetVentana (IVentana v)
    {
        this.ventanaActual = v;
    }
    
//    public  boolean conectar(){
//         if(System.getSecurityManager()==null){
//                System.setSecurityManager(new RMISecurityManager());
//         }
//        try {
//            observable = (IPartida) Naming.lookup("ChatServer");
//            observable.Add(this);
//        }catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }
    public boolean desconectar(){
        if(observable!=null){
            try {
                observable.Remove(this);
                return true;
            } catch (RemoteException ex) {
                
            }
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getActionCommand().equals("ENVIARMENSAJE")) {
            
            String mensaje = this.ventanaActual.getMensaje();
//            try {
//                this.observable.SendMessage(mensaje);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
                
    }

    @Override
    public void Update(Serializable obj) throws RemoteException {
        
       // ArrayList<String> aux = (ArrayList<String>)obj;
        
        
        this.ventanaActual.RecibirMensaje(obj);
    }
    
}
