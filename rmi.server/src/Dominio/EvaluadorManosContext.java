/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Configuraciones.enumFigura;
import Interfaz.IMano;

/**
 *
 * @author Sebastian
 */
public class EvaluadorManosContext {
    
   
    public static enumFigura evaluarMano(IMano unaMano) {

        enumFigura ret          = enumFigura.NINGUNA;
        EvaluadorManos poker    = new EvaluadorManos(new Poker());
        EvaluadorManos pierna   = new EvaluadorManos(new Pierna());
        EvaluadorManos par      = new EvaluadorManos(new Par());
        EvaluadorManos escalera = new EvaluadorManos(new Escalera());

        //Primero tengo que evaluar poker porque si evaluo trio o par tambien evaluaría OK
        if ((poker.evaluarMano((Mano) unaMano)) == enumFigura.POKER)  {
            ret = enumFigura.POKER;
        } else if ((pierna.evaluarMano((Mano) unaMano)) == enumFigura.PIERNA) {
            ret = enumFigura.PIERNA;
        } else if ((par.evaluarMano((Mano) unaMano)) == enumFigura.PAR) {
            ret = enumFigura.PAR;
        }else if ((escalera.evaluarMano((Mano) unaMano)) == enumFigura.ESCALERA) {
            ret = enumFigura.PAR;
        }
        return ret;
    }
}
