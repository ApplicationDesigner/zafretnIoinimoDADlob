/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import BBDD.ManejadorBD;
import Dominio.Jugador;
import Persistencia.JugadorPersistente;

/**
 *
 * @author Marcelo
 */
public class Test {
    public static void main(String[] args) {
         ManejadorBD bd = ManejadorBD.getInstancia();
         bd.conectar("jdbc:mysql://localhost/dda?user=root&password=1234567.");
         Jugador j = new Jugador();
         j.setNickName("chelo_iri");
         j.setNombre("marcelo");
         j.setPassword("1234567.");
         j.setSaldoInicial(500);
         j.setSaldo(0);
         
         bd.agregar(new JugadorPersistente(j));
         System.out.println(bd.obtenerTodos(new JugadorPersistente(null)));
//        ClientePersistente cp = new ClientePersistente(null);
//        Cliente c = (Cliente)bd.obtenerTodos(cp).get(0);
//        c.setNombre("NUEVO NOMBRE!");
//        c.setCedula("NUEVA CEDULA!");
//        System.out.println(c);
//        cp.setCliente(c);
//        bd.modificar(cp);
//        System.out.println(c);
        //bd.eliminar(cp);
        
         bd.desconectar();
         System.out.println("OK");
    }
}
