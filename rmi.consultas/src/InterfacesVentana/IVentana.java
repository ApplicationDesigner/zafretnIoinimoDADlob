/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfacesVentana;

import Controlador.Controlador;
import java.util.ArrayList;

/**
 *
 * @author santiagoa
 */
public interface IVentana {
    
    //public String getMensaje();
    //public void RecibirMensaje(ArrayList<String> mensaje);
    
    //public void SetControlador(Controlador controller);
    public void iniciarComponentes();
    public void setControlador(Controlador c);
    
}
