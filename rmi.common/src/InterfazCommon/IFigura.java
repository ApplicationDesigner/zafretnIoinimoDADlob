/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazCommon;
//import Configuraciones.enumFigura;
import Configuraciones.enumFigura;
import InterfazCommon.IMano;
import java.io.Serializable;

/**
 *
 * @author Marcelo
 */
public interface IFigura  extends Serializable{
    
    public enumFigura evaluarMano(IMano unaMano);
}
