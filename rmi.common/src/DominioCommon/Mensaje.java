/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DominioCommon;

import InterfazCommon.IMensaje;

/**
 *
 * @author Marcelo
 */
public class Mensaje implements IMensaje {
    private String accion;
    private Object valor;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Object getValor() {
        return valor;
    }

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
