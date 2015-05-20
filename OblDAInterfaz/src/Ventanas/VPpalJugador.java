/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Controlador.Controlador;
import Interfaces.IIngresarAPartida;
import Interfaz.IJugador;
import Interfaz.IPartida;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author Sebastian
 */
public final class VPpalJugador extends javax.swing.JFrame implements IIngresarAPartida {
    private IJugador jugador;
    private ArrayList<IPartida> listaPartidas;
    /**
     * Creates new form VPpalJugador
     */
    public VPpalJugador() {
        iniciarComponentes();        
    }
    
//    public VPpalJugador(IJugador j) {
//        this.jugador = j;
//        iniciarComponentes();      
//    }
    
    public VPpalJugador(IJugador j,ArrayList<IPartida> listaPartidas) {
        this.jugador = j;
        this.listaPartidas = listaPartidas;
        iniciarComponentes();      
        this.cargarComboPartidas(this.listaPartidas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIngresarPartida = new javax.swing.JButton();
        lblSeleccionarPartida = new javax.swing.JLabel();
        jcbSeleccionarPartidas = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIngresarPartida.setText("Ingresar");
        btnIngresarPartida.setActionCommand("IIngresarAPartida");

        lblSeleccionarPartida.setText("Seleccionar partida:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblSeleccionarPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbSeleccionarPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnIngresarPartida)
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeleccionarPartida)
                    .addComponent(jcbSeleccionarPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresarPartida))
                .addContainerGap(245, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(VPpalJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPpalJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPpalJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPpalJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPpalJugador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresarPartida;
    private javax.swing.JComboBox jcbSeleccionarPartidas;
    private javax.swing.JLabel lblSeleccionarPartida;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setControlador(Controlador c) {
        this.btnIngresarPartida.addActionListener(c);
    }

    @Override
    public void iniciarComponentes() {        
        initComponents();
        this.setSize(500, 500);
        this.setVisible(true);
    }

    @Override
    public void setJugador(IJugador j) {
        this.jugador = j;
    }

    @Override
    public IJugador getJugador() {
        return this.jugador;
    }
    
    private void cargarComboPartidas(ArrayList<IPartida> listaPartidas){
        for(IPartida p : listaPartidas){
            jcbSeleccionarPartidas.addItem(new JPTableroPokerComboItem("Partida " + p.getNumero(), p.getNumero()));
        }
        
        //jcbSeleccionarPartidas.setModel(new javax.swing.DefaultComboBoxModel(listaPartidas.toArray()));
    }
    
    @Override
    public int getPartidaSeleccionada(){
        Object item = jcbSeleccionarPartidas.getSelectedItem();
        return ((JPTableroPokerComboItem)item).getValue();
    }
}
