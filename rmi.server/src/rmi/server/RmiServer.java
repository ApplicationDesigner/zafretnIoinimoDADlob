/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.server;

import Dominio.Casino;
import InterfazCommon.ICasino;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Sebastian
 */
public class RmiServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try{
            if(System.getSecurityManager()==null){
                System.setSecurityManager(new SecurityManager());
            }
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            
            Casino casino = Casino.getInstance();
            Naming.rebind("CasinoServer", casino);
            System.out.println("levantado .. esperando por peticiones");
            
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
//        
//        System.out.print("Inicializando servidor...");
//         
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new RMISecurityManager());
//        }
//               
//        String name = "//127.0.0.1/StringServer";
//        
//        try {
//        	
//            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
//            //IStringUtils server = new StringUtilsImpl();
//            //Naming.rebind(name, server);
//            System.out.println("OK");
//            
//        } catch (Exception e) {
//            System.err.println("Server Exception: " +  e.getMessage());
//            e.printStackTrace();
//            
//        }
    }
    
}
