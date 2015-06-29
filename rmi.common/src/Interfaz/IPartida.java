package Interfaz;

import java.util.ArrayList;
import java.util.Observer;
import Configuraciones.enumFigura;
import Dominio.Mazo;
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

    public abstract int getNumero();

    public abstract void setNumero(int numero);

    public abstract float getPozo();

    public abstract void setPozo(float pozo);
    
    public abstract ArrayList<IMano> getColManos();
             
    public abstract void setColManos(ArrayList<IMano> colManos);

    public abstract Mazo getMazo();

    public abstract void setMazo(Mazo mazo);

    public abstract void ingresarJugador(IJugador j);

    public abstract void iniciarReparticion();

    public abstract void agregarObserver(Observer CTableroPoker);
   
    public abstract void quitarObserver(Observer CTableroPoker);

    public abstract void iniciarRonda();

    public abstract enumFigura evaluarMano(IMano unaMano);

    //public abstract void jugadorAposto(IJugador unJugador);

    public abstract IMano buscarMano(IJugador unJugador);

    public abstract float modificarPozo(float monto);

    public abstract void accionJugador(IJugador unJugador, String accion, Float monto);

    public abstract void repartirCartas(IMano mano);

    public abstract void reponerCartas(IMano mano, ArrayList indices);    

    public abstract ArrayList<String> getListaNombresJugadores();

    public abstract IMano getManoGanadora();

    @Override
    public abstract String toString();

    public void SendMessage(String mensaje) throws RemoteException;

    

}
