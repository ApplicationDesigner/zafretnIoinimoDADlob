/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Configuraciones.enumFigura;

/**
 *
 * @author Sebastian
 */
public class EvaluadorManos {
    
    private Figura figura;
    

    
    public EvaluadorManos(Figura unaFigura) {
        this.figura = unaFigura;
    }
    
    public enumFigura evaluarMano(Mano unaMano) {
        return this.figura.evaluarMano(unaMano);
    }
    
    

}
