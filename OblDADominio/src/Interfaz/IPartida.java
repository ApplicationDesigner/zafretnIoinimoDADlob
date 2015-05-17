package Interfaz;

import Dominio.Mano;
import java.util.ArrayList;
import java.util.Observer;
import Configuraciones.enumFigura;
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
    public abstract void iniciarRonda();
    public abstract enumFigura evaluarMano(IMano unaMano);
    //public abstract void jugadorAposto(IJugador unJugador);
    public abstract IMano buscarMano(IJugador unJugador);
    public abstract float modificarPozo(float monto);
    public abstract void accionJugador(IJugador unJugador, String accion, Float monto);
    public abstract void repartirCartas(IMano mano);
    public abstract void reponerCartas(IMano mano, ArrayList indices);
    public abstract IMano evaluarGanador(ArrayList<IMano> colManos);
    public abstract ArrayList<String> getListaNombresJugadores();
    
    @Override
    public abstract String toString();    

}