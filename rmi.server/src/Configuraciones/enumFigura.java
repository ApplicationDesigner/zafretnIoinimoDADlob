/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuraciones;

/**
 *
 * @author Marcelo
 */
public enum enumFigura {
    
        NINGUNA, PAR, PIERNA, ESCALERA, POKER;

        private final String[] enumFiguraString = {"NINGUNA","PAR", "PIERNA", "ESCALERA", "POKER"};

        @Override
        public String toString() {
            return enumFiguraString[this.ordinal()];
        }
}
