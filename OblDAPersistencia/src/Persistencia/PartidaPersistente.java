/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.PartidaPoker;
import Interfaz.IPersistente;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MIriarte
 */
public class PartidaPersistente implements IPersistente {

    private PartidaPoker partida;

    public PartidaPersistente(PartidaPoker unaPartida) {
        this.partida = unaPartida;
    }

    public PartidaPoker getPartidaPoker() {
        return partida;
    }

    public void setPartidaPoker(PartidaPoker partidaPoker) {
        this.partida = partidaPoker;
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO partida (numero,duracion,total_apostado) VALUES ("
                + this.partida.getNumero() + "," + this.partida.getDuracion() + ","
                + this.partida.getPozo() + ")";
    }

    @Override
    public String getUpdateSql() {
        return "UPDATE partida SET duracion=" + this.partida.getDuracion()
                + ",total_apostado=" + this.partida.getPozo()
                + " WHERE numero=" + this.partida.getNumero();
    }

    @Override
    public String getDeleteSql() {
        return "DELETE FROM partida WHERE numero=" + this.partida.getNumero() + "";
    }

    @Override
    public String getSelectSql() {
        String sql = "SELECT * FROM partida";
        if (this.partida != null) {
            sql += " WHERE numero=" + this.partida.getNumero();
        }
        return sql;
    }

    @Override
    public void leer(ResultSet rs) {
        try {
            this.partida.setNumero(rs.getInt("numero"));
            this.partida.setDuracion(rs.getInt("duracion"));
            this.partida.setPozo(rs.getInt("total_apostado"));
        } catch (SQLException ex) {
            System.out.println("Error al leer partida.\n" + ex.getMessage());
        }
    }

    @Override
    public Object crearNuevo() {
        this.partida = new PartidaPoker();
        return this.partida;
    }

}
