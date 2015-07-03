/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Controlador.Controlador;
import DominioCommon.Carta;
import InterfacesVentana.ITableroPoker;
import InterfazCommon.IJuego;
import InterfazCommon.IJugador;
import InterfazCommon.IMano;
import InterfazCommon.IPartida;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Sebastian
 */
public final class JPTableroPoker extends javax.swing.JPanel implements ITableroPoker {

    private IJugador jugador;
    private IPartida partida;

    /**
     * Creates new form JPPartida
     */
    public JPTableroPoker() {
        iniciarComponentes();
    }

    public IJugador getJugador() {
        return jugador;
    }

    public void setJugador(IJugador jugador) {
        this.jugador = jugador;
    }

    public IPartida getPartida() {
        return partida;
    }

    public void setPartida(IPartida partida) {
        this.partida = partida;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnApostar = new javax.swing.JButton();
        btnCarta1 = new javax.swing.JButton();
        btnCarta2 = new javax.swing.JButton();
        btnCarta3 = new javax.swing.JButton();
        btnCarta4 = new javax.swing.JButton();
        btnCarta5 = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        btnRetirarme = new javax.swing.JButton();
        txtMonto = new javax.swing.JTextField();
        lblNickName = new javax.swing.JLabel();
        lblJugador = new javax.swing.JLabel();
        lblFigura = new javax.swing.JLabel();
        lblFiguraReal = new javax.swing.JLabel();
        lblNroJuego = new javax.swing.JLabel();
        lblNroJuegoReal = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        lblSaldoReal = new javax.swing.JLabel();
        lblPozo = new javax.swing.JLabel();
        lblPozoReal = new javax.swing.JLabel();
        lblListaJugadores = new javax.swing.JLabel();
        lblListaJugadoresReal = new javax.swing.JLabel();
        btnPedirCartas = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaLogs = new javax.swing.JTextArea();
        btnPasar = new javax.swing.JButton();
        btnAbandonarPartidaSI = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAbandonarPartidaNO = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 0));

        btnApostar.setText("Apostar");
        btnApostar.setActionCommand("btnApostar");

        btnCarta1.setActionCommand("Carta1");
        btnCarta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarta1ActionPerformed(evt);
            }
        });

        btnCarta2.setActionCommand("Carta2");
        btnCarta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarta2ActionPerformed(evt);
            }
        });

        btnCarta3.setActionCommand("Carta3");
        btnCarta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarta3ActionPerformed(evt);
            }
        });

        btnCarta4.setActionCommand("Carta4");
        btnCarta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarta4ActionPerformed(evt);
            }
        });

        btnCarta5.setActionCommand("Carta5");
        btnCarta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarta5ActionPerformed(evt);
            }
        });

        btnPagar.setText("Pagar");
        btnPagar.setActionCommand("btnPagar");

        btnRetirarme.setText("Retirarme");
        btnRetirarme.setActionCommand("btnRetirarme");

        lblNickName.setForeground(new java.awt.Color(255, 255, 255));

        lblJugador.setForeground(new java.awt.Color(255, 255, 255));
        lblJugador.setText("Jugador:");

        lblFigura.setForeground(new java.awt.Color(255, 255, 255));
        lblFigura.setText("Figura:");

        lblFiguraReal.setForeground(new java.awt.Color(255, 255, 255));

        lblNroJuego.setForeground(new java.awt.Color(255, 255, 255));
        lblNroJuego.setText("Juego Nro:");

        lblNroJuegoReal.setForeground(new java.awt.Color(255, 255, 255));

        lblSaldo.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldo.setText("Saldo Jugador:");

        lblSaldoReal.setForeground(new java.awt.Color(255, 255, 255));

        lblPozo.setForeground(new java.awt.Color(255, 255, 255));
        lblPozo.setText("Pozo:");

        lblPozoReal.setForeground(new java.awt.Color(255, 255, 255));

        lblListaJugadores.setForeground(new java.awt.Color(255, 255, 255));
        lblListaJugadores.setText("Jugadores:");

        lblListaJugadoresReal.setForeground(new java.awt.Color(255, 255, 255));

        btnPedirCartas.setText("Pedir Cartas");
        btnPedirCartas.setActionCommand("Pedir_Cartas");

        lblMensaje.setForeground(new java.awt.Color(255, 255, 0));

        jtaLogs.setColumns(20);
        jtaLogs.setRows(5);
        jScrollPane1.setViewportView(jtaLogs);

        btnPasar.setText("Pasar");
        btnPasar.setActionCommand("btnPasar");

        btnAbandonarPartidaSI.setText("Si");
        btnAbandonarPartidaSI.setActionCommand("btnAbandonarPartidaSI");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("¿Desea abandonar la partida?");

        btnAbandonarPartidaNO.setText("No");
        btnAbandonarPartidaNO.setActionCommand("btnAbandonarPartidaNO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCarta1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFigura, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(lblFiguraReal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCarta2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCarta3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCarta4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCarta5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPedirCartas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPasar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApostar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRetirarme, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNickName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSaldoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblPozo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNroJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblListaJugadores, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPozoReal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNroJuegoReal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblListaJugadoresReal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAbandonarPartidaSI, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAbandonarPartidaNO, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFigura, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiguraReal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCarta5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarta3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarta4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarta1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCarta2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRetirarme)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPedirCartas)
                                .addComponent(btnPagar)
                                .addComponent(btnPasar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApostar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNickName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSaldoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNroJuego)
                            .addComponent(lblNroJuegoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPozo)
                            .addComponent(lblPozoReal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(lblListaJugadores)
                        .addGap(18, 18, 18)
                        .addComponent(lblListaJugadoresReal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAbandonarPartidaSI)
                        .addComponent(btnAbandonarPartidaNO)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarta1ActionPerformed
//        Carta unaCarta = this.partida.buscarMano(jugador).getColCartas().get(0);
//        this.cambiarImagen(unaCarta, btnCarta1);
    }//GEN-LAST:event_btnCarta1ActionPerformed

    private void btnCarta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarta2ActionPerformed
        Carta unaCarta = null;
        try {
            unaCarta = this.partida.buscarMano(jugador).getColCartas().get(1);
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cambiarImagen(unaCarta, btnCarta2);
    }//GEN-LAST:event_btnCarta2ActionPerformed

    private void btnCarta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarta3ActionPerformed
        Carta unaCarta = null;
        try {
            unaCarta = this.partida.buscarMano(jugador).getColCartas().get(2);
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cambiarImagen(unaCarta, btnCarta3);
    }//GEN-LAST:event_btnCarta3ActionPerformed

    private void btnCarta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarta4ActionPerformed
        Carta unaCarta = null;
        try {
            unaCarta = this.partida.buscarMano(jugador).getColCartas().get(3);
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cambiarImagen(unaCarta, btnCarta4);
    }//GEN-LAST:event_btnCarta4ActionPerformed

    private void btnCarta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarta5ActionPerformed
        Carta unaCarta = null;
        try {
            unaCarta = this.partida.buscarMano(jugador).getColCartas().get(4);
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cambiarImagen(unaCarta, btnCarta5);
    }//GEN-LAST:event_btnCarta5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonarPartidaNO;
    private javax.swing.JButton btnAbandonarPartidaSI;
    private javax.swing.JButton btnApostar;
    private javax.swing.JButton btnCarta1;
    private javax.swing.JButton btnCarta2;
    private javax.swing.JButton btnCarta3;
    private javax.swing.JButton btnCarta4;
    private javax.swing.JButton btnCarta5;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnPasar;
    private javax.swing.JButton btnPedirCartas;
    private javax.swing.JButton btnRetirarme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtaLogs;
    private javax.swing.JLabel lblFigura;
    private javax.swing.JLabel lblFiguraReal;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblListaJugadores;
    private javax.swing.JLabel lblListaJugadoresReal;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNickName;
    private javax.swing.JLabel lblNroJuego;
    private javax.swing.JLabel lblNroJuegoReal;
    private javax.swing.JLabel lblPozo;
    private javax.swing.JLabel lblPozoReal;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoReal;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setControlador(Controlador c) {
        this.btnPagar.addActionListener(c);
        this.btnPasar.addActionListener(c);
        this.btnRetirarme.addActionListener(c);
        this.btnApostar.addActionListener(c);
        this.btnPedirCartas.addActionListener(c);
        this.btnAbandonarPartidaNO.addActionListener(c);
        this.btnAbandonarPartidaSI.addActionListener(c);

    }

    @Override
    public void iniciarComponentes() {
        this.setVisible(true);
        this.setSize(800, 500);
        initComponents();

        this.habilitarBotonPagar(false);
        this.habilitarBotonPedirCartas(false);
        this.habilitarBotonRetirarme(false);
        this.habilitarBotonApostar(false);
        this.habilitarBotonPasar(false);
        this.habilitarBotonAbandonarPartidaNO(false);
        this.habilitarBotonAbandonarPartidaSI(false);
    }

    @Override
    public void mostrarMano(IMano unaMano) {
        this.mostrarNickName();
        btnCarta1.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaMano.getColCartas().get(0).getPathImagen())));
        btnCarta1.setActionCommand("btnCarta1");
        btnCarta1.setBorder(null);

        btnCarta2.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaMano.getColCartas().get(1).getPathImagen())));
        btnCarta2.setActionCommand("btnCarta2");

        btnCarta3.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaMano.getColCartas().get(2).getPathImagen())));
        btnCarta3.setActionCommand("btnCarta3");

        btnCarta4.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaMano.getColCartas().get(3).getPathImagen())));
        btnCarta4.setActionCommand("btnCarta4");

        btnCarta5.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaMano.getColCartas().get(4).getPathImagen())));
        btnCarta5.setActionCommand("btnCarta5");

        //TODO implementar con conectarJuegoPoker o sino cambiar por otro metodo
        //IJuego ij = JuegoPoker.getInstance();
        IPartida ip = null;
        
        //TODO implementar con conectarJuegoPoker o sino cambiar por otro metodo
//        try {
//            //ip = ij.buscarPartida(this.partida.getNumero());
//        } catch (RemoteException ex) {
//            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            this.lblFiguraReal.setText(ip.evaluarMano(unaMano).toString());
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.lblNroJuegoReal.setText("" + this.partida.getNumero());
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.lblListaJugadoresReal.setText(ip.getListaNombresJugadores().toString());
        } catch (RemoteException ex) {
            Logger.getLogger(JPTableroPoker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mostarSaldoJugador(String monto) {
        this.lblSaldoReal.setText(monto);
    }

    @Override
    public float getMontoApostado() {
        float monto = 0f;
        if (this.txtMonto.getText().equals("")) {
            this.mostrarMensaje("El monto apostado no debe ser vacio.");
        } else {
            try {
                monto = Float.parseFloat(this.txtMonto.getText());
                if (monto < 0) {
                    this.mostrarMensaje("El monto debe ser mayor a 0.");
                }
            } catch (Exception ex) {
                this.mostrarMensaje("El monto es incorrecto.");
            }
        }

        return monto;
    }

    @Override
    public void mostrarPozo(String pozo) {
        this.lblPozoReal.setText(pozo);
    }

    @Override
    public ArrayList<String> getBotonesDeCartasSeleccionadas() {
        ArrayList<String> listaCartas = new ArrayList<>();
        if (this.btnCarta1.isEnabled() == false) {
            listaCartas.add(btnCarta1.getActionCommand());
        }
        if (this.btnCarta2.isEnabled() == false) {
            listaCartas.add(btnCarta2.getActionCommand());
        }
        if (this.btnCarta3.isEnabled() == false) {
            listaCartas.add(btnCarta3.getActionCommand());
        }
        if (this.btnCarta4.isEnabled() == false) {
            listaCartas.add(btnCarta4.getActionCommand());
        }
        if (this.btnCarta5.isEnabled() == false) {
            listaCartas.add(btnCarta5.getActionCommand());
        }
        return listaCartas;
    }

    @Override
    public void mostrarNickName() {
        this.lblNickName.setText(jugador.getNickName());
    }

    @Override
    public void mostrarMensaje(String texto) {
        this.lblMensaje.setText(texto);
    }

    @Override
    public void habilitarBotonApostar(boolean estado) {
        this.btnApostar.setEnabled(estado);
    }

    @Override
    public void habilitarBotonPedirCartas(boolean estado) {
        this.btnPedirCartas.setEnabled(estado);
    }

    @Override
    public void habilitarBotonPagar(boolean estado) {
        this.btnPagar.setEnabled(estado);
    }

    @Override
    public void habilitarBotonPasar(boolean estado) {
        this.btnPasar.setEnabled(estado);
    }

    @Override
    public void habilitarBotonRetirarme(boolean estado) {
        this.btnRetirarme.setEnabled(estado);
    }

    @Override
    public void escribirLog(String log) {
        this.jtaLogs.append("> " + log);
    }

    @Override
    public void habilitarBotonAbandonarPartidaSI(boolean estado) {
        this.btnAbandonarPartidaSI.setEnabled(estado);
    }

    @Override
    public void habilitarBotonAbandonarPartidaNO(boolean estado) {
        this.btnAbandonarPartidaNO.setEnabled(estado);
    }

    @Override
    public void limpiarCampos() {
        this.txtMonto.setText("");
        this.lblMensaje.setText("");
    }

    @Override
    public void deshabilitarPanel() {
        btnCarta1.setEnabled(false);
        btnCarta2.setEnabled(false);
        btnCarta3.setEnabled(false);
        btnCarta4.setEnabled(false);
        btnCarta5.setEnabled(false);
    }

    private void cambiarImagen(Carta unaCarta, JButton btn) {
        if (btnPedirCartas.isEnabled() == true) {
            if (unaCarta.getActiva() == true) {
                unaCarta.setActiva(false);
            } else {
                unaCarta.setActiva(true);
            }
            btn.setIcon(new javax.swing.ImageIcon(getClass().getResource(unaCarta.getPathImagen())));
        }
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

}
