package InterfazCommon;

import Configuraciones.enumFigura;
import java.util.ArrayList;
import java.util.Observer;
import DominioCommon.Mazo;
import java.io.Serializable;
import java.rmi.RemoteException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
public interface IPartida extends IObservable {

    public abstract int getNumero()  throws RemoteException;

    public abstract void setNumero(int numero)  throws RemoteException;

    public abstract float getPozo()  throws RemoteException;

    public abstract void setPozo(float pozo)  throws RemoteException;
    
    public abstract ArrayList<IMano> getColManos()  throws RemoteException;
             
    public abstract void setColManos(ArrayList<IMano> colManos)  throws RemoteException;

    public abstract Mazo getMazo()  throws RemoteException;

    public abstract void setMazo(Mazo mazo)  throws RemoteException;

    public abstract void ingresarJugador(IJugador j)  throws RemoteException;

    public abstract void iniciarReparticion() throws RemoteException;


    public abstract void iniciarRonda()  throws RemoteException;

    public abstract enumFigura evaluarMano(IMano unaMano)  throws RemoteException;

    public abstract void jugadorAposto(IJugador unJugador)  throws RemoteException;

    public abstract IMano buscarMano(IJugador unJugador)  throws RemoteException;

    public abstract float modificarPozo(float monto) throws RemoteException;

    public abstract void accionJugador(IJugador unJugador, String accion, Float monto)  throws RemoteException;

    public abstract void repartirCartas(IMano mano) throws RemoteException;

    public abstract IMano reponerCartas(IMano mano, ArrayList indices) throws RemoteException;    

    public abstract ArrayList<String> getListaNombresJugadores() throws RemoteException;

    public abstract IMano getManoGanadora() throws RemoteException;

    //@Override
   // public abstract String toString() throws RemoteException ;

//    public void SendMessage(Object obj) throws RemoteException;

    

}
