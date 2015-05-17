/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuraciones;

/**
 *
 * @author Sebastian
 */
public class Constantes {
    
    private static int cantCartasEnMano             = 5;
    private static int cantCartasEnMazo             = 52;
    private static int cantMinimoJugadoresPorMesa   = 2;
    private static int apuestaBase                  = 50;

    public static int getCantCartasEnMano() {
        return cantCartasEnMano;
    }

    public static void setCantCartasEnMano(int cantCartasEnMano) {
        Constantes.cantCartasEnMano = cantCartasEnMano;
    }

    public static int getCantCartasEnMazo() {
        return cantCartasEnMazo;
    }

    public static void setCantCartasEnMazo(int cantCartasEnMazo) {
        Constantes.cantCartasEnMazo = cantCartasEnMazo;
    }

    public static int getCantMinimoJugadoresPorMesa() {
        return cantMinimoJugadoresPorMesa;
    }

    public static void setCantMinimoJugadoresPorMesa(int cantMinimoJugadoresPorMesa) {
        Constantes.cantMinimoJugadoresPorMesa = cantMinimoJugadoresPorMesa;
    }

    public static int getApuestaBase() {
        return apuestaBase;
    }

    public static void setApuestaBase(int apuestaBase) {
        Constantes.apuestaBase = apuestaBase;
    }
    
    
    
}
