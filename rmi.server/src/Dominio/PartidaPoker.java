/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;


import Configuraciones.enumFigura;
import InterfazCommon.IJugador;
import InterfazCommon.IMano;
import InterfazCommon.IPartida;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observer;
import Configuraciones.Constantes;
import DominioCommon.EvaluadorManosContext;
import DominioCommon.Mano;
import DominioCommon.Mazo;
import DominioCommon.Mensaje;
import DominioCommon.OrdenarManos;
import InterfazCommon.IObserver;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Sebastian
 */
public class PartidaPoker extends UnicastRemoteObject implements IPartida {

    private int numero;
    private float pozo;
    ArrayList<IMano> colManos;
    private Mazo mazo;
    private int duracion;

    private int contadorAcciones;
    private int contarDescartes;

    public PartidaPoker() throws RemoteException {
        this.numero = 0;
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.contarDescartes = 0;
        this.duracion = 0;
    }

    public PartidaPoker(int numero, float pozo)  throws RemoteException{
        this.numero = numero;
        this.pozo = pozo;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.duracion = 0;
    }

    @Override
    public int getNumero()  throws RemoteException {
        return numero;
    }

    @Override
    public void setNumero(int numero)  throws RemoteException {
        this.numero = numero;
    }

    @Override
    public float getPozo() throws RemoteException  {
        return pozo;
    }

    @Override
    public void setPozo(float pozo) throws RemoteException  {
        this.pozo = pozo;
    }

    @Override
    public ArrayList<IMano> getColManos()  throws RemoteException {
        return colManos;
    }

    @Override
    public void setColManos(ArrayList<IMano> colManos)  throws RemoteException {
        this.colManos = colManos;
    }

    public Mazo getMazo()  throws RemoteException {
        return mazo;
    }

    @Override
    public void setMazo(Mazo mazo) throws RemoteException  {
        this.mazo = mazo;
    }

    public int getDuracion()  throws RemoteException {
        return duracion;
    }

    public void setDuracion(int duracion)  throws RemoteException {
        this.duracion = duracion;
    }
    

    public void repartirCartas(IMano mano)  throws RemoteException {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }

    @Override
    public void reponerCartas(IMano mano, ArrayList indices)  throws RemoteException {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir(), (int) indices.get(i));
        }
    }

    @Override
    public void iniciarReparticion()  throws RemoteException {
        this.mazo.Barajar();
        for (IMano m : this.colManos) {
            this.repartirCartas(m);
        }
    }

    @Override
    public enumFigura evaluarMano(IMano unaMano)  throws RemoteException {
        return EvaluadorManosContext.evaluarMano(unaMano);
    }

    @Override
    public IMano getManoGanadora() throws RemoteException  {

        Collections.sort(this.colManos, new OrdenarManos());
        return this.colManos.get(this.colManos.size() - 1);
    }

//    @Override
//    public String toString()  throws RemoteException {
//        return ("PartidaPoker numero: " + this.getNumero());
//    }

    @Override
    public void ingresarJugador(IJugador j) throws RemoteException  {
        IMano unaMano = new Mano();
        unaMano.setUnJugador(j);
        this.colManos.add(unaMano);
        System.out.println("---Cant manos en partida " + this.colManos.size());

        if (this.colManos.size() == Constantes.getCantMinimoJugadoresPorMesa()) {
            this.iniciarRonda();
        }
    }



    @Override
    public void iniciarRonda()  throws RemoteException {
        this.iniciarReparticion();
        for (IMano unaMano : this.colManos) {
            this.notificarAccion("REPARTIR", unaMano);
            System.out.println("Notificar Repartir");
            //Cuando se inicia la ronda de apuestas deben poner la ciega = 50
            System.out.println("El jugador: " + unaMano.getUnJugador().getNickName() + " pone la apuesta base de: " + Constantes.getApuestaBase());

            this.accionJugador(unaMano.getUnJugador(), "APUESTABASE", Constantes.getApuestaBase());
        }
    }

    private void notificarAccion(String accion, Object obj)  throws RemoteException {
        Mensaje unMensaje = new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);
//        this.setChanged();
//        this.notifyObservers(unMensaje);
    }

    @Override
    public void jugadorAposto(IJugador unJugador)  throws RemoteException {
        IMano m = this.buscarMano(unJugador);
        this.notificarAccion("APOSTAR", m);
        System.out.println("Jugador Aposto");
    }
    @Override
    public IMano buscarMano(IJugador unJugador)  throws RemoteException {

        for (IMano m : this.colManos) {
            if (m.getUnJugador().getNickName() == unJugador.getNickName()) {
                return m;
            }
        }
        return null;
    }

    @Override
    public float modificarPozo(float monto) throws RemoteException  {
        this.pozo += monto;
        return this.pozo;
    }

    @Override
    public void accionJugador(IJugador unJugador, String accion, Float monto)  throws RemoteException {
        IMano m = this.buscarMano(unJugador);
        boolean puedeApostar;
        boolean accionApostar = false;

        switch (accion) {
            case "APUESTABASE":

                puedeApostar = unJugador.apostar(monto);
                if (puedeApostar) {
                    if(this.validarMontoApuesta(m, monto)){
                        this.modificarPozo(monto);
                    }else{
                        this.notificarAccion("SINSALDO", m);
                    }
                } else {
                    accion = "NOPUEDEAPOSTAR";
                }

                this.notificarAccion(accion, m);

                break;
            case "APOSTAR":

                puedeApostar = unJugador.apostar(monto);
                if (puedeApostar) {

                    if (validarMontoApuesta(m, monto) == true) {
                        this.modificarPozo(monto);
                        accionApostar = true;
                        contadorAcciones = 1;
                    } else {
                        accion = "NOPUEDEAPOSTAR";
                    }
                } else {
                    accion = "NOPUEDEAPOSTAR";
                }

                this.notificarAccion(accion, m);

                break;

            case "PAGAR":

                puedeApostar = unJugador.apostar(monto);

                if (puedeApostar) {
                    contadorAcciones++;
                    this.modificarPozo(monto);
                } else {
                    accion = "NOPUEDEAPOSTAR";
                }

                this.notificarAccion(accion, m);

                break;

            case "PASAR":
                contadorAcciones++;
                this.notificarAccion(accion, m);
                break;

            case "DESCARTO":

                this.notificarAccion(accion, m);
                this.contarDescartes++;
                break;

            case "RETIRARSE":
                contadorAcciones++;

                this.colManos.remove(this.buscarMano(unJugador));

                this.notificarAccion(accion, m);
                break;

            case "ABANDONA":
                this.notificarAccion(accion, m);
                break;

            case "CONTINUA":
                this.notificarAccion(accion, m);
                break;

            default:
                this.notificarAccion(accion, m);
                break;

        }

        //TODO Cambiar por lo que usamosa ahora
       // if (this.contadorAcciones == this.countObservers()) {

            if (this.colManos.size() != 1) {

                if (this.contarDescartes == 0) {
                    for (IMano unaMano : this.colManos) {
                        this.notificarAccion("DESCARTAR", unaMano);
                    }
                } else if (this.contarDescartes == (this.colManos.size())) {
                    mostrarGanador();
                }

            } else {
                mostrarGanador();
            }
        }

    private void mostrarGanador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean validarMontoApuesta(IMano m, Float monto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getListaNombresJugadores() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SendMessage(String mensaje) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Add(IObserver obs) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Remove(IObserver obs) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Notify(Serializable param) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    


}
