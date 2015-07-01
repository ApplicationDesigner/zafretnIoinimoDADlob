/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DominioCommon;

import java.util.ArrayList;
import java.util.Collections;
import Configuraciones.enumFigura;

/**
 *
 * @author Marcelo
 */
public class Poker implements Figura{

    @Override
    public enumFigura evaluarMano(Mano unaMano) {
        
        enumFigura ret = enumFigura.NINGUNA;
        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : unaMano.getColCartas()) {
            valores.add(unaCarta.getValor());
        }

        int cant = Collections.frequency(valores, valores.get(0));
        if (cant == 4) {
            ret = enumFigura.POKER;
        } else {
            cant = Collections.frequency(valores, valores.get(1));

            if (cant == 4) {
                ret = enumFigura.POKER;
            }
        }

        return ret;
    }
    
}
