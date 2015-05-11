/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Interfaces.ITableroPoker;

/**
 *
 * @author Sebastian
 */
public class CTableroPoker extends Controlador {
    private final ITableroPoker itp;
    
    CTableroPoker(ITableroPoker itp) {
        this.itp = itp;
    }   
}
