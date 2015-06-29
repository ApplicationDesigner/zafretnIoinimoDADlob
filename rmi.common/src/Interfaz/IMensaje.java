/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.Serializable;

/**
 *
 * @author Sebastian
 */
public interface IMensaje extends Serializable { 
  
    public String getAccion();
    public void setAccion(String accion);
    public Object getValor();
    public void setValor(Object valor);

}
