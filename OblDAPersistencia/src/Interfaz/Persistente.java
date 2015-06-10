/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miriarte
 */
public interface Persistente {
    
    public String getInsertSql();

    public String getUpdateSql();

    public String getDeleteSql();

    public String getSelectSql();

    public int getOid();
    
    public void setOid(int oid);

    public void leer(ResultSet rs);

    public Persistente getNuevo();

    public Object getObjeto();
}
