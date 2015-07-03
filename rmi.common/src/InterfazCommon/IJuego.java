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
public interface IJuego  extends IObservable {

    public abstract ArrayList<IPartida> getColPartidas()  throws RemoteException ;

    public abstract void setColPartidas(ArrayList<IPartida> colPartidas)  throws RemoteException ;

    public abstract String getNombre()  throws RemoteException ;

    public abstract void setNombre(String nombre)  throws RemoteException ;

    public abstract int getNumero()  throws RemoteException ;

    public abstract void setNumero(int numero) throws RemoteException ;

    public abstract float getGanancias() throws RemoteException ;

    public abstract void setGanancias(float ganancias) throws RemoteException ;

    public abstract IPartida buscarPartida(int numero) throws RemoteException ;

    public abstract void agregarPartida(IPartida p) throws RemoteException ;

    public abstract void ingresarJugadorAPartida(int nroPartida, IJugador j) throws RemoteException ;

    public abstract void sumarGanancias(float monto) throws RemoteException ;
    
//    @Override
//    public abstract String toString();
}
