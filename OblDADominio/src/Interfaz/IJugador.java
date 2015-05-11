/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

/**
 *
 * @author Sebastian
 */
public interface IJugador {
    
    public abstract String getNickName();
    public abstract void setNickName(String nickName);
    public abstract String getNombre();
    public abstract void setNombre(String nombre);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract float getSaldo();
    public abstract void setSaldo(float saldo);
}
