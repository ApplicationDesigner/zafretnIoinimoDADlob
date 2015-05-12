/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Interfaz.IJugador;

/**
 *
 * @author Sebastian
 */
public class Jugador implements IJugador {
    private String nickName;
    private String nombre;
    private String password;
    private float saldo;

    public Jugador() {
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
    public float getSaldo() {
        return saldo;
    }

    @Override
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean apostar(float monto) {
        boolean retorno = false;
        if(this.getSaldo() >= monto){
            this.saldo -= monto;
            retorno = true;
        }
        return retorno;
    }
    
    
}
