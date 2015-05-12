package Interfaz;

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
    
    @Override
    public abstract String toString();    

}