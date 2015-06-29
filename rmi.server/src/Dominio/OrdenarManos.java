/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Configuraciones.enumFigura;
import Interfaz.IMano;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Configuraciones.Constantes;

/**
 *
 * @author Sebastian
 */
public class OrdenarManos implements Comparator<IMano> {

    @Override
    public int compare(IMano o1, IMano o2) {

        int ret = EvaluadorManosContext.evaluarMano(o1).compareTo(EvaluadorManosContext.evaluarMano(o2));

        if (ret == 0) {
            //Es la misma figura

            if (EvaluadorManosContext.evaluarMano(o1) == enumFigura.ESCALERA) {
                //Es esalera. Ordeno las cartas y me quedo con la mayor
                //luego comparo la mayor de cada mano
                ArrayList<Carta> colCartasMano1 = o1.getColCartas();
                ArrayList<Carta> colCartasMano2 = o2.getColCartas();
                Collections.sort(colCartasMano1);
                Collections.sort(colCartasMano2);

                ret = colCartasMano1.get(Constantes.getCantCartasEnMano() - 1).compareTo(colCartasMano2.get(Constantes.getCantCartasEnMano() - 1));

            } else {

                if (EvaluadorManosContext.evaluarMano(o1) == enumFigura.NINGUNA) {
                    //Me quedo con la mayor de las dos manos

                    //Ordeno
                    Collections.sort(o1.getColCartas());
                    Collections.sort(o2.getColCartas());
                    //Me quedo con la mayor
                    Carta mayor1 = o1.getColCartas().get(o1.getColCartas().size() - 1);
                    Carta mayor2 = o2.getColCartas().get(o2.getColCartas().size() - 1);

                    ret = mayor1.compareTo(mayor2);
                } else {
                    //Remuevo las cartas cuyo valor no se repite, ordeno
                    //y me quedo con la mayor.
                    ArrayList<Carta> col1 = new ArrayList<>(o1.getColCartas());
                    ArrayList<Carta> col2 = new ArrayList<>(o2.getColCartas());
                    
                    Carta mayor1 = getCartaMayor(col1);
                    Carta mayor2 = getCartaMayor(col2);
                    ret = mayor1.compareTo(mayor2);
                }

            }
        }
        return ret;
    }

    private Carta getCartaMayor(ArrayList<Carta> colCartas) {

        Carta ret = null;
        int cant = 0;

        ArrayList<Carta.Valor> valores = new ArrayList<>();

        for (Carta unaCarta : colCartas) {
            valores.add(unaCarta.getValor());
        }

        //Remuevo NO repetidas
        ArrayList<Carta> cartasNORepetidas = new ArrayList<>();
        
        for (int i = 0; i < valores.size(); i++) {
            cant = Collections.frequency(valores, valores.get(i));

            if (cant == 1) {
                for(Carta c: colCartas) {
                    if(c.getValor() == valores.get(i)) {
                        cartasNORepetidas.add(c);
                    }
                } 
            }
        }
        
        //Remuevo todas las NO repetidas
        colCartas.removeAll(cartasNORepetidas);
        //Ordeno
        Collections.sort(colCartas);
        //Me quedo con la mayor
        ret = colCartas.get(colCartas.size() - 1);

        return ret;
    }

}
