/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Configuraciones.enumFigura;
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
    
    private int contadorAcciones;

    public PartidaPoker() {
        this.numero = 0;
        this.pozo = 0;
        this.colManos = new ArrayList<>();
        this.mazo = new Mazo();
        this.contadorAcciones = 0;
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

    @Override
    public void repartirCartas(IMano mano) {

        int cartasFaltantes = 5 - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir());
        }
    }

    @Override
    public void reponerCartas(IMano mano, ArrayList indices) {

        int cartasFaltantes = 5 - mano.getColCartas().size();
        for (int i = 0; i < cartasFaltantes; i++) {
            mano.agregarCarta(this.mazo.Repartir(), (int) indices.get(i));
        }
    }

    @Override
    public void iniciarReparticion() {
        this.mazo.Barajar();
        for (IMano m : this.colManos) {
            this.repartirCartas(m);
        }
    }

    @Override
    public enumFigura evaluarMano(IMano unaMano) {

        enumFigura ret = enumFigura.NINGUNA;
        EvaluadorManos poker = new EvaluadorManos(new Poker());
        EvaluadorManos pierna = new EvaluadorManos(new Pierna());
        EvaluadorManos par = new EvaluadorManos(new Par());

        //Primero tengo que evaluar poker porque si evaluo trio o par tambien evaluaría OK
        if ((poker.evaluarMano((Mano) unaMano)) == enumFigura.POKER)  {
            ret = enumFigura.POKER;
        } else if ((pierna.evaluarMano((Mano) unaMano)) == enumFigura.PIERNA) {
            ret = enumFigura.PIERNA;
        } else if ((par.evaluarMano((Mano) unaMano)) == enumFigura.PAR) {
            ret = enumFigura.PAR;
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
        System.out.println("Observer agregado...");
        if (this.countObservers() == 2) {
            System.out.println("Count observers " + this.countObservers());
            this.iniciarRonda();
        }
    }

    @Override
    public void iniciarRonda() {
        this.iniciarReparticion();
        for (IMano unaMano : this.colManos) {
            System.out.println("Mano: " + unaMano.toString());
            this.notificarAccion("REPARTIR", unaMano);
            System.out.println("Notificar Repartir");
            //Cuando se inicia la ronda de apuestas deben poner la ciega = 50
            this.accionJugador(unaMano.getUnJugador(), "APOSTAR", 50f);
        }
    }

    private void notificarAccion(String accion, Object obj) {
        Mensaje unMensaje = new Mensaje();
        unMensaje.setAccion(accion);
        unMensaje.setValor(obj);
        this.setChanged();
        this.notifyObservers(unMensaje);
    }

//    @Override
//    public void jugadorAposto(IJugador unJugador) {
//        IMano m = this.buscarMano(unJugador);
//        this.notificarAccion("APOSTAR", m);
//        System.out.println("Jugador Aposto");
//    }
    @Override
    public IMano buscarMano(IJugador unJugador) {

        for (IMano m : this.colManos) {

            if (m.getUnJugador().getNickName() == unJugador.getNickName()) {
                return m;
            }
        }

        return null;
    }

    @Override
    public float modificarPozo(float monto) {
        this.pozo += monto;

        return this.pozo;
    }

    @Override
    public void accionJugador(IJugador unJugador, String accion, Float monto) {
        IMano m = this.buscarMano(unJugador);
        boolean puedeApostar;
        boolean accionApostar = false;
        contadorAcciones++;
        switch (accion) {
            case "APOSTAR":
                
                puedeApostar = unJugador.apostar(monto);
                if (puedeApostar) {
                    this.modificarPozo(monto);
                    accionApostar = true;
                    contadorAcciones = 1;        
                } else {
                    accion = "NOPUEDEAPOSTAR";
                }

                this.notificarAccion(accion, m);

                break;

            case "PAGAR":

                puedeApostar = unJugador.apostar(monto);

                if (puedeApostar) {
                    this.modificarPozo(monto);
                } else {
                    accion = "NOPUEDEAPOSTAR";
                }

                this.notificarAccion(accion, m);

                break;

            case "PASAR":
                this.notificarAccion(accion, m);
                break;

            case "RETIRARSE":
                this.notificarAccion(accion, m);
                break;

            case "ABANDONARMESA":
                //TODO No da el tiempo, quedara para el infinito......
                break;

            default:

            break;

        }
        
        if(this.contadorAcciones == this.countObservers()){
            //TODO: Iniciar nueva ronda
        }
        
    }

    @Override
    public IMano evaluarGanador(ArrayList<IMano> colManos) {
        return null;
    }

    @Override
    public ArrayList<String> getListaNombresJugadores() {
        ArrayList<String> listaNombres = new ArrayList<>();
        for(IMano unaMano : this.getColManos()){
            listaNombres.add(unaMano.getUnJugador().getNickName());
        }
        return listaNombres;
    }

}
