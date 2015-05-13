/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Mano;
import Dominio.Mensaje;
import Interfaces.ITableroPoker;
import Interfaz.ICarta;
import Interfaz.IMano;
import java.util.Observable;

/**
 *
 * @author Sebastian
 */
public class CTableroPoker extends Controlador {
    private final ITableroPoker itp;
    
    CTableroPoker(ITableroPoker itp) {
        this.itp = itp;
    }   

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Inicio Update");
        if (((Mensaje) o1).getAccion().equals("REPARTIR")) {
            if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
                System.out.println("Recibi mano");
                System.out.println(itp.getJugador().getNickName());
                
                for(ICarta c: (((Mano) ((Mensaje) o1).getValor())).getColCartas()) {
                    System.out.println(c.toString());
                }
            }

        }
    }
}
