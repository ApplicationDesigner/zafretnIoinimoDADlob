/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.ICarta;

/**
 *
 * @author Marcelo
 */
public final class Carta implements Comparable<Carta>, ICarta {

    private final Valor valor;
    private final Palo palo;
    private final String pathImagen;
    private final boolean activa; 
    private static final String pathBase = "/_img/";

    public Carta(Valor valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
        activa = true;
        this.pathImagen = pathBase + valor.toString() + palo.toString()+ ".png";
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
