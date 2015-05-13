/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Carta;
import Dominio.Mano;
import Dominio.Mensaje;
import Interfaces.ILogin;
import Interfaces.ITableroPoker;
import Interfaz.ICarta;
import Interfaz.IMano;
import Ventanas.VLogin;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    public void actionPerformed(ActionEvent e) {
        String opcion = e.getActionCommand();
        switch (opcion){
            case "Apostar":
                float saldoJugador = itp.getJugador().getSaldo();
                
            break;
            case "Pagar":
            break;
            case "Retirarme":
            break;
        }
    }
    

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Inicio Update");
        if (((Mensaje) o1).getAccion().equals("REPARTIR")) {
            if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
//                System.out.println("Recibi mano");
//                System.out.println(itp.getJugador().getNickName());
                IMano unaMano = (((Mano) ((Mensaje) o1).getValor()));
                itp.mostrarMano(unaMano);
            }

        }
    }
}
