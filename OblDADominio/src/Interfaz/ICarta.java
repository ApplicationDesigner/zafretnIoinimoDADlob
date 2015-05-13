/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Dominio.Carta.Palo;
import Dominio.Carta.Valor;

/**
 *
 * @author Marcelo
 */
public interface ICarta {

    public abstract Valor getValor();
    public Palo getPalo();
    public String getPathImagen();
    
}
