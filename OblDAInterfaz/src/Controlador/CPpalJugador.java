/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.JuegoPoker;
import Interfaces.IIngresarAPartida;
import Interfaces.ITableroPoker;
import Interfaz.IJuego;
import Interfaz.IPartida;
import Ventanas.VTableroPoker;
import java.awt.event.ActionEvent;
import java.util.Observable;

/**
 *
 * @author Sebastian
 */
public class CPpalJugador extends Controlador {

    private final IIngresarAPartida iip;

    public CPpalJugador(IIngresarAPartida iip) {
        this.iip = iip;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("IIngresarAPartida")) {

            IJuego ij = JuegoPoker.getInstance();
            IPartida ip = ij.buscarPartida(1); //TODO Hacer combo y obtener el id 
            
            //Al iniciar saldo = saldo inicial            
            this.iip.getJugador().setSaldo(this.iip.getJugador().getSaldoInicial());
            
            ITableroPoker itp = new VTableroPoker(this.iip.getJugador());
            itp.setPartida(ip);
            Controlador c = new CTableroPoker(itp);
            itp.setControlador(c);

            ip.agregarObserver(c);
            
            ij.ingresarJugadorAPartida(1, this.iip.getJugador());

        }
    }

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
