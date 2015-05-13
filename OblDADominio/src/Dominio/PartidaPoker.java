/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJugador;
import Interfaz.IMano;
import Interfaz.IPartida;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Sebastian
 */
public class PartidaPoker extends Observable implements IPartida {

    private int numero;
    private float pozo;
    ArrayList<IMano> colManos;
    private Mazo mazo;

    public PartidaPoker() {
        this.numero = 0;
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
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

    public ArrayList<IMano> getColManos() {
        return colManos;
    }

    public void setColManos(ArrayList<IMano> colManos) {
        this.colManos = colManos;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void repartirCartas(IMano mano) {

        int cartasFaltantes = 5 - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }

    public void iniciarReparticion() {
        for (IMano m : this.colManos) {
            this.repartirCartas(m);
        }
    }

    public String evaluarMano(Mano unaMano) {

        String ret = "SINFIGURA";
        EvaluadorManos poker = new EvaluadorManos(new Poker());
        EvaluadorManos pierna = new EvaluadorManos(new Pierna());
        EvaluadorManos par = new EvaluadorManos(new Par());

        //Primero tengo que evaluar poker porque si evaluo trio o par tambien evaluaría OK
        if (poker.evaluarMano(unaMano)) {
            ret = "POKER";
        } else if (pierna.evaluarMano(unaMano)) {
            ret = "PIERNA";
        } else if (par.evaluarMano(unaMano)) {
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
        IMano unaMano = new Mano();
        unaMano.setUnJugador(j);
        this.colManos.add(unaMano);
    }

    @Override
    public void agregarObserver(Observer CTableroPoker) {
        this.addObserver(CTableroPoker);
        System.out.println("Observer agregad...");
        if(this.countObservers() == 2){
            System.out.println("Count observers " + this.countObservers());
            this.iniciarReparticion();
            for(IMano unaMano : this.colManos){
                System.out.println("Mano: " + unaMano.toString());
                this.notificarAccion("REPARTIR", unaMano);
                System.out.println("Notificar Repartir");
            }
        }
    }
    
    private void notificarAccion(String accion, Object obj){
        Mensaje unMensaje = new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);
        this.setChanged();
        this.notifyObservers(unMensaje);
    }

}
