/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaz;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author santiagoa
 */
public interface IObservable extends Remote {
    
    
    public void Add(IObserver obs)throws RemoteException;
    public void Remove(IObserver obs)throws RemoteException;
    public void Notify(Serializable param)throws RemoteException;
    
}
