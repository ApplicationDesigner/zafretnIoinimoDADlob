/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Sebastian
 */
public final class Mazo {

    private static final int tamanio = 52;
    private final ArrayList<Carta> colCartas;

    public static int getTamanio() {
        return tamanio;
    }

    public ArrayList<Carta> getColCartas() {
        return colCartas;
    }
    
    

    public Mazo() {
        this.colCartas = new ArrayList<>(tamanio);

        for (Carta.Valor unValor : Carta.Valor.values()) {
            for (Carta.Palo unPalo : Carta.Palo.values()) {

                colCartas.add(new Carta(unValor, unPalo));
            }
        }

    }

    public void Barajar() {
        Collections.shuffle(colCartas);
    }

    public Carta Repartir() {
        Carta cartaAux = new Carta(colCartas.get(0).getValor(), colCartas.get(0).getPalo());

        colCartas.remove(colCartas.get(0));

        return cartaAux;
    }

    @Override
    public String toString() {
        return "Mazo{" + "colCartas=" + colCartas.toString() + '}';
    }
    
    

    public void mostrarMazo() {       
           
        for (Carta unaCarta : this.colCartas) {
            System.out.println(unaCarta.toString());
        }   
    }
    
    public void CargarMazo(){
        Carta dosTrebol = new Carta(Carta.Valor.DOS, Carta.Palo.TREBOL);
        Carta tresTrebol = new Carta(Carta.Valor.TRES, Carta.Palo.TREBOL);
        Carta cuatroTrebol = new Carta(Carta.Valor.CUATRO, Carta.Palo.TREBOL);
        Carta cincoTrebol = new Carta(Carta.Valor.CINCO, Carta.Palo.TREBOL);
        Carta seisTrebol = new Carta(Carta.Valor.SEIS, Carta.Palo.TREBOL);
        Carta sieteTrebol = new Carta(Carta.Valor.SIETE, Carta.Palo.TREBOL);
        Carta ochoTrebol = new Carta(Carta.Valor.OCHO, Carta.Palo.TREBOL);
        Carta nueveTrebol = new Carta(Carta.Valor.NUEVE, Carta.Palo.TREBOL);
        Carta diezTrebol = new Carta(Carta.Valor.DIEZ, Carta.Palo.TREBOL);
        Carta asTrebol = new Carta(Carta.Valor.AS, Carta.Palo.TREBOL);
        Carta jTrebol = new Carta(Carta.Valor.J, Carta.Palo.TREBOL);
        Carta kTrebol = new Carta(Carta.Valor.K, Carta.Palo.TREBOL);
        Carta qTrebol = new Carta(Carta.Valor.Q, Carta.Palo.TREBOL);
        
        this.colCartas.add(dosTrebol);
        this.colCartas.add(tresTrebol);
        this.colCartas.add(cuatroTrebol);
        this.colCartas.add(cincoTrebol);
        this.colCartas.add(seisTrebol);
        this.colCartas.add(sieteTrebol);
        this.colCartas.add(ochoTrebol);
        this.colCartas.add(nueveTrebol);
        this.colCartas.add(diezTrebol);
        this.colCartas.add(asTrebol);
        this.colCartas.add(jTrebol);
        this.colCartas.add(kTrebol);
        this.colCartas.add(qTrebol);
        
        Carta dosPique = new Carta(Carta.Valor.DOS, Carta.Palo.PIQUE);
        Carta tresPique = new Carta(Carta.Valor.TRES, Carta.Palo.PIQUE);
        Carta cuatroPique = new Carta(Carta.Valor.CUATRO, Carta.Palo.PIQUE);
        Carta cincoPique = new Carta(Carta.Valor.CINCO, Carta.Palo.PIQUE);
        Carta seisPique = new Carta(Carta.Valor.SEIS, Carta.Palo.PIQUE);
        Carta sietePique = new Carta(Carta.Valor.SIETE, Carta.Palo.PIQUE);
        Carta ochoPique = new Carta(Carta.Valor.OCHO, Carta.Palo.PIQUE);
        Carta nuevePique = new Carta(Carta.Valor.NUEVE, Carta.Palo.PIQUE);
        Carta diezPique = new Carta(Carta.Valor.DIEZ, Carta.Palo.PIQUE);
        Carta asPique = new Carta(Carta.Valor.AS, Carta.Palo.PIQUE);
        Carta jPique = new Carta(Carta.Valor.J, Carta.Palo.PIQUE);
        Carta kPique = new Carta(Carta.Valor.K, Carta.Palo.PIQUE);
        Carta qPique = new Carta(Carta.Valor.Q, Carta.Palo.PIQUE);
        
        
        this.colCartas.add(dosPique);
        this.colCartas.add(tresPique);
        this.colCartas.add(cuatroPique);
        this.colCartas.add(cincoPique);
        this.colCartas.add(seisPique);
        this.colCartas.add(sietePique);
        this.colCartas.add(ochoPique);
        this.colCartas.add(nuevePique);
        this.colCartas.add(diezPique);
        this.colCartas.add(asPique);
        this.colCartas.add(jPique);
        this.colCartas.add(kPique);
        this.colCartas.add(qPique);
        
        
        Carta dosCorazon = new Carta(Carta.Valor.DOS, Carta.Palo.CORAZON);
        Carta tresCorazon = new Carta(Carta.Valor.TRES, Carta.Palo.CORAZON);
        Carta cuatroCorazon = new Carta(Carta.Valor.CUATRO, Carta.Palo.CORAZON);
        Carta cincoCorazon = new Carta(Carta.Valor.CINCO, Carta.Palo.CORAZON);
        Carta seisCorazon = new Carta(Carta.Valor.SEIS, Carta.Palo.CORAZON);
        Carta sieteCorazon = new Carta(Carta.Valor.SIETE, Carta.Palo.CORAZON);
        Carta ochoCorazon = new Carta(Carta.Valor.OCHO, Carta.Palo.CORAZON);
        Carta nueveCorazon = new Carta(Carta.Valor.NUEVE, Carta.Palo.CORAZON);
        Carta diezCorazon = new Carta(Carta.Valor.DIEZ, Carta.Palo.CORAZON);
        Carta asCorazon = new Carta(Carta.Valor.AS, Carta.Palo.CORAZON);
        Carta jCorazon = new Carta(Carta.Valor.J, Carta.Palo.CORAZON);
        Carta kCorazon = new Carta(Carta.Valor.K, Carta.Palo.CORAZON);
        Carta qCorazon = new Carta(Carta.Valor.Q, Carta.Palo.CORAZON);
        
        
        this.colCartas.add(dosCorazon);
        this.colCartas.add(tresCorazon);
        this.colCartas.add(cuatroCorazon);
        this.colCartas.add(cincoCorazon);
        this.colCartas.add(seisCorazon);
        this.colCartas.add(sieteCorazon);
        this.colCartas.add(ochoCorazon);
        this.colCartas.add(nueveCorazon);
        this.colCartas.add(diezCorazon);
        this.colCartas.add(asCorazon);
        this.colCartas.add(jCorazon);
        this.colCartas.add(kCorazon);
        this.colCartas.add(qCorazon);
        
        Carta dosDiamante = new Carta(Carta.Valor.DOS, Carta.Palo.DIAMANTE);
        Carta tresDiamante = new Carta(Carta.Valor.TRES, Carta.Palo.DIAMANTE);
        Carta cuatroDiamante = new Carta(Carta.Valor.CUATRO, Carta.Palo.DIAMANTE);
        Carta cincoDiamante = new Carta(Carta.Valor.CINCO, Carta.Palo.DIAMANTE);
        Carta seisDiamante = new Carta(Carta.Valor.SEIS, Carta.Palo.DIAMANTE);
        Carta sieteDiamante = new Carta(Carta.Valor.SIETE, Carta.Palo.DIAMANTE);
        Carta ochoDiamante = new Carta(Carta.Valor.OCHO, Carta.Palo.DIAMANTE);
        Carta nueveDiamante = new Carta(Carta.Valor.NUEVE, Carta.Palo.DIAMANTE);
        Carta diezDiamante = new Carta(Carta.Valor.DIEZ, Carta.Palo.DIAMANTE);
        Carta asDiamante = new Carta(Carta.Valor.AS, Carta.Palo.DIAMANTE);
        Carta jDiamante = new Carta(Carta.Valor.J, Carta.Palo.DIAMANTE);
        Carta kDiamante = new Carta(Carta.Valor.K, Carta.Palo.DIAMANTE);
        Carta qDiamante = new Carta(Carta.Valor.Q, Carta.Palo.DIAMANTE);
        
        
        this.colCartas.add(dosDiamante);
        this.colCartas.add(tresDiamante);
        this.colCartas.add(cuatroDiamante);
        this.colCartas.add(cincoDiamante);
        this.colCartas.add(seisDiamante);
        this.colCartas.add(sieteDiamante);
        this.colCartas.add(ochoDiamante);
        this.colCartas.add(nueveDiamante);
        this.colCartas.add(diezDiamante);
        this.colCartas.add(asDiamante);
        this.colCartas.add(jDiamante);
        this.colCartas.add(kDiamante);
        this.colCartas.add(qDiamante);
    }
	

}
