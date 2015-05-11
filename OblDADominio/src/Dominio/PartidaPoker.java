/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IPartida;

/**
 *
 * @author Sebastian
 */
public class PartidaPoker implements IPartida {
    
    private int numero;
    private float pozo;

    public PartidaPoker() {
    }    
    
    public PartidaPoker(int numero, float pozo) {
        this.numero = numero;
        this.pozo = pozo;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public float getPozo() {
        return pozo;
    }

    @Override
    public void setPozo(float pozo) {
        this.pozo = pozo;
    }
    
    @Override
    public String toString() {
        return ("PartidaPoker numero: " + this.getNumero());
    }
    
    
}
