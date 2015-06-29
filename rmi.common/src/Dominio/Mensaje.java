/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IMensaje;

/**
 *
 * @author Marcelo
 */
public class Mensaje implements IMensaje{
    private String accion;
    private Object valor;

    @Override
    public String getAccion() {
        return accion;
    }

    @Override
    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public Object getValor() {
        return valor;
    }

    @Override
    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Mensaje() {
    }

    public Mensaje(String accion, Object valor) {
        this.accion = accion;
        this.valor = valor;
    }
    
    
}
