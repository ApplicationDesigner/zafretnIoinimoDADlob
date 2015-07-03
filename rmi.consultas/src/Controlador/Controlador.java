/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;


import InterfacesVentana.IVentana;
import InterfazCommon.ICasino;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author santiagoa
 */
public class Controlador extends UnicastRemoteObject implements ActionListener {

    IVentana ventanaActual;
    private ICasino observable;
    
    public Controlador() throws RemoteException
    {
        
    }
    
    public void SetVentana (IVentana v)
    {
        this.ventanaActual = v;
    }
    

    public boolean desconectar(){
        if(observable!=null){
            //observable.Remove(this);
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

   
    
}
