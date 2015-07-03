/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesVentana;

import InterfazCommon.IPartida;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public interface IConsultas extends IVentana {
//    String getNickName();
//    String getPass();
    void setGanancias(float ganancias);
    void setHistoricoPartidas(ArrayList<IPartida> listaPartidas);
    public abstract void setLblMensaje(String mensaje);
}
