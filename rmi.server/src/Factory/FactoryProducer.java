/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

/**
 *
 * @author Sebastian
 */
public class FactoryProducer {
    
    public static AbstractFactory getFactory(String tipo) {
        
        switch (tipo) {
            case "JUEGO":
                return new JuegoFactory();
            case "PARTIDA":            
                return new PartidaFactory();
        }
        
        return null;       
    }
        
}
