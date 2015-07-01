/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DominioCommon;

import Configuraciones.enumFigura;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Marcelo
 */
public class Par implements Figura{

    @Override
    public enumFigura evaluarMano(Mano unaMano) {
        
        enumFigura ret = enumFigura.NINGUNA;
        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : unaMano.getColCartas()) {
            valores.add(unaCarta.getValor());
        }

        int cant = Collections.frequency(valores, valores.get(0));
        if (cant == 2) {
            ret = enumFigura.PAR;
        } else {
            cant = Collections.frequency(valores, valores.get(1));

            if (cant == 2) {
                ret = enumFigura.PAR;
            } else {
                cant = Collections.frequency(valores, valores.get(2));

                if (cant == 2) {
                    ret = enumFigura.PAR;
                } else {
                    cant = Collections.frequency(valores, valores.get(3));

                    if (cant == 2) {
                        ret = enumFigura.PAR;
                    }
                }
            }
        }
        return ret;
    }
    
    
}
