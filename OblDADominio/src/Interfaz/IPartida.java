package Interfaz;

import Dominio.Mano;
import java.util.Observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sebastian
 */
public interface IPartida {
    
    public abstract int getNumero();
    public abstract void setNumero(int numero);
    public abstract float getPozo();
    public abstract void setPozo(float pozo);
    public abstract void ingresarJugador(IJugador j);
    public abstract void iniciarReparticion();
    public abstract void agregarObserver(Observer CTableroPoker);
    public abstract String evaluarMano(IMano unaMano);
    public abstract void jugadorAposto(IJugador unJugador);
    public abstract IMano buscarMano(IJugador unJugador);
    public abstract float modificarPozo(float monto);
    
    @Override
    public abstract String toString();    

}