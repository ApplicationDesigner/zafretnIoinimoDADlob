/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Jugador;
import Interfaz.Persistente;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miriarte
 */
public class JugadorPersistente implements Persistente {

    private Jugador jugador;

    public JugadorPersistente(Jugador unJugador) {
        this.jugador = unJugador;
    }
    
    public Jugador getJugador() {
        return this.jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO jugador (nickName,nombre,password,saldoInicial,saldo) VALUES ('"
                + this.jugador.getNickName() + "','" + this.jugador.getNombre() + "','"
                + this.jugador.getPassword() + "'," + this.jugador.getSaldoInicial() + ","
                + this.jugador.getSaldo() + ")";
    }

    @Override
    public String getUpdateSql() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteSql() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSelectSql() {
        return "SELECT * FROM jugador";
    }

    @Override
    public int getOid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOid(int oid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leer(ResultSet rs) {
        try {
            this.jugador.setNickName(rs.getString("nickName"));
            this.jugador.setNombre(rs.getString("nombre"));
            this.jugador.setPassword(rs.getString("password"));
            this.jugador.setSaldoInicial(rs.getFloat("saldoInicial"));
            this.jugador.setSaldo(rs.getFloat("saldo"));
        } catch (SQLException ex) {
            System.out.println("Error al leer jugador.\n" + ex.getMessage());
        }
    }

    @Override
    public Persistente getNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObjeto() {
        return this.jugador;
    }

}
