/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;

/**
 *
 * @author MIriarte
 */
public class ConfiguracionPersistente implements IPersistente {

    @Override
    public String getInsertSql() {
        return "INSERT INTO configuracion (timeout) VALUES ("
                + Configuraciones.Constantes.getTimeOut() + ")";
    }

    @Override
    public String getUpdateSql() {
        return "UPDATE configuracion SET timeout=" + Configuraciones.Constantes.getTimeOut();
    }

    @Override
    public String getDeleteSql() {
        return "DELETE * FROM configuracion";
    }

    @Override
    public String getSelectSql() {
        return "SELECT * FROM configuracion";
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
