/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.IJugador;

/**
 *
 * @author Sebastian
 */
public class Jugador implements IJugador {
    private String nickName;
    private String nombre;
    private String password;
    private float saldoInicial;
    private float saldo;
    private float montoApostado;

    public Jugador() {
        this.nickName       = "";
        this.nombre         = "";
        this.password       = "";
        this.saldoInicial   = 0f;
        this.saldo          = 0f;
        this.montoApostado  = 0f;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public float getMontoApostado() {
        return montoApostado;
    }

    @Override
    public void setMontoApostado(float montoApostado) {
        this.montoApostado = montoApostado;
    }   

    @Override
    public float getSaldo() {
        return saldo;
    }

    @Override
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public float getSaldoInicial() {
        return saldoInicial;
    }

    @Override
    public void setSaldoInicial(float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }   

    @Override
    public boolean apostar(float monto) {
        boolean retorno = false;
        if(this.getSaldo() >= monto){
            this.saldo -= monto;
            this.montoApostado = monto;
            retorno = true;
        }
        
        System.out.println(this.getNickName() + " aposte: " + this.montoApostado + " mi saldo es: " + this.getSaldo());
        return retorno;
    }

    @Override
    public String toString() {
        return "Jugador: " + "nickName=" + nickName + ", nombre=" + nombre + ", password=" + password + ", saldoInicial=" + saldoInicial + ", saldo=" + saldo + ", montoApostado=" + montoApostado;
    }

}
