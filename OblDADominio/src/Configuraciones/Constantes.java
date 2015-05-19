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
    private static float apuestaBase                = 50f;
    private static float porcentajeGanancias        = 0.10f;

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

    public static float getApuestaBase() {
        return apuestaBase;
    }

    public static void setApuestaBase(float apuestaBase) {
        Constantes.apuestaBase = apuestaBase;
    }

    public static float getPorcentajeGanancias() {
        return porcentajeGanancias;
    }

    public static void setPorcentajeGanancias(float porcentajeGanancias) {
        Constantes.porcentajeGanancias = porcentajeGanancias;
    }
    
    
    
    
    
}
