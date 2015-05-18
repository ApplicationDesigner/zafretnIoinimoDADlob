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

        System.out.println("Hubo una accion " + opcion);
        switch (opcion) {
            case "btnApostar":

                System.out.println("saldoJugador: " + saldoJugador);

                float montoApostado = this.itp.getMontoApostado();

                //Si montoApostado es 0 es porque hubo un error
                if (montoApostado != 0) {

                    if (saldoJugador > montoApostado) {

                        System.out.println("saldoJugador > montoApostado");
                        this.montoApostado = montoApostado;
                        itp.getPartida().accionJugador(itp.getJugador(), "APOSTAR", montoApostado);

                        itp.habilitarBotonApostar(false);

                    } else {
                        itp.mostrarMensaje("El monto es incorrecto.");
                    }
                }

                break;
            case "btnPagar":
                
                if (saldoJugador > this.montoApostado) {
                    
                    itp.getPartida().accionJugador(itp.getJugador(), "PAGAR", this.montoApostado);
                } else {
                    //TODO mensaje de error en lblMensaje
                }
                break;

            case "btnPasar":

                System.out.println("El jugador " + itp.getJugador().getNickName() + " pasa");
                itp.getPartida().accionJugador(itp.getJugador(), "PASAR", 0f);

                break;

            case "btnRetirarme":
                itp.getPartida().accionJugador(itp.getJugador(), "RETIRARSE", 0f);
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
                itp.habilitarBotonPasar(true);
                itp.escribirLog("El jugador " + nickJugador + " pone la apuesta base.\n");

                break;

            case "REPARTIR":
                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);

                    IMano unaMano = (((Mano) ((Mensaje) o1).getValor()));
                    itp.mostrarMano(unaMano);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }

                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));
                itp.habilitarBotonApostar(true);
                itp.habilitarBotonPasar(true);
                itp.escribirLog("El jugador " + nickJugador + " recibio sus cartas.\n");

                break;

            case "APOSTAR":

                this.montoApostado = ((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getMontoApostado());

                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                } else {
                    //El resto solo puede pagar o retirarse
                    itp.habilitarBotonPagar(true);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(true);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);
                }

                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));
                itp.escribirLog("El jugador " + nickJugador + " aposto: " + Float.toString(this.montoApostado) + "\n");

                break;

            case "PAGAR":
              this.montoApostado = ((((IMano) ((Mensaje) o1).getValor())).getUnJugador().getMontoApostado());

                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }

                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));
                itp.escribirLog("El jugador " + nickJugador + " pago: " + Float.toString(this.montoApostado) + "\n");

                break;

            case "PASAR":

                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }
                
                itp.escribirLog("El jugador " + nickJugador + " pasa.\n");

                break;

            case "RETIRARSE":
                if (nickJugador == itp.getJugador().getNickName()) {

                    //Deshabilito los botones
                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(false);
                    itp.habilitarBotonRetirarme(false);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }
                
                itp.escribirLog("El jugador " + nickJugador + " se retira.\n");

                break;
 

            case "GANADOR":
                if (nickJugador == itp.getJugador().getNickName()) {

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }
                
                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));

                itp.escribirLog("El jugador ganador es: " + nickJugador + " con la figura: " + itp.getPartida().evaluarMano(((IMano) ((Mensaje) o1).getValor())) + "\n");

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
