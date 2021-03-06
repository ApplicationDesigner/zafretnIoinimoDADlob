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
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.JugadorPersistente;
import persistencia.ManejadorBD;
import persistencia.PartidaPersistente;

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

    @Override
    public ArrayList<IPartida> getColPartidasDisponibles() throws RemoteException {

        ArrayList<IPartida> partidasDisponibles = new ArrayList<>();
        //Muestro solo las partidas que no estan completas
        for (IPartida p : this.getColPartidas()) {
            if (p.getColManos().size() < Configuraciones.Constantes.getCantMinimoJugadoresPorMesa()) {
                partidasDisponibles.add(p);
            }
        }
        
        if(partidasDisponibles.size() == 0) {
            //Solo voy a tener un nuego y va a ser JuegoPoker
            IJuego j1 = Casino.colJuegos.get(0);
            IPartida p1;
            p1 = Casino.instance.agregarPartida(j1, "POKER");
            System.out.println(p1.toString());

            j1.agregarPartida(p1);
            partidasDisponibles.add(p1);
        }
        return partidasDisponibles;
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

            try {
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }

                Random rand = new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int randomNum = rand.nextInt((15900 - 11900) + 1) + 11900;

                System.out.println("Random port para JuegoPoker" + randomNum);
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT + 1);

                Naming.rebind("JuegoPokerServer", j);
                System.out.println("levantado juego.. esperando por peticiones");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

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

            try {
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }

                Random rand = new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int randomNum = rand.nextInt((15900 - 11900) + 1) + 11900;

                //System.out.println("el puerto random es: " + randomNum);
                LocateRegistry.createRegistry(randomNum);

                String partidaID = "Partida" + Integer.toString(p.getNumero());
                //Naming.rebind("PARTIDA1", p);
                Naming.rebind(partidaID, p);
                System.out.println("levantado partida " + partidaID + ".. esperando por peticiones");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

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

        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());

        IJugador j = new Jugador();
        j.setNickName(nick);
        ArrayList resultado = bd.obtenerTodos(new JugadorPersistente((Jugador) j));
        j = (IJugador) resultado.get(0);

        return j;

//        for (IJugador j : Casino.colJugadores) {
//            if (j.getNickName().equals(nick)) {
//                return j;
//            }
//        }
//        return null;
    }

    private static void datosPrecargados() throws RemoteException {

        for (int i = 0; i < 10; i++) {
            IJugador j = new Jugador();
            j.setNickName("n" + i);
            //j.setNickName("n" + i);
            j.setPassword("p" + i);
            j.setSaldo(500);
            j.setSaldoInicial(500);
            Casino.colJugadores.add(j);
        }

        System.out.println("");
        IJuego j1;
        j1 = Casino.instance.agregarJuego("POKER");
        System.out.println(j1.toString());

        IPartida p1;
        p1 = Casino.instance.agregarPartida(j1, "POKER");
        System.out.println(p1.toString());

        j1.agregarPartida(p1);

//        IPartida p2;
//        p2 = Casino.instance.agregarPartida(j1, "POKER");
//        System.out.println(p2.toString());
//
//        j1.agregarPartida(p2);
        
        
//        ManejadorBD bd = ManejadorBD.getInstancia();
//        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
//        String sql = "TRUNCATE TABLE partida";
//        bd.ejecutarConsulta(sql);
//        sql = "TRUNCATE TABLE partida_jugador";
//        bd.ejecutarConsulta(sql);
        
        
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

    @Override
    public ArrayList<String> getHistoricoPartidas() throws RemoteException {
        ArrayList<String> retorno = new ArrayList<>();

        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
        //return bd.obtenerTodos(new PartidaPersistente(null));

        String sql = "SELECT * FROM partida AS P JOIN partida_jugador AS PJ ON PJ.numero_partida=P.numero_partida JOIN jugador AS J ON J.nickName=PJ.nickName";
        ResultSet rs = bd.obtenerResultSet(sql);
        String cadena = "";

        try {
            while (rs.next()) {
                if (rs.getString("ganador").equalsIgnoreCase("T")) {
                    cadena += "Nro Partida: " + rs.getInt("numero_partida");
                    cadena += ", Duracion: " + rs.getInt("duracion");
                    cadena += ", Total Apostado: " + rs.getInt("total_apostado");
                    cadena += ", Jugador ganador: " + rs.getString("nickName");
                    retorno.add(cadena);
                    cadena = "";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Casino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public float getHistoricoGanancias() throws RemoteException {
        ManejadorBD bd = ManejadorBD.getInstancia();
        bd.conectar(Configuraciones.Constantes.getCadenaConexion());
        ResultSet rs = bd.obtenerResultSet("SELECT ganancias FROM casino");
        float ganancias = 0;
        try {
            if (rs.next()) {
                ganancias = rs.getFloat("ganancias");
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Casino.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ganancias;
    }

}
