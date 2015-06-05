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
public interface Persistencia {

    public ArrayList<String> getInsertSql();

    public void setOid(int oid);

    public String getUpdateSql();

    public String getDeleteSql();

    public String getSelectSql();

    public int getOid();

    public void leer(ResultSet rs) throws SQLException;

    public Persistencia crearNuevo();

    public Object getObjeto();

    public void limpiar();
}
