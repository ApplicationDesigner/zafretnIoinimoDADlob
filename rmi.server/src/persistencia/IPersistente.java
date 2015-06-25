/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miriarte
 */
public interface IPersistente {
    
    public String getInsertSql();

    public String getUpdateSql();

    public String getDeleteSql();

    public String getSelectSql();

    public void leer(ResultSet rs);

    public Object crearNuevo();

    
}
