/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazCommon;

import DominioCommon.Carta.Palo;
import DominioCommon.Carta.Valor;
import java.io.Serializable;



/**
 *
 * @author Marcelo
 */
public interface ICarta extends Serializable {

    public abstract Valor getValor();
    public Palo getPalo();
    public String getPathImagen();
    public boolean getActiva();
    public void setActiva(boolean estado);
    
}
