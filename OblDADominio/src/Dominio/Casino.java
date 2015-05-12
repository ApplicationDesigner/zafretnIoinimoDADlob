/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Factory.AbstractFactory;
import Factory.FactoryProducer;
import Interfaz.ICasino;
import Interfaz.IJuego;
import Interfaz.IJugador;
import Interfaz.IPartida;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Casino implements ICasino {
    
    private static String nombre;
    private static Casino instance = null;
    private static ArrayList<IJuego> colJuegos; 
    private static ArrayList<IPartida> colPartidas;
    private static ArrayList<IJugador> colJugadores;
    
    private Casino() {  
        
        Casino.nombre       = "CASINOOBLDA";
        Casino.colJuegos    = new ArrayList<>();
        Casino.colPartidas  = new ArrayList<>(); 
        Casino.colJugadores = new ArrayList<>(); 
        datosPrecargados();
    }
    
    public static Casino getInstance() {
        
        if(Casino.instance == null) {
            Casino.instance = new Casino();
        }
        
        return Casino.instance;                
    }
    
    @Override
    public IJuego agregarJuego(String tipoJuego) {
        IJuego ret = null;
        
        try {
            
            AbstractFactory juegoFactory = FactoryProducer.getFactory("JUEGO");
        
            IJuego j = juegoFactory.getIJuego(tipoJuego);   
            j.setNumero(Casino.colJuegos.size() + 1);
            Casino.colJuegos.add(j);            
            ret = j;            
        } catch(Exception e) {
            
        }      
        
        return ret;
    }
    
    @Override
    public IPartida agregarPartida(IJuego j, String tipoPartida) {
        IPartida ret = null;
        
        try {            
            AbstractFactory partidaFactory = FactoryProducer.getFactory("PARTIDA");
            IPartida p = partidaFactory.getIPartida(tipoPartida);
            p.setNumero(Casino.colPartidas.size() + 1);                      
            Casino.colPartidas.add(p);            
            j.agregarPartida(p);
            ret = p;            
        } catch(Exception e) {
            
        }        
        return ret;
    }
    
    @Override
    public String toString() {
        return Casino.nombre;
    }

    @Override
    public boolean validarLogin(String nick, String pass) {
        boolean retorno = false;
        IJugador unJugador = this.buscarJugador(nick);
        if(unJugador != null){
            if(unJugador.getPassword().equals(pass)){
                retorno = true;
            }
        }
        return retorno;
    }
    
    @Override
    public IJugador buscarJugador(String nick) {
               
        for(IJugador j: Casino.colJugadores) {
            if(j.getNickName().equals(nick)) {
                return j;
            }
        }        
        return null;
    }

    private void datosPrecargados() {
    
        for(int i = 0; i < 10; i++)
        {
            IJugador j = new Jugador();
            j.setNickName("nomJugador" + i);
            j.setNickName("nickJugador" + i);
            j.setPassword("passJug" + i);
            j.setSaldo(500);
            Casino.colJugadores.add(j);
        }
    }
    
    

    
}
