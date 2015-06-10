/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import Interfaz.Persistente;
import java.sql.*;
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

    public void agregar(Persistente p) {
        String sql = p.getInsertSql();
        this.ejecutarConsulta(sql);

    }

    public void modificar(Persistente p) {
        String sql = p.getUpdateSql();
        this.ejecutarConsulta(sql);
    }

    public void eliminar(Persistente p) {
        String sql = p.getDeleteSql();
        this.ejecutarConsulta(sql);
    }

    public void leerPersistente(Persistente p) {
        try {
            String sql = p.getSelectSql();
            ResultSet rs = this.obtenerResultSet(sql);
            while (rs.next()) {
                p.leer(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener jugador.\n" + e.getMessage());
        }
    }

    public ArrayList obtenerTodos(Persistente p) {
        ArrayList ret = new ArrayList();
        try {
            String sql = p.getSelectSql();
            System.out.println(sql);
            ResultSet rs = this.obtenerResultSet(sql);
            Persistente nuevo = null;
            while (rs.next()) {
//                if (nuevo != null) {
//                    ret.add(nuevo.getObjeto());
//                }
                nuevo.leer(rs);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener jugadores.\n" + e.getMessage());
        }
        return ret;
    }

}
