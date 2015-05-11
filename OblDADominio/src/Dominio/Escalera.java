/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Marcelo
 */
public class Escalera implements Figura {

    @Override
    public boolean evaluarMano(Mano unaMano) {
        
        boolean ret = false;
        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : unaMano.getColCartas()) {
            valores.add(unaCarta.getValor());
        }
        
        Collections.sort(valores);
        
        if(     valores.get(0).getSiguiente() == valores.get(1)
            &&  valores.get(1).getSiguiente() == valores.get(2)
            &&  valores.get(2).getSiguiente() == valores.get(3)
            &&  valores.get(3).getSiguiente() == valores.get(4)
          ) {
            ret = true;
        }
        

        return ret;
    }
    
}
