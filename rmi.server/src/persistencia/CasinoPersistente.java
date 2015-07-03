/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import InterfazCommon.ICasino;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MIriarte
 */
//public class CasinoPersistente implements IPersistente{
    public class CasinoPersistente implements IPersistente {
    
    private ICasino casino;

    public ICasino getCasino() {
        return casino;
    }

    public void setCasino(ICasino casino) {
        this.casino = casino;
    }

    @Override
    public String getInsertSql() {
        String ret = "";
        try {
            ret = "INSERT INTO casino (ganancias) VALUES ("
                    + this.casino.getGanancias() + ")";
        } catch (RemoteException ex) {
            Logger.getLogger(CasinoPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public String getUpdateSql() {
        String ret = "";
        try {
            ret = "UPDATE casino SET ganancias=" + this.casino.getGanancias();
        } catch (RemoteException ex) {
            Logger.getLogger(CasinoPersistente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    @Override
    public String getDeleteSql() {
        return "DELETE * FROM casino";
    }

    @Override
    public String getSelectSql() {
        return "SELECT * FROM casino";
    }

    @Override
    public void leer(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object crearNuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
