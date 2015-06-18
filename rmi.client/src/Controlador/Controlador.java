/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

import java.util.Observer;


/**
 *
 * @author Sebastian
 */
public class Controlador extends UnicastRemoteObject 
                                   implements ObservadorRemoto, Observer, ActionListener{
    
    private ObservableRemoto observable;
    private ObservableLocal observableLocal = new ObservableLocal();

    public Controlador() throws RemoteException {
    }
    
    public void registrar(String url){
        if(System.getSecurityManager()==null){
            System.setSecurityManager(new SecurityManager());
        }
        try {
            observable =(ObservableRemoto) Naming.lookup(url);
            observable.agregar(this);
        } catch (Exception ex) {
            System.out.println("Error: " +ex);
        }
    }

    public ObservableRemoto getObservable() {
        return observable;
    }
    
    public void desregistrar(){
        try {
            observable.quitar(this);
        } catch (RemoteException ex) {
           
        }
    }

    
    @Override
    public void actualizar(ObservableRemoto origen, Object param) throws RemoteException {
        observableLocal.avisar(param);
    }

    public void addObserver(Observer o) {
        observableLocal.addObserver(o);
    }

    public void deleteObserver(Observer o) {
        observableLocal.deleteObserver(o);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
