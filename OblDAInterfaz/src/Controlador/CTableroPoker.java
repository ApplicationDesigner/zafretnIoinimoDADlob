/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Carta;
import Dominio.Mano;
import Dominio.Mensaje;
import Interfaces.ITableroPoker;
import Interfaz.IMano;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
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
                    
                    itp.getPartida().accionJugador(itp.getJugador(), "APOSTAR", montoApostado);
//                    boolean aposto  = itp.getJugador().apostar(montoApostado);
//                    if(aposto) {
//                        System.out.println("Voy a setear el pozo");
//                        System.out.println("Pozo anterior: " + itp.getPartida().getPozo());
//                        //itp.getPartida().setPozo(itp.getPartida().getPozo() + montoApostado);
//                        itp.getPartida().modificarPozo(montoApostado);
//                        System.out.println("Pozo posterior: " + itp.getPartida().getPozo());
//                        
//                        itp.getPartida().jugadorAposto(itp.getJugador());
//                    } else {
//                        //TODO mensaje de error en lblMensaje
//                    }
                    
                } else {
                    //TODO mensaje de error en lblMensaje
                }
                        
                
            break;
            case "Pagar":
            break;
            case "Retirarme":
            break;
                
            case "Pedir_Cartas":
                IMano unaMano = itp.getPartida().buscarMano(itp.getJugador());
                ArrayList<String> listaCartas = itp.getPathImagenCartasSeleccionadas();
                
                Iterator<Carta> i = null;
                
                for(String path : listaCartas){
                    i = unaMano.getColCartas().iterator();
                   while(i.hasNext()){
                        Carta c = i.next();
                        if(c.getPathImagen() == path){
                            i.remove();
                        }
                    }
                }
                //TODO: Ver de repartirle al jugador que corresponde
                itp.getPartida().iniciarReparticion();
                
            break;
                
            default:
                
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
                    itp.mostrarPozo(itp.getPartida().getPozo());
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
                //TODO Implementar
               
            break;
                
            case "PASAR":                
                //TODO Implementar
            break;
                
            case "RETIRARSE":                
                //TODO Implementar
            break;
                
            case "ABANDONARMESA":
                //TODO No da el tiempo, quedara para el infinito......
            break;
                
            default:
                
            break;
                
        }        
        
//
    }

}
