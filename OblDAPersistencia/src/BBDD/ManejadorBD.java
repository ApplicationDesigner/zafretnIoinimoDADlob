/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Interfaz.Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author miriarte
 */
public class ManejadorBD {

    private Connection conexion;

    private static ManejadorBD instancia;

    public static ManejadorBD getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorBD();
        }
        return instancia;
    }

    public void conectar(String url) {
        try {
            this.conexion = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Error en la conexion: " + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            this.conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion: " + ex.getMessage());
        }
    }

    public void ejecutarConsulta(String sql) {
        try {
            Statement st = this.conexion.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta:\n " + sql + "\n" + ex.getMessage());
        }
    }

    public ResultSet obtenerResultSet(String sql) {
        ResultSet rs = null;
        try {
            Statement st = conexion.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar resulSet:\n" + sql + "\n" + e.getMessage());
        }
        return rs;
    }

    public int proximoOid() {
        int oid = -1;
        try {
            String sql = "SELECT valor FROM Parametros WHERE nombre='oid'";
            ResultSet rs = this.obtenerResultSet(sql);
            if (rs.next()) {
                oid = rs.getInt("valor");
            }
            rs.close();
            oid++;
            this.ejecutarConsulta("UPDATE Parametros set valor=" + oid + " WHERE nombre='oid'");
        } catch (SQLException e) {
            System.out.println("Error al obtener el proximo oid." + e.getMessage());
        }
        return oid;
    }

    public void agregar(Persistencia p) {
        int oid = this.proximoOid();
        p.setOid(oid);
        ArrayList<String> l = p.getInsertSql();
        for (String sql : l) {
            this.ejecutarConsulta(sql);
        }
    }

    public void modificar(Persistencia p) {
        String sql = p.getUpdateSql();
        this.ejecutarConsulta(sql);
    }

    public void eliminar(Persistencia p) {
        String sql = p.getDeleteSql();
        p.setOid(0);
        this.ejecutarConsulta(sql);
    }

    public void leerPersistente(Persistencia p) {
        try {
            String sql = p.getSelectSql();
            ResultSet rs = this.obtenerResultSet(sql);
            p.limpiar();
            while (rs.next()) {
                p.leer(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario.\n" + e.getMessage());
        }
    }

    public ArrayList obtenerTodos(Persistencia p) {
        ArrayList ret = new ArrayList();
        try {
            String sql = p.getSelectSql();
            System.out.println(sql);
            ResultSet rs = this.obtenerResultSet(sql);
            int oidAnt = -1;
            int oid;
            Persistencia nuevo = null;
            while (rs.next()) {
                oid = rs.getInt("oid");
                if (oid != oidAnt) {
                    oidAnt = oid;
                    if (nuevo != null) {
                        ret.add(nuevo.getObjeto());
                    }
                    nuevo = p.crearNuevo();
                    nuevo.limpiar();
                    nuevo.setOid(rs.getInt("oid"));
                }
                nuevo.leer(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios.\n" + e.getMessage());
        }
        return ret;
    }

}
