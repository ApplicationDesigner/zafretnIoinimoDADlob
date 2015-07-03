/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import InterfazCommon.IJuego;
import InterfazCommon.IJugador;
import InterfazCommon.IObserver;
import InterfazCommon.IPartida;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian
 */
public class JuegoPoker extends UnicastRemoteObject implements IJuego {
    
    
    private static JuegoPoker instance = null;
    private static ArrayList<IPartida> colPartidas;
    
    private static String nombre;
    private static int numero;    
    private static float ganancias;
    
    
    private JuegoPoker()  throws RemoteException {
        JuegoPoker.colPartidas      = new ArrayList<>();       
        JuegoPoker.nombre           = "JuegoPoker"; 
        JuegoPoker.ganancias        = 0;
    }
    
    public static JuegoPoker getInstance()  throws RemoteException {
        if(JuegoPoker.instance == null) {
            JuegoPoker.instance = new JuegoPoker();
        }        
        return JuegoPoker.instance;
    }    

    
    @Override
    public ArrayList<IPartida> getColPartidas()  throws RemoteException {
        return colPartidas;
    }

    @Override
    public void setColPartidas(ArrayList<IPartida> colPartidas)  throws RemoteException {
        JuegoPoker.colPartidas = colPartidas;
    }

    @Override
    public String getNombre()  throws RemoteException {
        return nombre;
    }

    @Override
    public void setNombre(String nombre)  throws RemoteException {
        JuegoPoker.nombre = nombre;
    }

    @Override
    public int getNumero()  throws RemoteException {
        return numero;
    }

    @Override
    public void setNumero(int numero)  throws RemoteException {
        JuegoPoker.numero = numero;
    }

    @Override
    public float getGanancias()  throws RemoteException {
        return ganancias;
    }

    @Override
    public void setGanancias(float ganancias)  throws RemoteException {
        JuegoPoker.ganancias = ganancias;
    }
    
    @Override
    public void sumarGanancias(float monto)  throws RemoteException {        
        this.setGanancias(this.getGanancias() + monto);
        System.out.println("Las ganancias del JuegoPoker son: " + this.getGanancias());
    }
    
    
    @Override
    public void agregarPartida(IPartida p)  throws RemoteException {
        JuegoPoker.colPartidas.add(p);        
    }
    
//    @Override
//    public String toString()  throws RemoteException {
//        return ("Nombre: " + JuegoPoker.nombre + " Numero: " + JuegoPoker.numero);
//    }

    @Override
    public IPartida buscarPartida(int numero)  throws RemoteException {
        IPartida retorno = null;
        for(IPartida unaPartida : this.getColPartidas()){
            try {
                if(unaPartida.getNumero() == numero){
                    retorno = unaPartida;
                }
            } catch (RemoteException ex) {
                Logger.getLogger(JuegoPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return retorno;
    }

    @Override
    public void ingresarJugadorAPartida(int nroPartida, IJugador j)  throws RemoteException {
        IPartida unaPartida = this.buscarPartida(nroPartida);
        if(unaPartida != null){
            try {
                unaPartida.ingresarJugador(j);
            } catch (RemoteException ex) {
                System.out.println("La partida No ingreso jugador");
                System.err.println(ex.getMessage());
                Logger.getLogger(JuegoPoker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
