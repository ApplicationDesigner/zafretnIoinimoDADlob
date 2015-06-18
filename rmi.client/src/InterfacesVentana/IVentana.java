package InterfacesVentana;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controlador.Controlador;

/**
 *
 * @author Sebastian
 */
public interface IVentana {
   public abstract void setControlador(Controlador c);
   public abstract void iniciarComponentes();
}
