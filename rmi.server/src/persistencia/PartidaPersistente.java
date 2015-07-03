/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Dominio.PartidaPoker;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MIriarte
 */
//public class PartidaPersistente implements IPersistente {
public class PartidaPersistente implements IPersistente{

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
        String ret = "";
        try {
            ret = "INSERT INTO partida (numero_partida,duracion,total_apostado) VALUES ("
                    + this.partida.getNumero() + "," + this.partida.getDuracion() + ","
                    + this.partida.getPozo() + ")";
        } catch (RemoteException ex) {
            Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public String getUpdateSql() {
        String ret = "";
        try {
            ret = "UPDATE partida SET duracion=" + this.partida.getDuracion()
                    + ",total_apostado=" + this.partida.getPozo()
                    + " WHERE numero_partida=" + this.partida.getNumero();
        } catch (RemoteException ex) {
            Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public String getDeleteSql() {
        String ret = "";
        try {
            ret = "DELETE FROM partida WHERE numero_partida=" + this.partida.getNumero() + "";
        } catch (RemoteException ex) {
            Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public String getSelectSql() {
        String sql = "SELECT * FROM partida";
        if (this.partida != null) {
            try {
                sql += " WHERE numero_partida=" + this.partida.getNumero();
            } catch (RemoteException ex) {
                Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sql;
    }

    @Override
    public void leer(ResultSet rs) {
        try {
            try {
                this.partida.setNumero(rs.getInt("numero_partida"));
                this.partida.setDuracion(rs.getInt("duracion"));
                this.partida.setPozo(rs.getInt("total_apostado"));
            } catch (RemoteException ex) {
                Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer partida.\n" + ex.getMessage());
        }
    }

    @Override
    public Object crearNuevo() {
        try {
            this.partida = new PartidaPoker();
        } catch (RemoteException ex) {
            Logger.getLogger(PartidaPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.partida;
    }

}
