/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Configuraciones.enumFigura;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Marcelo
 */
public class Pierna implements Figura{

    @Override
    public enumFigura evaluarMano(Mano unaMano) {
        
        enumFigura ret = enumFigura.NINGUNA;
        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : unaMano.getColCartas()) {
            valores.add(unaCarta.getValor());
        }

        int cant = Collections.frequency(valores, valores.get(0));
        if (cant == 3) {
            ret = enumFigura.PIERNA;
        } else {
            cant = Collections.frequency(valores, valores.get(1));

            if (cant == 3) {
                ret = enumFigura.PIERNA;
            } else {
                cant = Collections.frequency(valores, valores.get(2));

                if (cant == 3) {
                    ret = enumFigura.PIERNA;
                }
            }
        }
        return ret;
    }
    
    
}
