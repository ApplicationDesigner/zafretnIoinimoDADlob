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
    private float montoApostado;

    CTableroPoker(ITableroPoker itp) {
        this.itp = itp;
        this.montoApostado = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String opcion = e.getActionCommand();
        float saldoJugador = itp.getJugador().getSaldo();
        switch (opcion) {
            case "Apostar":

                System.out.println("saldoJugador: " + saldoJugador);

                float montoApostado = this.itp.getMontoApostado();
                System.out.println("montoApostado: " + montoApostado);

                if (saldoJugador > montoApostado) {

                    System.out.println("saldoJugador > montoApostado");
                    this.montoApostado = montoApostado;
                    itp.getPartida().accionJugador(itp.getJugador(), "APOSTAR", montoApostado);

                    itp.habilitarBotonApostar(false);

                } else {
                    //TODO mensaje de error en lblMensaje
                }

                break;
            case "Pagar":
                System.out.println("saldoJugador: " + saldoJugador);
                if (saldoJugador > this.montoApostado) {
                    System.out.println("saldoJugador > montoApostado");
                    itp.getPartida().accionJugador(itp.getJugador(), "PAGAR", this.montoApostado);
                } else {
                    //TODO mensaje de error en lblMensaje
                }
                break;
            case "Retirarme":
                itp.getPartida().accionJugador(itp.getJugador(), "RETIRARSE", 0F);
                break;

            case "Pedir_Cartas":
                IMano unaMano = itp.getPartida().buscarMano(itp.getJugador());
                ArrayList<String> ubicacionEnMesa = itp.getBotonesDeCartasSeleccionadas();
                ArrayList indices = new ArrayList<>();

                for (Carta unaCarta : unaMano.getColCartas()) {
                    if (unaCarta.getActiva() == false) {
                        indices.add(unaMano.getColCartas().indexOf(unaCarta));
                    }
                }
                Iterator<Carta> i = unaMano.getColCartas().iterator();
                while (i.hasNext()) {
                    Carta c = i.next();
                    if (c.getActiva() == false) {
                        i.remove();
                    }
                }

                itp.getPartida().reponerCartas(unaMano, indices);
                itp.mostrarMano(unaMano);
                itp.habilitarBotonPedirCartas(false);

                String log = itp.getJugador().getNickName() + " descarto " + indices.size() + " cartas \n";
                itp.getPartida().accionJugador(itp.getJugador(), log, 0F);
                break;

            default:

                break;
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("Inicio Update");

        String accion = ((Mensaje) o1).getAccion();
        String nickJugador = ((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName());
        float saldoJugador = ((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getSaldo());

        switch (accion) {

            case "APUESTABASE":
                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones                    
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }

                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));
                itp.habilitarBotonApostar(true);
                itp.escribirLog("El jugador " + nickJugador + " pone la apuesta base.\n");

                break;

            case "REPARTIR":
                if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
                    System.out.println("Recibi mano");
                    System.out.println(itp.getJugador().getNickName());
                    IMano unaMano = (((Mano) ((Mensaje) o1).getValor()));
                    itp.mostrarMano(unaMano);
                    //TODO: Refrescar el saldo del jugador en pantalla
                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }
                //TODO: Refrescar el pozo total en pantalla
                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));

                break;

            case "APOSTAR":

                if (((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getNickName()) == itp.getJugador().getNickName()) {
                    //Deshabilito todos los botones
                    //TODO: Si lo pongo aca a la linea de abajo, deshabilita de entrada al iniciar la partida
//                    itp.habilitarBotonApostar(false);
//                    itp.habilitarBotonPagar(false);
//                    itp.habilitarBotonPedirCartas(false);
//                    itp.habilitarBotonRetirarme(false);

                    //System.out.println("Deshabilito todos los botones");
                } else {
                    //Deshabilito botón apostar
                    itp.habilitarBotonApostar(false);
                    //System.out.println("Deshabilito el boton apostar");
                }

                break;

            case "PAGAR":
                //TODO Implementar

                break;

            case "PASAR":
                //TODO Implementar
                break;

            case "RETIRARSE":
                //TODO Deshabilitar Botones
                break;

            case "ABANDONARMESA":
                //TODO No da el tiempo, quedara para el infinito......
                break;

            default:
                itp.escribirLog(accion);
                break;

        }

//
    }

    private void mostrarSaldoJugador(String saldoJugador) {
        this.itp.mostarSaldoJugador(saldoJugador);
    }

    private void mostrarMontoPozo(String montoPozo) {
        itp.mostrarPozo(montoPozo);
    }

}
