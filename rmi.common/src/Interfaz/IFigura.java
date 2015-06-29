/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import Configuraciones.enumFigura;
import Interfaz.IMano;

/**
 *
 * @author Marcelo
 */
public interface IFigura {
    
    public enumFigura evaluarMano(IMano unaMano);
}
