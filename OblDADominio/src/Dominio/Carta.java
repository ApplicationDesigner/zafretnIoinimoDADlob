/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.ICarta;
import Configuraciones.Constantes;

/**
 *
 * @author Marcelo
 */
public final class Carta implements Comparable<Carta>, ICarta {

    private final Valor valor;
    private final Palo palo;
    private String pathImagen;
    private boolean activa; 

    public Carta(Valor valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
        this.activa = true;
        this.pathImagen = Constantes.getPathBase() + valor.toString() + palo.toString()+ ".png";
    }

    
    
    public Valor getValor() {
        return valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public enum Palo {

        PIQUE, TREBOL, DIAMANTE, CORAZON;

        private final String[] paloString = {"p", "t", "d", "c"};

        @Override
        public String toString() {
            return paloString[this.ordinal()];
        }
    }

    public enum Valor {

        DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, J, Q, K, AS;

        private final String[] valorString = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "AS"};

        public Valor getSiguiente() {
            return Valor.values()[ordinal() + 1];
        }
        
        @Override
        public String toString() {
            return valorString[this.ordinal()];
        }
    }

    public String getPathImagen() {
        return pathImagen;
    }

    public boolean getActiva() {
        return activa;
    }
        
    public void setActiva(boolean estado){
        this.activa = estado;
        if(estado == false){
            this.pathImagen = Constantes.getPathBase() + "disable.png";
        }else{
            this.pathImagen = Constantes.getPathBase() + valor.toString() + palo.toString()+ ".png";
        }
    }
    
    

    @Override
    public int compareTo(Carta unaCarta) {
        if(this.valor == unaCarta.getValor()){
            return this.palo.compareTo(unaCarta.getPalo());
        }
        return this.valor.compareTo(unaCarta.getValor());
    }

    @Override
    public String toString() {
        return "Carta{" + "valor=" + valor + ", palo=" + palo + '}';
    }

}
