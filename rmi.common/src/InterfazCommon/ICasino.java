package InterfazCommon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sebastian
 */
public interface ICasino extends IObservable {    
    
    public abstract boolean validarLogin(String nick, String pass)  throws RemoteException;
    public abstract IJuego agregarJuego(String tipoJuego)  throws RemoteException;
    public abstract IPartida agregarPartida(IJuego j, String tipoPartida)  throws RemoteException;
    public abstract IJugador buscarJugador(String nick)  throws RemoteException;
    public abstract ArrayList<IPartida> getColPartidas()  throws RemoteException;
    public abstract float getGanancias()  throws RemoteException;
    public abstract ArrayList<IPartida> getHistoricoPartidas() throws RemoteException;
    public abstract float getHistoricoGanancias() throws RemoteException;
    
    //@Override
    //public abstract String toString();
    public void notificarAccion(String accion, Object obj) throws RemoteException;
            
}
