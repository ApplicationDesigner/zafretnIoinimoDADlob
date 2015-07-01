/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Configuraciones.Constantes;
import DominioCommon.Carta;
import DominioCommon.JuegoPoker;
import DominioCommon.Mano;
import DominioCommon.Mensaje;
import InterfacesVentana.ITableroPoker;
import InterfazCommon.IJuego;
import InterfazCommon.IMano;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class CTableroPoker extends Controlador {

    private final ITableroPoker itp;
    private float montoApostado;

    CTableroPoker(ITableroPoker itp)  throws RemoteException {
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

                float montoApostado = this.itp.getMontoApostado();

                //Si montoApostado es 0 es porque hubo un error
                if (montoApostado > 0) {

                    if (saldoJugador > montoApostado) {
                        this.montoApostado = montoApostado;
                        try {
                            itp.getPartida().accionJugador(itp.getJugador(), "APOSTAR", montoApostado);
                        } catch (RemoteException ex) {
                            Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        itp.habilitarBotonApostar(false);
                        itp.limpiarCampos();

                    } else {
                        itp.mostrarMensaje("El monto es incorrecto.");
                    }
                } else {
                    itp.mostrarMensaje("El monto debe ser mayor que 0");
                }

                break;
            case "btnPagar":

                if (saldoJugador > this.montoApostado) {

            try {
                itp.getPartida().accionJugador(itp.getJugador(), "PAGAR", this.montoApostado);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
                } else {
                     itp.escribirLog("Sin saldo disponible.\n");
                     itp.mostrarMensaje("Sin saldo disponible.");
                }
                break;

            case "btnPasar":

                System.out.println("El jugador " + itp.getJugador().getNickName() + " pasa");
        {
            try {
                itp.getPartida().accionJugador(itp.getJugador(), "PASAR", 0f);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

                break;

            case "btnRetirarme":
        {
            try {
                itp.getPartida().accionJugador(itp.getJugador(), "RETIRARSE", 0f);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                itp.deshabilitarPanel();
                break;

            case "Pedir_Cartas":
                itp.limpiarCampos();
                IMano unaMano = null;
        try {
            unaMano = itp.getPartida().buscarMano(itp.getJugador());
        } catch (RemoteException ex) {
            Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
                ArrayList<String> ubicacionEnMesa = itp.getBotonesDeCartasSeleccionadas();
                ArrayList indices = new ArrayList<>();

                unaMano.getColCartas().stream().filter((unaCarta) -> (unaCarta.getActiva() == false)).forEach((unaCarta) -> {
//TODO descomentar y arreglar                 
//  indices.add(unaMano.getColCartas().indexOf(unaCarta));
        });
                if(indices.size() > 4){
                    itp.mostrarMensaje("Puede cambiar hasta 4 cartas!");
                }else{
                
                    Iterator<Carta> i = unaMano.getColCartas().iterator();
                    while (i.hasNext()) {
                        Carta c = i.next();
                        if (c.getActiva() == false) {
                            i.remove();
                        }
                    }

            try {
                itp.getPartida().reponerCartas(unaMano, indices);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
                    itp.mostrarMano(unaMano);
                    itp.habilitarBotonPedirCartas(false);

                    String log = "Haz descartado " + indices.size() + " cartas\n";
                    itp.escribirLog(log);
            try {
                itp.getPartida().accionJugador(itp.getJugador(), "DESCARTO", 0f);
                //itp.getPartida().accionJugador(itp.getJugador(), log, 0F);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                break;

            case "btnAbandonarPartidaSI":

                //itp.getPartida().accionJugador(itp.getJugador(), "ABANDONA", 0f);
                float gananciasJugador = itp.getJugador().getSaldo() - itp.getJugador().getSaldoInicial();
                //10% de comision
                float comision = 0;

                System.out.println("Las ganancias del jugador son: " + gananciasJugador);
                System.out.println("La comision es: " + comision);

                if (gananciasJugador > 0) {
                    IJuego ij = JuegoPoker.getInstance();
                    comision = gananciasJugador * Constantes.getPorcentajeGanancias();
                    ij.sumarGanancias(comision);

                    System.out.println("Las ganancias del JuegoP");
                    //saldoInicial = saldo - comision
                    itp.getJugador().setSaldoInicial(itp.getJugador().getSaldo() - comision);
                } else {
                    //saldoInicial = saldo
                    itp.getJugador().setSaldoInicial(itp.getJugador().getSaldo());
                }

                System.out.println("El saldo del jugador es: " + itp.getJugador().getSaldoInicial());

                itp.habilitarBotonAbandonarPartidaNO(false);
                itp.habilitarBotonAbandonarPartidaSI(false);

        {
            try {
                itp.getPartida().Remove(this);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;

            case "btnAbandonarPartidaNO":
        {
            try {
                itp.getPartida().ingresarJugador(itp.getJugador());
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        {
            try {
                itp.getPartida().accionJugador(itp.getJugador(), "CONTINUA", 0f);
            } catch (RemoteException ex) {
                Logger.getLogger(CTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

                itp.habilitarBotonAbandonarPartidaNO(false);
                itp.habilitarBotonAbandonarPartidaSI(false);
                break;

            default:

                break;
        }
    }

    public void update(Observable o, Object o1) throws RemoteException {

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
                itp.habilitarBotonAbandonarPartidaNO(false);
                itp.habilitarBotonAbandonarPartidaSI(false);
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
            case "DESCARTAR":
                if (nickJugador == itp.getJugador().getNickName()) {

                    itp.habilitarBotonPagar(false);
                    itp.habilitarBotonPedirCartas(true);
                    itp.habilitarBotonRetirarme(false);
                    itp.habilitarBotonApostar(false);
                    itp.habilitarBotonPasar(false);

                    itp.escribirLog("Puedes descartar.\n");
                }

                break;

            case "DESCARTO":
                itp.escribirLog("El jugador: " + nickJugador + " descarto.\n");

                break;

            case "GANADOR":
                if (nickJugador == itp.getJugador().getNickName()) {

                    mostrarSaldoJugador(Float.toString(saldoJugador));
                }

                mostrarMontoPozo(Float.toString(itp.getPartida().getPozo()));

                itp.escribirLog("El jugador ganador es: " + nickJugador + " con la figura: " + itp.getPartida().evaluarMano(((IMano) ((Mensaje) o1).getValor())) + "\n");
                itp.habilitarBotonAbandonarPartidaNO(true);
                itp.habilitarBotonAbandonarPartidaSI(true);
                break;

            case "ABANDONA":
                itp.escribirLog("Un jugador abandona la mesa.\n");
                break;
            case "SINSALDO":

                if (nickJugador == itp.getJugador().getNickName()) {
                    itp.escribirLog("Sin saldo disponible.\n");
                    itp.mostrarMensaje("Sin saldo disponible.");

                    itp.getJugador().setSaldoInicial(itp.getJugador().getSaldo());
                    itp.habilitarBotonAbandonarPartidaNO(false);
                    itp.habilitarBotonAbandonarPartidaSI(false);

                    itp.getPartida().Remove(this);
                }

                break;

            case "CONTINUA":
                itp.escribirLog("El jugador " + nickJugador + " continua en la mesa.\n");
                break;

            case "NOPUEDEAPOSTAR":
                if (nickJugador == itp.getJugador().getNickName()) {
                    itp.escribirLog("El monto de su apuesta no es valido.\n");
                    itp.mostrarMensaje("El monto de su apuesta no es valido.");
                }
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
