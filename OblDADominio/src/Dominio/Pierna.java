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
public class Pierna implements Figura{

    @Override
    public boolean evaluarMano(Mano unaMano) {
        
        boolean ret = false;
        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : unaMano.getColCartas()) {
            valores.add(unaCarta.getValor());
        }

        int cant = Collections.frequency(valores, valores.get(0));
        if (cant == 3) {
            ret = true;
        } else {
            cant = Collections.frequency(valores, valores.get(1));

            if (cant == 3) {
                ret = true;
            } else {
                cant = Collections.frequency(valores, valores.get(2));

                if (cant == 3) {
                    ret = true;
                }
            }
        }
        return ret;
    }
    
    
}
