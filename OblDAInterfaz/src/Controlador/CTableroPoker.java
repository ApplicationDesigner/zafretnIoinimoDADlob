/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Mano;
import Dominio.Mensaje;
import Interfaces.ITableroPoker;
import Interfaz.IMano;
import java.awt.event.ActionEvent;
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
                
                System.out.println("saldoJugador: " + saldoJugador);                 
                
                float montoApostado = this.itp.getMontoApostado();
                System.out.println("montoApostado: " + montoApostado);
                
                if(saldoJugador > montoApostado) {
                    
                    System.out.println("saldoJugador > montoApostado");
                    
                    boolean aposto  = itp.getJugador().apostar(montoApostado);
                    if(aposto) {
                        System.out.println("Voy a setear el pozo");
                        System.out.println("Pozo anterior: " + itp.getPartida().getPozo());
                        itp.getPartida().setPozo(itp.getPartida().getPozo() + montoApostado);
                        System.out.println("Pozo posterior: " + itp.getPartida().getPozo());
                    } else {
                        //TODO mensaje de error en lblMensaje
                    }
                    
                } else {
                    //TODO mensaje de error en lblMensaje
                }
                        
                
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
        
        String accion = ((Mensaje) o1).getAccion();
        
        switch(accion) {
            case "REPARTIR":
                if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
                    System.out.println("Recibi mano");
                    System.out.println(itp.getJugador().getNickName());
                    IMano unaMano = (((Mano) ((Mensaje) o1).getValor()));
                    itp.mostrarMano(unaMano);
                    itp.mostarSaldoJugador(unaMano);
                }
            break;
            
            case "APOSTAR":               
                if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
                    //Deshabilito todos los botones
                    
                    System.out.println("Deshabilito todos los botones");
                    
                } else {
                    //Deshabilito botón apostar
                    System.out.println("Deshabilito el boton apostar");
                    
                }
            break;
                
            case "PAGAR":
            break;
                
            case "RETIRARSE":
            break;
                
            
                
            default:
            break;
        }
        
//
    }

}
