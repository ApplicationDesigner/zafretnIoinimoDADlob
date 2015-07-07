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
import Configuraciones.Constantes;
import DominioCommon.EvaluadorManosContext;
import DominioCommon.Jugador;
import DominioCommon.Mano;
import DominioCommon.Mazo;
import DominioCommon.Mensaje;
import DominioCommon.OrdenarManos;
import InterfazCommon.IMensaje;
import InterfazCommon.IObserver;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.JugadorPersistente;
import persistencia.ManejadorBD;
import persistencia.PartidaPersistente;

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

    ArrayList<IObserver> observadores;

    public PartidaPoker() throws RemoteException {
        this.numero = 0;
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.contarDescartes = 0;
        this.duracion = 0;
        this.observadores = new ArrayList<>();
    }

    public PartidaPoker(int numero, float pozo) throws RemoteException {
        this.numero = numero;
        this.pozo = pozo;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
        this.duracion = 0;
        this.observadores = new ArrayList<>();
    }

    @Override
    public int getNumero() throws RemoteException {
        return numero;
    }

    @Override
    public void setNumero(int numero) throws RemoteException {
        this.numero = numero;
    }

    @Override
    public float getPozo() throws RemoteException {
        return pozo;
    }

    @Override
    public void setPozo(float pozo) throws RemoteException {
        this.pozo = pozo;
    }

    @Override
    public ArrayList<IMano> getColManos() throws RemoteException {
        return colManos;
    }

    @Override
    public void setColManos(ArrayList<IMano> colManos) throws RemoteException {
        this.colManos = colManos;
    }

    public Mazo getMazo() throws RemoteException {
        return mazo;
    }

    @Override
    public void setMazo(Mazo mazo) throws RemoteException {
        this.mazo = mazo;
    }

    public int getDuracion() throws RemoteException {
        return duracion;
    }

    public void setDuracion(int duracion) throws RemoteException {
        this.duracion = duracion;
    }

    public void repartirCartas(IMano mano) throws RemoteException {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }

    @Override
    public IMano reponerCartas(IMano mano, ArrayList indices) throws RemoteException {

        int cartasFaltantes = Constantes.getCantCartasEnMano() - mano.getColCartas().size();
//        System.out.println("Cartas Faltantes: " + cartasFaltantes);
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir(), (int) indices.get(i));
        }

        for (IMano m : this.colManos) {
//            System.out.println("jugador en mano: " + m.getUnJugador().getNickName());
            if (m.getUnJugador().getNickName().equals(mano.getUnJugador().getNickName())) {
                m.setColCartas(mano.getColCartas());
            }
        }

        return mano;
    }

    @Override
    public void iniciarReparticion() throws RemoteException {
        this.mazo.Barajar();
        for (IMano m : this.colManos) {
            this.repartirCartas(m);
        }
    }

    @Override
    public enumFigura evaluarMano(IMano unaMano) throws RemoteException {
        return EvaluadorManosContext.evaluarMano(unaMano);
    }

    @Override
    public IMano getManoGanadora() throws RemoteException {

        Collections.sort(this.colManos, new OrdenarManos());
        return this.colManos.get(this.colManos.size() - 1);
    }

//    @Override
//    public String toString()  throws RemoteException {
//        return ("PartidaPoker numero: " + this.getNumero());
//    }
    @Override
    public void ingresarJugador(IJugador j) throws RemoteException {
        IMano unaMano = new Mano();
        unaMano.setUnJugador(j);
        this.colManos.add(unaMano);
        System.out.println("---Cant manos en partida " + this.colManos.size());
        System.out.println("---Cant observers en partida " + this.observadores.size());

        if (this.colManos.size() == Constantes.getCantMinimoJugadoresPorMesa()) {
            this.iniciarRonda();
        }
    }

    @Override
    public void iniciarRonda() throws RemoteException {
        this.iniciarReparticion();
        for (IMano unaMano : this.colManos) {
            try {
                this.notificarAccion("REPARTIR", unaMano);
                System.out.println("Notificar Repartir");
                //Cuando se inicia la ronda de apuestas deben poner la ciega = 50
                System.out.println("El jugador: " + unaMano.getUnJugador().getNickName() + " pone la apuesta base de: " + Constantes.getApuestaBase());

                this.accionJugador(unaMano.getUnJugador(), "APUESTABASE", Constantes.getApuestaBase());
            } catch (Exception ex) {
                System.out.println("Error en iniciarRonda.");
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public void jugadorAposto(IJugador unJugador) throws RemoteException {
        IMano m = this.buscarMano(unJugador);
        this.notificarAccion("APOSTAR", m);
        System.out.println("Jugador Aposto");
    }

    @Override
    public IMano buscarMano(IJugador unJugador) throws RemoteException {

        for (IMano m : this.colManos) {
            System.out.println("jugador en mano: " + m.getUnJugador().getNickName());
            if (m.getUnJugador().getNickName().equals(unJugador.getNickName())) {
                return m;
            }
        }
        return null;
    }

    @Override
    public float modificarPozo(float monto) throws RemoteException {

        this.pozo += monto;

        //REALIZAR EL UPDATE EN LA BBDD EN TABLA CASINO
        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
//      
//        
        String sql = "SELECT * FROM partida WHERE numero_partida = " + Integer.toString(this.getNumero());
        ResultSet rs = bd.obtenerResultSet(sql);

        float totalApostadoActual = 0f;

        try {
            if (rs.next()) {
                totalApostadoActual = rs.getFloat("total_apostado");

            }
        } catch (SQLException ex) {
            Logger.getLogger(JuegoPoker.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (totalApostadoActual > 0) {
            System.out.println("totalApostadoActual = " + totalApostadoActual);
            System.out.println("Voy a setear la montoPartida a " + Float.toString(monto + totalApostadoActual));
            sql = "UPDATE partida SET total_apostado = " + Float.toString(monto + totalApostadoActual) + " WHERE numero_partida = " + Integer.toString(this.getNumero());
            System.out.println(sql);
            bd.ejecutarConsulta(sql);
        }

        return this.pozo;
    }

    @Override
    public void accionJugador(IJugador unJugador, String accion, Float monto) throws RemoteException {
        System.out.println("*******************");
        System.out.println("ACCIONJUGADOR");
        System.out.println("Jugador " + unJugador.getNickName());

        IMano m = this.buscarMano(unJugador);
        boolean puedeApostar;
        boolean accionApostar = false;

        if (m != null) {
            switch (accion) {
                case "APUESTABASE":

                    System.out.println("El saldo del jugador es: " + unJugador.getSaldo());
                    puedeApostar = unJugador.apostar(monto);
                    if (puedeApostar) {

                        if (this.validarMontoApuesta(m, monto)) {
                            this.modificarPozo(monto);
                            m.setUnJugador(unJugador);
                            this.updateSaldoJugador(unJugador);

                            this.notificarAccion(accion, m);
                        } else {
                            this.notificarAccion("SINSALDO", m);
                        }
                    } else {
                        accion = "NOPUEDEAPOSTAR";
                        this.notificarAccion(accion, m);
                    }

//                    this.notificarAccion(accion, m);
                    break;
                case "APOSTAR":

                    puedeApostar = unJugador.apostar(monto);

                    if (puedeApostar) {

//                        System.out.println("cartas en la mano ");
//                        m.mostrarMano();
//                        System.out.println("Jugador en la mano " + m.getUnJugador().getNickName());
                        if (validarMontoApuesta(m, monto) == true) {
                            this.modificarPozo(monto);
                            this.updateSaldoJugador(unJugador);

                            accionApostar = true;
                            contadorAcciones = 1;
                            m.setUnJugador(unJugador);
                            this.notificarAccion(accion, m);
                        } else {
                            accion = "NOPUEDEAPOSTAR";
                            this.notificarAccion(accion, m);
                        }
                    } else {
                        accion = "NOPUEDEAPOSTAR";
                        this.notificarAccion(accion, m);
                    }

//                    this.notificarAccion(accion, m);
                    break;

                case "PAGAR":

                    puedeApostar = unJugador.apostar(monto);

                    if (puedeApostar) {
                        contadorAcciones++;
                        this.modificarPozo(monto);
                        m.setUnJugador(unJugador);
                        this.updateSaldoJugador(unJugador);

                        this.notificarAccion(accion, m);
                    } else {
                        accion = "NOPUEDEAPOSTAR";
                        this.notificarAccion(accion, m);
                    }

//                    this.notificarAccion(accion, m);
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

            if (this.contadorAcciones == observadores.size()) {

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
        } else {
            System.out.println("No se encontro la mano.");
        }

    }

    private void mostrarGanador() throws RemoteException {
        IMano manoGanador = this.getManoGanadora();

        //Gana el pozo. Le sumo el saldo del pozo al saldo del jugador ganador
        manoGanador.getUnJugador().setSaldo(manoGanador.getUnJugador().getSaldo() + this.getPozo());
        this.updateSaldoJugador(manoGanador.getUnJugador());
        
        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
        bd.agregar(new PartidaPersistente(this));

        for (IMano m : this.colManos) {
            String ganador = "F";
            if (m == manoGanador) {
                ganador = "T";
            }
            bd.conectar(Configuraciones.Constantes.getCadenaConexion());
            String sql = "INSERT INTO partida_jugador (nickName,numero_partida,ganador) VALUES ('"
                    + m.getUnJugador().getNickName() + "'," + this.getNumero() + ",'" + ganador + "')";
            bd.ejecutarConsulta(sql);
            System.out.println(sql);
        }

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

    @Override
    public ArrayList<String> getListaNombresJugadores() throws RemoteException {
        ArrayList<String> listaNombres = new ArrayList<>();
        for (IMano unaMano : this.getColManos()) {
            listaNombres.add(unaMano.getUnJugador().getNickName());
        }
        return listaNombres;
    }

//    @Override
//    public void SendMessage(Object obj) throws RemoteException {
//        IMensaje unMensaje = (IMensaje) new Mensaje();
//        unMensaje.setAccion(accion);
//        unMensaje.setValor(obj);
//        
//        this.Notify(unMensaje);
//    }
    private void notificarAccion(String accion, Object obj) throws RemoteException {
        IMensaje unMensaje = new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);

        System.out.println("Notify accion = " + accion);
        this.Notify(unMensaje);
//        this.setChanged();
//        this.notifyObservers(unMensaje);
    }

    @Override
    public void Add(IObserver obs) throws RemoteException {
        this.observadores.add(obs);
        System.out.println("Cantidad de observadores: " + this.observadores.size());
    }

    @Override
    public void Remove(IObserver obs) throws RemoteException {
        this.observadores.remove(obs);
    }

    @Override
    public void Notify(Serializable param) throws RemoteException {
        ArrayList<IObserver> tmp = new ArrayList(observadores);
        for (IObserver obs : tmp) {
            try {
                obs.Update(param);
            } catch (RemoteException ex) {
                //perdi la conexion
                System.out.println("Perdi la conexion. Remuevo el observador.");
                observadores.remove(obs);
            }
        }
    }

    private void updateSaldoJugador(IJugador unJugador) {

        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());

        String sql;
        sql = "UPDATE jugador SET saldo = " + Float.toString(unJugador.getSaldo()) + " WHERE nickName = '" + unJugador.getNickName() + "'";
        System.out.println("UPDATE de saldoJugador = " + sql);
        bd.ejecutarConsulta(sql);
    }

}
