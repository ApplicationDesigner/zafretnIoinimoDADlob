/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesVentana;

/**
 *
 * @author Sebastian
 */
public interface ILogin extends IVentana {
    String getNickName();
    String getPass();
    public abstract void setLblMensaje(String mensaje);
}
