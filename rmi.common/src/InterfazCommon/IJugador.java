/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazCommon;

import java.io.Serializable;

/**
 *
 * @author Sebastian
 */
public interface IJugador extends Serializable {
    
    public abstract String getNickName();
    public abstract void setNickName(String nickName);
    public abstract String getNombre();
    public abstract void setNombre(String nombre);
    public abstract String getPassword();
    public abstract void setPassword(String password);
    public abstract float getSaldo();
    public abstract void setSaldo(float saldo);
    public abstract boolean apostar(float monto);
    public abstract float getSaldoInicial();
    public abstract void setSaldoInicial(float saldoInicial);
    public abstract float getMontoApostado();
    public abstract void setMontoApostado(float montoApostado);
}
