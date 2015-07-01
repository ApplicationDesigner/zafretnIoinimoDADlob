/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import DominioCommon.Jugador;
import DominioCommon.Mensaje;
import Factory.AbstractFactory;
import Factory.FactoryProducer;
import InterfazCommon.ICasino;
import InterfazCommon.IJuego;
import InterfazCommon.IJugador;
import InterfazCommon.IMensaje;
import InterfazCommon.IObserver;
import InterfazCommon.IPartida;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.JugadorPersistente;
import persistencia.ManejadorBD;

/**
 *
 * @author Sebastian
 */
public class Casino extends UnicastRemoteObject implements ICasino {

    private static String nombre;
    private static Casino instance = null;
    private static ArrayList<IJuego> colJuegos;
    private static ArrayList<IPartida> colPartidas;
    private static ArrayList<IJugador> colJugadores;
    private static ArrayList<IObserver> observadores;

    public Casino() throws RemoteException {

        Casino.nombre = "CASINOOBLDA";
        Casino.colJuegos = new ArrayList<>();
        Casino.colPartidas = new ArrayList<>();
        Casino.colJugadores = new ArrayList<>();
        Casino.observadores = new ArrayList<>();

    }

    public static Casino getInstance() throws RemoteException {

        if (Casino.instance == null) {
            Casino.instance = new Casino();
            datosPrecargados();
        }
        return Casino.instance;
    }

    @Override
    public ArrayList<IPartida> getColPartidas() throws RemoteException {
        return colPartidas;
    }

    public static ArrayList<IJuego> getColJuegos() throws RemoteException {
        return colJuegos;
    }

    @Override
    public IJuego agregarJuego(String tipoJuego) throws RemoteException {
        IJuego ret = null;

        try {

            AbstractFactory juegoFactory = FactoryProducer.getFactory("JUEGO");

            IJuego j = juegoFactory.getIJuego(tipoJuego);
            j.setNumero(Casino.colJuegos.size() + 1);
            Casino.colJuegos.add(j);
            ret = j;
        } catch (Exception e) {

        }

        return ret;
    }

    public IPartida agregarPartida(IJuego j, String tipoPartida) throws RemoteException {
        IPartida ret = null;

        try {
            AbstractFactory partidaFactory = FactoryProducer.getFactory("PARTIDA");
            IPartida p = partidaFactory.getIPartida(tipoPartida);
            p.setNumero(Casino.colPartidas.size() + 1);
            Casino.colPartidas.add(p);
            j.agregarPartida(p);
            ret = p;
        } catch (Exception e) {

        }
        return ret;
    }

    public float getGanancias() throws RemoteException {
        float total = 0;
        try {
            for (IJuego j : Casino.getInstance().getColJuegos()) {
                if (j != null) {
                    total += j.getGanancias();
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Casino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

//    @Override
//    public String toString() {
//        return Casino.nombre;
//    }
    @Override
    public boolean validarLogin(String nick, String pass) throws RemoteException {
        boolean retorno = false;
        IJugador unJugador = this.buscarJugador(nick);
        if (unJugador != null) {
            if (unJugador.getPassword().equals(pass)) {

                retorno = true;
//                try {
//                    this.notificarAccion("LOGINOK", unJugador);
//                     retorno = true;
//                } catch (RemoteException ex) {
//                    Logger.getLogger(Casino.class.getName()).log(Level.SEVERE, null, ex);
//                    retorno = false;
//                }               
            }
        }
        return retorno;
    }

    @Override
    public IJugador buscarJugador(String nick) throws RemoteException {

//        ManejadorBD bd = ManejadorBD.getInstancia();
//        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
//        
//        IJugador j = null;
//        j.setNickName(nick);
//        System.out.println(bd.obtenerTodos(new JugadorPersistente(null)));
//        
//        return j;
        for (IJugador j : Casino.colJugadores) {
            if (j.getNickName().equals(nick)) {
                return j;
            }
        }
        return null;

    }

    private static void datosPrecargados() throws RemoteException {

        for (int i = 0; i < 10; i++) {
            IJugador j = new Jugador();
            j.setNickName("n" + i);
            j.setNickName("n" + i);
            j.setPassword("p" + i);
            j.setSaldoInicial(500);
            Casino.colJugadores.add(j);
        }

        IJuego j1;
        j1 = Casino.instance.agregarJuego("POKER");
        System.out.println(j1.toString());

        IPartida p1;
        p1 = Casino.instance.agregarPartida(j1, "POKER");
        System.out.println(p1.toString());

        j1.agregarPartida(p1);

        IPartida p2;
        p2 = Casino.instance.agregarPartida(j1, "POKER");
        System.out.println(p2.toString());

        j1.agregarPartida(p2);
    }

    @Override
    public void notificarAccion(String accion, Object obj) throws RemoteException {

        IMensaje unMensaje = (IMensaje) new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);
//        this.setChanged();
//        this.notifyObservers(unMensaje);

        this.Notify(unMensaje);
    }

    @Override
    public void Add(IObserver obs) throws RemoteException {
        this.observadores.add(obs);
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
                observadores.remove(obs);
            }
        }
    }

}
