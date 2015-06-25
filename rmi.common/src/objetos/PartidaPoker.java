/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Configuraciones.enumFigura;
import interfaces.IJugador;
import interfaces.IMano;
import interfaces.IPartida;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import Configuraciones.Constantes;

/**
 *
 * @author Sebastian
 */
public class PartidaPoker extends Observable implements IPartida {

    private int numero;
    private float pozo;
    ArrayList<IMano> colManos;
    private Mazo mazo;
    private int duracion;

    private int contadorAcciones;
    private int contarDescartes;

    public PartidaPoker() {
        this.numero = 0;
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.contarDescartes = 0;
        this.duracion = 0;
    }

    public PartidaPoker(int numero, float pozo) {
        this.numero = numero;
        this.pozo = pozo;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.duracion = 0;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public float getPozo() {
        return pozo;
    }

    @Override
    public void setPozo(float pozo) {
        this.pozo = pozo;
    }

    @Override
    public ArrayList<IMano> getColManos() {
        return colManos;
    }

    @Override
    public void setColManos(ArrayList<IMano> colManos) {
        this.colManos = colManos;
    }

    @Override
    public Mazo getMazo() {
        return mazo;
    }

    @Override
    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    

    @Override
    public void repartirCartas(IMano mano) {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }

    @Override
    public void reponerCartas(IMano mano, ArrayList indices) {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir(), (int) indices.get(i));
        }
    }

    @Override
    public void iniciarReparticion() {
        this.mazo.Barajar();
        for (IMano m : this.colManos) {
            this.repartirCartas(m);
        }
    }

    @Override
    public enumFigura evaluarMano(IMano unaMano) {
        return EvaluadorManosContext.evaluarMano(unaMano);
    }

    @Override
    public IMano getManoGanadora() {

        Collections.sort(this.colManos, new OrdenarManos());
        return this.colManos.get(this.colManos.size() - 1);
    }

    @Override
    public String toString() {
        return ("PartidaPoker numero: " + this.getNumero());
    }

    @Override
    public void ingresarJugador(IJugador j) {
        IMano unaMano = new Mano();
        unaMano.setUnJugador(j);
        this.colManos.add(unaMano);
        System.out.println("---Cant manos en partida " + this.colManos.size());

        if (this.colManos.size() == Constantes.getCantMinimoJugadoresPorMesa()) {
            this.iniciarRonda();
        }
    }

    @Override
    public void agregarObserver(Observer CTableroPoker) {
        this.addObserver(CTableroPoker);
        System.out.println("Observer agregado Count observers " + this.countObservers());
        if (this.countObservers() == Constantes.getCantMinimoJugadoresPorMesa()) {

            //this.iniciarRonda();
        }
    }

    @Override
    public void quitarObserver(Observer CTableroPoker) {
        this.deleteObserver(CTableroPoker);
        System.out.println("Observer quitado Count observers " + this.countObservers());
    }

    @Override
    public void iniciarRonda() {
        this.iniciarReparticion();
        for (IMano unaMano : this.colManos) {
            this.notificarAccion("REPARTIR", unaMano);
            System.out.println("Notificar Repartir");
            //Cuando se inicia la ronda de apuestas deben poner la ciega = 50
            System.out.println("El jugador: " + unaMano.getUnJugador().getNickName() + " pone la apuesta base de: " + Constantes.getApuestaBase());

            this.accionJugador(unaMano.getUnJugador(), "APUESTABASE", Constantes.getApuestaBase());
        }
    }

    private void notificarAccion(String accion, Object obj) {
        Mensaje unMensaje = new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);
        this.setChanged();
        this.notifyObservers(unMensaje);
    }

//    @Override
//    public void jugadorAposto(IJugador unJugador) {
//        IMano m = this.buscarMano(unJugador);
//        this.notificarAccion("APOSTAR", m);
//        System.out.println("Jugador Aposto");
//    }
    @Override
    public IMano buscarMano(IJugador unJugador) {

        for (IMano m : this.colManos) {
            if (m.getUnJugador().getNickName() == unJugador.getNickName()) {
                return m;
            }
        }
        return null;
    }

    @Override
    public float modificarPozo(float monto) {
        this.pozo += monto;
        return this.pozo;
    }

    @Override
    public void accionJugador(IJugador unJugador, String accion, Float monto) {
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

        if (this.contadorAcciones == this.countObservers()) {

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
    }

    @Override
    public ArrayList<String> getListaNombresJugadores() {
        ArrayList<String> listaNombres = new ArrayList<>();
        for (IMano unaMano : this.getColManos()) {
            listaNombres.add(unaMano.getUnJugador().getNickName());
        }
        return listaNombres;
    }

    private void mostrarGanador() {
        IMano manoGanador = this.getManoGanadora();

        //Gana el pozo. Le sumo el saldo del pozo al saldo del jugador ganador
        manoGanador.getUnJugador().setSaldo(manoGanador.getUnJugador().getSaldo() + this.getPozo());

        //Reinicio el pozo para la proxima ronda
        this.pozo = 0;
        //Reinicio contadores
        this.contadorAcciones = 0;
        this.contarDescartes = 0;

        this.notificarAccion("GANADOR", manoGanador);

        //Reinicio todo        
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.contarDescartes = 0;
    }

    private boolean validarMontoApuesta(IMano m, Float monto) {
        for (IMano unaMano : this.colManos) {
            if (unaMano.getUnJugador() != m.getUnJugador()) {
                if (unaMano.getUnJugador().getSaldo() < monto) {
                    return false;
                }
            }
        }
        return true;
    }

}
