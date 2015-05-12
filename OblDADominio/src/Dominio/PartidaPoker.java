/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJugador;
import Interfaz.IPartida;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class PartidaPoker implements IPartida {
    
    private int numero;
    private float pozo;
    ArrayList<Mano> colManos;
    private Mazo mazo;

    public PartidaPoker() {
    }    
    
    public PartidaPoker(int numero, float pozo) {
        this.numero = numero;
        this.pozo = pozo;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
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

    public ArrayList<Mano> getColManos() {
        return colManos;
    }

    public void setColManos(ArrayList<Mano> colManos) {
        this.colManos = colManos;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }
    
    public void repartirCartas(Mano mano) {
        
        for(int i = 0; i < (5 - mano.getColCartas().size()); i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }
    
    public void iniciarReparticion() {        
        for(Mano m:this.colManos) {
            this.repartirCartas(m);
        }
    }
    public String evaluarMano(Mano unaMano) {
        
        String ret = "SINFIGURA";
        EvaluadorManos poker = new EvaluadorManos(new Poker());
        EvaluadorManos pierna = new EvaluadorManos(new Pierna());
        EvaluadorManos par = new EvaluadorManos(new Par());
        
        //Primero tengo que evaluar poker porque si evaluo trio o par tambien evaluaría OK
        if(poker.evaluarMano(unaMano)) {
            ret = "POKER";
        } else if(pierna.evaluarMano(unaMano)) {
            ret = "PIERNA";
        } else if(par.evaluarMano(unaMano)) {
            ret = "PAR";
        }
        
        return ret;
    }
       
    
    @Override
    public String toString() {
        return ("PartidaPoker numero: " + this.getNumero());
    }

    @Override
    public void ingresarJugador(IJugador j) {
        Mano unaMano = new Mano();
        unaMano.setUnJugador(j);
        this.getColManos().add(unaMano);
    }

    
    
    
    
}
