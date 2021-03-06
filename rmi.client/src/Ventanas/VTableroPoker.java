/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Controlador.Controlador;
import InterfacesVentana.ITableroPoker;
import InterfazCommon.IJugador;
import InterfazCommon.IMano;
import InterfazCommon.IPartida;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Sebastian
 */
public final class VTableroPoker extends javax.swing.JFrame implements ITableroPoker{
    private JPTableroPoker jptp;
    private IJugador jugador;
    private IPartida partida;

    /**
     * Creates new form VPartida
     */
    public VTableroPoker() {
        iniciarComponentes();
    }
    
    public VTableroPoker(IJugador j) {
        this.jugador = j;       
        iniciarComponentes();
    }

    public VTableroPoker(IJugador j, IPartida p) {
        this.jugador = j;  
        this.partida = p;
        iniciarComponentes();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VTableroPoker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VTableroPoker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VTableroPoker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VTableroPoker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VTableroPoker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void setControlador(Controlador c) {
        this.jptp.setControlador(c);
    }

    @Override
    public void iniciarComponentes() {
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setVisible(true);
        
        this.jptp = new JPTableroPoker();
        this.jptp.setJugador(jugador);
        this.jptp.setPartida(partida);
        
        System.out.println("------------------------");
        System.out.println("En VTableroPoker");
        System.out.println("jugador = " + jugador.getNickName());
        try {
            System.out.println("partida = Partida" + partida.getNumero());
        } catch (RemoteException ex) {
            Logger.getLogger(VTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.jptp.setSize(800, 500);
        this.getContentPane().removeAll();
        this.getContentPane().add(this.jptp);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @Override
    public IJugador getJugador() {
        return this.jptp.getJugador();
    }

    @Override
    public void mostrarMano(IMano unaMano) {
        this.jptp.mostrarMano(unaMano);
    }

    @Override
    public void mostarSaldoJugador(String saldoJugador) {
//        System.out.println("En ITableroPoker mostrarSaldoJugador = " + saldoJugador);
        this.jptp.mostarSaldoJugador(saldoJugador);
    }

    @Override
    public IPartida getPartida() {
        return this.jptp.getPartida();
    }

    @Override
    public void setPartida(IPartida partida) {
        this.partida = partida;        
    }

    @Override
    public float getMontoApostado() {
        return this.jptp.getMontoApostado();
    }

    @Override
    public void mostrarPozo(String pozo) {
        this.jptp.mostrarPozo(pozo);
    }

    @Override
    public ArrayList<String> getBotonesDeCartasSeleccionadas() {
        return this.jptp.getBotonesDeCartasSeleccionadas();
    }

    @Override
    public void habilitarBotonApostar(boolean estado) {
        this.jptp.habilitarBotonApostar(estado);
    }

    @Override
    public void habilitarBotonPedirCartas(boolean estado) {
        this.jptp.habilitarBotonPedirCartas(estado);
    }

    @Override
    public void habilitarBotonPagar(boolean estado) {
        this.jptp.habilitarBotonPagar(estado);
    }
    
    @Override
    public void habilitarBotonPasar(boolean estado) {
        this.jptp.habilitarBotonPasar(estado);
    }

    @Override
    public void habilitarBotonRetirarme(boolean estado) {
        this.jptp.habilitarBotonRetirarme(estado);
    }

    @Override
    public void escribirLog(String log) {
        this.jptp.escribirLog(log);
    }

    @Override
    public void mostrarNickName() {
        this.jptp.mostrarNickName();
    }

    @Override
    public void mostrarMensaje(String texto) {
        this.jptp.mostrarMensaje(texto);
    }

    @Override
    public void habilitarBotonSeguirJugandoNO(boolean estado) {
        this.jptp.habilitarBotonSeguirJugandoNO(estado);
    }

    @Override
    public void habilitarBotonSeguirJugandoSi(boolean estado) {
        this.jptp.habilitarBotonSeguirJugandoSi(estado);
    }

    @Override
    public void limpiarCampos() {
        this.jptp.limpiarCampos();
    }

    @Override
    public void deshabilitarPanel() {
        this.jptp.deshabilitarPanel();
    }

    @Override
    public String getMensaje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RecibirMensaje(Serializable mesaje) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetControlador(Controlador controller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setManoActual(IMano unaMano) {
        jptp.setManoActual(unaMano);
    }

    @Override
    public IMano getManoActual() {
        return jptp.getManoActual();
    }

    @Override
    public void cerrarVentana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
