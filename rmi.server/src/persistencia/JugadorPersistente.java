/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import persistencia.IPersistente;
import java.sql.ResultSet;
import java.sql.SQLException;
import Dominio.Jugador;

/**
 *
 * @author miriarte
 */
public class JugadorPersistente implements IPersistente {

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
        return "UPDATE jugador SET nombre='" + this.jugador.getNombre()
                + "',password='" + this.jugador.getPassword()
                + "',saldoInicial=" + this.jugador.getSaldoInicial() + ""
                + ",saldo=" + this.jugador.getSaldo() + ""
                + " WHERE nickName='" + this.jugador.getNickName() + "'";
    }

    @Override
    public String getDeleteSql() {
        return "DELETE FROM jugador WHERE nickName='" + this.jugador.getNickName() + "'";
    }

    @Override
    public String getSelectSql() {
        String sql = "SELECT * FROM jugador";
        if (this.jugador != null) {
            sql += " WHERE nickName=" + this.jugador.getNickName();
        }
        return sql;

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
    public Object crearNuevo() {
        this.jugador = new Jugador();
        return this.jugador;
    }

}
